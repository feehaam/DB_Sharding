package playground.db.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import playground.entity.Region;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static playground.entity.Region.*;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "playground.repository",
        entityManagerFactoryRef = "multiEntityManager",
        transactionManagerRef = "multiTransactionManager")
@EntityScan("playground.entity")
public class DatabaseConfiguration {
    //JPA entities path
    private final String PACKAGE_SCAN = "playground.entity";

    // Prepare the data sources bean
    @Primary
    @Bean(name = "northernDataSource")
    @ConfigurationProperties("spring.datasource.data-source-1")
    public DataSource db1DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
    @Bean(name = "southernDataSource")
    @ConfigurationProperties("spring.datasource.data-source-2")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    //The multi datasource configuration
    @Bean(name = "multiRoutingDataSource")
    public DataSource multiRoutingDataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(NORTHERN, db1DataSource());
        targetDataSources.put(SOUTHERN, db1DataSource());
        targetDataSources.put(PACIFIC, db2DataSource());

        MultiRoutingDataSource multiRoutingDataSource = new MultiRoutingDataSource();
        multiRoutingDataSource.setDefaultTargetDataSource(db1DataSource());
        multiRoutingDataSource.setTargetDataSources(targetDataSources);
        return multiRoutingDataSource;
    }

    //add multi playground.data.entity configuration code
    @Bean(name = "multiEntityManager")
    public LocalContainerEntityManagerFactoryBean multiEntityManager() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(multiRoutingDataSource());
        entityManager.setPackagesToScan(PACKAGE_SCAN);
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setJpaProperties(getJpaProperties());
        return entityManager;
    }

    private Properties getJpaProperties() {
        Properties hibernateProperties;
        Object currentDataSource = ((MultiRoutingDataSource) multiRoutingDataSource()).determineCurrentLookupKey();
        hibernateProperties = switch (currentDataSource) {
            case NORTHERN, SOUTHERN -> postgresProperties();
            case null, default -> mysqlProperties();
        };
        return hibernateProperties;
    }

    @Bean(name = "multiTransactionManager")
    public PlatformTransactionManager multiTransactionManager() {
        Object currentDataSource = ((MultiRoutingDataSource) multiRoutingDataSource()).determineCurrentLookupKey();
        if (Region.PACIFIC.equals(currentDataSource)) {
            // Configure MongoTransactionManager or equivalent here for MongoDB.
        } else {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(multiEntityManager().getObject());
            return transactionManager;
        }
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(multiEntityManager().getObject());
        return transactionManager;
    }

    @Primary
    @Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean dbSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(multiRoutingDataSource());
        sessionFactoryBean.setPackagesToScan(PACKAGE_SCAN);
        sessionFactoryBean.setHibernateProperties(getJpaProperties());
        return sessionFactoryBean;
    }

    //add hibernate properties
    private Properties postgresProperties() {
        return generateHibernateProperty(true, true, false, true, "org.hibernate.dialect.PostgreSQLDialect");
    }

    private Properties mysqlProperties() {
        return generateHibernateProperty(true, true, false, true, "org.hibernate.dialect.MySQLDialect");
    }

    private Properties mongoProperties() {
        Properties properties = new Properties();
        // MongoDB doesn't use Hibernate, instead Spring Data MongoDB is used.
        // So, this would typically be empty or hold relevant MongoDB properties
        return properties;
    }

    private Properties generateHibernateProperty(boolean showSql, boolean formatSql, boolean generatorMapping,
                                                 boolean nonContextualCreation, String dialect){
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.format_sql", formatSql);
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.id.new_generator_mappings", generatorMapping);
        properties.put("hibernate.jdbc.lob.non_contextual_creation", nonContextualCreation);
        return properties;
    }
}

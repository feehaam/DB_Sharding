package playground.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PrimaryService {

    private final JdbcTemplate primaryJdbcTemplate;

    @Autowired
    public PrimaryService(@Qualifier("primaryJdbcTemplate") JdbcTemplate primaryJdbcTemplate) {
        this.primaryJdbcTemplate = primaryJdbcTemplate;
    }

    // Implement service methods using primaryJdbcTemplate
}


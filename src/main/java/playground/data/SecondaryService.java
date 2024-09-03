package playground.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SecondaryService {

    private final JdbcTemplate secondaryJdbcTemplate;

    @Autowired
    public SecondaryService(@Qualifier("secondaryJdbcTemplate") JdbcTemplate secondaryJdbcTemplate) {
        this.secondaryJdbcTemplate = secondaryJdbcTemplate;
    }

    // Implement service methods using secondaryJdbcTemplate
}

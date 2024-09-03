//package playground.data;
//
//@Service
//public class PrimaryService {
//
//    private final JdbcTemplate primaryJdbcTemplate;
//
//    @Autowired
//    public PrimaryService(@Qualifier("primaryJdbcTemplate") JdbcTemplate primaryJdbcTemplate) {
//        this.primaryJdbcTemplate = primaryJdbcTemplate;
//    }
//
//    @Transactional(transactionManager = "primaryTransactionManager")
//    public void performPrimaryDataOperation() {
//        // Perform database operations using primaryJdbcTemplate
//    }
//}
//
//@Service
//public class SecondaryService {
//
//    private final JdbcTemplate secondaryJdbcTemplate;
//
//    @Autowired
//    public SecondaryService(@Qualifier("secondaryJdbcTemplate") JdbcTemplate secondaryJdbcTemplate) {
//        this.secondaryJdbcTemplate = secondaryJdbcTemplate;
//    }
//
//    @Transactional(transactionManager = "secondaryTransactionManager")
//    public void performSecondaryDataOperation() {
//        // Perform database operations using secondaryJdbcTemplate
//    }
//}

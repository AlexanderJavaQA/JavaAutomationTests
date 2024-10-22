package springjdbc.oracle;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DatabaseConnectOracle {

    public static JdbcTemplate createDatabaseConnection(String dbUrl, String dbUsername, String dbPassword) {
        DataSource dataSource = new DriverManagerDataSource(dbUrl, dbUsername, dbPassword);
        return new JdbcTemplate(dataSource);
    }

    public static JdbcTemplate oracleDatabaseUat() {
        String dbUrl = "jdbc:oracle:thin:@//10.81.8.36:1521/u00pgu";
        String dbUsername = "pgu";
        String dbPassword = "pgu";

        return createDatabaseConnection(dbUrl, dbUsername, dbPassword);
    }

    public static JdbcTemplate oracleDatabaseDev2() {
        String dbUrl = "jdbc:oracle:thin:@//10.81.21.87:1521/u00pgu";
        String dbUsername = "pgu";
        String dbPassword = "pgu";

        return createDatabaseConnection(dbUrl, dbUsername, dbPassword);
    }
}



package springjdbc.postgres;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DatabaseConnectPostgres {

    public static JdbcTemplate createDatabaseConnection(String dbUrl, String dbUsername, String dbPassword) {
        DataSource dataSource = new DriverManagerDataSource(dbUrl, dbUsername, dbPassword);
        return new JdbcTemplate(dataSource);
    }

    public static JdbcTemplate smevaDatabaseUat() {
        String dbUrl = "jdbc:postgresql://10.65.242.130:3011/smeva";
        String dbUsername = "smeva";
        String dbPassword = "smeva";

        return createDatabaseConnection(dbUrl, dbUsername, dbPassword);
    }

    public static JdbcTemplate smevaDatabaseDev2() {
        String dbUrl = "jdbc:postgresql://10.65.242.126:3011/smeva";
        String dbUsername = "smeva";
        String dbPassword = "smeva";

        return createDatabaseConnection(dbUrl, dbUsername, dbPassword);
    }

    public static JdbcTemplate surveillanceDatabaseDev2() {
        String dbUrl = "jdbc:postgresql://10.65.242.126:3011/surveillance";
        String dbUsername = "surveillance";
        String dbPassword = "surveillance";

        return createDatabaseConnection(dbUrl, dbUsername, dbPassword);
    }

    public static JdbcTemplate surveillanceDatabaseUat() {
        String dbUrl = "jdbc:postgresql://10.65.242.126:3011/surveillance";
        String dbUsername = "surveillance";
        String dbPassword = "surveillance";

        return createDatabaseConnection(dbUrl, dbUsername, dbPassword);

    }

}


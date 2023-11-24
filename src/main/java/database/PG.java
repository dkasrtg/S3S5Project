package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class PG {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/s3s5";
    private static final String USERNAME = "s3s5";
    private static final String PASSWORD = "s3s5";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
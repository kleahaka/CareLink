package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    // Read connection info from system properties or environment variables for flexibility in tests
    private static final String DEFAULT_JDBC_URL = "jdbc:mysql://localhost:3307/carelink";
    private static final String DEFAULT_USER = "klea";
    private static final String DEFAULT_PASSWORD = "klea1234";

    private static Connection connection;
    public static Connection getConnection() throws SQLException{
        if(connection==null || connection.isClosed()){
            String url = System.getProperty("DB_URL", System.getenv().getOrDefault("DB_URL", DEFAULT_JDBC_URL));
            String user = System.getProperty("DB_USER", System.getenv().getOrDefault("DB_USER", DEFAULT_USER));
            String pass = System.getProperty("DB_PASSWORD", System.getenv().getOrDefault("DB_PASSWORD", DEFAULT_PASSWORD));
            connection = DriverManager.getConnection(url,user,pass);
        }
        return connection;
    }
}

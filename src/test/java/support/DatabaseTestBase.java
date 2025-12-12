package support;

import db.DatabaseManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DatabaseTestBase {

    private static boolean initialized = false;

    @BeforeAll
    public static void initDatabase() throws SQLException {
        // point DatabaseManager to H2 in-memory database
        System.setProperty("DB_URL", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;MODE=MySQL;DATABASE_TO_UPPER=false");
        System.setProperty("DB_USER", "sa");
        System.setProperty("DB_PASSWORD", "");

        if (!initialized) {
            try (Connection c = DatabaseManager.getConnection(); Statement s = c.createStatement()) {
                // create minimal schema used by DAOs (no FKs to keep simple)
                s.execute("CREATE TABLE IF NOT EXISTS roli (roliId INT AUTO_INCREMENT PRIMARY KEY, emri VARCHAR(255))");
                s.execute("CREATE TABLE IF NOT EXISTS statusi (statusiId INT AUTO_INCREMENT PRIMARY KEY, emri VARCHAR(255))");

                // avoid reserved name USER by using app_user
                s.execute("CREATE TABLE IF NOT EXISTS app_user (userid INT AUTO_INCREMENT PRIMARY KEY, emri VARCHAR(255), mbiemri VARCHAR(255), email VARCHAR(255), password VARCHAR(255), roliId INT, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");

                s.execute("CREATE TABLE IF NOT EXISTS familjar (familjarId INT AUTO_INCREMENT PRIMARY KEY, lidhja_familjare VARCHAR(255), kontakt VARCHAR(255), userid INT)");

                // match DAO table/column names
                s.execute("CREATE TABLE IF NOT EXISTS i_moshuar (imoshuarId INT AUTO_INCREMENT PRIMARY KEY, mosha INT, adresa VARCHAR(255), kontakt_emergjence VARCHAR(255), userid INT)");

                s.execute("CREATE TABLE IF NOT EXISTS vullnetar (vullnetarId INT AUTO_INCREMENT PRIMARY KEY, zoni_sherbimit VARCHAR(255), disponueshmeria VARCHAR(255), userid INT)");

                s.execute("CREATE TABLE IF NOT EXISTS kerkesandihme (kerkeseId INT AUTO_INCREMENT PRIMARY KEY, data_kerkeses DATE, lloji VARCHAR(255), pershkrimi VARCHAR(1024), data_mbarimi DATE, imoshuarId INT, vullnetarId INT, statusiId INT)");

                s.execute("CREATE TABLE IF NOT EXISTS raport (raportId INT AUTO_INCREMENT PRIMARY KEY, data_raportit DATE, rezultati VARCHAR(255), pershkrimi VARCHAR(1024), kerkeseId INT)");

                s.execute("CREATE TABLE IF NOT EXISTS familjar_moshuar (familjarId INT, imoshuarId INT, lidhja_f_m VARCHAR(255), PRIMARY KEY(familjarId, imoshuarId))");
            }
            initialized = true;
        }
    }

    @BeforeEach
    public void cleanBefore() throws SQLException {
        try (Connection c = DatabaseManager.getConnection(); Statement s = c.createStatement()) {
            s.execute("TRUNCATE TABLE raport");
            s.execute("TRUNCATE TABLE kerkesandihme");
            s.execute("TRUNCATE TABLE familjar_moshuar");
            s.execute("TRUNCATE TABLE familjar");
            s.execute("TRUNCATE TABLE i_moshuar");
            s.execute("TRUNCATE TABLE vullnetar");
            s.execute("TRUNCATE TABLE app_user");
        }
    }

    @AfterEach
    public void afterEach() throws SQLException {
        // keep DB open across tests; nothing to do here
    }
}

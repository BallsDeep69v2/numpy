package repository.setup;

import java.sql.*;

public class TestConnectionSupplier {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("""
                jdbc:h2:mem:test;\
                init=runscript from 'src/main/java/sql/createTablesH2.sql'\\;
                
                """);
    }
}

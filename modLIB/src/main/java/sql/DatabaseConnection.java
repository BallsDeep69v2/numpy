package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String database_url = "jdbc:sqlserver://DESKTOP-L6DDB08\\MODLIBSERVER:50017";
    private static final String username = ";user=test";
    private static final String password = ";password=test1234";
    private static final String properties = ";encrypt=true;trustServerCertificate=true";

    public static Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(database_url + username + password + properties);
    }
}

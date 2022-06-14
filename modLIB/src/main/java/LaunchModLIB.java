import app.ModLIBApp;
import javafx.application.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LaunchModLIB {
    public static void main(String[] args) {
        Application.launch(ModLIBApp.class, args);
    }
}
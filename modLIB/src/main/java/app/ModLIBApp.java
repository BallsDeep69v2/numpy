package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sql.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class ModLIBApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ModLIBStage.setSTAGE(stage);

        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/DefaultAnsicht.fxml")))));
        stage.setTitle("modLIB");
        stage.getIcons().add(new Image("/icons/book_blue.png"));
        stage.setOnCloseRequest(windowEvent -> {
            try {
                Connection connection = DatabaseConnection.DATABASE_CONNECTION;
                if (connection != null) {
                    connection.close();
                    System.out.println("closed Connection");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Platform.exit();
        });
        stage.show();
    }
}

package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DefaultAnsicht implements Initializable {

    @FXML
    private TableView<String> tbData;

    @FXML
    private TableColumn<String, Integer> ISBN;

    @FXML
    private TableColumn<String, String> title;

    @FXML
    private TableColumn<String, String> autor;

    @FXML
    private TableColumn<String, String> genre;

    @FXML
    private TableColumn<String, String> kurzb;

    @FXML
    private TableColumn<String, Integer> jahr;

    @FXML
    private TableColumn<String, Integer> pages;

    @FXML
    private Button btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Stage stage = new Stage();
        btnLogin.setOnAction(
                actionEvent -> {
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Login.fxml")))));
                        stage.centerOnScreen();
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}

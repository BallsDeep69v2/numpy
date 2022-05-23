package controller;

import app.ModLIBStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Login implements Initializable {


    @FXML
    private TextField usernametf;

    @FXML
    private TextField passwtf;

    @FXML
    private Button loginbtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginbtn.setOnAction(actionEvent -> {
            if (usernametf.getText().equals(passwtf.getText())) {
                try {
                    ModLIBStage.STAGE.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Home.fxml")))));
                    ModLIBStage.STAGE.centerOnScreen();
                    ((Stage)loginbtn.getScene().getWindow()).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

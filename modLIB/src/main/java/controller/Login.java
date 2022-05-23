package controller;

import app.modLIBStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
                    modLIBStage.STAGE.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Home.fxml")))));
                    modLIBStage.STAGE.centerOnScreen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

package controller;

import app.ModLIBStage;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Login implements Initializable {


    @FXML
    private Button cancelbtn;

    @FXML
    private Button loginbtn;

    @FXML
    private PasswordField passwordTf;

    @FXML
    private TextField usernametf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cancelbtn.setOnAction(actionEvent -> ((Stage) loginbtn.getScene().getWindow()).close());

        loginbtn.setOnAction(actionEvent -> {
            if (usernametf.getText().equals(passwordTf.getText())) {
                try {
                    ModLIBStage.STAGE.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Home.fxml")))));
                    ModLIBStage.STAGE.centerOnScreen();
                    ((Stage) loginbtn.getScene().getWindow()).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Benutzername bzw. Passwort falsch");
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/book_blue.png"));
                alert.show();
            }
        });

        usernametf.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().getCode() == KeyEvent.VK_ENTER) loginbtn.fire();
        });

        passwordTf.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().getCode() == KeyEvent.VK_ENTER) loginbtn.fire();
        });

        usernametf.styleProperty().bind(Bindings.when(usernametf.focusedProperty()).then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);").otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));
        passwordTf.styleProperty().bind(Bindings.when(passwordTf.focusedProperty()).then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);").otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));

    }

}

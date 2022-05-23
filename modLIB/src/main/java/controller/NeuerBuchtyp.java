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

public class NeuerBuchtyp implements Initializable {


    @FXML
    private Button backBtn;

    @FXML
    private TextField isbntf;

    @FXML
    private TextField autortf;

    @FXML
    private TextField titeltf;

    @FXML
    private TextField genretf;

    @FXML
    private TextField pagestf;

    @FXML
    private TextField kbtf;

    @FXML
    private TextField jahrtf;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = ModLIBStage.STAGE;
        backBtn.setOnAction(
                actionEvent -> {
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Home.fxml")))));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}

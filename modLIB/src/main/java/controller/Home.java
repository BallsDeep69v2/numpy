package controller;

import app.modLIBStage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Home implements Initializable {
    @FXML
    private Button btnAusleihenAnsicht;

    @FXML
    private Button btnExemplarAnsicht;

    @FXML
    private Button btnBuchtypenAnsicht;

    @FXML
    private Button btnNeuerBuchtyp;

    @FXML
    private Button btnNeuesExemplar;

    @FXML
    private Button btnNeueAusleihe;

    @FXML
    private Button btnPersonen;

    @FXML
    private Button btnPersonenAnsicht;

    @FXML
    private Button btnAnalyse;

    @FXML
    private Button btnLogout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Stage stage = modLIBStage.STAGE;

        btnAnalyse.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Statistiken.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}

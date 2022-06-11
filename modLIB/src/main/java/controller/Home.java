package controller;

import app.ModLIBStage;

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
    private Button btnAnalyse;

    @FXML
    private Button btnAusleihenAnsicht;

    @FXML
    private Button btnBuchtypenAnsicht;

    @FXML
    private Button btnExemplarAnsicht;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnNeueAusleihe;

    @FXML
    private Button btnNeuerBuchtyp;

    @FXML
    private Button btnNeuesExemplar;

    @FXML
    private Button btnPersonenAnsicht;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Stage stage = ModLIBStage.STAGE;

        btnAusleihenAnsicht.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/AusleihenAnsicht.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnExemplarAnsicht.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/BuchexemplarAnsicht.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnBuchtypenAnsicht.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/BuchtypenAnsicht.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnNeuerBuchtyp.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/NeuerBuchtype.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnNeuesExemplar.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/NeuesBuchexemplar.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnNeueAusleihe.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/NeueAusleihe.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnPersonenAnsicht.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/PersonenAnsicht.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnAnalyse.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Statistiken.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnLogout.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/DefaultAnsicht.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}

package controller;

import app.modLIBStage;
import javafx.event.ActionEvent;
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
    private Button btnDashboard;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btn_Timetable;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnClasses;

    @FXML
    void handleButtonClicks(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Stage stage = modLIBStage.STAGE;

        btnDashboard.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Dashboard.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnStudents.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Students.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btn_Timetable.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Dashboard.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnDashboard.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Dashboard.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}

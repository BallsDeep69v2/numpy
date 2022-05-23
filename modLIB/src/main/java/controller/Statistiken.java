package controller;

import app.modLIBStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Statistiken implements Initializable {


    @FXML
    private BarChart<Integer, Integer> bar1;

    @FXML
    private BarChart<Integer, Integer> bar2;

    @FXML
    private BarChart<Integer, Integer> bar3;

    @FXML
    private Button backBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Stage stage = modLIBStage.STAGE;
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

package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;

import java.net.URL;
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

    }
}

package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NeueAusleihe implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private ChoiceBox<String> personcb;

    @FXML
    private ChoiceBox<String> idcb;

    @FXML
    private Button scanbtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

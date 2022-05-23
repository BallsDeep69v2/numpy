package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NeuesBuchexemplar implements Initializable {


    @FXML
    private Button insertbtn;

    @FXML
    private ChoiceBox<String> typcb;

    @FXML
    private ChoiceBox<String> buechercb;

    @FXML
    private Button backBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

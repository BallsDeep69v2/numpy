package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class NeuePerson implements Initializable {


    @FXML
    private GridPane emailtf;

    @FXML
    private ChoiceBox<String> classcb;

    @FXML
    private TextField nametf;

    @FXML
    private TextField nachtf;

    @FXML
    private Button backBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

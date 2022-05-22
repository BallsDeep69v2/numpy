package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AusleihenAnsicht implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private Text search;

    @FXML
    private TextField searchword;

    @FXML
    private RadioButton open;

    @FXML
    private RadioButton closed;

    @FXML
    private TableView<?> tbData;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> title;

    @FXML
    private TableColumn<?, ?> status;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

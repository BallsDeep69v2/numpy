package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
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
    private TableView<String> tbData;

    @FXML
    private TableColumn<String, Date> date;

    @FXML
    private TableColumn<String, String> name;

    @FXML
    private TableColumn<String, String> title;

    @FXML
    private TableColumn<String, String> status;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

package controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class BuchexemplarAnsicht implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



        @FXML
        private Button backBtn;

        @FXML
        private Text search;

        @FXML
        private TextField searchwordtf;

        @FXML
        private TableView<String> tbData;

        @FXML
        private TableColumn<String, Integer> ISBN;

        @FXML
        private TableColumn<String, String> title;

        @FXML
        private TableColumn<String, String> autor;

        @FXML
        private TableColumn<String, String> genre;

        @FXML
        private TableColumn<String, String> kurzb;

        @FXML
        private TableColumn<String, Integer> jahr;

        @FXML
        private TableColumn<String, String> pages;


}

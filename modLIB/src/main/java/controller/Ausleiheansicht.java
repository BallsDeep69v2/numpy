package controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Ausleiheansicht {

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

}

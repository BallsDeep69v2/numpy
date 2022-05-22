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

public class PersonenAnsicht implements Initializable {



        @FXML
        private Text search;

        @FXML
        private TextField searchword;

        @FXML
        private TableView<?> tbData;

        @FXML
        private TableColumn<?, ?> klasse1;

        @FXML
        private TableColumn<?, ?> firstName;

        @FXML
        private TableColumn<?, ?> lastName;

        @FXML
        private TableColumn<?, ?> email;

        @FXML
        private TableColumn<?, ?> klasse;

    @FXML
    private Button backBtn;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }
}

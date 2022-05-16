package controller;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
public class PersonenAnsicht {


    public class DashboardController {

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

    }

}

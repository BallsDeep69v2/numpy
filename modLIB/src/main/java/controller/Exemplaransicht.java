package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
public class Exemplaransicht {



    public class DashboardController {

        @FXML
        private Button backBtn;

        @FXML
        private Text search;

        @FXML
        private TextField searchwordtf;

        @FXML
        private TableView<?> tbData;

        @FXML
        private TableColumn<?, ?> ISBN;

        @FXML
        private TableColumn<?, ?> title;

        @FXML
        private TableColumn<?, ?> autor;

        @FXML
        private TableColumn<?, ?> genre;

        @FXML
        private TableColumn<?, ?> kurzb;

        @FXML
        private TableColumn<?, ?> jahr;

        @FXML
        private TableColumn<?, ?> pages;

    }

}

package controller;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
public class Students {



    public class StudentsController {

        @FXML
        private TableView<?> tbData;

        @FXML
        private TableColumn<?, ?> studentId;

        @FXML
        private TableColumn<?, ?> firstName;

        @FXML
        private TableColumn<?, ?> lastName;

        @FXML
        private TextField search;

    }

}

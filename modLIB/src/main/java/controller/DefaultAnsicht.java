package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DefaultAnsicht {

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

    @FXML
    private Button btnLogin;

}

package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DefaultAnsicht {

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
    private TableColumn<String, Integer> pages;

    @FXML
    private Button btnLogin;

}

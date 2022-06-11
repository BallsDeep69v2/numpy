package controller;
import app.ModLIBStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BuchtypenAnsicht implements Initializable {


    @FXML
    private TableColumn<?, ?> ISBN;

    @FXML
    private TableColumn<?, ?> autor;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<?, ?> genre;

    @FXML
    private TableColumn<?, ?> jahr;

    @FXML
    private TableColumn<?, ?> kurzb;

    @FXML
    private TableColumn<?, ?> pages;

    @FXML
    private Text search;

    @FXML
    private TextField searchwordtf;

    @FXML
    private TableView<?> tbData;

    @FXML
    private TableColumn<?, ?> title;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = ModLIBStage.STAGE;
        backBtn.setOnAction(
                actionEvent -> {
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Home.fxml")))));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}

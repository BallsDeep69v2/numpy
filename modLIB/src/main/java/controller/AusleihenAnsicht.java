package controller;

import app.ModLIBStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Objects;
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

package controller;

import app.modLIBStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NeuePerson implements Initializable {


    @FXML
    private GridPane emailtf;

    @FXML
    private ChoiceBox<String> classcb;

    @FXML
    private TextField nametf;

    @FXML
    private TextField nachtf;

    @FXML
    private Button backBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = modLIBStage.STAGE;
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

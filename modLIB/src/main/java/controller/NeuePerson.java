package controller;

import app.ModLIBStage;
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
    private Button addbtn;

    @FXML
    private Button backBtn;

    @FXML
    private ChoiceBox<?> classcb;

    @FXML
    private GridPane emailtf;

    @FXML
    private TextField mailtf;

    @FXML
    private TextField nachtf;

    @FXML
    private TextField nametf;


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

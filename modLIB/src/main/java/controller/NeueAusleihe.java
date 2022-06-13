package controller;

import app.ModLIBStage;
import domain.Person;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NeueAusleihe implements Initializable {

    @FXML
    private Button addbtn;

    @FXML
    private Button backBtn;

    @FXML
    private ListView<Person> personlistview;

    @FXML
    private Button scanbtn;

    @FXML
    private TextField searchtf;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = ModLIBStage.STAGE;

        //initialize content
        searchtf.setFocusTraversable(false);

        //binding
        searchtf.styleProperty().bind(Bindings.when(searchtf.focusedProperty()).then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);").otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));

        //setOnAction for buttons
        backBtn.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Home.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}

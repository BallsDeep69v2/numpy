package controller;

import app.ModLIBStage;
import domain.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.JdbcSchuelerRepository;
import repository.SchuelerRepository;
import sql.TestConnectionSupplier;

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
    private ChoiceBox<String> classcb;

    @FXML
    private TextField classtf;

    @FXML
    private TextField mailtf;

    @FXML
    private TextField nachtf;

    @FXML
    private TextField nametf;


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = ModLIBStage.STAGE;
        backBtn.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/PersonenAnsicht.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        initializeAddButton(new JdbcSchuelerRepository(new TestConnectionSupplier().getConnectionWithTestData()));

        ObservableList<String> schoolClasses = FXCollections.observableArrayList();
        schoolClasses.addAll(Person.getAllSchoolClasses());

        classcb.setItems(schoolClasses);
        classcb.getSelectionModel().selectFirst();

        classtf.textProperty().bindBidirectional(classcb.valueProperty());
    }

    private void initializeAddButton(SchuelerRepository repository) {
        addbtn.setOnAction(actionEvent -> {
            try {
                Person toAdd = generatePersonFromTextFields();
                Person.PERSON_LIST.add(toAdd);
                repository.save(toAdd);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Person erfolgreich hinzugefuegt");
                alert.setTitle("Meldung");
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/book_blue.png"));
                alert.show();
                backBtn.fire();
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Sie müssen zuerst alle Felder ausfüllen, bevor Sie den Schüler hinzufügen können");
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/book_blue.png"));
                alert.show();
            }
        });
    }

    private Person generatePersonFromTextFields() {
        if (nametf.getText().isBlank() || nachtf.getText().isBlank() || mailtf.getText().isBlank() || classtf.getText().isBlank())
            throw new IllegalArgumentException();
        return new Person(nametf.getText(), nachtf.getText(), classtf.getText(), mailtf.getText());
    }
}

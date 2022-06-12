package controller;

import app.ModLIBStage;
import domain.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import repository.SchuelerRepository;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PersonenAnsicht implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<Person, String> email;

    @FXML
    private TableColumn<Person, String> firstName;

    @FXML
    private TableColumn<Person, String> klasse;

    @FXML
    private TableColumn<Person, String> lastName;

    @FXML
    private Text search;

    @FXML
    private TextField searchword;

    @FXML
    private TableView<Person> tbData;

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
        initializeTableView();
        loadData(null); //machen wir, wenn die Datenbank funktioniert
    }

    private void initializeTableView() {
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        klasse.setCellValueFactory(new PropertyValueFactory<>("klasse"));
        email.setCellValueFactory(new PropertyValueFactory<>("eMail"));
    }

    private void loadData(SchuelerRepository repository) {//lädt aktuell nur Testdaten
        tbData.getItems().add(new Person("Wilhem Arthur Ferdinand Emanuel", "Tröstler", "2BHIF", "wilhem.troestler@htlstp.at"));
    }
}

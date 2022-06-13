package controller;

import app.ModLIBStage;
import domain.Person;
import javafx.beans.binding.Bindings;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.JdbcSchuelerRepository;
import repository.SchuelerRepository;
import sql.TestConnectionSupplier;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

    @FXML
    private Button newPersonbtn;

    @FXML
    private Button csvImportbtn;


    @FXML
    private Button deleteAllBtn;

    SchuelerRepository repository;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        repository = new JdbcSchuelerRepository(new TestConnectionSupplier().getConnectionWithTestData());

        Stage stage = ModLIBStage.STAGE;
        backBtn.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Home.fxml")))));

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        initializeTableView();
        loadData(new JdbcSchuelerRepository(new TestConnectionSupplier().getConnectionWithTestData()));

        deleteAllBtn.setOnAction(actionEvent -> {
            repository.deleteAll();
            loadData(repository);
        });
        csvImportbtn.setOnAction(actionEvent -> {

            File file = chooseAFile(stage);
            if (file != null) {

                try {
                    repository.saveAll(Files.lines(
                                    Path.of(file.getPath()))
                            .skip(1)
                            .map(this::personFromString)
                            .collect(Collectors.toList()));
                    loadData(repository);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        searchword.styleProperty().bind(Bindings.when(searchword.focusedProperty()).then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);").otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));
        searchword.setFocusTraversable(false);
    }

    private void initializeTableView() {
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        klasse.setCellValueFactory(new PropertyValueFactory<>("klasse"));
        email.setCellValueFactory(new PropertyValueFactory<>("eMail"));
    }

    private void loadData(SchuelerRepository repository) {
        //lädt aktuell nur Testdaten
        tbData.getItems().clear();
        tbData.getItems().addAll(repository.findAll());
    }


    private File chooseAFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        return file;
    }

    private Person personFromString(String string) {
        String[] splitted = string.split(",");
        return new Person(splitted[0], splitted[1], splitted[4], " ");
    }
}

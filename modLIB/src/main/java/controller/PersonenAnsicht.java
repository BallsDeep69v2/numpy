package controller;

import app.ModLIBStage;
import domain.Person;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.JdbcPersonRepository;
import repository.PersonRepository;
import sql.DatabaseConnection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class PersonenAnsicht implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private Button deleteAllBtn;

    @FXML
    private TableColumn<Person, String> email;

    @FXML
    private TableColumn<Person, String> firstName;

    @FXML
    private TableColumn<Person, String> klasse;

    @FXML
    private TableColumn<Person, String> lastName;

    @FXML
    private TextField searchword;

    @FXML
    private TableView<Person> tbData;

    @FXML
    private Button newPersonbtn;

    @FXML
    private Button csvImportbtn;


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = ModLIBStage.STAGE;
        JdbcPersonRepository personRepository = new JdbcPersonRepository(DatabaseConnection.DATABASE_CONNECTION);

        //initialize content
        initializeTableView();
        loadData(personRepository);
        searchword.setFocusTraversable(false);

        //binding
        searchword.styleProperty().bind(Bindings.when(searchword.focusedProperty()).then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);").otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));

        //setOnAction for buttons
        backBtn.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Home.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        newPersonbtn.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/NeuePerson.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        csvImportbtn.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Wählen Sie eine Datei aus");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"), new FileChooser.ExtensionFilter("Text", "*.txt"));
            File file = fileChooser.showOpenDialog(ModLIBStage.STAGE);

            try {
                if (file != null && file.exists()) Person.createPersonsFromCSVFile(file.toPath(), personRepository);
                else throw new IOException();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Datei könnte nicht gefunden/geöffnet werden!");
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/book_blue.png"));
                alert.show();
            } finally {
                loadData(personRepository);
            }
        });

        deleteAllBtn.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wollen Sie wirklich alle Personen löschen?\nDiese Handlung kann nicht rückgängig gemacht werden!");
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/book_blue.png"));
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                personRepository.deleteAll();
                loadData(personRepository);
            }//else canceled
        });
    }

    private void initializeTableView() {
        firstName.setCellValueFactory(personStringCellDataFeatures -> personStringCellDataFeatures.getValue().firstNameProperty());
        lastName.setCellValueFactory(personStringCellDataFeatures -> personStringCellDataFeatures.getValue().lastNameProperty());
        klasse.setCellValueFactory(personStringCellDataFeatures -> personStringCellDataFeatures.getValue().schoolClassProperty());
        email.setCellValueFactory(personStringCellDataFeatures -> personStringCellDataFeatures.getValue().eMailProperty());
    }

    private void loadData(PersonRepository repository) {
        tbData.getItems().clear();
        tbData.refresh();
        tbData.getItems().addAll(repository.findAll());
    }
}


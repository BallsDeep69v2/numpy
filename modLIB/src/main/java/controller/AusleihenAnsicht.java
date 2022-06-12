package controller;

import app.ModLIBStage;
import domain.Ausleihe;
import domain.BuchExemplar;
import domain.BuchTyp;
import domain.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import repository.AusleiheRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.util.Objects;
import java.util.ResourceBundle;

public class AusleihenAnsicht implements Initializable {

    @FXML
    private RadioButton adllradiobtn;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<Ausleihe, String> date;

    @FXML
    private TableColumn<Ausleihe, String> name;

    @FXML
    private RadioButton openradiotbn;

    @FXML
    private Text search;

    @FXML
    private TextField searchword;

    @FXML
    private TableColumn<Ausleihe, String> status;

    @FXML
    private TableView<Ausleihe> tbData;

    @FXML
    private TableColumn<Ausleihe, String> title;

    private ToggleGroup toggleGroup;

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
        loadData(null);
    }

    private void initializeTableView() {
        name.setCellValueFactory(ausleiheStringCellDataFeatures -> new SimpleStringProperty(ausleiheStringCellDataFeatures.getValue().getAusleiher().getFullName()));
        date.setCellValueFactory(ausleiheStringCellDataFeatures -> new SimpleStringProperty(
                        new SimpleDateFormat("dd.MM.yyyy")
                                .format(Date.valueOf(ausleiheStringCellDataFeatures.getValue().getBeginDate()))
                )
        );
        status.setCellValueFactory(ausleiheStringCellDataFeatures -> new SimpleStringProperty(ausleiheStringCellDataFeatures.getValue().isStatus() ? "Ja" : "Nein"));
        title.setCellValueFactory(ausleiheStringCellDataFeatures -> new SimpleStringProperty(ausleiheStringCellDataFeatures.getValue().getExemplar().getTyp().getTitle()));
    }

    private void loadData(AusleiheRepository repository) {
        Person person = new Person();
        BuchTyp buchtyp = new BuchTyp("isbn", "Troestlers Meisterwerke fuer Jung und Alt", "Wilhem Arthur Ferdinand Emanuel Tröstler");
        BuchExemplar exemplar = new BuchExemplar(5, buchtyp);
        Ausleihe ausleihe = new Ausleihe(exemplar, person, LocalDate.now());
        tbData.getItems().add(ausleihe);
    }

    private void initializeToggleGroup() {
        adllradiobtn.setToggleGroup(toggleGroup);
        openradiotbn.setToggleGroup(toggleGroup);
    }
}

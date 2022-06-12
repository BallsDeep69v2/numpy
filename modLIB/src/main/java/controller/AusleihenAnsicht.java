package controller;

import app.ModLIBStage;
import domain.Ausleihe;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.AusleiheRepository;
import repository.JdbcAusleiheRepository;
import sql.TestConnectionSupplier;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;

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

    @FXML
    private ToggleGroup toggleGroup = new ToggleGroup();

    @SneakyThrows
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
        initializeToggleGroup();

        AusleiheRepository ausleiheRepository = new JdbcAusleiheRepository(new TestConnectionSupplier().getConnectionWithTestData());
        loadData(ausleiheRepository);
        adllradiobtn.setOnAction(actionEvent -> loadData(ausleiheRepository));
        openradiotbn.setOnAction(actionEvent -> loadData(ausleiheRepository));
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
        tbData.getItems().clear();
        if (adllradiobtn.isSelected())
            tbData.getItems().addAll(repository.findAll());
        else {
            tbData.getItems().addAll(repository.findAllPending());
        }
    }

    private void initializeToggleGroup() {
        adllradiobtn.setToggleGroup(toggleGroup);
        openradiotbn.setToggleGroup(toggleGroup);
        adllradiobtn.setSelected(true);
    }
}

package controller;

import app.ModLIBStage;
import domain.BuchExemplar;
import domain.BuchTyp;
import javafx.beans.property.SimpleStringProperty;
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
import repository.BuchTypRepository;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BuchexemplarAnsicht implements Initializable {

    @FXML
    private TableColumn<BuchExemplar, String> ISBN;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<BuchExemplar, String> id;

    @FXML
    private Text search;

    @FXML
    private TextField searchwordtf;

    @FXML
    private TableView<BuchExemplar> tbData;

    @FXML
    private TableColumn<BuchExemplar, String> title;

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
        initializeTableViews();
        loadData(null);
    }

    private void initializeTableViews() {
        ISBN.setCellValueFactory(buchExemplarStringCellDataFeatures -> new SimpleStringProperty(buchExemplarStringCellDataFeatures.getValue().getTyp().getIsbn()));
        id.setCellValueFactory(buchExemplarStringCellDataFeatures -> new SimpleStringProperty(buchExemplarStringCellDataFeatures.getValue().getId().toString()));
        title.setCellValueFactory(buchExemplarStringCellDataFeatures -> new SimpleStringProperty(buchExemplarStringCellDataFeatures.getValue().getTyp().getTitle()));
    }

    private void loadData(BuchTypRepository repository) {
        var buchtyp = new BuchTyp("TroestlerInc",
                "Die neuen Leiden des jungen Csambals",
                "Wilhem Arthur Ferdinand Emanuel Tröstler",
                "Eine herzzerreißende Epik über das Schicksal des HTL-Entrepreneurs Christian Csambal",
                "Erfolg",
                2018, 3000);
        tbData.getItems().add(new BuchExemplar(1, buchtyp));
        tbData.getItems().add(new BuchExemplar(2, buchtyp));
    }

}

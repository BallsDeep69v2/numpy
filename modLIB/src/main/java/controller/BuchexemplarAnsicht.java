package controller;

import app.ModLIBStage;
import domain.BuchExemplar;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.BuchExemplarRepository;
import repository.JdbcBuchExemplarRepository;
import sql.DatabaseConnection;

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
    private TextField searchwordtf;

    @FXML
    private TableView<BuchExemplar> tbData;

    @FXML
    private TableColumn<BuchExemplar, String> title;


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = ModLIBStage.STAGE;

        //initialize content
        initializeTableViews();
        loadData(new JdbcBuchExemplarRepository(DatabaseConnection.DATABASE_CONNECTION));
        searchwordtf.setFocusTraversable(false);

        //binding
        searchwordtf.styleProperty().bind(Bindings.when(searchwordtf.focusedProperty()).then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);").otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));

        //setOnAction for buttons
        backBtn.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Home.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initializeTableViews() {
        ISBN.setCellValueFactory(buchExemplarStringCellDataFeatures -> new SimpleStringProperty(buchExemplarStringCellDataFeatures.getValue().getTyp().getIsbn().toString()));
        id.setCellValueFactory(buchExemplarStringCellDataFeatures -> new SimpleStringProperty(buchExemplarStringCellDataFeatures.getValue().getId().toString()));
        title.setCellValueFactory(buchExemplarStringCellDataFeatures -> new SimpleStringProperty(buchExemplarStringCellDataFeatures.getValue().getTyp().getTitle()));
    }

    private void loadData(BuchExemplarRepository repository) {
        tbData.getItems().addAll(repository.findAll());
    }
}

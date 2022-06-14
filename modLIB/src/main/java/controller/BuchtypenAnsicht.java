package controller;

import app.ModLIBStage;
import domain.BuchTyp;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.BuchTypRepository;
import repository.JdbcBuchTypRepository;
import sql.DatabaseConnection;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BuchtypenAnsicht implements Initializable {

    @FXML
    private TableColumn<BuchTyp, String> ISBN;

    @FXML
    private TableColumn<BuchTyp, String> autor;

    @FXML
    private Button backBtn;

    @FXML
    private TableColumn<BuchTyp, String> genre;

    @FXML
    private TableColumn<BuchTyp, String> jahr;

    @FXML
    private TableColumn<BuchTyp, String> kurzb;

    @FXML
    private TableColumn<BuchTyp, String> pages;

    @FXML
    private TextField searchwordtf;

    @FXML
    private TableView<BuchTyp> tbData;

    @FXML
    private TableColumn<BuchTyp, String> title;


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = ModLIBStage.STAGE;

        //initialize content
        initializeTableViews();
        loadData(new JdbcBuchTypRepository(DatabaseConnection.DATABASE_CONNECTION));
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
        ISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        autor.setCellValueFactory(new PropertyValueFactory<>("author"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        kurzb.setCellValueFactory(new PropertyValueFactory<>("description"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        //Falls Seitenzahl 0 ist, wird es als Leerstring angezeigt, um Verwirrung zu vermeiden
        pages.setCellValueFactory(buchTypIntegerCellDataFeatures -> new SimpleStringProperty(buchTypIntegerCellDataFeatures.getValue().getNumberOfPages() == 0 ? "" : buchTypIntegerCellDataFeatures.getValue().getNumberOfPages().toString()));
        //Falls das Jahr 0 ist, wird es als Leerstring angezeigt, um Verwirrung zu vermeiden
        jahr.setCellValueFactory(buchTypIntegerCellDataFeatures -> new SimpleStringProperty(buchTypIntegerCellDataFeatures.getValue().getYear() == 0 ? "" : buchTypIntegerCellDataFeatures.getValue().getYear().toString()));
    }

    private void loadData(BuchTypRepository repository) {
        tbData.getItems().addAll(repository.findAll());
    }
}

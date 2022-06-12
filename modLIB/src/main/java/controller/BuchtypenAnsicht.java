package controller;

import app.ModLIBStage;
import domain.BuchTyp;
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
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.BuchTypRepository;
import repository.JdbcBuchTypRepository;
import sql.TestConnectionSupplier;


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
    private TableColumn<BuchTyp, Integer> jahr;

    @FXML
    private TableColumn<BuchTyp, String> kurzb;

    @FXML
    private TableColumn<BuchTyp, Integer> pages;

    @FXML
    private Text search;

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
        backBtn.setOnAction(
                actionEvent -> {
                    try {
                        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Home.fxml")))));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        initializeTableViews();
        loadData(new JdbcBuchTypRepository(new TestConnectionSupplier().getConnectionWithTestData()));

        searchwordtf.styleProperty().bind(Bindings.when(searchwordtf.focusedProperty()).then("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);").otherwise("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);"));
        searchwordtf.setFocusTraversable(false);
    }

    private void initializeTableViews() {
        ISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        autor.setCellValueFactory(new PropertyValueFactory<>("author"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        kurzb.setCellValueFactory(new PropertyValueFactory<>("description"));
        genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        pages.setCellValueFactory(new PropertyValueFactory<>("numberOfPages"));
        jahr.setCellValueFactory(new PropertyValueFactory<>("year"));
    }

    private void loadData(BuchTypRepository repository){
        tbData.getItems().addAll(repository.findAll());
    }
}

package controller;

import app.ModLIBStage;
import domain.BuchTyp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import lombok.SneakyThrows;
import repository.BuchExemplarRepository;
import repository.BuchTypRepository;
import repository.JdbcBuchExemplarRepository;
import repository.JdbcBuchTypRepository;
import sql.TestConnectionSupplier;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NeuesBuchexemplar implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private ChoiceBox<Integer> buechercb;

    @FXML
    private Button insertbtn;

    @FXML
    private ChoiceBox<BuchTyp> typcb;


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = ModLIBStage.STAGE;

        //initialize content
        fillBuchtypChoiceBox(new JdbcBuchTypRepository(new TestConnectionSupplier().getConnectionWithTestData()));
        buechercb.getItems().addAll(1, 2, 3, 4, 5);
        buechercb.setValue(1);

        //setOnAction for buttons
        initializeInsertBtn(new JdbcBuchExemplarRepository(new TestConnectionSupplier().getConnectionWithTestData()));
        backBtn.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Home.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void fillBuchtypChoiceBox(BuchTypRepository repository) {
        typcb.getItems().addAll(repository.findAll());
        typcb.setConverter(new StringConverter<>() {

            @Override
            public String toString(BuchTyp buchTyp) {
                if (buchTyp == null) return "";
                return buchTyp.getIsbn() + " , " + buchTyp.getTitle() + " , " + buchTyp.getAuthor();
            }

            @Override
            public BuchTyp fromString(String s) {
                throw new UnsupportedOperationException();
            }
        });
    }

    private void initializeInsertBtn(BuchExemplarRepository repository) {
        insertbtn.setOnAction(actionEvent -> {
            if (typcb.getSelectionModel().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Wählen Sie einen Buchtyp aus");
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/book_blue.png"));
                alert.show();
            } else {
                repository.saveMultiple(typcb.getValue(), buechercb.getValue());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, buechercb.getValue() + " Buchexemplare erfolgreich hinzugefuegt");
                alert.setTitle("Meldung");
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/book_blue.png"));
                alert.show();
                backBtn.fire();
            }
        });
    }
}

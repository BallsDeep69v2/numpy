package controller;

import app.ModLIBStage;
import domain.BuchTyp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.BuchTypRepository;
import repository.JdbcBuchTypRepository;
import sql.DatabaseConnection;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NeuerBuchtyp implements Initializable {

    @FXML
    private Button addbtn;

    @FXML
    private TextField autortf;

    @FXML
    private Button backBtn;

    @FXML
    private TextField genretf;

    @FXML
    private TextField isbntf;

    @FXML
    private TextField jahrtf;

    @FXML
    private TextField descriptionTF;

    @FXML
    private TextField pagestf;

    @FXML
    private Button scanbtn;

    @FXML
    private TextField titeltf;


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = ModLIBStage.STAGE;

        //setOnAction for buttons
        initializeAddButton(new JdbcBuchTypRepository(DatabaseConnection.DATABASE_CONNECTION));
        backBtn.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Home.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initializeAddButton(BuchTypRepository repository) {
        addbtn.setOnAction(actionEvent -> {
            try {
                BuchTyp buchTyp = generateBuchTypFromTextFields();
                repository.save(buchTyp);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Buchtyp erfolgreich hinzugefuegt");
                alert.setTitle("Meldung");
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/book_blue.png"));
                alert.show();
                backBtn.fire();
            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Geben Sie bitte valide Zahlen bei Jahr und Seitenzahl ein oder lassen sie diese Felder frei");
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/book_blue.png"));
                alert.show();
                jahrtf.clear();
                pagestf.clear();
            }
        });
    }

    private BuchTyp generateBuchTypFromTextFields() {
        if (isbntf.getText().isBlank() || titeltf.getText().isBlank() || autortf.getText().isBlank())
            throw new IllegalArgumentException();
        BuchTyp buchTyp = new BuchTyp(Integer.parseInt(isbntf.getText()), titeltf.getText(), autortf.getText());
        if (!genretf.getText().isBlank()) buchTyp.setGenre(genretf.getText());
        if (!descriptionTF.getText().isBlank()) buchTyp.setDescription(descriptionTF.getText());
        if (!pagestf.getText().isBlank()) buchTyp.setNumberOfPages(Integer.parseInt(pagestf.getText()));
        if (!jahrtf.getText().isBlank()) buchTyp.setYear(Integer.parseInt(jahrtf.getText()));
        return buchTyp;
    }
}

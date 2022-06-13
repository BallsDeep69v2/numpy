package controller;

import app.ModLIBStage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import qr.printing.PrintPDF;
import resources.ResourcesPath;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class QRCodes implements Initializable {

    @FXML
    private Button backBtn;

    @FXML
    private Button delQRsbtn;

    @FXML
    private Button openQRsbtn;

    @FXML
    private Button printQRsbtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = ModLIBStage.STAGE;

        //setOnAction for buttons
        backBtn.setOnAction(actionEvent -> {
            try {
                stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Home.fxml")))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        printQRsbtn.setOnAction(actionEvent -> {
            PrintPDF.printGeneratedPDFFileByDefaultPrinter();
        });

        delQRsbtn.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wollen Sie wirklich alle QRCodes löschen?\nVergewissern Sie sich, dass Sie diese bereits ausgedruckt haben.");
            ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/book_blue.png"));
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.out.println("deleting");
                try {
                    Files.walk(ResourcesPath.getRessourcePath()).filter(path -> path.toFile().exists() && path.toFile().getName().endsWith(".png")).map(Path::toFile).forEach(File::delete);
                } catch (IOException e) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Eine oder mehrere Dateien konnten nicht gelöscht werden.");
                    ((Stage) errorAlert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/icons/book_blue.png"));
                    errorAlert.show();
                }
            }//else canceled
        });

        openQRsbtn.setOnAction(actionEvent -> {
            try {
                Desktop.getDesktop().open(ResourcesPath.getRessourcePath().toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

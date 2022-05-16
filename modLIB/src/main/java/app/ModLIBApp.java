package app;

import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.util.Objects;

public class ModLIBApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SvgImageLoaderFactory.install();
        modLIBStage.setSTAGE(stage);
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/pages/Home.fxml")))));
        stage.setTitle("moin");
        stage.show();
    }
}

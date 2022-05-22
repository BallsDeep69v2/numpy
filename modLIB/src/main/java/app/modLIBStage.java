package app;

import javafx.stage.Stage;

public class modLIBStage {

    public static Stage STAGE;

    public static void setSTAGE(Stage stage) {
        if (STAGE == null) STAGE = stage;
    }
}

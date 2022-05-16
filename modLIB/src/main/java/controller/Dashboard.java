package controller;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
public class Dashboard {

    public class DashboardController {

        @FXML
        private TableView<?> tbData;

        @FXML
        private TableColumn<?, ?> studentId;

        @FXML
        private TableColumn<?, ?> firstName;

        @FXML
        private TableColumn<?, ?> lastName;

        @FXML
        private PieChart pieChart;

    }

}

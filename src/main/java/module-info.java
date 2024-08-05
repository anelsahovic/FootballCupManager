module com.example.footballcupmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.footballcupmanager to javafx.fxml;
    exports com.example.footballcupmanager;
}
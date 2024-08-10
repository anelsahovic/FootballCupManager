module com.example.footballcupmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports footballcupmanager;
    opens footballcupmanager to javafx.fxml;
}
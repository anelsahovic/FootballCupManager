module com.example.footballcupmanager {
    requires javafx.controls;
    requires javafx.fxml;



    exports footballcupmanager;
    opens footballcupmanager to javafx.fxml;
}
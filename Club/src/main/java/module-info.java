module com.example.implementation {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.implementation to javafx.fxml;
    exports com.example.implementation;
}
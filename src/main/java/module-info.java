module com.example.sacms {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.j;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.example.sacms to javafx.fxml;
    opens com.example.demo to javafx.fxml;
    exports com.example.sacms;
    exports com.example.demo;
}
package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.*;

public class Check_Teacher {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ood";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @FXML
    public void handleCheckTea_record(String T_nameToCheck, String teacherIdToCheck, String T_clubNameToCheck) {
        // Validate input fields
        if (T_nameToCheck.isEmpty() || teacherIdToCheck.isEmpty() || T_clubNameToCheck.isEmpty()) {
            showErroralert();
            return;
        }

        // Establish a database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Create a prepared statement for the SELECT query
            String selectQuery = "SELECT * FROM teacher_attendance WHERE Name = ? AND Teacher_ID = ? AND Club_Name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                // Set values for the prepared statement
                preparedStatement.setString(1, T_nameToCheck);
                preparedStatement.setString(2, teacherIdToCheck);
                preparedStatement.setString(3, T_clubNameToCheck);


                // Execute the SELECT query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Check if a record exists
                if (resultSet.next()) {
                    // Display a message indicating that the record exists
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Record Found");
                    alert.setHeaderText(null);
                    alert.setContentText("Record already exists in the database." +
                            "You can UPDATE and SUBMIT the records.");
                    alert.showAndWait();
                } else {
                    // Display a message indicating that the record does not exist
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Record Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("Record does not exist in the database. NEW TEACHER ENTRY !!");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            // Display an error label if a database error occurs
            showErrorAlert();
        }
    }
    private void showErrorAlert () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("An error occurred while saving the record to the database.");
        alert.showAndWait();
    }
    private void showErroralert () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter data in all fields.");
        alert.showAndWait();
    }
}

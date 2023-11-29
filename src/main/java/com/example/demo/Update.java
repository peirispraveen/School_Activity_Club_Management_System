package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ood";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private final validations validator = new Att_Validate();

    @FXML
    public void handleUpdaterecord(String status_U, String markFName_U, String markLName_U, String markStuId_U, String markStuClubName_U, String S_selectedEvent_U) {
        // Validate inputs using the validation interface
        validator.stuIdCheck(markStuId_U);
        validator.clubnameCheck(markStuClubName_U);
        // Validate input fields
        if (markFName_U.isEmpty() || markLName_U.isEmpty() || markStuId_U.isEmpty() || markStuClubName_U.isEmpty() || S_selectedEvent_U.isEmpty() || status_U.isEmpty()) {
            showErroralert();
            return;
        }
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Create a prepared statement for the UPDATE query
            String updateQuery = "UPDATE student_attendance SET Status = ? WHERE First_Name = ? AND Last_Name = ? AND Student_ID = ? AND Club_Name = ? AND Event_Name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                // Set values for the prepared statement
                preparedStatement.setString(1, status_U);
                preparedStatement.setString(2, markFName_U);
                preparedStatement.setString(3, markLName_U);
                preparedStatement.setString(4, markStuId_U);
                preparedStatement.setString(5, markStuClubName_U);
                preparedStatement.setString(6, S_selectedEvent_U);

                // Execute the UPDATE query
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if a record was updated
                Alert alert;
                if (rowsAffected > 0) {
                    // Display a success alert
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Record Updated");
                    alert.setHeaderText(null);
                    alert.setContentText("The record was updated successfully.");
                    alert.showAndWait();
                } else {
                    // Display an alert if no matching record was found
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Record Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("No matching record found for update.");
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            // Display an error alert if a database error occurs
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

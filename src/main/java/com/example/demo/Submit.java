package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Submit {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ood";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Event handler for the SUBMIT button
    @FXML
    public void handleSubmitrecord(String Name, String markStudentId, String markClubName, String selectedEvent, String status) {
        // Validate input fields
        if (Name.isEmpty() || markStudentId.isEmpty() || markClubName.isEmpty() || selectedEvent.isEmpty() || status.isEmpty()) {
            showErroralert();
            return;
        }
        // Establish a database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Create a prepared statement for the INSERT query
            String insertQuery = "INSERT INTO student_attendance(Name,Student_ID,Club_Name,Event_Name,Status) " +
                    "VALUES (?, ?, ?, ?, ?)";
            //System.out.println("done");
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                // Set values for the prepared statement
                preparedStatement.setString(1, Name);
                preparedStatement.setString(2, markStudentId);
                preparedStatement.setString(3, markClubName);
                preparedStatement.setString(4, selectedEvent);
                preparedStatement.setString(5, status);

                // Execute the INSERT query
                preparedStatement.executeUpdate();
            }

            // Display a success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Record Saved");
            alert.setHeaderText(null);
            alert.setContentText("The record was added successfully.");
            alert.showAndWait();

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


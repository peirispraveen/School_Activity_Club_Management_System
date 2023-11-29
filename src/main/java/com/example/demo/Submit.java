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

    private final validations validator = new Att_Validate();

    // Event handler for the SUBMIT button
    @FXML
    public void handleSubmitrecord(String firstNameToCheck, String lastNameToCheck, String studentIdToCheck, String clubNameToCheck, String selectedEvent, String status) {
        // Validate inputs using the validation interface
        validator.stuIdCheck(studentIdToCheck);
        validator.clubnameCheck(clubNameToCheck);
        // Validate input fields
        if (firstNameToCheck.isEmpty() || lastNameToCheck.isEmpty() || studentIdToCheck.isEmpty() || clubNameToCheck.isEmpty() || selectedEvent.isEmpty() || status.isEmpty()) {
            showErroralert();
            return;
        }
        // Establish a database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Create a prepared statement for the INSERT query
            String insertQuery = "INSERT INTO student_attendance(First_Name,Last_Name,Student_ID,Club_Name,Event_Name,Status) " + "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                // Set values for the prepared statement
                preparedStatement.setString(1, firstNameToCheck);
                preparedStatement.setString(2, lastNameToCheck);
                preparedStatement.setString(3, studentIdToCheck);
                preparedStatement.setString(4, clubNameToCheck);
                preparedStatement.setString(5, selectedEvent);
                preparedStatement.setString(6, status);

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


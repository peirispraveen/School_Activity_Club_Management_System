package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.sql.*;
import java.util.Optional;

public class Check {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ood";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    public static boolean found ;

    private final validations validator = new Att_Validate();

    public void checkRecord(String firstNameToCheck, String lastNameToCheck, String studentIdToCheck, String clubNameToCheck) {
        // Validate inputs using the validation interface
        validator.stuIdCheck(studentIdToCheck);
        validator.clubnameCheck(clubNameToCheck);
        // Validate input fields
        if (firstNameToCheck.isEmpty() || lastNameToCheck.isEmpty() || studentIdToCheck.isEmpty() || clubNameToCheck.isEmpty() ) {
            showErroralert();
            return;
        }
        // Establish a database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Create a prepared statement for the SELECT query
            String selectQuery = "SELECT * FROM student_table WHERE First_Name = ? AND Last_Name = ? AND Student_ID = ? AND Club_Name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                // Set values for the prepared statement
                preparedStatement.setString(1, firstNameToCheck);
                preparedStatement.setString(2, lastNameToCheck);
                preparedStatement.setString(3, studentIdToCheck);
                preparedStatement.setString(4, clubNameToCheck);

                // Execute the SELECT query
                ResultSet resultSet = preparedStatement.executeQuery();

                // Check if a record exists
                boolean recordFound = resultSet.next();

                // Check if a record exists
                if (recordFound) {
                    // Display a message indicating that the record exists
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Record Found");
                    alert.setHeaderText(null);
                    alert.setContentText("Record already exists in the database. You can UPDATE and SUBMIT the records.");
                    alert.showAndWait();
                    found = true;

                } else {
                    // Display a message indicating that the record does not exist
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Record Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("Record does not exist in the database.Try again !!");
                    alert.showAndWait();

                }
            }
        } catch (SQLException e) {
            // Display an error alert if a database error occurs
            showErrorAlert();
        }
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("An error occurred while saving the record to the database_11.");
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

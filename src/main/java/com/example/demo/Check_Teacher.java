package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.*;

public class Check_Teacher {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ood";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    public static boolean found ;
    private final validations validator = new Att_Validate();

    @FXML
    public void handleCheckTea_record(String T_FnameToCheck, String T_LnameToCheck, String teacherIdToCheck, String T_clubNameToCheck) {
        // Validate inputs using the validation interface
        validator.advidCheck(teacherIdToCheck);
        validator.clubnameCheck(T_clubNameToCheck);
        // Validate input fields
        if (T_FnameToCheck.isEmpty() || T_LnameToCheck.isEmpty() || teacherIdToCheck.isEmpty() || T_clubNameToCheck.isEmpty()) {
            showErroralert();
            return;
        }
        // Establish a database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Create a prepared statement for the SELECT query
            String selectQuery = "SELECT * FROM teacher_table WHERE First_Name = ? AND Last_Name = ? AND Teacher_ID = ? AND Club_Name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                // Set values for the prepared statement
                preparedStatement.setString(1, T_FnameToCheck);
                preparedStatement.setString(2, T_LnameToCheck);
                preparedStatement.setString(3, teacherIdToCheck);
                preparedStatement.setString(4, T_clubNameToCheck);


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
                    alert.setContentText("Record already exists in the database." + "You can UPDATE and SUBMIT the records.");
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
            // Display an error label if a database error occurs
            showErrorAlert();
        }
    }
    // Method to show an error alert
    private void showErrorAlert () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("An error occurred while saving the record to the database.");
        alert.showAndWait();
    }
    // Method to show an error alert
    private void showErroralert () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter data in all fields.");
        alert.showAndWait();
    }
}

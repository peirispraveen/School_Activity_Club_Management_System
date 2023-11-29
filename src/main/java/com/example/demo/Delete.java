package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ood";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private final validations validator = new Att_Validate();

    @FXML
    public void deleteRecord(String deleteFName, String deleteLName, String deleteStudentId, String deleteNameofClub, String deletetable) {
        // Validate inputs using the validation interface
        validator.stuIdCheck(deleteStudentId);
        validator.clubnameCheck(deleteNameofClub);
        // Validate input fields
        if (deleteFName.isEmpty() || deleteLName.isEmpty() || deleteStudentId.isEmpty() || deleteNameofClub.isEmpty() || deletetable.isEmpty()) {
            showErroralert();
            return;
        }
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String deleteQuery = "DELETE FROM " + deletetable + " WHERE First_Name = ? AND Last_Name = ? AND Student_ID = ? AND Club_Name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, deleteFName);
                preparedStatement.setString(2, deleteLName);
                preparedStatement.setString(3, deleteStudentId);
                preparedStatement.setString(4, deleteNameofClub);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Display a success alert
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Record Deleted");
                    alert.setHeaderText(null);
                    alert.setContentText("The record was deleted successfully.");
                    alert.showAndWait();
                } else {
                    // Display an alert if no matching record was found
                    showErrorAlert("No matching record found for delete.");
                }
            }
        } catch (SQLException e) {
            // Display an error alert if a database error occurs
            showErrorAlert("An error occurred while deleting the record from the database.");
        }
    }

    private void showErrorAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
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

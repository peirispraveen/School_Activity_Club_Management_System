package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;

import java.sql.*;

// Class for handling teacher attendance chart
public class Chart_Teacher {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ood";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Method to handle generating teacher attendance chart
    @FXML
    public void handleTea_Chart(String selectedEvent, ObservableList<XYChart.Data<String, Number>> absentData, ObservableList<XYChart.Data<String, Number>> presentData) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // SQL query to retrieve teacher attendance data for the selected event
            String query = "SELECT Event_Name, Status, COUNT(*) AS Count FROM teacher_attendance WHERE Event_Name = ? GROUP BY Status";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Set the event parameter in the prepared statement
                preparedStatement.setString(1, selectedEvent);

                // Execute the query and process the result set
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        // Retrieve status and count from the result set
                        String status = resultSet.getString("Status");
                        int count = resultSet.getInt("Count");

                        // Add data to the corresponding lists based on status
                        if ("Absent".equals(status)) {
                            absentData.add(new XYChart.Data<>(status, count));
                        } else if ("Present".equals(status)) {
                            presentData.add(new XYChart.Data<>(status, count));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // Display an error alert if a database error occurs
            showErrorAlert();
        }
    }

    // Method to display an error alert
    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("An error occurred while implementing the chart.");
        alert.showAndWait();
    }
}


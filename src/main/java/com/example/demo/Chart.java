package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;

import java.sql.*;

public class Chart {
    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ood";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Event handler for handling student chart data
    @FXML
    public void handleStu_Chart(String selectedEvent, ObservableList<XYChart.Data<String, Number>> absentData, ObservableList<XYChart.Data<String, Number>> presentData) {
        // Establish a database connection
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // SQL query to get attendance data for the selected event
            String query = "SELECT Event_Name, Status, COUNT(*) AS Count FROM student_attendance WHERE Event_Name = ? GROUP BY Status";

            // Create a prepared statement for the SQL query
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                // Set the parameter in the prepared statement
                preparedStatement.setString(1, selectedEvent);
                // Execute the query and get the result set
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Iterate over the result set to populate data for the chart
                    while (resultSet.next()) {
                        String status = resultSet.getString("Status");
                        int count = resultSet.getInt("Count");

                        // Add data to the appropriate ObservableList based on status
                        if ("Absent".equals(status)) {
                            absentData.add(new XYChart.Data<>(status, count));
                        } else if ("Present".equals(status)) {
                            presentData.add(new XYChart.Data<>(status, count));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // Display an error alert if a chart error occurs
            showErrorAlert();
        }
    }
    // Method to show an error alert
    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("An error occurred while implementing the chart.");
        alert.showAndWait();
    }
}

package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;

import java.sql.*;

public class Chart_Teacher {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ood";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @FXML
    public void handleTea_Chart(String selectedEvent, ObservableList<XYChart.Data<String, Number>> absentData, ObservableList<XYChart.Data<String, Number>> presentData) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT Event_Name, Status, COUNT(*) AS Count FROM teacher_attendance WHERE Event_Name = ? GROUP BY Status";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, selectedEvent);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String status = resultSet.getString("Status");
                        int count = resultSet.getInt("Count");

                        if ("Absent".equals(status)) {
                            absentData.add(new XYChart.Data<>(status, count));
                        } else if ("Present".equals(status)) {
                            presentData.add(new XYChart.Data<>(status, count));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // Display an error label if a chart error occurs
            showErrorAlert();
        }
    }
    private void showErrorAlert () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("An error occurred while implementing tha chart.");
        alert.showAndWait();
    }
}

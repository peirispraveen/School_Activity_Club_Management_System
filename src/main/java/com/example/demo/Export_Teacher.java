package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.Optional;

public class Export_Teacher {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ood";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @FXML
    public static void exportCSV_tea(String tableName) {
        // Get the desired file name from the user
        TextInputDialog dialog = new TextInputDialog("exported_data");
        dialog.setTitle("CSV Export");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the file name:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String fileName = result.get() + ".csv"; // Add the .csv extension

            // Choose file location to save
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName(fileName);
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    String query = "SELECT * FROM teacher_attendance"; // Replace with your actual table name
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                         ResultSet resultSet = preparedStatement.executeQuery();
                         FileWriter csvWriter = new FileWriter(file)) {

                        ResultSetMetaData metaData = resultSet.getMetaData();
                        int columnCount = metaData.getColumnCount();

                        // Write CSV header
                        for (int col = 1; col <= columnCount; col++) {
                            csvWriter.append(metaData.getColumnName(col));
                            if (col < columnCount) {
                                csvWriter.append(",");
                            }
                        }
                        csvWriter.append("\n");

                        // Write data rows
                        while (resultSet.next()) {
                            for (int col = 1; col <= columnCount; col++) {
                                csvWriter.append(resultSet.getString(col));
                                if (col < columnCount) {
                                    csvWriter.append(",");
                                }
                            }
                            csvWriter.append("\n");
                        }

                        // Display a success alert
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("CSV Export");
                        alert.setHeaderText(null);
                        alert.setContentText("CSV file saved successfully!");
                        alert.showAndWait();

                    }
                } catch (Exception e) {
                    // Display an error alert
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("An error occurred while saving the CSV file.");
                    alert.showAndWait();
                }
            }
        }
    }
}

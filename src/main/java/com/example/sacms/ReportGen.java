package com.example.sacms;

import java.sql.*;
import java.io.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReportGen {
    private static final String url = "jdbc:mysql://localhost:3306/sacms";
    private static final String username = "root";
    private static final String password = "";
    @FXML
    private AnchorPane excelAnchor;
    @FXML
    protected static Label excelLabel;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // The details of the enrolled students are generated into an excel sheet
    public static boolean excelGenerateStudents(String path) throws SQLException {
        try (Connection newCon = getConnection()) {
            String query = "SELECT * FROM student";
            try (PreparedStatement stmt = newCon.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                // Create a new workbook
                XSSFWorkbook workbook = new XSSFWorkbook();

                // Create a sheet in the workbook
                XSSFSheet sheet = workbook.createSheet("Student Data");

                Font boldFont = workbook.createFont();
                boldFont.setBold(true);

                // Create a cell style with the bold font
                CellStyle boldStyle = workbook.createCellStyle();
                boldStyle.setFont(boldFont);  // the column header is bolded

                // Create header row
                XSSFRow headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Student ID");
                headerRow.createCell(1).setCellValue("First Name");
                headerRow.createCell(2).setCellValue("Last Name");
                headerRow.createCell(3).setCellValue("Email");
                headerRow.createCell(4).setCellValue("Date of Birth");

                for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                    headerRow.getCell(i).setCellStyle(boldStyle);
                }

                int rowNum = 1;
                while (rs.next()) {
                    // Iterate over the result set and add student data to the sheet
                    XSSFRow row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(rs.getString("student_id"));
                    row.createCell(1).setCellValue(rs.getString("first_name"));
                    row.createCell(2).setCellValue(rs.getString("last_name"));
                    row.createCell(3).setCellValue(rs.getString("email"));
                    DateOfBirth studentDateOfBirth = parseDateOfBirth(rs.getString("dateOfBirth"));
                    row.createCell(4).setCellValue(studentDateOfBirth.toString());
                }

                // Save the excel sheet in a specific directory
                try (FileOutputStream fileOut = new FileOutputStream(path + File.separator + "students_registered.xlsx")) {
                    workbook.write(fileOut);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // same process as above
    public static boolean excelGenerateAdvisors(String path) throws SQLException {
        try (Connection newCon = getConnection()) {
            String query = "SELECT * FROM advisor";
            try (PreparedStatement stmt = newCon.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();

                // Create a new workbook
                XSSFWorkbook workbook = new XSSFWorkbook();

                // Create a sheet in the workbook
                XSSFSheet sheet = workbook.createSheet("Advisor Data");

                Font boldFont = workbook.createFont();
                boldFont.setBold(true);

                // Create a cell style with the bold font
                CellStyle boldStyle = workbook.createCellStyle();
                boldStyle.setFont(boldFont);

                // Create header row
                XSSFRow headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Advisor ID");
                headerRow.createCell(1).setCellValue("First Name");
                headerRow.createCell(2).setCellValue("Last Name");
                headerRow.createCell(3).setCellValue("Email");
                headerRow.createCell(4).setCellValue("Date of Birth");

                for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                    headerRow.getCell(i).setCellStyle(boldStyle);
                }

                int rowNum = 1;
                while (rs.next()) {
                    // Iterate over the result set and add advisor data to the sheet
                    XSSFRow row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(rs.getString("advisor_id"));
                    row.createCell(1).setCellValue(rs.getString("first_name"));
                    row.createCell(2).setCellValue(rs.getString("last_name"));
                    row.createCell(3).setCellValue(rs.getString("email"));
                    DateOfBirth studentDateOfBirth = parseDateOfBirth(rs.getString("dateOfBirth"));
                    row.createCell(4).setCellValue(studentDateOfBirth.toString());
                }

                try (FileOutputStream fileOut = new FileOutputStream(path + File.separator + "advisors_registered.xlsx")) {
                    workbook.write(fileOut);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

            } catch (SQLException e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static DateOfBirth parseDateOfBirth(String dateString) {  // processing date of birth
        String[] dateParts = dateString.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        return new DateOfBirth(day, month, year);
    }

    @FXML
    private void backButton() throws IOException{
        Stage prevStage = (Stage) excelAnchor.getScene().getWindow();
        prevStage.close();
    }

    protected static void setLabel() {
        excelLabel.setText("Export Error Encountered");
    }



}

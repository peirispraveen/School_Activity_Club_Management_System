package com.example.sacms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.*;

import java.io.IOException;
import java.util.List;

public class ViewStudents {
    @FXML
    Button backButton;
    @FXML
    private TableColumn<Student, String> studentIdColumn;
    @FXML
    private TableColumn<Student, String> fnameColumn;
    @FXML
    private TableColumn<Student, String> lnameColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, String> dobColumn;

    @FXML
    private TableView<Student> studentsTableView;
    @FXML
    private AnchorPane studentViewAnchor;

    @FXML
    private void initialize() {
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("studentFirstName"));
        lnameColumn.setCellValueFactory(new PropertyValueFactory<>("studentLastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        loadDataIntoTableView();
    }

    private void loadDataIntoTableView() {
        try {
            List<Student> studentData = DBConnect.fetchStudentData();

            studentsTableView.getItems().clear();

            studentsTableView.getItems().addAll(studentData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void backButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("view-members.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        mainStage.setTitle("Members Enrolled");
        mainStage.setScene(scene);
        mainStage.show();
        Stage prevStage = (Stage) backButton.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void downloadStudentExcel() throws IOException {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Download Path");

        Stage stage = (Stage) studentViewAnchor.getScene().getWindow();

        File selectedDirectory = directoryChooser.showDialog(stage.getOwner());
        if (selectedDirectory != null) {
            String path = selectedDirectory.getAbsolutePath();
            try {
                if (ReportGen.excelGenerateStudents(path)){
                    FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("excel-download.fxml"));
                    Stage newStage = new Stage();
                    Scene newScene = new Scene(fxmlLoader.load(), 350, 180);
                    newStage.setTitle("Excel Export");
                    newStage.setScene(newScene);
                    newStage.show();
                }
            }catch (SQLException e) {
                e.printStackTrace();
                FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("excel-download.fxml"));
                Stage newStage = new Stage();
                Scene newScene = new Scene(fxmlLoader.load(), 350, 180);
                newStage.setTitle("Excel Export");
                newStage.setScene(newScene);
                newStage.show();
                ReportGen.setLabel();

            }

        }
    }
}

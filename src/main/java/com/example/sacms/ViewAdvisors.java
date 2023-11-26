package com.example.sacms;

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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class ViewAdvisors {

    @FXML
    private AnchorPane advisorViewAnchor;
    @FXML
    private Button backButton;
    @FXML
    private TableColumn<Advisor, String> adfnameColumn;
    @FXML
    private TableColumn<Advisor, String> adlnameColumn;
    @FXML
    private TableColumn<Advisor, String> ademailColumn;
    @FXML
    private TableColumn<Advisor, String> addobColumn;
    @FXML
    private TableColumn<Advisor, String> advisorIdColumn;
    @FXML
    private TableView<Advisor> advisorTableView;


    @FXML
    private void initialize() {
        advisorIdColumn.setCellValueFactory(new PropertyValueFactory<>("advisorId"));
        adfnameColumn.setCellValueFactory(new PropertyValueFactory<>("advisorFirstName"));
        adlnameColumn.setCellValueFactory(new PropertyValueFactory<>("advisorLastName"));
        ademailColumn.setCellValueFactory(new PropertyValueFactory<>("advisorEmail"));
        addobColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        loadDataIntoTableView();
    }

    private void loadDataIntoTableView() {
        try {
            List<Advisor> advisorData = DBConnect.fetchAdvisorData();

            advisorTableView.getItems().clear();

            advisorTableView.getItems().addAll(advisorData);
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
    private void downloadAdvisorExcel() throws IOException {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Download Path");

        Stage stage = (Stage) advisorViewAnchor.getScene().getWindow();

        File selectedDirectory = directoryChooser.showDialog(stage.getOwner());
        if (selectedDirectory != null) {
            String path = selectedDirectory.getAbsolutePath();
            try {
                if (ReportGen.excelGenerateAdvisors(path)){
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

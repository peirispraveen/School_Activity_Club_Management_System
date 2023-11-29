package com.example.sacms;

import com.example.implementation.ClubApplication;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClubController {


    @FXML
    private AnchorPane muaadhAnchor;

    public void createClub(ActionEvent actionEvent) throws IOException {
        Stage currentStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage createStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.implementation.ClubApplication.class.getResource("Create Club.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        createStage.setTitle("Create Club");
        createStage.setScene(scene);
        createStage.setResizable(false);
        createStage.show();
    }

    public void updateClub(ActionEvent actionEvent) throws IOException {
        Stage currentStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage updateStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.implementation.ClubApplication.class.getResource("Update Profile1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        updateStage.setTitle("Update Club");
        updateStage.setScene(scene);
        updateStage.setResizable(false);
        updateStage.show();
    }

    public void deleteClub(ActionEvent actionEvent) throws IOException {
        Stage currentStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage deleteStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.implementation.ClubApplication.class.getResource("Delete Club.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        deleteStage.setTitle("Delete Club");
        deleteStage.setScene(scene);
        deleteStage.setResizable(false);
        deleteStage.show();
    }



    public void generateReports(ActionEvent actionEvent) throws IOException {
        Stage currentStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage generateReportsStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.implementation.ClubApplication.class.getResource("Generate Report.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        generateReportsStage.setTitle("Generated Reports");
        generateReportsStage.setScene(scene);
        generateReportsStage.setResizable(false);
        generateReportsStage.show();
    }


    public void viewClub(ActionEvent actionEvent) throws IOException {
        Stage currentStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage viewStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.implementation.ClubApplication.class.getResource("View Club.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        viewStage.setTitle("View Club");
        viewStage.setScene(scene);
        viewStage.setResizable(false);
        viewStage.show();
    }

    public void manageMembers(ActionEvent actionEvent) throws IOException {
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage updateMembers = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ClubApplication.class.getResource("Update Members1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        updateMembers.setTitle("Update Members");
        updateMembers.setScene(scene);
        updateMembers.setResizable(false);
        updateMembers.show();
    }

    @FXML
    private void backButton() throws IOException {
        FXMLLoader userRegLoader = new FXMLLoader(UserRegApplication.class.getResource("advisor-login-page.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(userRegLoader.load(), 950, 600);
        stage.setTitle("Advisor Login");
        stage.setScene(scene);
        stage.show();

        Stage prevStage = (Stage) muaadhAnchor.getScene().getWindow();
        prevStage.close();
    }
}

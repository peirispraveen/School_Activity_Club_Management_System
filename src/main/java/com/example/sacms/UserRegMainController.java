package com.example.sacms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.Objects;

public class UserRegMainController {

    @FXML
    private AnchorPane consoleAnchor;
    @FXML
    private AnchorPane viewMembersAnchor;
    @FXML
    private TextField adminLogin;
    @FXML
    private TextField adminLoginPass;
    @FXML
    private Label adminLogLabel;
    @FXML
    private Label adminPassLabel;
    @FXML
    private AnchorPane adminLogAnchor;
    @FXML
    private Label adminLabel;
    @FXML
    private Text sacmslabel;



    @FXML
    private void studentRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("student-login-page.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        newStage.setTitle("Student Login");
        newStage.setScene(scene);
        newStage.show();
        Stage prevStage = (Stage) consoleAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void advisorRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("advisor-login-page.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        newStage.setTitle("Advisor Login");
        newStage.setScene(scene);
        newStage.show();
        Stage prevStage = (Stage) consoleAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void viewStudents() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("show-students.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        newStage.setTitle("Students Enrolled");
        newStage.setScene(scene);
        newStage.show();
        Stage prevStage = (Stage) viewMembersAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void viewAdvisors() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("show-advisors.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        newStage.setTitle("Advisors Enrolled");
        newStage.setScene(scene);
        newStage.show();
        Stage prevStage = (Stage) viewMembersAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void adminPage() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("view-login.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        newStage.setTitle("Administrator Login");
        newStage.setScene(scene);
        newStage.show();

        Stage prevStage = (Stage) consoleAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void onAdminLogButtonClicked() throws IOException{
        if (Objects.equals(adminLogin.getText(), "") || Objects.equals(adminLoginPass.getText(),"")) {
            adminLabel.setText("Enter Admin Credentials");
            adminLogLabel.setText("*required");
            adminPassLabel.setText("*required");
        } else {
            if (!Objects.equals(adminLogin.getText(), "admin") && !Objects.equals(adminLoginPass.getText(),"admin")) {
                adminLabel.setText("Password does not match the username");
            }else {
                FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("view-members.fxml"));
                Stage newStage = new Stage();
                Scene newScene = new Scene(fxmlLoader.load(), 950, 600);
                newStage.setTitle("View Members");
                newStage.setScene(newScene);
                newStage.show();

                Stage prevStage = (Stage) adminLogAnchor.getScene().getWindow();
                prevStage.close();
            }
        }
    }

    @FXML
    private void adminLogBackButton() throws IOException {
        FXMLLoader userRegLoader = new FXMLLoader(UserRegApplication.class.getResource("UserReg.fxml"));
        Scene scene = new Scene(userRegLoader.load(), 950, 600);
        Stage stage = new Stage();
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();

        Stage prevStage = (Stage) adminLogAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void viewMembersBackButton() throws IOException{
        FXMLLoader userRegLoader = new FXMLLoader(UserRegApplication.class.getResource("UserReg.fxml"));
        Scene scene = new Scene(userRegLoader.load(), 950, 600);
        Stage stage = new Stage();
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();

        Stage prevStage = (Stage) viewMembersAnchor.getScene().getWindow();
        prevStage.close();
    }

    public void onClickViewEventButton(ActionEvent e) throws Exception
    {
        EventController.onClickViewEventButton(e);
    }
}

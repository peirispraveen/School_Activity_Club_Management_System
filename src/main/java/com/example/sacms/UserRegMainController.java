package com.example.sacms;

import com.example.demo.HelloController;
import com.example.implementation.ClubApplication;
import com.example.implementation.Storage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.sql.SQLException;
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
    private AnchorPane studentOptionAnchor;
    @FXML
    private AnchorPane advisorOptionAnchor;


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
                newStage.setTitle("Admin");
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

    @FXML
    private void trackingPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("hello-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Style.css")).toExternalForm());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        Stage prevStage = (Stage) viewMembersAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void backButtonC() throws IOException {
        FXMLLoader userRegLoader = new FXMLLoader(UserRegApplication.class.getResource("student-login-page.fxml"));
        Scene scene = new Scene(userRegLoader.load(), 950, 600);
        Stage stage = new Stage();
        stage.setTitle("Student Login");
        stage.setScene(scene);
        stage.show();

        Stage prevStage = (Stage) studentOptionAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void backButtonZ() throws IOException {
        FXMLLoader userRegLoader = new FXMLLoader(UserRegApplication.class.getResource("advisor-login-page.fxml"));
        Scene scene = new Scene(userRegLoader.load(), 950, 600);
        Stage stage = new Stage();
        stage.setTitle("Advisor Login");
        stage.setScene(scene);
        stage.show();

        Stage prevStage = (Stage) advisorOptionAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void viewStudentEventsButton() throws IOException{
        FXMLLoader userRegLoader = new FXMLLoader(UserRegApplication.class.getResource("student-event-view.fxml"));
        Scene scene = new Scene(userRegLoader.load(), 950, 600);
        Stage stage = new Stage();
        stage.setTitle("Student Events");
        stage.setScene(scene);
        stage.show();

        Stage prevStage = (Stage) studentOptionAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void joinClubButton() throws IOException{
        FXMLLoader userRegLoader = new FXMLLoader(UserRegApplication.class.getResource("join-club.fxml"));
        Scene scene = new Scene(userRegLoader.load(), 950, 600);
        Stage stage = new Stage();
        stage.setTitle("Join Club");
        stage.setScene(scene);
        stage.show();

        Stage prevStage = (Stage) studentOptionAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void viewAdvisorEventsButton() throws IOException{
        FXMLLoader userRegLoader = new FXMLLoader(UserRegApplication.class.getResource("advisor-event-view.fxml"));
        Scene scene = new Scene(userRegLoader.load(), 950, 600);
        Stage stage = new Stage();
        stage.setTitle("Ongoing Events Events");
        stage.setScene(scene);
        stage.show();

        Stage prevStage = (Stage) advisorOptionAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void clubEditPageButton() throws IOException, SQLException {
        Stage stage = new Stage();
        Storage.allAvailables();
        FXMLLoader fxmlLoader = new FXMLLoader(ClubApplication.class.getResource("Club.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        stage.setTitle("Club");
        stage.setScene(scene);
        stage.show();

        Stage prevStage = (Stage) advisorOptionAnchor.getScene().getWindow();
        prevStage.close();
    }
}

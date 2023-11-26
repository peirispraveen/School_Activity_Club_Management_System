package com.example.sacms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.sql.SQLOutput;
import java.util.List;

import static com.example.sacms.AddMember.studentList;
import static com.example.sacms.AddMember.advisorList;

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
    private void studentRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("student-reg.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        newStage.setTitle("Student Sign In");
        newStage.setScene(scene);
        newStage.show();
        Stage prevStage = (Stage) consoleAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void advisorRegister() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("advisor-reg.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        newStage.setTitle("Advisor Sign In");
        newStage.setScene(scene);
        newStage.show();
        Stage prevStage = (Stage) consoleAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    protected void loadStudentsFromFile() throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("StudentDetails.ser"))) {
            studentList = (List<Student>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: StudentDetails.ser");
        } catch (EOFException e) {
            System.out.println("End of file reached unexpectedly. The file might be empty.");
        } catch (Exception e) {
            System.out.println("Error reading from StudentDetails.ser");
            e.printStackTrace();
        }
    }

    @FXML
    protected void loadAdvisorsFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("AdvisorDetails.ser"))) {
            advisorList = (List<Advisor>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: AdvisorDetails.ser");
        } catch (EOFException e) {
            System.out.println("End of file reached unexpectedly. The file might be empty.");
        } catch (Exception e) {
            System.out.println("Error reading from AdvisorDetails.ser");
            e.printStackTrace();
        }
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
    private void adminPage(MouseEvent mouseEvent) {
    }
}

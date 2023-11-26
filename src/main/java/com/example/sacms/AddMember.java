package com.example.sacms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;

public class AddMember implements Member {

    @FXML
    private Button backButton;
    @FXML
    private AnchorPane studentAnchor;
    @FXML
    private TextField studentIdBox;
    @FXML
    private TextField studentPassBox1;
    @FXML
    private ImageView studentLogImage;
    @FXML
    private Button studentRegButton;
    @FXML
    private TextField studentPassBox2;
    @FXML
    private Label studentRegLabel;
    @FXML
    private TextField studentFnameBox;
    @FXML
    private TextField studentLnameBox;
    @FXML
    private TextField studentEmailBox;
    @FXML
    private AnchorPane advisorAnchor;
    @FXML
    private TextField advisorIdBox;
    @FXML
    private TextField advisorPassBox1;
    @FXML
    private ImageView advisorLogImage;
    @FXML
    private Button advisorRegButton;
    @FXML
    private TextField advisorPassBox2;
    @FXML
    private Label advisorRegLabel;
    @FXML
    private TextField advisorFnameBox;
    @FXML
    private TextField advisorLnameBox;
    @FXML
    private TextField advisorEmailBox;
    private String studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
    private String studentPassword;
    private String studentPassTemp;
    private String advisorId;
    private String advisorFirstName;
    private String advisorLastName;
    private String advisorEmail;
    private String advisorPassword;
    private String PassTemp1;
    private String PassTemp2;

    protected static List<Student> studentList = new ArrayList<>();
    protected static List<Advisor> advisorList = new ArrayList<>();
    @FXML
    private DatePicker dateOfBirthBox;
    private int date;
    private int month;
    private int year;
    @FXML
    private Label fNameLabel;
    @FXML
    private Label lNameLabel;
    @FXML
    private Label stIdLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label doBLabel;
    @FXML
    private Label passLabel;
    @FXML
    private Label passLabel2;
    @FXML
    private Label adfNameLabel;
    @FXML
    private Label adlNameLabel;
    @FXML
    private Label adIdLabel;
    @FXML
    private Label ademailLabel;
    @FXML
    private Label addoBLabel;
    @FXML
    private Label adpassLabel;
    @FXML
    private Label adpassLabel2;
    @FXML
    private DatePicker adDateOfBirthBox;
    @FXML
    private CheckBox passCheckBox;
    @FXML
    private PasswordField passField;

    public AddMember() {
    }

    @FXML
    protected static void backButton(Button backButton) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("UserReg.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        mainStage.setTitle("Registration");
        mainStage.setScene(scene);
        mainStage.show();
        Stage prevStage = (Stage) backButton.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void onStudentRegButtonClicked() throws IOException{
        clearStudentLabels();
        if (Objects.equals(studentIdBox.getText(), "") || Objects.equals(studentFnameBox.getText(), "") ||
        Objects.equals(studentLnameBox.getText(), "") || Objects.equals(studentEmailBox.getText(), "") ||
        Objects.equals(studentPassBox1.getText(), "") || Objects.equals(studentPassBox2.getText(), "")){
            studentRegLabel.setText("Please fill the above credentials");
        }
        else {
            System.out.println("test");

            try {
                if (!Objects.equals(studentIdBox.getText(), "")){
                    studentId = studentIdBox.getText();
                }else{
//                    studentRegLabel.setText("Enter your Student ID");
                    stIdLabel.setText("*required");
                    return;
                }
            }catch (InputMismatchException | NumberFormatException | NullPointerException e1){
                studentRegLabel.setText("Invalid Student ID");
                stIdLabel.setText("invalid");
                return;
            }

            try {
                if (!Objects.equals(studentFnameBox.getText(), "")){
                    if(Regex.namePatternMatches(studentFnameBox.getText())){
                        studentFirstName = studentFnameBox.getText();
                    }else{
                        studentRegLabel.setText("Name cannot contain special characters/numbers");
                        fNameLabel.setText("*invalid");
                        return;
                    }

                }else{
                    studentRegLabel.setText("Enter your First Name");
                    fNameLabel.setText("*required");
                    return;
                }
            }catch (NullPointerException e2){
                studentRegLabel.setText("Invalid First Name");
                return;
            }

            try {
                if (!Objects.equals(studentLnameBox.getText(), "")){
                    if (Regex.namePatternMatches(studentLnameBox.getText())){
                        studentLastName = studentLnameBox.getText();
                    }else{
                        studentRegLabel.setText("Name cannot contain special characters/numbers");
                        lNameLabel.setText("*invalid");
                        return;
                    }

                }else{
                    studentRegLabel.setText("Enter your Last Name");
                    lNameLabel.setText("*required");
                    return;
                }
            }catch (NullPointerException e3){
                studentRegLabel.setText("Invalid Last Name");
                return;
            }

            try {
                if (!Objects.equals(studentEmailBox.getText(), "")){
                    try {
                        if(Regex.emailPatternMatches(studentEmailBox.getText())){
                            System.out.println("Valid email");
                            studentEmail = studentEmailBox.getText();
                        }else{
                            System.out.println("Invalid email");
                            studentRegLabel.setText("Invalid Email");
                            emailLabel.setText("*invalid");
                            return;
                        }
                    }catch (Exception s){
                        System.out.println(s.getMessage());
                    }
                }else{
                    studentRegLabel.setText("Enter your Email");
                    emailLabel.setText("*required");
                    return;
                }
            }catch (NullPointerException e4){
                studentRegLabel.setText("Invalid Email");
                return;
            }

            try {
                if (!Objects.equals(studentPassBox1.getText(), "")){
                    PassTemp1 = studentPassBox1.getText();
                }else{
                    studentRegLabel.setText("Enter a password");
                    passLabel.setText("*required");
                    return;
                }
            }catch (NullPointerException e5){
                studentRegLabel.setText("Password must not be empty!");
                return;
            }

            try {
                if (!Objects.equals(studentPassBox2.getText(), "")){
                    PassTemp2 = studentPassBox2.getText();
                }else{
                    studentRegLabel.setText("Re-Enter your password");
                    passLabel.setText("*re-enter");
                    return;
                }
            }catch (NullPointerException e6){
                studentRegLabel.setText("Password must not be empty!");
                return;
            }

            if (!(Objects.equals(PassTemp1, PassTemp2))) {
                System.out.println("Password Mismatch");
                studentRegLabel.setText("Password Mismatch");
                passLabel2.setText("*make sure your passwords match");
                return;
            } else {
                if (Regex.passwordPatternMatches(PassTemp1)) {
                    studentPassword = PassTemp1;
                }else {
                    passLabel2.setText("*one UpperCase letter \n" +
                            "*one lowerCase letter \n" +
                            "*one number \n" +
                            "*minimum 8 characters");
                    studentRegLabel.setText("Weak Password");
                    return;
                }
            }

            LocalDate currentDate = LocalDate.now();
            LocalDate minAge = currentDate.minusYears(14);

            try{
                LocalDate doB = dateOfBirthBox.getValue();
                if(!Objects.equals(doB.getDayOfMonth(),0) || !Objects.equals(doB.getMonthValue(), 0)
                        || !Objects.equals(doB.getYear(), 0)){

                    if (doB.isAfter(currentDate)){
                        doBLabel.setText("*invalid date");
                        return;
                    }else  if(doB.isAfter(minAge)){
                        doBLabel.setText("*minumum age is 14");
                        return;
                    } else {
                        date = doB.getMonthValue();
                        month = doB.getMonthValue();
                        year = doB.getYear();
                    }
                }else {
                    studentRegLabel.setText("Enter your Date of Birth");
                    return;
                }
            }catch(Exception e7){
                studentRegLabel.setText("Date of Birth must not be empty");
                return;
            }


            try {
                DateOfBirth dateOfBirth = new DateOfBirth(date, month, year);
                Student student = new Student(studentId, studentFirstName, studentLastName, studentEmail,dateOfBirth, studentPassword);
                studentList.add(student);
                DBConnect.insertStudent(studentId, studentFirstName, studentLastName, studentEmail,dateOfBirth, studentPassword);

                for (Object i: studentList){
                    System.out.println(student.getStudentId());
                    System.out.println(student.getStudentFirstName());
                    System.out.println(student.getStudentLastName());
                    System.out.println(student.getStudentEmail());
                    System.out.println(student.getDateOfBirth().toString());
                    System.out.println(student.getStudentPassword());
                }

                clearStudentFields();
                clearStudentLabels();
                studentRegLabel.setText("Registration Complete");
            }catch (Exception e7){
                System.out.println(e7.getMessage());
                studentRegLabel.setText("Error Encountered");
            }

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("StudentDetails.ser"))) {
                objectOutputStream.writeObject(studentList);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    private void onAdvisorRegButtonClicked() throws IOException{
        clearAdvisorLabels();
        if (Objects.equals(advisorIdBox.getText(), "") || Objects.equals(advisorFnameBox.getText(), "") ||
                Objects.equals(advisorLnameBox.getText(), "") || Objects.equals(advisorEmailBox.getText(), "") ||
                Objects.equals(advisorPassBox1.getText(), "") || Objects.equals(advisorPassBox2.getText(), "")) {
            advisorRegLabel.setText("Please fill the above credentials");
        } else {
            System.out.println("test");

            try {
                if (!Objects.equals(advisorIdBox.getText(), "")) {
                    advisorId = advisorIdBox.getText();
                } else {
                    advisorRegLabel.setText("Enter your Advisor ID");
                    adIdLabel.setText("*required");
                    return;
                }
            } catch (InputMismatchException | NumberFormatException | NullPointerException e1) {
                advisorRegLabel.setText("Invalid Advisor ID");
                adIdLabel.setText("invalid");
                return;
            }

            try {
                if (!Objects.equals(advisorFnameBox.getText(), "")) {
                    if (Regex.namePatternMatches(advisorFnameBox.getText())) {
                        advisorFirstName = advisorFnameBox.getText();
                    } else {
                        advisorRegLabel.setText("Name cannot contain special characters/numbers");
                        adfNameLabel.setText("*invalid");
                        return;
                    }
                } else {
                    advisorRegLabel.setText("Enter your First Name");
                    adfNameLabel.setText("*required");
                    return;
                }
            } catch (NullPointerException e2) {
                advisorRegLabel.setText("Invalid First Name");
                return;
            }

            try {
                if (!Objects.equals(advisorLnameBox.getText(), "")) {
                    if (Regex.namePatternMatches(advisorLnameBox.getText())) {
                        advisorLastName = advisorLnameBox.getText();
                    } else {
                        advisorRegLabel.setText("Name cannot contain special characters/numbers");
                        adlNameLabel.setText("*invalid");
                        return;
                    }
                } else {
                    advisorRegLabel.setText("Enter your Last Name");
                    adlNameLabel.setText("*required");
                    return;
                }
            } catch (NullPointerException e3) {
                advisorRegLabel.setText("Invalid Last Name");
                return;
            }

            try {
                if (!Objects.equals(advisorEmailBox.getText(), "")) {
                    try {
                        if (Regex.emailPatternMatches(advisorEmailBox.getText())) {
                            System.out.println("Valid email");
                            advisorEmail = advisorEmailBox.getText();
                        } else {
                            System.out.println("Invalid email");
                            advisorRegLabel.setText("Invalid Email");
                            ademailLabel.setText("*invalid");
                            return;
                        }
                    } catch (Exception s) {
                        System.out.println(s.getMessage());
                    }
                } else {
                    advisorRegLabel.setText("Enter your Email");
                    ademailLabel.setText("*required");
                    return;
                }
            } catch (NullPointerException e4) {
                advisorRegLabel.setText("Invalid Email");
                return;
            }

            try {
                if (!Objects.equals(advisorPassBox1.getText(), "")) {
                    PassTemp1 = advisorPassBox1.getText();
                } else {
                    advisorRegLabel.setText("Enter a password");
                    adpassLabel.setText("*required");
                    return;
                }
            } catch (NullPointerException e5) {
                advisorRegLabel.setText("Password must not be empty!");
                return;
            }

            try {
                if (!Objects.equals(advisorPassBox2.getText(), "")) {
                    PassTemp2 = advisorPassBox2.getText();
                } else {
                    advisorRegLabel.setText("Re-Enter your password");
                    adpassLabel.setText("*re-enter");
                    return;
                }
            } catch (NullPointerException e6) {
                advisorRegLabel.setText("Password must not be empty!");
                return;
            }

            if (!(Objects.equals(PassTemp1, PassTemp2))) {
                System.out.println("Password Mismatch");
                advisorRegLabel.setText("Password Mismatch");
                adpassLabel2.setText("*make sure your passwords match");
                return;
            } else {
                if (Regex.passwordPatternMatches(PassTemp1)) {
                    advisorPassword = PassTemp1;
                }else {
                    adpassLabel2.setText("*one UpperCase letter \n" +
                            "*one lowerCase letter \n" +
                            "*one number \n" +
                            "*minimum 8 characters");
                    advisorRegLabel.setText("Weak Password");
                    return;
                }
            }

//            try {
//                if (Regex.passwordPatternMatches(advisorPassword)){
//                    adpassLabel2.setText("Strong Password");
//                    adpassLabel2.setTextFill(Color.GREEN);
//                }
//            }catch (Exception g){
//                g.printStackTrace();
//            }

            LocalDate currentDate = LocalDate.now();
            LocalDate minAge = currentDate.minusYears(21);

            try{
                LocalDate doB = adDateOfBirthBox.getValue();
                if(!Objects.equals(doB.getDayOfMonth(),0) || !Objects.equals(doB.getMonthValue(), 0)
                        || !Objects.equals(doB.getYear(), 0)){

                    if (doB.isAfter(currentDate)){
                        addoBLabel.setText("*invalid date");
                        return;
                    }else  if(doB.isAfter(minAge)){
                        addoBLabel.setText("*minumum age is 21");
                        return;
                    } else {
                        date = doB.getMonthValue();
                        month = doB.getMonthValue();
                        year = doB.getYear();
                    }

                }else {
                    advisorRegLabel.setText("Enter your Date of Birth");
                    return;
                }
            }catch(Exception e7){
                advisorRegLabel.setText("Date of Birth must not be empty");
                return;
            }

            try {
                DateOfBirth dateOfBirth = new DateOfBirth(date, month, year);
                Advisor advisor = new Advisor(advisorId, advisorFirstName, advisorLastName, advisorEmail, dateOfBirth, advisorPassword);
                advisorList.add(advisor);
                DBConnect.insertAdvisor(advisorId, advisorFirstName, advisorLastName, advisorEmail, dateOfBirth, advisorPassword);

                for (Object i : advisorList) {
                    System.out.println(advisor.getAdvisorId());
                    System.out.println(advisor.getAdvisorFirstName());
                    System.out.println(advisor.getAdvisorLastName());
                    System.out.println(advisor.getAdvisorEmail());
                    System.out.println(advisor.getDateOfBirth().toString());
                    System.out.println(advisor.getAdvisorPassword());
                }

                clearAdvisorFields();
                clearAdvisorLabels();
                advisorRegLabel.setText("Registration Complete");
            } catch (Exception e7) {
                System.out.println(e7.getMessage());
                advisorRegLabel.setText("Error Encountered");
            }

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("AdvisorDetails.ser"))) {
                objectOutputStream.writeObject(advisorList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearStudentFields(){
        studentIdBox.clear();
        studentFnameBox.clear();
        studentLnameBox.clear();
        studentEmailBox.clear();
        studentPassBox1.clear();
        studentPassBox2.clear();
        dateOfBirthBox.setValue(null);

    }

    private void clearStudentLabels(){
        fNameLabel.setText("");
        lNameLabel.setText("");
        stIdLabel.setText("");
        emailLabel.setText("");
        doBLabel.setText("");
        passLabel.setText("");
        passLabel2.setText("");
    }

    private void clearAdvisorFields() {
        advisorIdBox.clear();
        advisorFnameBox.clear();
        advisorLnameBox.clear();
        advisorEmailBox.clear();
        advisorPassBox1.clear();
        advisorPassBox2.clear();
        adDateOfBirthBox.setValue(null);
    }

    private void clearAdvisorLabels(){
        adfNameLabel.setText("");
        adlNameLabel.setText("");
        adIdLabel.setText("");
        ademailLabel.setText("");
        addoBLabel.setText("");
        adpassLabel.setText("");
        adpassLabel2.setText("");
    }

    @FXML
    private void onPassCheckClicked() {
    }

    @FXML
    private void backButton() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("UserReg.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        mainStage.setTitle("Registration");
        mainStage.setScene(scene);
        mainStage.show();
        Stage prevStage = (Stage) backButton.getScene().getWindow();
        prevStage.close();
    }


//    @FXML
//    private void onPassCheckClicked() throws IOException {
//        if (passCheckBox.isSelected()){
//            passField.setVisible(true);
//        }
//    }
}

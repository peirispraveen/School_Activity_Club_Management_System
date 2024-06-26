package com.example.sacms;

import com.example.implementation.ClubApplication;
import com.example.implementation.Storage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DBConnect {

    private static final String url = "jdbc:mysql://localhost:3306/sacms";  // url for DB connection
    private static final String username = "root";  // username for DB connection
    private static final String password = "";  // password for DB connection

    // Student and Advisor logins are handled in this class
    @FXML
    private AnchorPane studentLogAnchor;
    @FXML
    private TextField studentLogin;
    @FXML
    private TextField studentLoginPass;
    @FXML
    private Label studentSubLabel;
    @FXML
    private Label studentLogLabel;
    @FXML
    private Label studentPassLabel;
    @FXML
    private ImageView studentLogImage;
    @FXML
    private AnchorPane advisorLogAnchor;
    @FXML
    private TextField advisorLogin;
    @FXML
    private TextField advisorLoginPass;
    @FXML
    private Label advisorSubLabel;
    @FXML
    private Label advisorLogLabel;
    @FXML
    private Label advisorPassLabel;
    @FXML
    private ImageView advisorLogImage;

    public static Connection getConnection() throws SQLException {  // driver manager to get the connection
        return DriverManager.getConnection(url, username, password);
    }

    // Inserting new student to the database
    public static void insertStudent(String studentId, String firstName, String lastName, String email, DateOfBirth dateOfBirth, String password) {
        if (!isStudentExists(studentId)) {  // checks if the student already exists
            try (Connection connection = getConnection()) {
                String query = "INSERT INTO student (student_id, first_name, last_name, email, dateOfBirth, password) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, studentId);
                    preparedStatement.setString(2, firstName);
                    preparedStatement.setString(3, lastName);
                    preparedStatement.setString(4, email);
                    preparedStatement.setString(5, dateOfBirth.toString());
                    preparedStatement.setString(6, password);

                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Student with ID " + studentId + " already exists.");
        }
    }

    // New advisor adding to DB
    public static void insertAdvisor(String advisorId, String firstName, String lastName, String email, DateOfBirth dateOfBirth, String password) {
        if (!isAdvisorExists(advisorId)) {  // If the advisor already exists
            try (Connection connection = getConnection()) {
                String query = "INSERT INTO advisor (advisor_id, first_name, last_name, email, dateOfBirth, password) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, advisorId);
                    preparedStatement.setString(2, firstName);
                    preparedStatement.setString(3, lastName);
                    preparedStatement.setString(4, email);
                    preparedStatement.setString(5, dateOfBirth.toString());
                    preparedStatement.setString(6, password);

                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Advisor with ID " + advisorId + " already exists.");
        }
    }

    public static void getStudentById(String studentId) {  // retrieve student details
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM student WHERE student_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println("Student ID: " + resultSet.getString("student_id"));
                        System.out.println("First Name: " + resultSet.getString("first_name"));
                        System.out.println("Last Name: " + resultSet.getString("last_name"));
                        System.out.println("Email: " + resultSet.getString("email"));
                        System.out.println("Date of Birth: " + resultSet.getString("dateOfBirth"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getAdvisorById(String advisorId) {  // retrieve advisor details
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM advisor WHERE advisor_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, advisorId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println("Advisor ID: " + resultSet.getString("advisor_id"));
                        System.out.println("First Name: " + resultSet.getString("first_name"));
                        System.out.println("Last Name: " + resultSet.getString("last_name"));
                        System.out.println("Email: " + resultSet.getString("email"));
                        System.out.println("Date of Birth: " + resultSet.getString("dateOfBirth"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(String studentId) {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM student WHERE student_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentId);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAdvisor(String advisorId) {
        try (Connection connection = getConnection()) {
            String query = "DELETE FROM advisor WHERE advisor_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, advisorId);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isStudentExists(String studentId) {  // Checks if student exists in the database
        try (Connection connection = getConnection()) {
            String query = "SELECT COUNT(*) FROM student WHERE student_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isStudentMatch(String studentId, String password) {  // checks if the student id matches with the password
        try (Connection connection = getConnection()) {
            String query = "SELECT 1 FROM student WHERE student_id = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentId);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean isAdvisorMatch(String advisorId, String password) {  // checks if the advisor id matches with password
        try (Connection connection = getConnection()) {
            String query = "SELECT 1 FROM advisor WHERE advisor_id = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, advisorId);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isAdvisorExists(String advisorId) {  // // Checks if advisor exists in the database
        try (Connection connection = getConnection()) {
            String query = "SELECT COUNT(*) FROM advisor WHERE advisor_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, advisorId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Student> fetchStudentData() throws SQLException {  // fetch the student data from the database
        List<Student> studentList = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM student";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String studentId = resultSet.getString("student_id");
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");
                        String email = resultSet.getString("email");
                        DateOfBirth dateOfBirth = parseDateOfBirth(resultSet.getString("dateOfBirth"));
                        String password = resultSet.getString("password");

                        Student student = new Student(studentId, firstName, lastName, email, dateOfBirth, password);
                        studentList.add(student);
                    }
                }
            }
        }

        return studentList;
    }

    public static List<Advisor> fetchAdvisorData() throws SQLException {  // fetch the advisor data from the database
        List<Advisor> advisorList = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM advisor";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String advisorId = resultSet.getString("advisor_id");
                        String firstName = resultSet.getString("first_name");
                        String lastName = resultSet.getString("last_name");
                        String email = resultSet.getString("email");
                        DateOfBirth dateOfBirth = parseDateOfBirth(resultSet.getString("dateOfBirth"));
                        String password = resultSet.getString("password");

                        Advisor advisor = new Advisor(advisorId, firstName, lastName, email, dateOfBirth, password);
                        advisorList.add(advisor);
                    }
                }
            }
        }

        return advisorList;
    }

    static DateOfBirth parseDateOfBirth(String dateString) {  // date of birth is processed when retrieving from the database
        String[] dateParts = dateString.split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int day = Integer.parseInt(dateParts[2]);

        return new DateOfBirth(day, month, year);
    }


    // the student login process
    @FXML
    private void onStudentLogButtonClicked() throws IOException {
        if (!Objects.equals(studentLogin.getText(), "") && !Objects.equals(studentLoginPass.getText(),"")) {
            if (isStudentMatch(studentLogin.getText(), studentLoginPass.getText())) {

                String studentLogId = studentLogin.getText();
                JoinClub.retrieveCurrentStudent(studentLogId);  // the student details of the currently logged student is retrieved

                FXMLLoader userRegLoader = new FXMLLoader(UserRegApplication.class.getResource("student-options.fxml"));
                Scene scene = new Scene(userRegLoader.load(), 950, 600);
                Stage stage = new Stage();
                stage.setTitle("Student Overview");
                stage.setScene(scene);
                stage.show();

                Stage prevStage = (Stage) studentLogAnchor.getScene().getWindow();
                prevStage.close();
            }else {
                studentSubLabel.setText("Password and ID doesn't match");
            }
        }else {
            if (Objects.equals(studentLogin.getText(), "")) {
                studentLogLabel.setText("*required");
            }else {
                studentPassLabel.setText("*required");
            }
        }
    }

    @FXML
    private void studentSignUpButton() throws IOException{ // goes to the registration page
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("student-reg.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        newStage.setTitle("Student Sign In");
        newStage.setScene(scene);
        newStage.show();
        Stage prevStage = (Stage) studentLogAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void studentLogBackButton() throws IOException{  // goes back to the menu
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("UserReg.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        newStage.setTitle("Home");
        newStage.setScene(scene);
        newStage.show();
        Stage prevStage = (Stage) studentLogAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void advisorLogBackButton() throws IOException{  // goes back to the menu
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("UserReg.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        newStage.setTitle("Home");
        newStage.setScene(scene);
        newStage.show();
        Stage prevStage = (Stage) advisorLogAnchor.getScene().getWindow();
        prevStage.close();

    }

    @FXML
    private void advisorSignUpButton() throws IOException{  // goes to the registration page
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("advisor-reg.fxml"));
        Stage newStage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        newStage.setTitle("Advisor Sign In");
        newStage.setScene(scene);
        newStage.show();
        Stage prevStage = (Stage) advisorLogAnchor.getScene().getWindow();
        prevStage.close();
    }

    @FXML
    private void onAdvisorLogButtonClicked() throws IOException, SQLException{  // goes to advisor page
        if (!Objects.equals(advisorLogin.getText(), "") && !Objects.equals(advisorLoginPass.getText(),"")) {
            if (isAdvisorMatch(advisorLogin.getText(), advisorLoginPass.getText())) {
                FXMLLoader userRegLoader = new FXMLLoader(UserRegApplication.class.getResource("advisor-options.fxml"));
                Scene scene = new Scene(userRegLoader.load(), 950, 600);
                Stage stage = new Stage();
                stage.setTitle("Advisor Overview");
                stage.setScene(scene);
                stage.show();

                Stage prevStage = (Stage) advisorLogAnchor.getScene().getWindow();
                prevStage.close();
            }else {
                advisorSubLabel.setText("Password and ID doesn't match");
            }
        }else {
            if (Objects.equals(studentLogin.getText(), "")) {
                studentLogLabel.setText("*required");
            }else {
                studentPassLabel.setText("*required");
            }
        }
    }

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println("Class not found");
            e.printStackTrace();
        }

        System.out.println("Driver class registered");
        Connection sample = null;

        try {
            sample = DriverManager.getConnection(url, username, password);
        }catch (SQLException e2) {
            System.out.println("sql exception found");
            e2.printStackTrace();
            return;
        }

        if (sample != null){
            System.out.println("success");
        }else {
            System.out.println("failed to connect");
        }

        if (isStudentMatch("ST221", "Password1")) {
            System.out.println("Found");
        }else {
            System.out.println("not");
        }

        if (isAdvisorMatch("AD443", "Password5")) {
            System.out.println("Found");
        }else {
            System.out.println("not");
        }

    }

}

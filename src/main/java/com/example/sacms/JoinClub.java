package com.example.sacms;

import com.example.implementation.Club;
import com.example.implementation.ClubAdvisor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.sacms.DBConnect.parseDateOfBirth;

public class JoinClub {
    private static final String url = "jdbc:mysql://localhost:3306/sacms";
    private static final String username = "root";
    private static final String password = "";
    @FXML
    private TableColumn clubIdColumn;
    @FXML
    private TableColumn clubNameColumn;
    @FXML
    private TableColumn clubJoinColumn;
    @FXML
    private TableView joinClubTable;
    public static String currStId;
    @FXML
    private AnchorPane joinClubAnchor;
    @FXML
    private Label joinClubStudentLabel;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @FXML
    private void initialize() throws SQLException {
        clubIdColumn.setCellValueFactory(new PropertyValueFactory<>("clubId"));
        clubNameColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        setupJoinButton();
        loadClubsIntoTableView();
    }

    private void setupJoinButton() {
        clubJoinColumn.setCellFactory(param -> new TableCell<Club, Club>() {
            private final Button joinButton = new Button("Join");

            {
                joinButton.setOnAction(event -> {
                    Club club = getTableView().getItems().get(getIndex());
                    joinClub(club, currStId);
                });
            }

            @Override
            protected void updateItem(Club item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(joinButton);
                }
            }
        });
    }

    public static List<Club> availableClubs() {
        List<Club> clubList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM club";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String clubId = resultSet.getString("club_id");
                        String clubName = resultSet.getString("club_name");

                        Club club = new Club(clubId, clubName);
                        clubList.add(club);
                    }
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return clubList;
    }

    private void loadClubsIntoTableView() throws SQLException{
        List<Club> clubData = availableClubs();

        joinClubTable.getItems().clear();

        joinClubTable.getItems().addAll(clubData);
    }

    public static void retrieveCurrentStudent(String studentId) {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM student WHERE student_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, studentId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        currStId = resultSet.getString("student_id");
                        String currStFName = resultSet.getString("first_name");
                        String currStLName = resultSet.getString("last_name");
                        String currStEmail = resultSet.getString("email");
                        DateOfBirth dateOfBirth = parseDateOfBirth(resultSet.getString("dateOfBirth"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void joinClub(Club club, String studentId){
        if (studentId != null && !studentId.isEmpty()) {

            if (!alreadyInClub(currStId, club.getClubId()))
                {try (Connection connection = getConnection()) {
                    String query = "INSERT INTO student_club (student_id, club_id) VALUES (?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, studentId);
                        preparedStatement.setString(2, club.getClubId());

                        preparedStatement.executeUpdate();

                        System.out.println("Student with ID " + studentId + " joined the club " + club.getClubName());
                    }
                    joinClubStudentLabel.setText("Joined to " + club.getClubName());

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("Already a member of the club");
                joinClubStudentLabel.setText("Already a member of the club");
            }
        } else {
            System.out.println("Student ID is not available.");
        }
    }

    private static boolean alreadyInClub(String currStId, String clubId) {
        try (Connection connection = getConnection()) {
            String query = "SELECT COUNT(*) FROM student_club WHERE student_id = ? AND club_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, currStId);
                preparedStatement.setString(2, clubId);

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

    @FXML
    private void backButton() throws IOException {
        FXMLLoader userRegLoader = new FXMLLoader(UserRegApplication.class.getResource("UserReg.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(userRegLoader.load(), 950, 600);
        stage.setTitle("Registration");
        stage.setScene(scene);
        stage.show();

        Stage prevStage = (Stage) joinClubAnchor.getScene().getWindow();
        prevStage.close();
    }
}

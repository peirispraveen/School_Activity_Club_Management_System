package com.example.implementation;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.sql.*;

public class CreateClub extends ClubApplication {
    public TextField clubId;
    public TextField clubName;
    public ComboBox<String> clubAdvisor;
    public DatePicker createdDate;
    public Text errorCall;
    public TextField maxParticipants;
    public TextArea clubDescription;
    Club list;
    List<ClubAdvisor> clubAdvisorList;
    List<Club> clubList;
    Connection con;
    Statement stmt;

    public void initialize() throws SQLException {
        String url= "jdbc:mysql://localhost:3307/Club_Management";
        String user="root";
        String password="";
        con=DriverManager.getConnection(url,user,password);

        clubAdvisorList = Storage.getAvailableClubAdvisor();
        for(ClubAdvisor advisor : clubAdvisorList){
            clubAdvisor.getItems().add(advisor.getFName()+" "+advisor.getLName());
        }
    }

    public void createClubBtn(ActionEvent CreateClub) throws IOException, SQLException {
        clubList=Storage.getAvailableClubs();

        if (clubId.getText().equals("") ||clubId.getText().contains(";") ) {
            errorCall.setText("Fill Club ID without ';'");
        }
        else if (clubName.getText().equals("") || clubName.getText().contains(";")) {
            errorCall.setText("Fill Club Name without ';'");
        }
        else if(clubDescription.getText().equals("") || clubDescription.getText().contains(";")){
            errorCall.setText("Fill Club Description without ';'");
        }
        else if(clubAdvisor.getValue()==null){
            errorCall.setText("Select Club Advisor");
        }
        else if (createdDate.getValue() == null || createdDate.getValue().isAfter(LocalDate.now())) {
            errorCall.setText("Enter a valid date");
        } else {
            boolean clubExist=clubList.stream()
                    .anyMatch(club -> club.getClubId().equals(clubId.getText()) || club.getClubName().equals(clubName.getText()));

                if (clubExist) {
                    errorCall.setText("Club ID or Name already exist");
                }else{
                    try{
                        if(Integer.parseInt(maxParticipants.getText())>0){
                            String[] name=clubAdvisor.getValue().split("\\s+");
                            list = new Club(clubId.getText(), clubName.getText(),clubDescription.getText(),new ClubAdvisor(name[0],name[1]),
                                    Integer.parseInt(maxParticipants.getText()), Date.valueOf(createdDate.getValue()));
                            String sql = "";
                            DBConnection.insertToDatabase(list.getClubId(), list.getClubName(),list.getClubDescription(),list.getClubAdvisor(),list.getMaxParticipants(),list.getCreatedDate());
                           
                            clubList.add(list);
                            errorCall.setText("");
                            addAnother(CreateClub);
                        }else{
                            errorCall.setText("Enter positive value");
                        }
                    }catch(NumberFormatException e){
                        errorCall.setText("Enter integer value for maximum participants");
                    }
                }
            }
        }



    public void addAnother(ActionEvent event) throws IOException {

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Add Another item");
        confirm.setContentText("Data saved.\nDo you want to add another item to the list? ");

        if (confirm.showAndWait().get() == ButtonType.OK) {
            Stage prevStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            prevStage.close();
            Stage currentStage = new Stage();
            FXMLLoader loader=new FXMLLoader(ClubApplication.class.getResource("Create Club.fxml"));
            Scene scene = new Scene(loader.load(),800,500);
            currentStage.setScene(scene);
            currentStage.setTitle("Create Club");
            currentStage.show();

        } else {
            event.consume();
            Stage prevStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            prevStage.close();
            FXMLLoader homeFXML = new FXMLLoader(ClubApplication.class.getResource("Club.fxml"));
            Scene scene1 = new Scene(homeFXML.load(), 800, 500);
            Stage homePage = new Stage();
            homePage.setScene(scene1);
            homePage.show();
            homePage.centerOnScreen();
        }
    }

    public void backBtn(ActionEvent actionEvent) throws IOException {
        Stage currentStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage prevStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ClubApplication.class.getResource("Club.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        prevStage.setTitle("Club");
        prevStage.setScene(scene);
        prevStage.setResizable(false);
        prevStage.show();
    }


    public void idReleased() {
        try {
            clubId.setText(clubId.getText().substring(0,5));
            clubId.positionCaret(5);
        }catch (Exception ignored) {}

    }
    public void nameReleased() {
        try {
            clubName.setText(clubName.getText().substring(0,20));
            clubName.positionCaret(20);
        }catch (Exception ignored) {}
    }
    public void descReleased() {
        try {
            clubDescription.setText(clubDescription.getText().substring(0,60));
            clubDescription.positionCaret(60);
        }catch (Exception ignored) {}
    } public void partReleased() {
        try {
            maxParticipants.setText(maxParticipants.getText().substring(0,3));
            maxParticipants.positionCaret(3);
        }catch (Exception ignored) {}
    }
}

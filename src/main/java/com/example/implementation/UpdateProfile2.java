package com.example.implementation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;




public class UpdateProfile2 extends Storage{

    @FXML
    public TextField clubId;
    @FXML
    public TextField clubName;
    public DatePicker createdDate;
    public ComboBox<String> clubAdvisor;
    public Text errorCall;
    public TextField maxParticipants;
    public TextArea clubDescription;
    List<ClubAdvisor> clubAdvisorList;
    Club updList=UpdateProfile1.updList;

    Connection con;
    Statement stmt;

    public void initialize() throws SQLException {
        String url= "jdbc:mysql://localhost:3306/Club_Management";
        String user="root";
        String password="";

        con= DriverManager.getConnection(url,user,password);
        stmt=con.createStatement();

        clubAdvisorList = getAvailableClubAdvisor();
        for(ClubAdvisor advisor : clubAdvisorList){
            clubAdvisor.getItems().add(advisor.getFName()+" "+advisor.getLName());
        }
        clubId.setText(updList.getClubId());
        clubName.setText(updList.getClubName());
        clubDescription.setText(updList.getClubDescription());
        clubAdvisor.setValue(updList.getClubAdvisor().toString());
        maxParticipants.setText(String.valueOf(updList.getMaxParticipants()));
        createdDate.setValue(LocalDate.parse(updList.getCreatedDate().toString()));

    }

    public void updateClub(ActionEvent actionEvent) throws IOException,SQLException {

        availableClubs.removeIf(club -> club.getClubId().equals(updList.getClubId()));
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
            boolean clubExist=availableClubs.stream()
                    .anyMatch(club -> club.getClubId().equals(clubId.getText()) || club.getClubName().equals(clubName.getText()));

            if (clubExist) {
                errorCall.setText("Club ID or Name already exist");

            }else{
                try {
                    if(Integer.parseInt(maxParticipants.getText())>0){
                    errorCall.setText("");

                    String sql="UPDATE Club "+
                            "SET `Club id`= '"+clubId.getText()+"',`Club Name`= '"+clubName.getText()+
                            "',`Club Description`= '"+clubDescription.getText()+"',`Club Advisor`= '"+clubAdvisor.getValue()+
                            "',`Max Number`= "+maxParticipants.getText()+",`Created Date`= '"+createdDate.getValue()+
                            "' WHERE `Club Id`= '"+updList.getClubId()+"';";
                        stmt.executeUpdate(sql);


                    availableClubs.add(new Club(clubId.getText(), clubName.getText(),clubDescription.getText(), new ClubAdvisor(clubAdvisor.getValue().split("\\s+")[0],clubAdvisor.getValue().split("\\s+")[1]),
                            Integer.parseInt(maxParticipants.getText()), Date.valueOf(createdDate.getValue())));
                    Stage currentStage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    currentStage.close();
                    Stage homeStage=new Stage();
                    FXMLLoader fxmlLoader=new FXMLLoader(ClubApplication.class.getResource("Update Profile1.fxml"));
                    Scene scene=new Scene(fxmlLoader.load(),800,500);
                    homeStage.setScene(scene);
                    homeStage.setTitle("Update Club");
                    homeStage.show();
                    }else{
                        errorCall.setText("Enter positive value for maximum participants");
                    }

                }catch (NumberFormatException e){
                    errorCall.setText("Enter integer value for maximum participants");
                }
            }
        }
    }

    public void backBtn(ActionEvent actionEvent) throws IOException {
        Stage currentStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage prevStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ClubApplication.class.getResource("Update Profile1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        prevStage.setTitle("Update Club");
        prevStage.setScene(scene);
        prevStage.setResizable(false);
        prevStage.show();
    }

    public void homeButton(ActionEvent event) throws IOException {
        Stage prevStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            prevStage.close();
            FXMLLoader homeFXML = new FXMLLoader(ClubApplication.class.getResource("Club.fxml"));
            Scene scene1 = new Scene(homeFXML.load(), 800, 500);
            Stage homePage = new Stage();
            homePage.setScene(scene1);
            homePage.show();
            homePage.centerOnScreen();
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


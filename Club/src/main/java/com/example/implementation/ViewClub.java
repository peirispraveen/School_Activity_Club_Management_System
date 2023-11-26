package com.example.implementation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewClub extends Storage implements Initializable {

    @FXML
    private TableView<Club> clubsTbl;
    @FXML
    private TableColumn<Club,String> clubColumn;
    @FXML
    private TextField clubId;
    @FXML
    private Text clubName;
    @FXML
    private TextArea clubDescription;
    @FXML
    private TextField clubAdvisor;
    @FXML
    private TextField maxNumber;
    @FXML
    private
    TextField currentParticipants;

    @FXML
    private TextField createdDate;
    @FXML
    private TableView<ClubMember> membersTbl;
    @FXML
    private TableColumn<ClubMember,String> fName;
    @FXML
    private TableColumn<ClubMember,String> lName;
    ObservableList<Club> clubList= FXCollections.observableArrayList();
    ObservableList<ClubMember> memberList= FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Label empty=new Label("No clubs available");
        clubsTbl.setPlaceholder(empty);
        Label emptyLabel = new Label("No members in this club");
        membersTbl.setPlaceholder(emptyLabel);

        clubColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubList.addAll(getAvailableClubs());
        clubsTbl.setItems(clubList);

        clubsTbl.setOnMouseClicked(event -> {
            if (event.getClickCount()==2){
                Club selectedClub = clubsTbl.getSelectionModel().getSelectedItem();
                if(selectedClub!=null){
                    displayDetails(selectedClub);
                }
            }
        });

    }

    public void displayDetails(Club selectedClub){

        clubId.setText(selectedClub.getClubId());
        clubName.setText(selectedClub.getClubName());
        clubDescription.setText(selectedClub.getClubDescription());
        clubAdvisor.setText(selectedClub.getClubAdvisor().toString());
        maxNumber.setText(String.valueOf(selectedClub.getMaxParticipants()));
        createdDate.setText(String.valueOf(selectedClub.getCreatedDate()));

        fName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        lName.setCellValueFactory(new PropertyValueFactory<>("lName"));
        memberList.clear();
        try{
        currentParticipants.setText(String.valueOf(selectedClub.getClubMembers().size()));
        memberList.addAll(selectedClub.getClubMembers());
        membersTbl.setItems(memberList);


        }catch (NullPointerException ignored){
            currentParticipants.setText("0");
        }

    }

    public void backBtn(ActionEvent actionEvent) throws IOException {
        Stage currentStage=(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage homeStage=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(ClubApplication.class.getResource("Club.fxml"));
        Scene scene=new Scene(fxmlLoader.load(),800,500);
        homeStage.setScene(scene);
        homeStage.setTitle("Home");
        homeStage.show();
    }




}

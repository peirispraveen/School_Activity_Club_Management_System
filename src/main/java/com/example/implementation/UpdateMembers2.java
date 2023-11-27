package com.example.implementation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class UpdateMembers2 extends Storage{
    public TableView<ClubMember> clubMemberTbl;
    public TableColumn<ClubMember,String> memberIdColumn;
    public TableColumn<ClubMember,String> fNameColumn;
    public TableColumn<ClubMember,String> lNameColumn;
    public Button deleteBtn;
    public Button addBtn;
    public Button updateBtn;
    public Button backBtn;
    public Button homeBtn;
    ObservableList<ClubMember> memberList= FXCollections.observableArrayList();
    Club updClub= UpdateMembers1.updList;


    public void initialize() throws SQLException {


        memberIdColumn.setCellValueFactory((new PropertyValueFactory<>("memberId")));
        fNameColumn.setCellValueFactory((new PropertyValueFactory<>("fName")));
        lNameColumn.setCellValueFactory((new PropertyValueFactory<>("lName")));
        for (Club club : getAvailableClubs()) {
            if(club.getClubId().equals(updClub.getClubId())){
                try {
                    memberList.addAll(club.getClubMembers());
                }catch (NullPointerException ignored){}
            }
        }
        FXCollections.sort(memberList);
        clubMemberTbl.setItems(memberList);
    }

    public void deleteMember(ActionEvent actionEvent) {
        ClubMember member=clubMemberTbl.getSelectionModel().getSelectedItem();
        memberList.remove(member);
        clubMemberTbl.refresh();
    }

    public void addMember(ActionEvent actionEvent) {

        Stage addMembersStage = new Stage();
        Label emptyLabel = new Label("No Members available");
        TableView<ClubMember> addMembersTable = new TableView<>();
        emptyLabel.setStyle("-fx-text-fill: white");
        addMembersTable.setPlaceholder(emptyLabel);
        TableColumn<ClubMember, String> memberFName = new TableColumn<>();
        TableColumn<ClubMember, String> memberLName = new TableColumn<>();

        Text fName = new Text("FName");
        fName.setFill(Color.WHITE);
        fName.setFont(new Font(16));
        memberFName.setGraphic(fName);
        Text lName = new Text("LName");
        lName.setFill(Color.WHITE);
        lName.setFont(new Font(16));
        memberLName.setGraphic(lName);

        memberFName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        memberLName.setCellValueFactory(new PropertyValueFactory<>("lName"));

        addMembersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        addMembersTable.getColumns().add(memberFName);
        addMembersTable.getColumns().add(memberLName);
        ObservableList<ClubMember> addingClubMembers = FXCollections.observableArrayList();

        memberFName.getStyleClass().add("tableColumn");
        memberLName.getStyleClass().add("tableColumn");


        for (ClubMember member : getAvailableClubMembers()) {
            try {
                if (!updClub.getClubMembers().contains(member)) {
                    addingClubMembers.add(member);
                }
            }catch (NullPointerException ignored){
                addingClubMembers.add(member);
            }
        }
        addMembersTable.setItems(addingClubMembers);

        Scene membersScene = new Scene(addMembersTable, 200, 300);
        String cssPath = getClass().getResource("StyleSheets/Main.css").toExternalForm();
        membersScene.getStylesheets().add(cssPath);
        addMembersStage.setScene(membersScene);

        addMembersStage.setTitle("Add Members");
        addMembersStage.show();
        addMembersStage.setResizable(false);


        Stage currentStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene currentScene=currentStage.getScene();


        currentScene.setOnMouseClicked(event -> {
            double clickX = event.getSceneX();
            double clickY = event.getSceneY();
            Bounds bounds = addMembersTable.localToScene(addMembersTable.getBoundsInLocal());
            if (!bounds.contains(clickX, clickY)) {
                addMembersStage.close();
            }
        });
        clubMemberTbl.setOnMouseClicked(event -> {
            double clickX = event.getSceneX();
            double clickY = event.getSceneY();
            Bounds bounds = addMembersTable.localToScene(addMembersTable.getBoundsInLocal());
            if (!bounds.contains(clickX, clickY)) {
                addMembersStage.close();
            }
        });


        addMembersTable.setOnMouseClicked(mouseEvent -> {

            if (mouseEvent.getClickCount() == 2) {
                ClubMember selectedMember = addMembersTable.getSelectionModel().getSelectedItem();
                if (!memberList.contains(selectedMember)){
                    addingClubMembers.remove(selectedMember);
                    FXCollections.sort(addingClubMembers);
                    addMembersTable.setItems(addingClubMembers);
                    addMembersTable.refresh();
                    memberList.add(selectedMember);
                    try {
                        FXCollections.sort(memberList);
                        clubMemberTbl.setItems(memberList);
                        clubMemberTbl.refresh();
                    }catch(Exception ignored){}

                }
            }
        });

    }

    public void updateMembers(ActionEvent actionEvent) throws IOException, SQLException {

        DBConnection.updateDatabaseClubMember(memberList,updClub.getClubId());
        updClub.setClubMembers(memberList);
        Stage currentStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage homeStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ClubApplication.class.getResource("Update Members1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        homeStage.setTitle("Select Club");
        homeStage.setScene(scene);
        homeStage.show();
    }

    public void backBtn(ActionEvent actionEvent) throws IOException {
        Stage currentStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage reportStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ClubApplication.class.getResource("Update Members1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        reportStage.setTitle("Select Club");
        reportStage.setScene(scene);
        reportStage.setResizable(false);
        reportStage.show();
    }

    public void homeButton(ActionEvent actionEvent) throws IOException {
        Stage currentStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage homeStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ClubApplication.class.getResource("Club.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        homeStage.setTitle("Club");
        homeStage.setScene(scene);
        homeStage.setResizable(false);
        homeStage.show();
    }
}


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
import javafx.scene.input.ScrollEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DeleteClub extends Storage {
    @FXML
    public TextField getClubName;
    @FXML
    public TableView<Club> clubsTable;
    @FXML
    public TableColumn<Club,String> clubId;
    @FXML
    public TableColumn<Club,String> clubName;
    public Text errorCall;
    ObservableList<Club> availableClubsTbl = FXCollections.observableArrayList();


    public void initialize() throws SQLException {
        clubsTable.addEventFilter(ScrollEvent.SCROLL, ScrollEvent::consume);



        Label label=new Label("No clubs were found");
        clubsTable.setPlaceholder(label);

        clubId.setCellValueFactory(new PropertyValueFactory<>("clubId"));
        clubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        getList();
        clubsTable.setItems(availableClubsTbl);
    }

    public void deleteClub(ActionEvent actionEvent) throws SQLException {
        String clubName=getClubName.getText();
        boolean found=false;
        for(Club club: getAvailableClubs()) {
            if (club.getClubName().equalsIgnoreCase(clubName) || club.getClubId().equals(clubName)) {
                found = true;
                DBConnection.deleteDatabaseClub(club.getClubId());

                availableClubs.remove(club);
                availableClubsTbl.clear();
                getList();
                clubsTable.setItems(availableClubsTbl);
                getClubName.clear();
                errorCall.setText(club.getClubName() + " has been deleted");
                break;
            }
        }
        if(!found){
            errorCall.setText("Club not found");
        }
    }

    public void backBtn(ActionEvent actionEvent) throws IOException {
        Stage currentStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage prevStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ClubApplication.class.getResource("Club.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        prevStage.setTitle("Update Club");
        prevStage.setScene(scene);
        prevStage.setResizable(false);
        prevStage.show();
    }



    public void getList(){
        for(Club club: getAvailableClubs()){
            availableClubsTbl.add(new Club(club.getClubId(),club.getClubName()));
        }
        clubsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Club selectedCode = clubsTable.getSelectionModel().getSelectedItem();
                if (selectedCode != null) {
                    getClubName.setText(selectedCode.getClubName());
                }
            }
        });
    }


}

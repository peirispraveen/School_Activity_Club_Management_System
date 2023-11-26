package com.example.implementation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GenerateReport extends Storage implements Initializable {

    public TableView<Club> report;
    public TableColumn<Club,String> clubId;
    public TableColumn<Club,String> clubName;
    public TableColumn<Club,ClubAdvisor> clubAdvisor;
    public TableColumn<Club,Integer> maxParticipants;
    public TableColumn<Club,Integer> currentParticipants;
    public TableColumn<Club, LocalDate> createdDate;
    List<Club> Club = new ArrayList<>(List.of());
    ObservableList<Club> reportTable= FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Label label=new Label("No clubs were found");
        report.setPlaceholder(label);

        clubId.setCellValueFactory(new PropertyValueFactory<>("clubId"));
        clubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        clubAdvisor.setCellValueFactory(new PropertyValueFactory<>("clubAdvisor"));
        maxParticipants.setCellValueFactory(new PropertyValueFactory<>("maxParticipants"));
        currentParticipants.setCellValueFactory(new PropertyValueFactory<>("currentParticipants"));
        createdDate.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        appendList();
        report.setItems(reportTable);
    }

    public void appendList(){
        for (Club club : getAvailableClubs()) {
            try {
                reportTable.add(new Club(club.getClubId(), club.getClubName(),club.getClubDescription(),club.getClubAdvisor(),
                        club.getMaxParticipants(), club.getClubMembers().size(), club.getCreatedDate()));
            }catch(NullPointerException ignored){
                reportTable.add(new Club(club.getClubId(),club.getClubName(),club.getClubDescription(), club.getClubAdvisor(),
                        club.getMaxParticipants(), 0, club.getCreatedDate()));
            }
        }
    }

    public void backBtn(ActionEvent actionEvent) throws IOException {
        Stage currentStage =(Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
        Stage reportStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(ClubApplication.class.getResource("Club.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        reportStage.setTitle("Club");
        reportStage.setScene(scene);
        reportStage.setResizable(false);
        reportStage.show();
    }
}

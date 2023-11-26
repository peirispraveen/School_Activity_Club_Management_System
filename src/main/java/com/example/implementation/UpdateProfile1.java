package com.example.implementation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateProfile1 extends Storage implements Initializable {
    @FXML
    public TextField updClub;
    @FXML
    public TableView<Club> clubsTable;
    @FXML
    public TableColumn<Club,String> clubId;
    @FXML
    public TableColumn<Club,String> clubName;
    public Text errorCall;
    ObservableList<Club> availableClubs = FXCollections.observableArrayList();
    public static Club updList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Label label=new Label("No clubs were found");
        label.setStyle("-fx-text-fill: white ");
        clubsTable.setPlaceholder(label);
        clubId.setCellValueFactory(new PropertyValueFactory<>("clubId"));
        clubName.setCellValueFactory(new PropertyValueFactory<>("clubName"));
        getList();
        clubsTable.setItems(availableClubs);

        FXCollections.sort(availableClubs);
        clubsTable.setItems(availableClubs);
        clubsTable.refresh();
    }

    public void getList(){
        for(Club club: getAvailableClubs()){
            availableClubs.add(new Club(club.getClubId(),club.getClubName()));
        }
        clubsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Club selectedCode = clubsTable.getSelectionModel().getSelectedItem();
                if (selectedCode != null) {
                    updClub.setText(selectedCode.getClubName());
                }
            }
        });
    }

    public Club getDetails(ActionEvent e) throws IOException {
        String itemCodeUpd = updClub.getText();
        boolean found = false;
        for (Club club : getAvailableClubs()) {
            if (club.getClubName().equalsIgnoreCase(itemCodeUpd) || club.getClubId().equals(itemCodeUpd)) {
                found = true;
                updList=club;
                errorCall.setText("");
                break;
            }
        }if(!found){
            errorCall.setText("Club not found");
        }
        if (found) {
            Stage prevStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            prevStage.close();
            Stage updateStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(ClubApplication.class.getResource("Update Profile2.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            updateStage.setTitle("Update Club Details of "+updList.getClubName());
            updateStage.setScene(scene);
            updateStage.setResizable(false);
            updateStage.show();
        }
        return updList;
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


}

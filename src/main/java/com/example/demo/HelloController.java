package com.example.demo;

import com.example.sacms.UserRegApplication;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloController {

    @FXML
    public Label checkStatusLabel;

    @FXML
    public Label checkStatusLabel_T;

    @FXML
    private ComboBox<String> eventComboBox;

    @FXML
    private ComboBox<String> T_eventComboBox;

    @FXML
    public BarChart<String, Number> attendanceRchart;

    @FXML
    public BarChart<String , Number> attendanceRchart_T;

    @FXML
    private TextField deleteLNameTextField;

    @FXML
    private TextField deleteFNameTextField;

    @FXML
    private TextField deleteStuIdTextField;

    @FXML
    private TextField deleteClubNameTextField;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField markStuIdTextField;

    @FXML
    private TextField markClubNameTextField;

    @FXML
    private ComboBox<String> Eventlist;

    @FXML
    private RadioButton absentRadioButton;

    @FXML
    private RadioButton presentRadioButton;

    @FXML
    private RadioButton pendingRadioButton;

    @FXML
    private Button submitButton;

    @FXML
    private TextField markT_FNameTextField;

    @FXML
    private TextField markT_LNameTextField;

    @FXML
    private TextField markTeaIdTextField;

    @FXML
    private TextField markTeaClubNameTextField;

    @FXML
    private ComboBox<String> deltablelist;

    @FXML
    private ComboBox<String> T_Eventlist;

    @FXML
    private RadioButton T_absentRadioButton;

    @FXML
    private RadioButton T_presentRadioButton;

    @FXML
    private RadioButton T_pendingRadioButton;

    @FXML
    private Button T_submitButton;

    @FXML
    private TextField markFNameTextField;

    @FXML
    private TextField markLNameTextField;

    @FXML
    private Button resetbutton;

    @FXML
    private Button exitbutton;

    @FXML
    private Button backButtonX;

    public void initialize(){
        ObservableList<String> list = FXCollections.observableArrayList("Event_01", "Event_02", "Event_03");
        Eventlist.setItems(list);
        ObservableList<String> list_2 = FXCollections.observableArrayList("Event_01", "Event_02", "Event_03");
        T_Eventlist.setItems(list_2);
        ObservableList<String> eventList = FXCollections.observableArrayList("Event_01", "Event_02", "Event_03");
        eventComboBox.setItems(eventList);
        ObservableList<String> eventList_2 = FXCollections.observableArrayList("Event_01", "Event_02", "Event_03");
        T_eventComboBox.setItems(eventList_2);
        ObservableList<String> table_list = FXCollections.observableArrayList("student_table", "student_attendance", "teacher_table", "teacher_attendance");
        deltablelist.setItems(table_list);
    }

    // Event handler for the DELETE button
    @FXML
    private void handleDeleteButton() {
        // Instantiate the delete class
        Delete deleteInstance = new Delete();

        try {
            String deleteFName = deleteFNameTextField.getText();
            String deleteLName = deleteLNameTextField.getText();
            String deleteStudentId = deleteStuIdTextField.getText();
            String deleteNameofClub = deleteClubNameTextField.getText();
            String deletetable = deltablelist.getValue();

            // Call the delete class method
            deleteInstance.deleteRecord(deleteFName, deleteLName, deleteStudentId, deleteNameofClub,deletetable);

            // Clear the input fields
            deleteFNameTextField.clear();
            deleteLNameTextField.clear();
            deleteStuIdTextField.clear();
            deleteClubNameTextField.clear();
            deltablelist.getSelectionModel().clearSelection();

            // Clear the input fields
            reset();

        } catch (NumberFormatException e) {
            // Display an error alert for number format exception
            showErrorAlert("Please enter a valid number for numeric fields.");
        }
    }

    // Event handler for the SUBMIT button
    @FXML
    private void handleSubmitButton(ActionEvent event) {
        // Instantiate the submit class
        Submit submitInstance = new Submit();
        try {
            String firstNameToCheck = markFNameTextField.getText();
            String lastNameToCheck = markLNameTextField.getText();
            String markStudentId = markStuIdTextField.getText();
            String markClubName = markClubNameTextField.getText();
            String selectedEvent = Eventlist.getValue();
            String status = null;
            if (absentRadioButton.isSelected()) {
                status = "Absent";
                presentRadioButton.setSelected(false);
                pendingRadioButton.setSelected(false);
            } else if (presentRadioButton.isSelected()) {
                status = "Present";
                absentRadioButton.setSelected(false);
                pendingRadioButton.setSelected(false);
            } else if (pendingRadioButton.isSelected()) {
                status = "Pending";
                absentRadioButton.setSelected(false);
                presentRadioButton.setSelected(false);
            }

            submitInstance.handleSubmitrecord(firstNameToCheck,lastNameToCheck,markStudentId,markClubName,selectedEvent,status);

            // Clear the input fields
            markFNameTextField.clear();
            markLNameTextField.clear();
            markStuIdTextField.clear();
            markClubNameTextField.clear();
            absentRadioButton.setSelected(false);
            presentRadioButton.setSelected(false);
            pendingRadioButton.setSelected(false);
            Eventlist.getSelectionModel().clearSelection();

            submitButton.setDisable(true);

            // Clear the input fields
            reset();

        } catch (NumberFormatException e) {
            // Display an error alert for number format exception
            showErrorAlert("Please enter a valid number for numeric fields.");
        }
    }

    @FXML
    private void handleCheckButton(ActionEvent event) {
        Check.found = false;
        // Instantiate the check class
        Check checkInstance = new Check();
        try {
            String firstNameToCheck = markFNameTextField.getText();
            String lastNameToCheck = markLNameTextField.getText();
            String studentIdToCheck = markStuIdTextField.getText();
            String clubNameToCheck = markClubNameTextField.getText();

            checkInstance.checkRecord(firstNameToCheck,lastNameToCheck,studentIdToCheck,clubNameToCheck);
            if (Check.found){
                submitButton.setDisable(false);
            }

            // Clear the input fields
            reset();

        } catch (NumberFormatException e) {
            // Display an error error for number format exception
            showErrorAlert("Please enter a valid number for numeric fields.");
        }
    }

    @FXML
    private void Updatestatus(){
        // Instantiate the update class
        Update updateInstance = new Update();
        try {
            String status_U = null;
            if (absentRadioButton.isSelected()) {
                status_U = "Absent";
                presentRadioButton.setSelected(false);
                pendingRadioButton.setSelected(false);
            } else if (presentRadioButton.isSelected()) {
                status_U = "Present";
                absentRadioButton.setSelected(false);
                pendingRadioButton.setSelected(false);
            } else if (pendingRadioButton.isSelected()) {
                status_U = "Pending";
                absentRadioButton.setSelected(false);
                presentRadioButton.setSelected(false);
            }

            String markFName_U = markFNameTextField.getText();
            String markLName_U = markLNameTextField.getText();
            String markStuId_U = markStuIdTextField.getText();
            String markStuClubName_U = markClubNameTextField.getText();
            String S_selectedEvent_U = Eventlist.getValue();

            updateInstance.handleUpdaterecord(status_U, markFName_U ,markLName_U , markStuId_U, markStuClubName_U, S_selectedEvent_U);

            // Clear the input fields
            markFNameTextField.clear();
            markLNameTextField.clear();
            markStuIdTextField.clear();
            markClubNameTextField.clear();
            absentRadioButton.setSelected(false);
            presentRadioButton.setSelected(false);
            pendingRadioButton.setSelected(false);
            Eventlist.getSelectionModel().clearSelection();

            // Clear the input fields
            reset();

        }catch (NumberFormatException e) {
            // Display an error label for number format exception
            showErrorAlert("Please enter a valid number for numeric fields.");
        }
    }

    // Event handler for the SUBMIT button
    @FXML
    private void T_handleSubmitButton() {
        // Instantiate the teacher's submit class
        Submit_Teacher Tea_submitInstance = new Submit_Teacher();
        try {
            String T_FmarkName = markT_FNameTextField.getText();
            String T_LmarkName = markT_LNameTextField.getText();
            String markTeaId = markTeaIdTextField.getText();
            String markTeaClubName = markTeaClubNameTextField.getText();
            String selectedEvent_2 = T_Eventlist.getValue();
            String T_status = null;
            if (T_absentRadioButton.isSelected()) {
                T_status = "Absent";
                T_presentRadioButton.setSelected(false);
                T_pendingRadioButton.setSelected(false);
            } else if (T_presentRadioButton.isSelected()) {
                T_status = "Present";
                T_absentRadioButton.setSelected(false);
                T_pendingRadioButton.setSelected(false);
            } else if (T_pendingRadioButton.isSelected()) {
                T_status = "Pending";
                T_absentRadioButton.setSelected(false);
                T_presentRadioButton.setSelected(false);
            }

            Tea_submitInstance.handleSubmitTea_record( T_FmarkName,T_LmarkName, markTeaId, markTeaClubName, selectedEvent_2, T_status);

            // Clear the input fields
            markT_FNameTextField.clear();
            markT_LNameTextField.clear();
            markTeaIdTextField.clear();
            markTeaClubNameTextField.clear();
            T_absentRadioButton.setSelected(false);
            T_presentRadioButton.setSelected(false);
            T_pendingRadioButton.setSelected(false);
            T_Eventlist.getSelectionModel().clearSelection();

            T_submitButton.setDisable(true);

            // Clear the input fields
            reset();

        } catch (NumberFormatException e) {
            // Display an error alert for number format exception
            showErrorAlert("Please enter a valid number for numeric fields.");
        }
    }

    @FXML
    private void T_handleCheckButton() {
        Check.found = false;
        // Instantiate the teacher's check class
        Check_Teacher Tea_checkInstance = new Check_Teacher();
        try {
            String T_FnameToCheck = markT_FNameTextField.getText();
            String T_LnameToCheck = markT_LNameTextField.getText();
            String teacherIdToCheck = markTeaIdTextField.getText();
            String T_clubNameToCheck = markTeaClubNameTextField.getText();

            Tea_checkInstance.handleCheckTea_record(T_FnameToCheck, T_LnameToCheck, teacherIdToCheck, T_clubNameToCheck);
            if (Check.found){
                T_submitButton.setDisable(false);
            }

            // Clear the input fields
            reset();

        } catch (NumberFormatException e) {
            // Display an error label for number format exception
            showErrorAlert("Please enter a valid number for numeric fields.");
        }
    }

    @FXML
    private void T_Updatestatus(){
        // Instantiate the teacher's update class
        Update_Teacher Tea_updateInstance = new Update_Teacher();
        try {
            String T_status_U = null;
            if (T_absentRadioButton.isSelected()) {
                T_status_U = "Absent";
                T_presentRadioButton.setSelected(false);
                T_pendingRadioButton.setSelected(false);
            } else if (T_presentRadioButton.isSelected()) {
                T_status_U = "Present";
                T_absentRadioButton.setSelected(false);
                T_pendingRadioButton.setSelected(false);
            } else if (T_pendingRadioButton.isSelected()) {
                T_status_U = "Pending";
                T_absentRadioButton.setSelected(false);
                T_presentRadioButton.setSelected(false);
            }

            String T_FmarkName_U = markT_FNameTextField.getText();
            String T_LmarkName_U = markT_LNameTextField.getText();
            String markTeaId_U = markTeaIdTextField.getText();
            String markTeaClubName_U = markTeaClubNameTextField.getText();
            String selectedEvent_U = T_Eventlist.getValue();

            Tea_updateInstance.handleUpdateTea_record(T_status_U, T_FmarkName_U, T_LmarkName_U,  markTeaId_U, markTeaClubName_U, selectedEvent_U);

            // Clear the input fields
            markT_FNameTextField.clear();
            markT_LNameTextField.clear();
            markTeaIdTextField.clear();
            markTeaClubNameTextField.clear();
            T_absentRadioButton.setSelected(false);
            T_presentRadioButton.setSelected(false);
            T_pendingRadioButton.setSelected(false);
            T_Eventlist.getSelectionModel().clearSelection();

            // Clear the input fields
            reset();

        }catch (NumberFormatException e) {
            // Display an error label for number format exception
            showErrorAlert("Please enter a valid number for numeric fields.");
        }
    }
    @FXML
    private void showEventStatusChart() {
        // Instantiate the student's chart class
        Chart Stu_chartInstance = new Chart();
        String selectedEvent = eventComboBox.getValue();
        if (selectedEvent == null || selectedEvent.isEmpty()) {
            // Handle the case where no event is selected
            showErrorAlert("Please select an event.");
            return;
        }
        ObservableList<XYChart.Data<String, Number>> absentData = FXCollections.observableArrayList();
        ObservableList<XYChart.Data<String, Number>> presentData = FXCollections.observableArrayList();

        Stu_chartInstance.handleStu_Chart(selectedEvent,absentData,presentData);

        XYChart.Series<String, Number> absentSeries = new XYChart.Series<>("Absent", absentData);
        XYChart.Series<String, Number> presentSeries = new XYChart.Series<>("Present", presentData);

        attendanceRchart.getData().clear(); // Clear previous data
        attendanceRchart.getData().addAll(absentSeries, presentSeries);
    }

    public void showEventStatusChart_T() {
        // Instantiate the teacher's chart class
        Chart_Teacher Tea_chartInstance = new Chart_Teacher();
        String selectedEvent = T_eventComboBox.getValue();
        if (selectedEvent == null || selectedEvent.isEmpty()) {
            // Handle the case where no event is selected
            showErrorAlert("Please select an event.");
            return;
        }
        ObservableList<XYChart.Data<String, Number>> absentData = FXCollections.observableArrayList();
        ObservableList<XYChart.Data<String, Number>> presentData = FXCollections.observableArrayList();

        Tea_chartInstance.handleTea_Chart(selectedEvent,absentData,presentData);

        XYChart.Series<String, Number> absentSeries = new XYChart.Series<>("Absent", absentData);
        XYChart.Series<String, Number> presentSeries = new XYChart.Series<>("Present", presentData);

        attendanceRchart_T.getData().clear(); // Clear previous data
        attendanceRchart_T.getData().addAll(absentSeries, presentSeries);
    }

    @FXML
    private void exportToCsv() {
        Export_Student.exportCSV_stu("student_attendance");
    }
    @FXML
    private void exportToCsv_T() {
        Export_Teacher.exportCSV_tea("teacher_attendance");
    }
    @FXML
    private void reset(){
        deleteFNameTextField.clear();
        deleteLNameTextField.clear();
        deleteStuIdTextField.clear();
        deleteClubNameTextField.clear();
        deltablelist.getSelectionModel().clearSelection();
        markFNameTextField.clear();
        markLNameTextField.clear();
        markStuIdTextField.clear();
        markClubNameTextField.clear();
        absentRadioButton.setSelected(false);
        presentRadioButton.setSelected(false);
        pendingRadioButton.setSelected(false);
        Eventlist.getSelectionModel().clearSelection();
        markT_FNameTextField.clear();
        markT_LNameTextField.clear();
        markTeaIdTextField.clear();
        markTeaClubNameTextField.clear();
        T_absentRadioButton.setSelected(false);
        T_presentRadioButton.setSelected(false);
        T_pendingRadioButton.setSelected(false);
        T_Eventlist.getSelectionModel().clearSelection();
    }
    @FXML
    private void exit() {
        // Create a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Exit Application");
        alert.setContentText("Are you sure you want to exit?");

        // Show the confirmation dialog and wait for user's response
        Optional<ButtonType> result = alert.showAndWait();

        // Check if the user clicked OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // If yes, exit the application
            Platform.exit();
        }
    }

    private void showErrorAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    @FXML
    private void backButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("view-members.fxml"));
        Stage newStage = new Stage();
        Scene newScene = new Scene(fxmlLoader.load(), 950, 600);
        newStage.setTitle("View Members");
        newStage.setScene(newScene);
        newStage.show();

        Node node = (Node) actionEvent.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }
}



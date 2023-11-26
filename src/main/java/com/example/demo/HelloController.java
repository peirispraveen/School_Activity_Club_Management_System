package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

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
    private TextField deleteNameTextField;

    @FXML
    private TextField deleteStuIdTextField;

    @FXML
    private TextField deleteClubNameTextField;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField markNameTextField;

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
    private TextField markT_NameTextField;

    @FXML
    private TextField markTeaIdTextField;

    @FXML
    private TextField markTeaClubNameTextField;

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

    public void initialize(){
        ObservableList<String> list = FXCollections.observableArrayList("Event_01", "Event_02", "Event_03");
        Eventlist.setItems(list);
        ObservableList<String> list_2 = FXCollections.observableArrayList("Event_01", "Event_02", "Event_03");
        T_Eventlist.setItems(list_2);
        ObservableList<String> eventList = FXCollections.observableArrayList("Event_01", "Event_02", "Event_03");
        eventComboBox.setItems(eventList);
        ObservableList<String> eventList_2 = FXCollections.observableArrayList("Event_01", "Event_02", "Event_03");
        T_eventComboBox.setItems(eventList_2);
    }

    // Event handler for the DELETE button
    @FXML
    private void handleDeleteButton() {
        // Instantiate the delete class
        Delete deleteInstance = new Delete();

        try {
            String deleteName = deleteNameTextField.getText();
            String deleteStudentId = deleteStuIdTextField.getText();
            String deleteNameofClub = deleteClubNameTextField.getText();

            // Call the delete class method
            deleteInstance.deleteRecord(deleteName, deleteStudentId, deleteNameofClub);

            // Clear the input fields
            deleteNameTextField.clear();
            deleteStuIdTextField.clear();
            deleteClubNameTextField.clear();

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
            String Name = markNameTextField.getText();
            String markStudentId = markStuIdTextField.getText();
            String markClubName = markClubNameTextField.getText();
            ObservableList<String> list = FXCollections.observableArrayList("Event_01", "Event_02", "Event_03");
            Eventlist.setItems(list);
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

            submitInstance.handleSubmitrecord(Name, markStudentId, markClubName,selectedEvent,status);

            // Clear the input fields
            markNameTextField.clear();
            markStuIdTextField.clear();
            markClubNameTextField.clear();
            absentRadioButton.setSelected(false);
            presentRadioButton.setSelected(false);
            pendingRadioButton.setSelected(false);
            Eventlist.getSelectionModel().clearSelection();


        } catch (NumberFormatException e) {
            // Display an error alert for number format exception
            showErrorAlert("Please enter a valid number for numeric fields.");
        }
    }

    @FXML
    private void handleCheckButton() {
        // Instantiate the check class
        Check checkInstance = new Check();
        try {
            String nameToCheck = markNameTextField.getText();
            String studentIdToCheck = markStuIdTextField.getText();
            String clubNameToCheck = markClubNameTextField.getText();

            checkInstance.handleCheckrecord(nameToCheck, studentIdToCheck, clubNameToCheck);

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

            String markName_U = markNameTextField.getText();
            String markStuId_U = markStuIdTextField.getText();
            String markStuClubName_U = markClubNameTextField.getText();
            String S_selectedEvent_U = Eventlist.getValue();

            updateInstance.handleUpdaterecord(status_U, markName_U, markStuId_U, markStuClubName_U, S_selectedEvent_U);

            // Clear the input fields
            markNameTextField.clear();
            markStuIdTextField.clear();
            markClubNameTextField.clear();
            absentRadioButton.setSelected(false);
            presentRadioButton.setSelected(false);
            pendingRadioButton.setSelected(false);
            Eventlist.getSelectionModel().clearSelection();

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
            String T_markName = markT_NameTextField.getText();
            String markTeaId = markTeaIdTextField.getText();
            String markTeaClubName = markTeaClubNameTextField.getText();
            ObservableList<String> list_2 = FXCollections.observableArrayList("Event_01", "Event_02", "Event_03");
            T_Eventlist.setItems(list_2);
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

            Tea_submitInstance.handleSubmitTea_record( T_markName, markTeaId, markTeaClubName, selectedEvent_2, T_status);

            // Clear the input fields
            markT_NameTextField.clear();
            markTeaIdTextField.clear();
            markTeaClubNameTextField.clear();
            T_absentRadioButton.setSelected(false);
            T_presentRadioButton.setSelected(false);
            T_pendingRadioButton.setSelected(false);
            T_Eventlist.getSelectionModel().clearSelection();

        } catch (NumberFormatException e) {
            // Display an error alert for number format exception
            showErrorAlert("Please enter a valid number for numeric fields.");
        }
    }

    @FXML
    private void T_handleCheckButton() {
        // Instantiate the teacher's check class
        Check_Teacher Tea_checkInstance = new Check_Teacher();
        try {
            String T_nameToCheck = markT_NameTextField.getText();
            String teacherIdToCheck = markTeaIdTextField.getText();
            String T_clubNameToCheck = markTeaClubNameTextField.getText();

            Tea_checkInstance.handleCheckTea_record(T_nameToCheck, teacherIdToCheck, T_clubNameToCheck);

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

            String T_markName_U = markT_NameTextField.getText();
            String markTeaId_U = markTeaIdTextField.getText();
            String markTeaClubName_U = markTeaClubNameTextField.getText();
            String selectedEvent_U = T_Eventlist.getValue();

            Tea_updateInstance.handleUpdateTea_record(T_status_U, T_markName_U, markTeaId_U, markTeaClubName_U, selectedEvent_U);

            // Clear the input fields
            markT_NameTextField.clear();
            markTeaIdTextField.clear();
            markTeaClubNameTextField.clear();
            T_absentRadioButton.setSelected(false);
            T_presentRadioButton.setSelected(false);
            T_pendingRadioButton.setSelected(false);
            T_Eventlist.getSelectionModel().clearSelection();

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

    private void showErrorAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}



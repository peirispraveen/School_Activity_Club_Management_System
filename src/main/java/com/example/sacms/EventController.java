package com.example.sacms;

import com.example.EventScheduling.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class EventController
{

    // attributes to connect with the database
    private static final String url = "jdbc:mysql://localhost:3306/SACMS";
    private static final String user = "root";
    private static final String password = "";

    // access fx elements
    @FXML
    private TextField clubID;
    @FXML
    private TextField eventID;
    @FXML
    private TextField eventType;
    @FXML
    private TextField eventYear;
    @FXML
    private TextField eventMonth;
    @FXML
    private TextField eventDay;
    @FXML
    private TextField startHour;
    @FXML
    private TextField startMin;
    @FXML
    private TextField endHour;
    @FXML
    private TextField endMin;
    @FXML
    private TextField eventPlace;
    @FXML
    private TextField eventPlatform;
    @FXML
    private TextField eventLink;
    @FXML
    private TextField meetingNum;
    @FXML
    private TextField eventName;
    @FXML
    private TextField activityNo;
    @FXML
    private Button resetButton;
    @FXML
    private TextField filePath;
    @FXML
    private Button submitButton;
    @FXML
    private Button generateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button postponeButton;

    // attributes to store validation times
    private int validPoints;
    @FXML
    private Button backButtonY;
    @FXML
    private AnchorPane rumethAnchor;
    @FXML
    private Button downloadButton;

    // connect with the database
    public static Connection connectDB() throws Exception
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    // disable the reset button
    public void setRestButtonDisable()
    {
        resetButton.setDisable(true);
        submitButton.setDisable(true);
    }

    // when any input changed enable reset button
    @FXML
    public void onInputTextChanged()
    {
        resetButton.setDisable(false);
        submitButton.setDisable(false);
    }

    // clearing inputs when reset buttons are trigerred
    @FXML
    public void onClickEventViewResetButton()
    {
        eventID.clear();
        filePath.clear();
        resetButton.setDisable(true);
        deleteButton.setDisable(true);
        postponeButton.setDisable(true);
        generateButton.setDisable(true);
    }

    public void clearCommonInputs()
    {
        clubID.clear();
        eventID.clear();
        eventYear.clear();
        eventMonth.clear();
        eventDay.clear();
        startHour.clear();
        startMin.clear();
        endHour.clear();
        endMin.clear();
    }
    @FXML
    public void onClickCreateActivityResetButton()
    {
        clearCommonInputs();
        activityNo.clear();
        eventName.clear();
        eventType.clear();
        eventLink.clear();
        setRestButtonDisable();
    }

    @FXML
    public void onClickCreateEventResetButton()
    {
        clearCommonInputs();
        eventName.clear();
        eventPlace.clear();
        setRestButtonDisable();
    }

    @FXML
    public void onClickCreateMeetingResetButton()
    {
        clearCommonInputs();
        meetingNum.clear();
        eventPlatform.clear();
        eventLink.clear();
        setRestButtonDisable();
    }

    @FXML
    public void onInputFilePathChanged()
    {
        resetButton.setDisable(false);
        generateButton.setDisable(false);
    }

    @FXML
    public void onClickPostponeEventResetButton()
    {
        clearCommonInputs();
        setRestButtonDisable();
    }

    @FXML
    public void onUpdateTextChanged()
    {
        postponeButton.setDisable(false);
        deleteButton.setDisable(false);
        resetButton.setDisable(false);
    }
    @FXML
    public void onClickAddEventButton(ActionEvent e) throws Exception
    {
        // creating new stage
        Stage newStage = new Stage();
        // capturing previous stage
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // set owner of the new stage to previous stage
        newStage.initOwner(previousStage);
        // loading ui
        Parent root = FXMLLoader.load(getClass().getResource("event-type-selection-ui.fxml"));
        // initializing scene
        Scene scene = new Scene(root, 400, 73);
        // set the scene to new stage
        newStage.setScene(scene);
        // restricting resize
        newStage.setResizable(false);
        newStage.setTitle("Please select the event type");
        // showing the stage in the middle of the screen
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        newStage.setX((primaryScreenBounds.getWidth() - scene.getWidth()) / 2);
        newStage.setY((primaryScreenBounds.getHeight() - scene.getHeight()) / 2);
        newStage.show();
    }

    // exit method for the program
    @FXML
    public void onClickExitButton()
    {
        System.exit(0);
    }

    // view events in a table
    @FXML
    public static void onClickViewEventButton(ActionEvent e) throws Exception
    {
        // capturing previous stage
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // loading fxml file
        Parent root = FXMLLoader.load(EventController.class.getResource("event-view-ui.fxml"));
        // capturing table and columns
        TableView tableView = (TableView<EventView>) root.lookup("#eventTableView");
        TableColumn<EventView, String> column1 = (TableColumn<EventView, String>) tableView.getColumns().get(0);
        TableColumn<EventView, String> column2 = (TableColumn<EventView, String>) tableView.getColumns().get(1);
        TableColumn<EventView, String> column3 = (TableColumn<EventView, String>) tableView.getColumns().get(2);
        TableColumn<EventView, String> column4 = (TableColumn<EventView, String>) tableView.getColumns().get(3);

        // setting cell value factors
        column1.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        column2.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        column3.setCellValueFactory(new PropertyValueFactory<>("type"));
        column4.setCellValueFactory(new PropertyValueFactory<>("date"));

        // observable list to store items that needed to be shown in the tbale
        ObservableList<EventView> obList = FXCollections.observableArrayList();
        // connect to database
        Statement statement = connectDB().createStatement();
        // queries to be executed
        String query1 = "select event_id, club_id, event_date from EventParent";
        String query2 = "select event_id from Meeting";
        String query3 = "select event_id from EventType";
        String query4 = "select event_id from Activity";
        // storing query1 results
        ResultSet resultSet1 = statement.executeQuery(query1);
        // 2D array list to store resultSet1
        ArrayList<ArrayList<String>> eventParent = new ArrayList<>();
        while (resultSet1.next()) {
            eventParent.add(new ArrayList<>(Arrays.asList(resultSet1.getString(1),
                    resultSet1.getString(2), resultSet1.getString(3))));
        }
        // closing resultSet1
        resultSet1.close();
        // execute query2
        ResultSet resultSet2 = statement.executeQuery(query2);
        // array list to store event_id in Meeting
        ArrayList<String> meeting = new ArrayList<>();
        while (resultSet2.next()) {
            meeting.add(resultSet2.getString(1));
        }
        // closing resultSet2
        resultSet2.close();
        // executing query3
        ResultSet resultSet3 = statement.executeQuery(query3);
        // arraylist to store event ids in EventParent
        ArrayList<String> event = new ArrayList<>();
        while (resultSet3.next()) {
            event.add(resultSet3.getString(1));
        }
        // closing resultSet3
        resultSet3.close();
        // executing query4
        ResultSet resultSet4 = statement.executeQuery(query4);
        // storing event_id in Activity
        ArrayList<String> activity = new ArrayList<>();
        while (resultSet4.next()) {
            activity.add(resultSet4.getString(1));
        }
        // closing resultSet4
        resultSet4.close();
        // closing connection4
        connectDB().close();
        // travel through array lists and find the type of the event
        for (ArrayList<String> strings : eventParent)
        {
            String type = "";
            if (type == "")
            {
                for (String s : meeting)
                {
                    if (strings.get(0).equals(s))
                    {
                        type = "meeting";
                        break;
                    }
                }
            }
            if (type == "")
            {
                for (String s : event)
                {
                    if (strings.get(0).equals(s))
                    {
                        type = "event";
                        break;
                    }
                }
            }
            if (type == "")
            {
                for (String s : activity)
                {
                    if (strings.get(0).equals(s))
                    {
                        type = "activity";
                        break;
                    }
                }
            }
            // if type found adding the event details to the observable list
            if (type != "") {
                EventView eventValue = new EventView(strings.get(0), strings.get(1), type, strings.get(2));
                obList.add(eventValue);
            }
        }
        // setting the observable list in the table
        tableView.setItems(obList);
        // initializing the scene
        Scene scene = new Scene(root, 950, 600);
        //setting the scene to the stage
        stage.setScene(scene);
        // showing stage
        stage.show();
    }

    // when Meeting button is trigered when creating an event
    @FXML
    public void onClickEventMeetingButton(ActionEvent e) throws Exception
    {
        // capturing current stage
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // capturing the owner of the current stage
        Stage previousStage = (Stage) currentStage.getOwner();
        // closing current stage
        currentStage.close();
        // loading ui
        Parent root = FXMLLoader.load(getClass().getResource("create-meeting-ui.fxml"));
        // intitializing new scene and setting it to previous stage
        previousStage.setScene(new Scene(root, 800, 600));
        // showing the previous stage
        previousStage.show();
    }

    // creating a new meeting
    @FXML
    public void onClickCreateMeetingButton(ActionEvent e) throws Exception
    {
        // calling the meeting class
        Meeting newMeeting = new Meeting(clubID.getText(), eventID.getText(), eventYear.getText(), eventMonth.getText(),
                eventDay.getText(), startHour.getText(), startMin.getText(), endHour.getText(), endMin.getText(),
                meetingNum.getText(), eventPlatform.getText(), eventLink.getText());
        newMeeting.createEvent();
        // initialising array in the meeting class
        newMeeting.values = new String[8];
        // setting validation times to 0
        validPoints = 0;
        // connect with the databse
        Statement statement = connectDB().createStatement();
        // if club id is valid
        if (EventValidator.isValidClubID())
        {
            // result set to store club_id
            ResultSet dbClubResult = statement.executeQuery("select club_id from Club");
            boolean dbClubIDMatch = false;
            // find if the club_id exists
            while (dbClubResult.next())
            {
                if (dbClubResult.getString(1).equals(clubID.getText()))
                {
                    newMeeting.values[0] = clubID.getText();
                    validPoints++;
                    dbClubIDMatch = true;
                    System.out.println("\033[0;34m[A]ClubID accepted\033[0m");
                }
            }
            // close the resultset
            dbClubResult.close();
            // is club_id does not exists
            if (!dbClubIDMatch)
            {
                System.out.println("\033[0;31m[E]Activity number rejected\033[0m");
                clubID.clear();
                clubID.setPromptText("IN");
                clubID.setStyle("-fx-prompt-text-fill: #b22222");
            }
        }
        else
        {
            System.out.println("\033[0;31m[E]ClubID rejected\033[0m");
            clubID.clear();
            clubID.setPromptText("IN");
            clubID.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate event_id
        if (EventValidator.isValidEventID())
        {
            boolean dbEventIDMatch = false;
            // resultset to store event_id
            ResultSet dbResult = statement.executeQuery("select event_id from EventParent");
            // find if the event_id exist
            while (dbResult.next()) {
                if (dbResult.getString(1).equals(eventID.getText())) {
                    dbEventIDMatch = true;
                }
            }
            // close resultset
            dbResult.close();
            // if event_id does not exist
            if (dbEventIDMatch)
            {
                System.out.println("\033[0;31m[E]EventID rejected\033[0m");
                eventID.clear();
                eventID.setPromptText("IN");
                eventID.setStyle("-fx-prompt-text-fill: #b22222");
            }
            // if event_id exist
            else
            {
                System.out.println("\033[0;34m[A]EventID accepted\033[0m");
                newMeeting.values[1] = eventID.getText();
                validPoints++;
            }
        }
        // if event_id is invalid
        else
        {
            System.out.println("\033[0;31m[E]EventID rejected\033[0m");
            eventID.clear();
            eventID.setPromptText("IN");
            eventID.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate ddate
        if (EventValidator.isValidYear() && EventValidator.isValidMonth() && EventValidator.isValidDay())
        {
            newMeeting.values[2] = eventYear.getText() + "/" + eventMonth.getText() + "/" + eventDay.getText();
            validPoints++;
            System.out.println("\033[0;34m[A]Date accepted\033[0m");
        }
        // if date is invalid
        else
        {
            System.out.println("\033[0;31m[E]Date rejected\033[0m");
            eventYear.clear();
            eventYear.setPromptText("IN");
            eventYear.setStyle("-fx-prompt-text-fill: #b22222");
            eventMonth.clear();
            eventMonth.setPromptText("IN");
            eventMonth.setStyle("-fx-prompt-text-fill: #b22222");
            eventDay.clear();
            eventDay.setPromptText("IN");
            eventDay.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate start_time
        if (EventValidator.isValidStartHour() && EventValidator.isValidStartMin())
        {
            newMeeting.values[3] = startHour.getText() + ":" + startMin.getText();
            validPoints++;
            System.out.println("\033[0;34m[A]StartTime accepted\033[0m");
        }
        // is start_time invalid
        else
        {
            System.out.println("\033[0;31m[E]StartTime rejected\033[0m");
            startHour.clear();
            startHour.setPromptText("IN");
            startHour.setStyle("-fx-prompt-text-fill: #b22222");
            startMin.clear();
            startMin.setPromptText("IN");
            startMin.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate end_time
        if (EventValidator.isValidEndHour() && EventValidator.isValidEndMin())
        {
            if (Integer.parseInt(startHour.getText()) < Integer.parseInt(endHour.getText()) ||
                    (Integer.parseInt(startHour.getText()) == Integer.parseInt(endHour.getText()) &&
                            (Integer.parseInt(startMin.getText()) < Integer.parseInt(endMin.getText()))))
            {
                newMeeting.values[4] = endHour.getText() + ":" + endMin.getText();
                System.out.println("\033[0;34m[A]EndTime accepted\033[0m");
                validPoints++;
            }
            // if end_time is invalid
            else
            {
                System.out.println("\033[0;31m[E]EndTime rejected\033[0m");
                endHour.clear();
                endHour.setPromptText("IN");
                endHour.setStyle("-fx-prompt-text-fill: #b22222");
                endMin.clear();
                endHour.setPromptText("IN");
                endHour.setStyle("-fx-prompt-text-fill: #b22222");
            }
        }
        // if end time is invalid
        else
        {
            System.out.println("\033[0;31m[E]EndTime rejected\033[0m");
            endHour.clear();
            endHour.setPromptText("IN");
            endHour.setStyle("-fx-prompt-text-fill: #b22222");
            endMin.clear();
            endMin.setPromptText("IN");
            endMin.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate meeting_no
        if (EventValidator.isValidNum())
        {
            boolean dbMeetingNoMatch = false;
            // result set to store meeting numbers
            ResultSet dbResult = statement.executeQuery("select meeting_no from Meeting");
            // find if meeting_no exist
            while (dbResult.next()) {
                if (dbResult.getString(1).equals(meetingNum.getText()))
                {
                    dbMeetingNoMatch = true;
                }

            }
            // close resultset
            dbResult.close();
            // is the meeting_no exist
            if (dbMeetingNoMatch)
            {
                System.out.println("\033[0;31m[E]Meeting Number rejected\033[0m");
                meetingNum.clear();
                meetingNum.setPromptText("IN");
                meetingNum.setStyle("-fx-prompt-text-fill: #b22222");
            }
            // if the meeting_no does not exist
            else
            {
                System.out.println("\033[0;34m[A]Meeting Number accepted\033[0m");
                newMeeting.values[5] = meetingNum.getText();
                validPoints++;
            }
        }
        // if the meeting_no is invalid
        else
        {
            System.out.println("\033[0;31m[E]Meeting Number rejected\033[0m");
            meetingNum.clear();
            meetingNum.setPromptText("IN");
            meetingNum.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate platform
        if (EventValidator.isValidPlatform())
        {
            System.out.println("\033[0;34m[A]Platform accepted\033[0m");
            newMeeting.values[6] = eventPlatform.getText();
            validPoints++;
        }
        // if platform is invalid
        else
        {
            System.out.println("\033[0;31m[E]Platform rejected\033[0m");
            eventPlatform.clear();
            eventPlatform.setPromptText("IN");
            eventPlatform.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate link
        if (EventValidator.isValidLink())
        {
            System.out.println("\033[0;34m[A]Link accepted\033[0m");
            newMeeting.values[7] = eventLink.getText();
            validPoints++;
        }
        // if link is invalid
        else
        {
            System.out.println("\033[0;31m[E]Link rejected\033[0m");
            eventLink.clear();
            eventLink.setPromptText("IN");
            eventLink.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // if all validation checks passed
        if (validPoints == 8)
        {
            // updating EventParent table
            String query1 = "INSERT INTO EventParent VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement1 = connectDB().prepareStatement(query1);
            for (int i = 0; i < 4; i++) {
                preparedStatement1.setString(i + 1, newMeeting.values[i + 1]);
            }
            // updating Meeting table
            preparedStatement1.setString(5, newMeeting.values[0]);
            preparedStatement1.executeUpdate();
            String query2 = "INSERT INTO Meeting VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement2 = connectDB().prepareStatement(query2);
            preparedStatement2.setString(1, newMeeting.values[1]);
            for (int i = 0; i < 3; i++) {
                preparedStatement2.setString(i + 2, newMeeting.values[i + 5]);
            }
            preparedStatement2.executeUpdate();
            actionCompleted(e);
            System.out.println("\033[0;34m[A]Action completed\033[0m");
            System.out.println();
            // clear all inputs
            onClickViewEventButton(e);
        }
        // if validation check failed
        else
        {
            System.out.println("\033[0;31m[E]Action rejected\033[0m");
            System.out.println();
            invalidInformation(e);
            setRestButtonDisable();
        }
        // close the connection with database
        connectDB().close();
    }

    public void invalidInformation(ActionEvent e) throws Exception
    {
        // creating new stage
        Stage newStage = new Stage();
        // capturing previous stage
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // setting the owner of new stage as previous stage
        newStage.initOwner(previousStage);
        // loading ui
        Parent root = FXMLLoader.load(getClass().getResource("event-validation-failed.fxml"));
        // initializing new scene
        Scene scene = new Scene(root, 400, 73);
        // setting scene to new stage
        newStage.setScene(scene);
        // restricting resize
        newStage.setResizable(false);
        // show new stage in the center of screen
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        newStage.setX((primaryScreenBounds.getWidth() - scene.getWidth()) / 2);
        newStage.setY((primaryScreenBounds.getHeight() - scene.getHeight()) / 2);
        newStage.show();
    }

    public void actionCompleted(ActionEvent e) throws Exception
    {
        // creating a new stage
        Stage newStage = new Stage();
        // capturing previous stage
        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // set new stage owner as previous stage
        newStage.initOwner(previousStage);
        // load ui
        Parent root = FXMLLoader.load(getClass().getResource("successful-ui.fxml"));
        // initialize new scene
        Scene scene = new Scene(root, 400, 73);
        // set scene to new stage
        newStage.setScene(scene);
        // restricting to resize
        newStage.setResizable(false);
        // show the new stage in the center of the screen
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        newStage.setX((primaryScreenBounds.getWidth() - scene.getWidth()) / 2);
        newStage.setY((primaryScreenBounds.getHeight() - scene.getHeight()) / 2);
        newStage.show();
    }
    // event button triggerred as new event
    @FXML
    public void onClickEventButton(ActionEvent e) throws Exception
    {
        // capturing current stage
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // capturing previous stage
        Stage previousStage = (Stage) currentStage.getOwner();
        // close current stage
        currentStage.close();
        // load ui
        Parent root = FXMLLoader.load(getClass().getResource("create-event-ui.fxml"));
        // initializing neew scene and setting it to previous stage
        previousStage.setScene(new Scene(root, 800, 600));
        // show previous stage
        previousStage.show();
    }

    // create new event
    @FXML
    public void onClickCreateEventButton(ActionEvent e) throws Exception
    {
        // calling Event class
        Event newEvent = new Event(clubID.getText(), eventID.getText(), eventYear.getText(), eventMonth.getText(),
                eventDay.getText(), startHour.getText(), startMin.getText(), endHour.getText(), endMin.getText(),
                eventName.getText(), eventPlace.getText());
        newEvent.createEvent();
        // initializing values array
        newEvent.values = new String[7];
        // set validation times to 0
        validPoints = 0;
        Statement statement = connectDB().createStatement();
        // validate the club_id
        if (EventValidator.isValidClubID())
        {
            // resultset to store club_id
            ResultSet dbClubResult = statement.executeQuery("select club_id from Club");
            boolean dbClubIDMatch = false;
            // check is the club_id exist
            while (dbClubResult.next()) {
                if (dbClubResult.getString(1).equals(clubID.getText()))
                {
                    newEvent.values[0] = clubID.getText();
                    validPoints++;
                    dbClubIDMatch = true;
                    System.out.println("\033[0;34m[A]ClubID accepted\033[0m");
                }
            }
            // close resultset
            dbClubResult.close();
            // if the club_id does not exist
            if (!dbClubIDMatch)
            {
                System.out.println("\033[0;31m[E]ClubID rejected\033[0m");
                clubID.clear();
                clubID.setPromptText("IN");
                clubID.setStyle("-fx-prompt-text-fill: #b22222");
            }
        }
        // if the club_id not valid
        else
        {
            System.out.println("\033[0;31m[E]ClubID rejected\033[0m");
            clubID.clear();
            clubID.setPromptText("IN");
            clubID.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate the event_id
        if (EventValidator.isValidEventID())
        {
            boolean dbEventIDMatch = false;
            // resultset to store event_id
            ResultSet dbResult = statement.executeQuery("select event_id from EventParent");
            // check if the event_id exist
            while (dbResult.next()) {
                if (dbResult.getString(1).equals(eventID.getText())) {
                    dbEventIDMatch = true;
                }
            }
            // close resultset
            dbResult.close();
            //if the event_id exist
            if (dbEventIDMatch)
            {
                System.out.println("\033[0;31m[E]EventID rejected\033[0m");
                eventID.clear();
                eventID.setPromptText("IN");
                eventID.setStyle("-fx-prompt-text-fill: #b22222");
            }
            // if the event_id does not exist
            else
            {
                newEvent.values[1] = eventID.getText();
                validPoints++;
                System.out.println("\033[0;34m[A]EventID accepted\033[0m");
            }
        }
        // if the event_id is invalid
        else
        {
            System.out.println("\033[0;31m[E]EventID rejected\033[0m");
            eventID.clear();
            eventID.setPromptText("IN");
            eventID.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate date
        if (EventValidator.isValidYear() && EventValidator.isValidMonth() && EventValidator.isValidDay())
        {
            newEvent.values[2] = eventYear.getText() + "/" + eventMonth.getText() + "/" + eventDay.getText();
            validPoints++;
            System.out.println("\033[0;34m[A]Date rejected\033[0m");
        }
        // if the date is invalid
        else
        {
            System.out.println("\033[0;31m[E]Date rejected\033[0m");
            eventYear.clear();
            eventYear.setPromptText("IN");
            eventYear.setStyle("-fx-prompt-text-fill: #b22222");
            eventMonth.clear();
            eventMonth.setPromptText("IN");
            eventMonth.setStyle("-fx-prompt-text-fill: #b22222");
            eventDay.clear();
            eventDay.setPromptText("IN");
            eventDay.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate start_time
        if (EventValidator.isValidStartHour() && EventValidator.isValidStartMin())
        {
            System.out.println("\033[0;34m[A]StartHour accepted\033[0m");
            newEvent.values[3] = startHour.getText() + ":" + startMin.getText();
            validPoints++;
        }
        // if the start_time is invalid
        else
        {
            System.out.println("\033[0;31m[E]StartHour rejected\033[0m");
            startHour.clear();
            startHour.setPromptText("IN");
            startHour.setStyle("-fx-prompt-text-fill: #b22222");
            startMin.clear();
            startMin.setPromptText("IN");
            startMin.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate end_time
        if (EventValidator.isValidEndHour() && EventValidator.isValidEndMin())
        {
            if (Integer.parseInt(startHour.getText()) < Integer.parseInt(endHour.getText()) ||
                    (Integer.parseInt(startHour.getText()) == Integer.parseInt(endHour.getText()) &&
                            (Integer.parseInt(startMin.getText()) < Integer.parseInt(endMin.getText()))))
            {
                newEvent.values[4] = endHour.getText() + ":" + endMin.getText();
                validPoints++;
                System.out.println("\033[0;34m[A]EndHour accepted\033[0m");
            }
            // if the end_time is invalid
            else
            {
                System.out.println("\033[0;31m[E]EndHour rejected\033[0m");
                endHour.clear();
                endHour.setPromptText("IN");
                endHour.setStyle("-fx-prompt-text-fill: #b22222");
                endMin.clear();
                endMin.setPromptText("IN");
                endMin.setStyle("-fx-prompt-text-fill: #b22222");
            }
        }
        // if the end_time is invalid
        else
        {
            System.out.println("\033[0;31m[E]EndHour rejected\033[0m");
            endHour.clear();
            endHour.setPromptText("IN");
            endHour.setStyle("-fx-prompt-text-fill: #b22222");
            endMin.clear();
            endMin.setPromptText("IN");
            endMin.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate name
        if (EventValidator.isValidName())
        {
            newEvent.values[5] = eventName.getText();
            validPoints++;
            System.out.println("\033[0;34m[A]Name rejected\033[0m");
        }
        // if the name is invalid
        else
        {
            System.out.println("\033[0;31m[E]Name rejected\033[0m");
            eventName.clear();
            eventName.setPromptText("IN");
            eventName.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate place
        if (EventValidator.isValidPlace())
        {
            newEvent.values[6] = eventPlace.getText();
            validPoints++;
            System.out.println("\033[0;31m[E]Place rejected\033[0m");
        }
        //if the place is invalid
        else
        {
            System.out.println("\033[0;31m[E]Place rejected\033[0m");
            eventPlace.clear();
            eventPlace.setPromptText("IN");
            eventPlace.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // if all inputs are valid
        if (validPoints == 7)
        {
            // update EventParent table
            String query1 = "INSERT INTO EventParent VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement1 = connectDB().prepareStatement(query1);
            for (int i = 0; i < 4; i++) {
                preparedStatement1.setString(i + 1, newEvent.values[i + 1]);
            }
            preparedStatement1.setString(5, newEvent.values[0]);
            preparedStatement1.executeUpdate();
            // update EventType table
            String query2 = "INSERT INTO EventType VALUES (?, ?, ?)";
            PreparedStatement preparedStatement2 = connectDB().prepareStatement(query2);
            preparedStatement2.setString(1, newEvent.values[1]);
            for (int i = 0; i < 2; i++) {
                preparedStatement2.setString(i + 2, newEvent.values[i + 5]);
            }
            preparedStatement2.executeUpdate();
            System.out.println("\033[0;34m[A]Action completed\033[0m");
            System.out.println();
            actionCompleted(e);
            onClickViewEventButton(e);
        }
        // if the inputs are invalid
        else
        {
            System.out.println("\033[0;31m[E]Action rejected\033[0m");
            System.out.println();
            invalidInformation(e);
            setRestButtonDisable();
        }
        // closing the connection with the database
        connectDB().close();
    }

    @FXML
    public void onClickActivityButton(ActionEvent e) throws Exception
    {
        // capturing current stage
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // capturing previous stage
        Stage previousStage = (Stage) currentStage.getOwner();
        // close current stage
        currentStage.close();
        // load ui
        Parent root = FXMLLoader.load(getClass().getResource("create-activity-ui.fxml"));
        // initializing new scene and setting it to previous stage
        previousStage.setScene(new Scene(root, 800, 600));
        // showing the previous stage
        previousStage.show();
    }

    // create new activity
    @FXML
    public void onClickCreateActivityButton(ActionEvent e) throws Exception
    {
        // calling activity class
        Activity newActivity = new Activity(clubID.getText(), eventID.getText(), eventYear.getText(), eventMonth.getText(),
                eventDay.getText(), startHour.getText(), startMin.getText(), endHour.getText(), endMin.getText(),
                eventType.getText(), eventLink.getText(), eventName.getText(), activityNo.getText());
        newActivity.createEvent();
        // initializing values array
        newActivity.values = new String[9];
        // set validation times to 0
        validPoints = 0;
        Statement statement = connectDB().createStatement();
        // validate club_id
        if (EventValidator.isValidClubID())
        {
            boolean dbClubIDMatch = false;
            // resultset to store club_id
            ResultSet dbResult = statement.executeQuery("select club_id from Club");
            // if the club_id exist
            while (dbResult.next())
            {
                if (dbResult.getString(1).equals(clubID.getText()))
                {
                    newActivity.values[0] = clubID.getText();
                    validPoints++;
                    dbClubIDMatch = true;
                    System.out.println("\033[0;34m[A]ClubID accepted\033[0m");
                }
            }
            // close the resultset
            dbResult.close();
            // if the clud_id does not exist
            if (!dbClubIDMatch)
            {
                System.out.println("\033[0;31m[E]ClubID rejected\033[0m");
                clubID.clear();
                clubID.setPromptText("IN");
                clubID.setStyle("-fx-prompt-text-fill: #b22222");
            }
        }
        // if the club_id invalid
        else
        {
            System.out.println("\033[0;31m[E]ClubID rejected\033[0m");
            clubID.clear();
            clubID.setPromptText("IN");
            clubID.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate event_id
        if (EventValidator.isValidEventID())
        {
            boolean dbEventIDMatch = false;
            // resultset to store event_id
            ResultSet dbResult = statement.executeQuery("select event_id from EventParent");
            // if the event_id exist
            while (dbResult.next())
            {
                if (dbResult.getString(1).equals(eventID.getText()))
                {
                    dbEventIDMatch = true;
                }
            }
            // close resulset
            dbResult.close();
            // if the event_id exist
            if (dbEventIDMatch)
            {
                System.out.println("\033[0;31m[E]EventID rejected\033[0m");
                eventID.clear();
                eventID.setPromptText("IN");
                eventID.setStyle("-fx-prompt-text-fill: #b22222");
            }
            // if the event_id does not exist
            else
            {
                System.out.println("\033[0;34m[A]EventID accepted\033[0m");
                newActivity.values[1] = eventID.getText();
                validPoints++;
            }
        }
        // if the event_id invalid
        else
        {
            System.out.println("\033[0;31m[E]EventID rejected\033[0m");
            eventID.clear();
            eventID.setPromptText("IN");
            eventID.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate date
        if (EventValidator.isValidYear() && EventValidator.isValidMonth() && EventValidator.isValidDay()) {
            newActivity.values[2] = eventYear.getText() + "/" + eventMonth.getText() + "/" + eventDay.getText();
            validPoints++;
            System.out.println("\033[0;34m[A]Date accepted\033[0m");
        }
        // if the date is invalid
        else
        {
            System.out.println("\033[0;31m[E]Date rejected\033[0m");
            eventYear.clear();
            eventYear.setPromptText("IN");
            eventYear.setStyle("-fx-prompt-text-fill: #b22222");
            eventMonth.clear();
            eventMonth.setPromptText("IN");
            eventMonth.setStyle("-fx-prompt-text-fill: #b22222");
            eventDay.clear();
            eventDay.setPromptText("IN");
            eventDay.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate start_time
        if (EventValidator.isValidStartHour() && EventValidator.isValidStartMin())
        {
            System.out.println("\033[0;34m[A]StartHour accepted\033[0m");
            newActivity.values[3] = startHour.getText() + ":" + startMin.getText();
            validPoints++;
        }
        // if the start_time invalid
        else
        {
            System.out.println("\033[0;31m[E]StartHour rejected\033[0m");
            startHour.clear();
            startHour.setPromptText("IN");
            startHour.setStyle("-fx-prompt-text-fill: #b22222");
            startMin.clear();
            startMin.setPromptText("IN");
            startMin.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate end_time
        if (EventValidator.isValidEndHour() && EventValidator.isValidEndMin())
        {
            if (Integer.parseInt(startHour.getText()) < Integer.parseInt(endHour.getText()) ||
                    (Integer.parseInt(startHour.getText()) == Integer.parseInt(endHour.getText()) &&
                            (Integer.parseInt(startMin.getText()) < Integer.parseInt(endMin.getText())))) {
                newActivity.values[4] = endHour.getText() + ":" + endMin.getText();
                validPoints++;
                System.out.println("\033[0;34m[A]EndHour accepted\033[0m");
            }
            // if the end_time is invalid
            else
            {
                System.out.println("\033[0;31m[E]EndHour rejected\033[0m");
                endHour.clear();
                endHour.setPromptText("IN");
                endHour.setStyle("-fx-prompt-text-fill: #b22222");
                endMin.clear();
                endMin.setPromptText("IN");
                endMin.setStyle("-fx-prompt-text-fill: #b22222");
            }
        }
        // if the end_time is invalid
        else
        {
            System.out.println("\033[0;31m[E]EndHour rejected\033[0m");
            endHour.clear();
            endHour.setPromptText("IN");
            endHour.setStyle("-fx-prompt-text-fill: #b22222");
            endMin.clear();
            endMin.setPromptText("IN");
            endMin.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate type
        if (EventValidator.isValidType())
        {
            System.out.println("\033[0;34m[A]Type accepted\033[0m");
            newActivity.values[5] = eventType.getText();
            validPoints++;
        }
        // if the type is invalid
        else
        {
            System.out.println("\033[0;31m[E]Type rejected\033[0m");
            eventType.clear();
            eventType.setPromptText("IN");
            eventType.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate name
        if (EventValidator.isValidName())
        {
            System.out.println("\033[0;34m[A]Name accepted\033[0m");
            newActivity.values[6] = eventName.getText();
            validPoints++;
        }
        // if the name is invalid
        else
        {
            System.out.println("\033[0;31m[E]Name rejected\033[0m");
            eventName.clear();
            eventName.setPromptText("IN");
            eventName.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate activity_no
        if (EventValidator.isValidActivityNo()) {
            boolean dbActivityNoMatch = false;
            // resultset to store activity_no
            ResultSet dbResult = statement.executeQuery("select activity_no from Activity");
            // if the activity_no exist
            while (dbResult.next()) {
                if (dbResult.getString(1).equals(activityNo.getText())) {
                    dbActivityNoMatch = true;
                    break;
                }
            }
            // close resultset
            dbResult.close();
            // if the activity_no exist
            if (dbActivityNoMatch)
            {
                System.out.println("\033[0;31m[E]Activity Number rejected\033[0m");
                activityNo.clear();
                activityNo.setPromptText("IN");
                activityNo.setStyle("-fx-prompt-text-fill: #b22222");
            }
            // if the activity no does not exist
            else
            {
                newActivity.values[7] = activityNo.getText();
                validPoints++;
                System.out.println("\033[0;34m[A]Activity Number accepted\033[0m");
            }
        }
        // if the activity_no invalid
        else
        {
            System.out.println("\033[0;31m[E]Activity Number rejected\033[0m");
            activityNo.clear();
            activityNo.setPromptText("IN");
            activityNo.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate link
        if (EventValidator.isValidLink())
        {
            System.out.println("\033[0;34m[A]Link accepted\033[0m");
            newActivity.values[8] = eventLink.getText();
            validPoints++;
        }
        // if the link is invalid
        else
        {
            System.out.println("\033[0;31m[E]Link rejected\033[0m");
            eventLink.clear();
            eventLink.setPromptText("IN");
            eventLink.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // if all inputs are valid
        if (validPoints == 9)
        {
            // updating EventParent
            String query1 = "INSERT INTO EventParent VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement1 = connectDB().prepareStatement(query1);
            for (int i = 0; i < 4; i++) {
                preparedStatement1.setString(i + 1, newActivity.values[i + 1]);
            }
            preparedStatement1.setString(5, newActivity.values[0]);
            preparedStatement1.executeUpdate();
            // updating Activity
            String query2 = "INSERT INTO Activity VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement2 = connectDB().prepareStatement(query2);
            preparedStatement2.setString(1, newActivity.values[1]);
            for (int i = 0; i < 4; i++) {
                preparedStatement2.setString(i + 2, newActivity.values[i + 5]);
            }
            preparedStatement2.executeUpdate();
            System.out.println("\033[0;34m[A]Action completed\033[0m");
            System.out.println();
            actionCompleted(e);
            onClickViewEventButton(e);
            setRestButtonDisable();
        }
        // if inputs are invalid
        else
        {
            System.out.println("\033[0;31m[E]Action rejected\033[0m");
            System.out.println();
            invalidInformation(e);
            setRestButtonDisable();
        }
        // close the database connection
        connectDB().close();
    }

    // delete event
    public void onClickDeleteEventButton(ActionEvent e) throws Exception
    {
        EventValidator validateEventID = new EventValidator(eventID.getText());
        // validate event_id
        if (validateEventID.isValidEventID())
        {
            Statement statement = connectDB().createStatement();
            String query1 = "select event_id from EventParent";
            // resultset to store event_id
            ResultSet resultSet1 = statement.executeQuery(query1);
            // delete all event related details if the event if exist
            while (resultSet1.next())
            {
                if (resultSet1.getString(1).equals(eventID.getText()))
                {
                    System.out.println("\033[0;34m[A]EventID accepted\033[0m");
                    resultSet1.close();
                    String query5 = "DELETE from Meeting WHERE event_id = ?";
                    PreparedStatement preparedStatement1 = connectDB().prepareStatement(query5);
                    preparedStatement1.setString(1, eventID.getText());
                    preparedStatement1.executeUpdate();
                    String query6 = "DELETE from Activity WHERE event_id = ?";
                    PreparedStatement preparedStatement2 = connectDB().prepareStatement(query6);
                    preparedStatement2.setString(1, eventID.getText());
                    preparedStatement2.executeUpdate();
                    String query7 = "DELETE from EventType WHERE event_id = ?;";
                    PreparedStatement preparedStatement3 = connectDB().prepareStatement(query7);
                    preparedStatement3.setString(1, eventID.getText());
                    preparedStatement3.executeUpdate();
                    String query8 = "DELETE from EventParent WHERE event_id = ?";
                    PreparedStatement preparedStatement4 = connectDB().prepareStatement(query8);
                    preparedStatement4.setString(1, eventID.getText());
                    preparedStatement4.executeUpdate();
                    System.out.println("\033[0;34m[A]Action completed\033[0m");
                    actionCompleted(e);
                    eventID.clear();
                    break;
                }
            }
            // close database connection
            connectDB().close();
        }
        // if event_id is invalid
        else
        {
            System.out.println("\033[0;31m[E]EventID rejected\033[0m");
            invalidInformation(e);
            // clear input
            eventID.clear();
            eventID.setStyle("-fx-prompt-text-fill: #b22222");
            postponeButton.setDisable(true);
            deleteButton.setDisable(true);
            resetButton.setDisable(true);
        }
    }

    // update event
    public void onClickUpdateEventButton(ActionEvent e) throws Exception
    {
        // get the stored event_id
        PostponeEvent.eventID = eventID.getText();
        EventValidator eventValidator = new EventValidator(eventID.getText());
        // validate event_id
        if (eventValidator.isValidEventID())
        {
            Statement statement = connectDB().createStatement();
            String query1 = "select event_id,club_id from EventParent";
            // resultset to store event_id and club_id
            ResultSet resultSet1 = statement.executeQuery(query1);
            boolean eventIDMatch = false;
            // if the event_id exist
            while (resultSet1.next()) {
                if (resultSet1.getString(1).equals(PostponeEvent.eventID)) {
                    PostponeEvent.clubID = resultSet1.getString(2);
                    resultSet1.close();
                    eventIDMatch = true;
                    break;
                }
            }
            // if the event_id exist
            if (eventIDMatch)
            {
                System.out.println("\033[0;34m[A]EventID accepted\033[0m");
                // capture previous stage
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                // load ui
                Parent root = FXMLLoader.load(getClass().getResource("postpone-event-ui.fxml"));
                // initializing new scene and setting it to stage
                stage.setScene(new Scene(root, 800, 600));
                // capture event_id and set stored event_id
                TextField eventIDTextField = (TextField) root.lookup("#eventID");
                eventIDTextField.setText(PostponeEvent.eventID);
                // capture club_id and set stored club_id
                TextField clubIDTextField = (TextField) root.lookup("#clubID");
                clubIDTextField.setText(PostponeEvent.clubID);
            }
            else
            {
                System.out.println("\033[0;31m[E]EventID rejected\033[0m");
                invalidInformation(e);
            }
            // close database connection
            connectDB().close();
        }
        // if event_id ivalid
        else
        {
            invalidInformation(e);
            eventID.clear();
            eventID.setStyle("-fx-prompt-text-fill: #b22222");
            postponeButton.setDisable(true);
            deleteButton.setDisable(true);
            resetButton.setDisable(true);
        }
    }

    // postpone event
    public void onClickPostponeEventButton(ActionEvent e) throws Exception
    {
        // calling PostponeEvent class
        PostponeEvent postponeEvent = new PostponeEvent(eventYear.getText(), eventMonth.getText(), eventDay.getText(),
                startHour.getText(), startMin.getText(), endHour.getText(), endMin.getText());
        // initializing values array
        postponeEvent.values = new String[3];
        postponeEvent.createEvent();
        // set validations to 0
        int validPoints = 0;
        // validate date
        if (EventValidator.isValidYear() && EventValidator.isValidMonth() && EventValidator.isValidDay())
        {
            postponeEvent.values[0] = eventYear.getText() + "/" + eventMonth.getText() + "/" + eventDay.getText();
            validPoints++;
            System.out.println("\033[0;34m[A]EDate accepted\033[0m");
        }
        // if date is invalid
        else
        {
            System.out.println("\033[0;31m[E]Date rejected\033[0m");
            eventYear.clear();
            eventYear.setPromptText("IN");
            eventYear.setStyle("-fx-prompt-text-fill: #b22222");
            eventMonth.clear();
            eventMonth.setPromptText("IN");
            eventMonth.setStyle("-fx-prompt-text-fill: #b22222");
            eventDay.clear();
            eventDay.setPromptText("IN");
            eventDay.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate start_time
        if (EventValidator.isValidStartHour() && EventValidator.isValidStartMin())
        {
            postponeEvent.values[1] = startHour.getText() + ":" + startMin.getText();
            validPoints++;
            System.out.println("\033[0;314m[E]StartTime accepted\033[0m");
        }
        // if start time is invalid
        else
        {
            System.out.println("\033[0;31m[E]StartTime rejected\033[0m");
            startHour.clear();
            startHour.setPromptText("IN");
            startHour.setStyle("-fx-prompt-text-fill: #b22222");
            startMin.clear();
            startMin.setPromptText("IN");
            startMin.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // validate end_time
        if (EventValidator.isValidEndHour() && EventValidator.isValidEndMin())
        {
            if (Integer.parseInt(startHour.getText()) < Integer.parseInt(endHour.getText()) ||
                    (Integer.parseInt(startHour.getText()) == Integer.parseInt(endHour.getText()) &&
                            (Integer.parseInt(startMin.getText()) < Integer.parseInt(endMin.getText()))))
            {
                postponeEvent.values[2] = endHour.getText() + ":" + endMin.getText();
                validPoints++;
                System.out.println("\033[0;34m[A]EndTime accepted\033[0m");
            }
            // if end time is invalid
            else
            {
                System.out.println("\033[0;31m[E]EndTime rejected\033[0m");
                endHour.clear();
                endHour.setPromptText("IN");
                endHour.setStyle("-fx-prompt-text-fill: #b22222");
                endMin.clear();
                endMin.setPromptText("IN");
                endMin.setStyle("-fx-prompt-text-fill: #b22222");
            }
        }
        // if end time is invalid
        else
        {
            System.out.println("\033[0;31m[E]EndTime rejected\033[0m");
            endHour.clear();
            endHour.setPromptText("IN");
            endHour.setStyle("-fx-prompt-text-fill: #b22222");
            endMin.clear();
            endMin.setPromptText("IN");
            endMin.setStyle("-fx-prompt-text-fill: #b22222");
        }
        // if inputs are valid
        if (validPoints == 3)
        {
            // update EventParent
            String query2 = "UPDATE EventParent SET event_date = ?, start_time = ?, end_time = ? WHERE event_id = ?";
            PreparedStatement preparedStatement1 = connectDB().prepareStatement(query2);
            preparedStatement1.setString(4, eventID.getText());
            for (int i = 0; i < 3; i++) {
                preparedStatement1.setString(i + 1, postponeEvent.values[i]);
            }
            preparedStatement1.executeUpdate();
            // close databse conncetion
            connectDB().close();
            System.out.println("\033[0;34m[A]Action completed\033[0m");
            System.out.println();
            actionCompleted(e);
            // caling event view method
            onClickViewEventButton(e);
        }
        else
        {
            System.out.println("\033[0;31m[E]Action rejected\033[0m");
            System.out.println();
            invalidInformation(e);
            setRestButtonDisable();
        }
    }


    public void onClickGenerateEventReportButton(ActionEvent e) throws Exception
    {
        // validate file path
        EventValidator validator = new EventValidator();
        if (validator.validateString(filePath.getText()))
        {
            System.out.println("\033[0;34m[A]FilePath accepted\033[0m");
            // get all data from EventParent
            String query1 = "SELECT * FROM EventParent";
            Statement statement1 = connectDB().createStatement();
            ResultSet resultSet1 = statement1.executeQuery(query1);
            // store EventParent data in 2D arraylist
            ArrayList<ArrayList<String>> eventParent = new ArrayList<>();
            while (resultSet1.next()) {
                eventParent.add(new ArrayList<>(Arrays.asList(resultSet1.getString("club_id"),
                        resultSet1.getString("event_id"), resultSet1.getString("event_date"),
                        resultSet1.getString("start_time"), resultSet1.getString("end_time"))));
            }
            resultSet1.close();
            // get all data from EventType
            String query2 = "SELECT * FROM EventType";
            Statement statement2 = connectDB().createStatement();
            ResultSet resultSet2 = statement2.executeQuery(query2);
            // store EventType data in 2D arraylist
            ArrayList<ArrayList<String>> event = new ArrayList<>();
            while (resultSet2.next()) {
                event.add(new ArrayList<>(Arrays.asList(resultSet2.getString("event_id"),
                        resultSet2.getString("name"), resultSet2.getString("place"))));
            }
            resultSet2.close();
            // get all data from Actiivity
            String query3 = "SELECT * FROM Activity";
            Statement statement3 = connectDB().createStatement();
            ResultSet resultSet3 = statement3.executeQuery(query3);
            // store Activity data in 2D arraylist
            ArrayList<ArrayList<String>> activity = new ArrayList<>();
            while (resultSet3.next()) {
                activity.add(new ArrayList<>(Arrays.asList(resultSet3.getString("event_id"),
                        resultSet3.getString("type"), resultSet3.getString("name"),
                        resultSet3.getString("activity_no"), resultSet3.getString("link"))));
            }
            // get all data from Meeting
            String query4 = "SELECT * FROM Meeting";
            Statement statement4 = connectDB().createStatement();
            ResultSet resultSet4 = statement4.executeQuery(query4);
            // store Meeting data in a 2D arraylist
            ArrayList<ArrayList<String>> meeting = new ArrayList<>();
            while (resultSet4.next()) {
                meeting.add(new ArrayList<>(Arrays.asList(resultSet4.getString("event_id"),
                        resultSet4.getString("meeting_no"), resultSet4.getString("platform"),
                        resultSet4.getString("link"))));
            }
            resultSet4.close();
            // extracting all arraylists data to one 2D array
            ArrayList<ArrayList<String>> report = new ArrayList<>();
            for (int i = 0; i < eventParent.size(); i++)
            {
                boolean found = false;
                for (int j = 0; j < event.size(); j++)
                {
                    if (eventParent.get(i).get(1).equals(event.get(j).get(0)))
                    {
                        report.add(new ArrayList<>(Arrays.asList(eventParent.get(i).get(0), eventParent.get(i).get(1),
                                eventParent.get(i).get(2), eventParent.get(i).get(3), eventParent.get(i).get(4),
                                event.get(j).get(1), event.get(j).get(1), "NULL", "NULL", "NULL", "NULL", "NULL")));
                        found = true;
                        break;
                    }
                }
                if (found)
                {
                    continue;
                }
                for (int j = 0; j < activity.size(); j++)
                {
                    if (eventParent.get(i).get(1).equals(activity.get(j).get(0)))
                    {
                        report.add(new ArrayList<>(Arrays.asList(eventParent.get(i).get(0), eventParent.get(i).get(1),
                                eventParent.get(i).get(2), eventParent.get(i).get(3), eventParent.get(i).get(4),
                                activity.get(j).get(2), "NULL", activity.get(j).get(1), activity.get(j).get(3),
                                "NULL", "NULL", activity.get(j).get(4))));
                        found = true;
                        break;
                    }
                }
                if (found)
                {
                    continue;
                }
                for (int j = 0; j < meeting.size(); j++)
                {
                    if (eventParent.get(i).get(1).equals(meeting.get(j).get(0)))
                    {
                        report.add(new ArrayList<>(Arrays.asList(eventParent.get(i).get(0), eventParent.get(i).get(1),
                                eventParent.get(i).get(2), eventParent.get(i).get(3), eventParent.get(i).get(4),
                                "NULL", "NULL", "NULL", "NULL", meeting.get(j).get(1), meeting.get(j).get(2),
                                meeting.get(j).get(3))));
                        break;
                    }
                }
            }

            downloadExcel(report);
//            // Create a new workbook
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            // Create a sheet in the workbook
//            XSSFSheet sheet = workbook.createSheet("Event Report");
//            Font boldFont = workbook.createFont();
//            boldFont.setBold(true);
//            // Create a cell style with the bold font
//            CellStyle boldStyle = workbook.createCellStyle();
//            boldStyle.setFont(boldFont);
//            // Create header row
//            XSSFRow headerRow = sheet.createRow(0);
//            headerRow.createCell(0).setCellValue("Club ID");
//            headerRow.createCell(1).setCellValue("Event ID");
//            headerRow.createCell(2).setCellValue("Start Date");
//            headerRow.createCell(3).setCellValue("Start Time");
//            headerRow.createCell(4).setCellValue("End Time");
//            headerRow.createCell(5).setCellValue("Name");
//            headerRow.createCell(6).setCellValue("Place");
//            headerRow.createCell(7).setCellValue("Type");
//            headerRow.createCell(8).setCellValue("Activity No");
//            headerRow.createCell(9).setCellValue("Meeting No");
//            headerRow.createCell(10).setCellValue("Platform");
//            headerRow.createCell(11).setCellValue("Link");
//            for (int i = 0; i < headerRow.getLastCellNum(); i++)
//            {
//                headerRow.getCell(i).setCellStyle(boldStyle);
//            }
//            for (int i = 0; i < report.size(); i++) {
//                // Iterate over the result set and add data to the sheet
//                XSSFRow row = sheet.createRow(i + 1);
//                for (int j = 0; j < report.get(0).size(); j++) {
//                    row.createCell(j).setCellValue(report.get(i).get(j));
//                }
//            }
//
//            DirectoryChooser directoryChooser = new DirectoryChooser();
//            directoryChooser.setTitle("Select Download Path");
//
//            Stage stage = (Stage) rumethAnchor.getScene().getWindow();
//
//            File selectedDirectory = directoryChooser.showDialog(stage.getOwner());
//            if (selectedDirectory != null) {
//                String filePath = selectedDirectory.getAbsolutePath();
//                // Save the workbook to a file
//                try (FileOutputStream fileOut = new FileOutputStream(filePath + File.separator + "events_report.xlsx"))
//                {
//                    workbook.write(fileOut);
//                    Stage newStage = new Stage();
//                    Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//                    newStage.initOwner(previousStage);
//                    Parent root = FXMLLoader.load(getClass().getResource("event-report-successful-ui.fxml"));
//                    Scene scene = new Scene(root, 400, 73);
//                    newStage.setScene(scene);
//                    newStage.setResizable(false);
//                    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//                    newStage.setX((primaryScreenBounds.getWidth() - scene.getWidth()) / 2);
//                    newStage.setY((primaryScreenBounds.getHeight() - scene.getHeight()) / 2);
//                    newStage.show();
//                    System.out.println("\033[0;34m[A]Action completed\033[0m");
//                    System.out.println();
////                    filePath.clear();
//                    resetButton.setDisable(true);
//                } catch (Exception f)
//                {
//                    System.out.println("\033[0;31m[E]Action rejected\033[0m");
//                    System.out.println();
//                    f.printStackTrace();
//                }
//                // close the database connection
//                connectDB().close();
//            }else {
//                System.out.println("No file path specified");
//            }

        }
        else
        {
            invalidInformation(e);
            System.out.println("\033[0;31m[E]Action rejected\033[0m");
            System.out.println();
            filePath.clear();
            filePath.setPromptText("IN");
            filePath.setStyle("-fx-prompt-text-fill: #b22222");
        }
    }

    @FXML
    private void downloadExcel (ArrayList<ArrayList<String>> report) throws Exception {
        // Create a new workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        // Create a sheet in the workbook
        XSSFSheet sheet = workbook.createSheet("Event Report");
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        // Create a cell style with the bold font
        CellStyle boldStyle = workbook.createCellStyle();
        boldStyle.setFont(boldFont);
        // Create header row
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Club ID");
        headerRow.createCell(1).setCellValue("Event ID");
        headerRow.createCell(2).setCellValue("Start Date");
        headerRow.createCell(3).setCellValue("Start Time");
        headerRow.createCell(4).setCellValue("End Time");
        headerRow.createCell(5).setCellValue("Name");
        headerRow.createCell(6).setCellValue("Place");
        headerRow.createCell(7).setCellValue("Type");
        headerRow.createCell(8).setCellValue("Activity No");
        headerRow.createCell(9).setCellValue("Meeting No");
        headerRow.createCell(10).setCellValue("Platform");
        headerRow.createCell(11).setCellValue("Link");
        for (int i = 0; i < headerRow.getLastCellNum(); i++)
        {
            headerRow.getCell(i).setCellStyle(boldStyle);
        }
        for (int i = 0; i < report.size(); i++) {
            // Iterate over the result set and add data to the sheet
            XSSFRow row = sheet.createRow(i + 1);
            for (int j = 0; j < report.get(0).size(); j++) {
                row.createCell(j).setCellValue(report.get(i).get(j));
            }
        }

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Download Path");

        Stage stage = (Stage) rumethAnchor.getScene().getWindow();

        File selectedDirectory = directoryChooser.showDialog(stage.getOwner());
        if (selectedDirectory != null) {
            String filePath = selectedDirectory.getAbsolutePath();
            // Save the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream(filePath + File.separator + "events_report.xlsx"))
            {
                workbook.write(fileOut);
                Stage newStage = new Stage();
                Stage previousStage = (Stage) generateButton.getScene().getWindow();
                newStage.initOwner(previousStage);
                Parent root = FXMLLoader.load(getClass().getResource("event-report-successful-ui.fxml"));
                Scene scene = new Scene(root, 400, 73);
                newStage.setScene(scene);
                newStage.setResizable(false);
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                newStage.setX((primaryScreenBounds.getWidth() - scene.getWidth()) / 2);
                newStage.setY((primaryScreenBounds.getHeight() - scene.getHeight()) / 2);
                newStage.show();
                System.out.println("\033[0;34m[A]Action completed\033[0m");
                System.out.println();
//                    filePath.clear();
                resetButton.setDisable(true);
            } catch (Exception f)
            {
                System.out.println("\033[0;31m[E]Action rejected\033[0m");
                System.out.println();
                f.printStackTrace();
            }
            // close the database connection
            connectDB().close();
        }else {
            System.out.println("No file path specified");
        }
    }

    @FXML
    private void backButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserRegApplication.class.getResource("view-members.fxml"));
        Stage newStage = new Stage();
        Scene newScene = new Scene(fxmlLoader.load(), 950, 600);
        newStage.setTitle("View Members");
        newStage.setScene(newScene);
        newStage.show();

        Stage prevStage = (Stage) backButtonY.getScene().getWindow();
        prevStage.close();
    }
}

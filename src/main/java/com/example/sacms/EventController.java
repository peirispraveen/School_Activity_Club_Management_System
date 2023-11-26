package com.example.sacms;

import com.example.sacms.EventScheduling.*;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.*;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;

public class EventController
{

    String url = "jdbc:mysql://localhost:3306/SACMS";
    String user = "root";
    String password = "";


    @FXML
    protected TextField clubID;
    @FXML
    protected TextField eventID;
    @FXML
    protected TextField eventType;
    @FXML
    protected TextField eventYear;
    @FXML
    protected TextField eventMonth;
    @FXML
    protected TextField eventDay;
    @FXML
    protected TextField startHour;
    @FXML
    protected TextField startMin;
    @FXML
    protected TextField endHour;
    @FXML
    protected TextField endMin;
    @FXML
    protected TextField eventPlace;
    @FXML
    protected TextField eventPlatform;
    @FXML
    protected TextField eventLink;
    @FXML
    protected TextField meetingNum;
    @FXML
    protected TextField eventName;
    @FXML
    protected TextField activityNo;
    @FXML
    private Button resetButton;
    private int validPoints;


    @FXML
    public void onInputTextChanged()
    {
        resetButton.setDisable(false);
    }

    @FXML
    public void onClickEventViewResetButton(ActionEvent e) throws Exception
    {
        eventID.clear();
    }

    @FXML
    public void onClickCreateActivityResetButton(ActionEvent e) throws Exception
    {
        clubID.clear();
        eventID.clear();
        activityNo.clear();
        eventName.clear();
        eventType.clear();
        eventLink.clear();
        eventYear.clear();
        eventMonth.clear();
        eventDay.clear();
        startHour.clear();
        startMin.clear();
        endHour.clear();
        endMin.clear();
    }


   @FXML
    public void onClickAddEventButton(ActionEvent e) throws Exception {

    Stage newStage = new Stage();
    Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    newStage.initOwner(previousStage);
    Parent root = FXMLLoader.load(getClass().getResource("event-type-selection-ui.fxml"));
    Scene scene = new Scene(root, 400, 73);
    newStage.setScene(scene);
    newStage.setResizable(false);
    newStage.setTitle("Please select the event type");
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    newStage.setX((primaryScreenBounds.getWidth() - scene.getWidth()) / 2);
    newStage.setY((primaryScreenBounds.getHeight() - scene.getHeight()) / 2);
    newStage.show();
    }

    @FXML
    public void onClickExitButton()
    {
        System.exit(0);
    }


    @FXML
    public void onClickViewEventButton(ActionEvent e) throws Exception
    {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("event-view-ui.fxml"));
        TableView tableView = (TableView<EventView>) root.lookup("#eventTableView");
        TableColumn<EventView, String > column1 = (TableColumn<EventView, String>) tableView.getColumns().get(0);
        TableColumn<EventView, String > column2 = (TableColumn<EventView, String>) tableView.getColumns().get(1);
        TableColumn<EventView, String > column3 = (TableColumn<EventView, String>) tableView.getColumns().get(2);
        TableColumn<EventView, String > column4 = (TableColumn<EventView, String>) tableView.getColumns().get(3);

        column1.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        column2.setCellValueFactory(new PropertyValueFactory<>("clubID"));
        column3.setCellValueFactory(new PropertyValueFactory<>("type"));
        column4.setCellValueFactory(new PropertyValueFactory<>("date"));

        ObservableList<EventView> obList = FXCollections.observableArrayList();

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query1 = "select event_id, club_id, event_date from EventParent";
            String query2 = "select event_id from Meeting";
            String query3 = "select event_id from EventType";
            String query4 = "select event_id from Activity";
            ResultSet resultSet1 = statement.executeQuery(query1);
            ArrayList<ArrayList<String>> eventParent = new ArrayList<>();
            while(resultSet1.next())
            {
                eventParent.add(new ArrayList<>(Arrays.asList(resultSet1.getString(1),
                        resultSet1.getString(2), resultSet1.getString(3))));
            }
            ResultSet resultSet2 = statement.executeQuery(query2);
            ArrayList<String> meeting = new ArrayList<>();
            while(resultSet2.next())
            {
                meeting.add(resultSet2.getString(1));
            }
            ResultSet resultSet3 = statement.executeQuery(query3);
            ArrayList<String> event = new ArrayList<>();
            while(resultSet3.next())
            {
                event.add(resultSet3.getString(1));
            }
            ResultSet resultSet4 = statement.executeQuery(query4);
            ArrayList<String> activity = new ArrayList<>();
            while(resultSet4.next())
            {
                activity.add(resultSet4.getString(1));
            }
            resultSet1.close();
            resultSet2.close();
            resultSet3.close();
            resultSet4.close();
            connection.close();
            for (ArrayList<String> strings : eventParent) {
                String type = "";
                if (type == "") {
                    for (String s : meeting) {
                        if (strings.get(0).equals(s)) {
                            type = "meeting";
                            break;
                        }
                    }
                }
                if (type == "") {
                    for (String s : event) {
                        if (strings.get(0).equals(s)) {
                            type = "event";
                            break;
                        }
                    }
                }
                if (type == "") {
                    for (String s : activity) {
                        if (strings.get(0).equals(s)) {
                            type = "activity";
                            break;
                        }
                    }
                }
                if (type != "")
                {
                    EventView eventValue = new EventView(strings.get(0), strings.get(1), type, strings.get(2));
                    obList.add(eventValue);
                }
            }
        }
        catch(Exception et)
        {
            et.printStackTrace();
        }

        tableView.setItems(obList);
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onClickEventMeetingButton(ActionEvent e) throws Exception
    {
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage previousStage = (Stage) currentStage.getOwner();
        currentStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("create-meeting-ui.fxml"));
        previousStage.setScene(new Scene(root, 800, 600));
        previousStage.show();
    }

    @FXML
    public void onClickCreateMeetingButton(ActionEvent e) throws Exception
    {
        Meeting newMeeting = new Meeting(clubID.getText(), eventID.getText(), eventYear.getText(), eventMonth.getText(),
                eventDay.getText(), startHour.getText(), startMin.getText(), endHour.getText(), endMin.getText(),
                meetingNum.getText(), eventPlatform.getText(), eventLink.getText());
        newMeeting.createEvent();
        newMeeting.values = new String[8];
        validPoints = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            if (EventValidator.isValidClubID())
            {
                ResultSet dbClubResult = statement.executeQuery("select club_id from Club");
                boolean dbClubIDMatch = false;
                while(dbClubResult.next())
                {
                    if(dbClubResult.getString(1).equals(clubID.getText()))
                    {
                     newMeeting.values[0] = clubID.getText();
                     validPoints++;
                     dbClubIDMatch = true;
                    }
                }
                dbClubResult.close();
                if(!dbClubIDMatch)
                {
                    clubID.clear();
                    clubID.setPromptText("IN");
                    clubID.setStyle("-fx-prompt-text-fill: #b22222");
                }
            }
            else
            {
                clubID.clear();
                clubID.setPromptText("IN");
                clubID.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidEventID())
            {
                boolean dbEventIDMatch = false;
                ResultSet dbResult = statement.executeQuery("select event_id from EventParent");
                while(dbResult.next())
                {
                    if(dbResult.getString(1).equals(eventID.getText()))
                    {
                        dbEventIDMatch = true;
                    }
                }
                dbResult.close();
                if(dbEventIDMatch)
                {
                    eventID.clear();
                    eventID.setPromptText("IN");
                    eventID.setStyle("-fx-prompt-text-fill: #b22222");
                }
                else
                {
                    newMeeting.values[1] = eventID.getText();
                    validPoints++;
                }
            }
            else
            {
                eventID.clear();
                eventID.setPromptText("IN");
                eventID.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidYear() && EventValidator.isValidMonth() && EventValidator.isValidDay())
            {
                newMeeting.values[2] = eventYear.getText() + "/" + eventMonth.getText() + "/" + eventDay.getText();
                validPoints++;
            } else {
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
            if (EventValidator.isValidStartHour() && EventValidator.isValidStartMin())
            {
                newMeeting.values[3] = startHour.getText() + ":" + startMin.getText();
                validPoints++;
            } else
            {
                startHour.clear();
                startHour.setPromptText("IN");
                startHour.setStyle("-fx-prompt-text-fill: #b22222");
                startMin.clear();
                startMin.setPromptText("IN");
                startMin.setStyle("-fx-prompt-text-fill: #b22222");
            }

            if (EventValidator.isValidEndHour() && EventValidator.isValidEndMin())
            {
                if (Integer.parseInt(startHour.getText()) < Integer.parseInt(endHour.getText()) ||
                        (Integer.parseInt(startHour.getText()) == Integer.parseInt(endHour.getText()) &&
                                (Integer.parseInt(startMin.getText()) < Integer.parseInt(endMin.getText()))))
                {
                    newMeeting.values[4] = endHour.getText() + ":" + endMin.getText();
                    validPoints++;
                } else
                {
                    endHour.clear();
                    endHour.setPromptText("IN");
                    endHour.setStyle("-fx-prompt-text-fill: #b22222");
                    endMin.clear();
                    endHour.setPromptText("IN");
                    endHour.setStyle("-fx-prompt-text-fill: #b22222");
                }
            }
            else
            {
                endHour.clear();
                endHour.setPromptText("IN");
                endHour.setStyle("-fx-prompt-text-fill: #b22222");
                endMin.clear();
                endMin.setPromptText("IN");
                endMin.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidNum())
            {
                boolean dbMeetingNoMatch = false;
                ResultSet dbResult = statement.executeQuery("select meeting_no from Meeting");
                while(dbResult.next())
                {
                    if(dbResult.getString(1).equals(meetingNum.getText()))
                    {
                        dbMeetingNoMatch = true;
                    }

                }
                dbResult.close();
                if(dbMeetingNoMatch)
                {
                    meetingNum.clear();
                    meetingNum.setPromptText("IN");
                    meetingNum.setStyle("-fx-prompt-text-fill: #b22222");
                }
                else
                {
                    newMeeting.values[5] = meetingNum.getText();
                    validPoints++;
                }
            }
            else
            {
                meetingNum.clear();
                meetingNum.setPromptText("IN");
                meetingNum.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidPlatform())
            {
                newMeeting.values[6] = eventPlatform.getText();
                validPoints++;
            }
            else
            {
                eventPlatform.clear();
                eventPlatform.setPromptText("IN");
                eventPlatform.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidLink())
            {
                newMeeting.values[7] = eventLink.getText();
                validPoints++;
            }
            else
            {
                eventLink.clear();
                eventLink.setPromptText("IN");
                eventLink.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (validPoints == 8)
            {
                String query1 = "INSERT INTO EventParent VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                for (int i = 0; i < 4; i++)
                {
                    preparedStatement1.setString(i + 1, newMeeting.values[i + 1]);
                }
                preparedStatement1.setString(5, newMeeting.values[0]);
                preparedStatement1.executeUpdate();
                String query2 = "INSERT INTO Meeting VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                preparedStatement2.setString(1, newMeeting.values[1]);
                for (int i = 0; i < 3; i++)
                {
                    preparedStatement2.setString(i + 2, newMeeting.values[i + 5]);
                }
                preparedStatement2.executeUpdate();
                System.out.println("Meeting data inserted successfully.");
                Stage newStage = new Stage();
                Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                newStage.initOwner(previousStage);
                Parent root = FXMLLoader.load(getClass().getResource("successful-ui.fxml"));
                Scene scene = new Scene(root, 400, 73);
                newStage.setScene(scene);
                newStage.setResizable(false);
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                newStage.setX((primaryScreenBounds.getWidth() - scene.getWidth()) / 2);
                newStage.setY((primaryScreenBounds.getHeight() - scene.getHeight()) / 2);
                newStage.show();
                clubID.clear();
                eventID.clear();
                meetingNum.clear();
                eventPlatform.clear();
                eventLink.clear();
                eventYear.clear();
                eventMonth.clear();
                eventDay.clear();
                endHour.clear();
                endMin.clear();
                startHour.clear();
                startMin.clear();
            }
            connection.close();
        }
        catch(SQLException edb)
        {
            edb.printStackTrace();
        }
    }

    @FXML
    public void onClickEventButton(ActionEvent e) throws Exception
    {
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage previousStage = (Stage) currentStage.getOwner();
        currentStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("create-event-ui.fxml"));
        previousStage.setScene(new Scene(root, 800, 600));
        previousStage.show();
    }

    @FXML
    public void onClickCreateEventButton(ActionEvent e) throws Exception
    {
        Event newEvent = new Event(clubID.getText(), eventID.getText(), eventYear.getText(), eventMonth.getText(),
                eventDay.getText(), startHour.getText(), startMin.getText(), endHour.getText(), endMin.getText(),
                eventName.getText(), eventPlace.getText());
        newEvent.createEvent();
        newEvent.values = new String[7];
        validPoints = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            if (EventValidator.isValidClubID())
            {
                ResultSet dbClubResult = statement.executeQuery("select club_id from Club");
                boolean dbClubIDMatch = false;
                while(dbClubResult.next())
                {
                    if(dbClubResult.getString(1).equals(clubID.getText()))
                    {
                     newEvent.values[0] = clubID.getText();
                     validPoints++;
                     dbClubIDMatch = true;
                    }
                }
                dbClubResult.close();
                if(!dbClubIDMatch)
                {
                    clubID.clear();
                    clubID.setPromptText("IN");
                    clubID.setStyle("-fx-prompt-text-fill: #b22222");
                }
            }
            else
            {
                clubID.clear();
                clubID.setPromptText("IN");
                clubID.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidEventID())
            {
                boolean dbEventIDMatch = false;
                 ResultSet dbResult = statement.executeQuery("select event_id from EventParent");
                while(dbResult.next())
                {
                    if(dbResult.getString(1).equals(eventID.getText()))
                    {
                        dbEventIDMatch = true;
                    }
                }
                dbResult.close();
                if(dbEventIDMatch)
                {
                    eventID.clear();
                    eventID.setPromptText("IN");
                    eventID.setStyle("-fx-prompt-text-fill: #b22222");
                }
                else
                {
                    newEvent.values[1] = eventID.getText();
                    validPoints++;
                }
            }
            else
            {
                eventID.clear();
                eventID.setPromptText("IN");
                eventID.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidYear() && EventValidator.isValidMonth() && EventValidator.isValidDay())
            {
                newEvent.values[2] = eventYear.getText() + "/" + eventMonth.getText() + "/" + eventDay.getText();
                validPoints++;
            } else {
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
            if (EventValidator.isValidStartHour() && EventValidator.isValidStartMin())
            {
                newEvent.values[3] = startHour.getText() + ":" + startMin.getText();
                validPoints++;
            } else
            {
                startHour.clear();
                startHour.setPromptText("IN");
                startHour.setStyle("-fx-prompt-text-fill: #b22222");
                startMin.clear();
                startMin.setPromptText("IN");
                startMin.setStyle("-fx-prompt-text-fill: #b22222");
            }

            if (EventValidator.isValidEndHour() && EventValidator.isValidEndMin())
            {
                if (Integer.parseInt(startHour.getText()) < Integer.parseInt(endHour.getText()) ||
                        (Integer.parseInt(startHour.getText()) == Integer.parseInt(endHour.getText()) &&
                                (Integer.parseInt(startMin.getText()) < Integer.parseInt(endMin.getText()))))
                {
                    newEvent.values[4] = endHour.getText() + ":" + endMin.getText();
                    validPoints++;
                } else
                {
                    endHour.clear();
                    endHour.setPromptText("IN");
                    endHour.setStyle("-fx-prompt-text-fill: #b22222");
                    endMin.clear();
                    endMin.setPromptText("IN");
                    endMin.setStyle("-fx-prompt-text-fill: #b22222");
                }
            }
            else
            {
                endHour.clear();
                    endHour.setPromptText("IN");
                    endHour.setStyle("-fx-prompt-text-fill: #b22222");
                    endMin.clear();
                    endMin.setPromptText("IN");
                    endMin.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidName())
            {
                    newEvent.values[5] = eventName.getText();
                    validPoints++;
            }
            else
            {
                eventName.clear();
                eventName.setPromptText("IN");
                eventName.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidPlace())
            {
                newEvent.values[6] = eventPlace.getText();
                validPoints++;
            } else
            {
                eventPlace.clear();
                eventPlace.setPromptText("IN");
                eventPlace.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (validPoints == 7)
            {
                try {
                    String query1 = "INSERT INTO EventParent VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                    for (int i = 0; i < 4; i++) {
                        preparedStatement1.setString(i + 1, newEvent.values[i + 1]);
                    }
                    preparedStatement1.setString(5, newEvent.values[0]);
                    preparedStatement1.executeUpdate();
                    String query2 = "INSERT INTO EventType VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                    preparedStatement2.setString(1, newEvent.values[1]);
                    for (int i = 0; i < 2; i++) {
                        preparedStatement2.setString(i + 2, newEvent.values[i + 5]);
                    }
                    preparedStatement2.executeUpdate();
                    Stage newStage = new Stage();
                    Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    newStage.initOwner(previousStage);
                    Parent root = FXMLLoader.load(getClass().getResource("successful-ui.fxml"));
                    Scene scene = new Scene(root, 400, 73);
                    newStage.setScene(scene);
                    newStage.setResizable(false);
                    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                    newStage.setX((primaryScreenBounds.getWidth() - scene.getWidth()) / 2);
                    newStage.setY((primaryScreenBounds.getHeight() - scene.getHeight()) / 2);
                    newStage.show();
                    clubID.clear();
                    eventID.clear();
                    eventName.clear();
                    eventPlace.clear();
                    eventYear.clear();
                    eventMonth.clear();
                    eventDay.clear();
                    endHour.clear();
                    endMin.clear();
                    startHour.clear();
                    startMin.clear();
                }
                catch(Exception el)
                {
                    el.printStackTrace();
                }
            }
            connection.close();
        }
        catch(SQLException edb)
        {
                edb.printStackTrace();
        }
    }

    @FXML
    public void onClickActivityButton(ActionEvent e) throws Exception
    {
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage previousStage = (Stage) currentStage.getOwner();
        currentStage.close();
        Parent root = FXMLLoader.load(getClass().getResource("create-activity-ui.fxml"));
        previousStage.setScene(new Scene(root, 800, 600));
        previousStage.show();
    }

    @FXML
    public void onClickCreateActivityButton(ActionEvent e) throws Exception
    {
        Activity newActivity = new Activity(clubID.getText(), eventID.getText(), eventYear.getText(), eventMonth.getText(),
                eventDay.getText(), startHour.getText(), startMin.getText(), endHour.getText(), endMin.getText(),
                eventType.getText(), eventLink.getText(), eventName.getText(), activityNo.getText());
        newActivity.createEvent();
        newActivity.values = new String[9];
        validPoints = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            if (EventValidator.isValidClubID())
            {
                boolean dbClubIDMatch = false;
                ResultSet dbResult = statement.executeQuery("select club_id from Club");
                while(dbResult.next())
                {
                    if(dbResult.getString(1).equals(clubID.getText()))
                    {
                     newActivity.values[0] = clubID.getText();
                     validPoints++;
                     dbClubIDMatch = true;
                    }
                }
                dbResult.close();
                if(!dbClubIDMatch)
                {
                    clubID.clear();
                    clubID.setPromptText("IN");
                    clubID.setStyle("-fx-prompt-text-fill: #b22222");
                }
            }
            else
            {
                clubID.clear();
                clubID.setPromptText("IN");
                clubID.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidEventID())
            {
                boolean dbEventIDMatch = false;
                 ResultSet dbResult = statement.executeQuery("select event_id from EventParent");
                while(dbResult.next())
                {
                    if(dbResult.getString(1).equals(eventID.getText()))
                    {
                        dbEventIDMatch = true;
                    }
                }
                dbResult.close();
                if(dbEventIDMatch)
                {
                    eventID.clear();
                    eventID.setPromptText("IN");
                    eventID.setStyle("-fx-prompt-text-fill: #b22222");
                }
                else
                {
                    newActivity.values[1] = eventID.getText();
                    validPoints++;
                }
            }
            else
            {
                eventID.clear();
                eventID.setPromptText("IN");
                eventID.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidYear() && EventValidator.isValidMonth() && EventValidator.isValidDay())
            {
                newActivity.values[2] = eventYear.getText() + "/" + eventMonth.getText() + "/" + eventDay.getText();
                validPoints++;
            } else {
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
            if (EventValidator.isValidStartHour() && EventValidator.isValidStartMin())
            {
                newActivity.values[3] = startHour.getText() + ":" + startMin.getText();
                validPoints++;
            } else
            {
                startHour.clear();
                startHour.setPromptText("IN");
                startHour.setStyle("-fx-prompt-text-fill: #b22222");
                startMin.clear();
                startMin.setPromptText("IN");
                startMin.setStyle("-fx-prompt-text-fill: #b22222");
            }

            if (EventValidator.isValidEndHour() && EventValidator.isValidEndMin())
            {
                if (Integer.parseInt(startHour.getText()) < Integer.parseInt(endHour.getText()) ||
                        (Integer.parseInt(startHour.getText()) == Integer.parseInt(endHour.getText()) &&
                                (Integer.parseInt(startMin.getText()) < Integer.parseInt(endMin.getText()))))
                {
                    newActivity.values[4] = endHour.getText() + ":" + endMin.getText();
                    validPoints++;
                } else
                {
                    endHour.clear();
                    endHour.setPromptText("IN");
                    endHour.setStyle("-fx-prompt-text-fill: #b22222");
                    endMin.clear();
                    endMin.setPromptText("IN");
                    endMin.setStyle("-fx-prompt-text-fill: #b22222");
                }
            }
            else
            {
                endHour.clear();
                endHour.setPromptText("IN");
                endHour.setStyle("-fx-prompt-text-fill: #b22222");
                endMin.clear();
                endMin.setPromptText("IN");
                endMin.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidType())
            {
                newActivity.values[5] = eventType.getText();
                validPoints++;
            } else
            {
                eventType.clear();
                eventType.setPromptText("IN");
                eventType.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidName())
            {
                newActivity.values[6] = eventName.getText();
                validPoints++;
            }
            else
            {
                eventName.clear();
                eventName.setPromptText("IN");
                eventName.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidActivityNo()) {
                boolean dbActivityNoMatch = false;
                ResultSet dbResult = statement.executeQuery("select activity_no from Activity");
                while (dbResult.next()) {
                    if (dbResult.getString(1).equals(activityNo.getText())) {
                        dbActivityNoMatch = true;
                    }
                }
                dbResult.close();
                if (dbActivityNoMatch) {
                    activityNo.clear();
                    activityNo.setPromptText("IN");
                    activityNo.setStyle("-fx-prompt-text-fill: #b22222");
                } else {
                    newActivity.values[7] = activityNo.getText();
                    validPoints++;
                }
            }
            else
            {
                activityNo.clear();
                activityNo.setPromptText("IN");
                activityNo.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (EventValidator.isValidLink())
            {
                newActivity.values[8] = eventLink.getText();
                validPoints++;
            } else
            {
                eventLink.clear();
                eventLink.setPromptText("IN");
                eventLink.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if (validPoints == 9)
            {
                String query1 = "INSERT INTO EventParent VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                for (int i = 0; i < 4; i++)
                {
                    preparedStatement1.setString(i + 1, newActivity.values[i + 1]);
                }
                preparedStatement1.setString(5, newActivity.values[0]);
                preparedStatement1.executeUpdate();
                String query2 = "INSERT INTO Activity VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                preparedStatement2.setString(1, newActivity.values[1]);
                for (int i = 0; i < 4; i++)
                {
                    preparedStatement2.setString(i + 2, newActivity.values[i + 5]);
                }
                preparedStatement2.executeUpdate();
                Stage newStage = new Stage();
                Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                newStage.initOwner(previousStage);
                Parent root = FXMLLoader.load(getClass().getResource("successful-ui.fxml"));
                Scene scene = new Scene(root, 400, 73);
                newStage.setScene(scene);
                newStage.setResizable(false);
                Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                newStage.setX((primaryScreenBounds.getWidth() - scene.getWidth()) / 2);
                newStage.setY((primaryScreenBounds.getHeight() - scene.getHeight()) / 2);
                newStage.show();
                clubID.clear();
                eventID.clear();
                activityNo.clear();
                eventName.clear();
                eventType.clear();
                eventLink.clear();
                eventYear.clear();
                eventMonth.clear();
                eventDay.clear();
                endHour.clear();
                endMin.clear();
                startHour.clear();
                startMin.clear();
            }
            connection.close();
        }
        catch(SQLException edb)
        {
            edb.printStackTrace();
        }
    }

    public void onClickDeleteEventButton(ActionEvent e) throws Exception
    {
        EventValidator validateClubID = new EventValidator(eventID.getText());
        if(validateClubID.isValidEventID()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();
                String query1 = "select event_id from EventParent";
                ResultSet resultSet1 = statement.executeQuery(query1);
                while (resultSet1.next()) {
                    if (resultSet1.getString(1).equals(eventID.getText())) {
                        resultSet1.close();
                        String query5 = "DELETE from EventParent WHERE event_id = ?";
                        String query6 = "DELETE from Meeting WHERE event_id = ?";
                        String query7 = "DELETE from Activity WHERE event_id = ?";
                        String query8 = "DELETE from EventType WHERE event_id = ?;";
                        PreparedStatement preparedStatement1 = connection.prepareStatement(query5);
                        PreparedStatement preparedStatement2 = connection.prepareStatement(query6);
                        PreparedStatement preparedStatement3 = connection.prepareStatement(query7);
                        PreparedStatement preparedStatement4 = connection.prepareStatement(query8);
                        preparedStatement1.setString(1, eventID.getText());
                        preparedStatement2.setString(1, eventID.getText());
                        preparedStatement3.setString(1, eventID.getText());
                        preparedStatement4.setString(1, eventID.getText());
                        preparedStatement1.executeUpdate();
                        preparedStatement2.executeUpdate();
                        preparedStatement3.executeUpdate();
                        preparedStatement4.executeUpdate();
                        connection.close();
                        Stage newStage = new Stage();
                        Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        newStage.initOwner(previousStage);
                        Parent root = FXMLLoader.load(getClass().getResource("successful-ui.fxml"));
                        Scene scene = new Scene(root, 400, 73);
                        newStage.setScene(scene);
                        newStage.setResizable(false);
                        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                        newStage.setX((primaryScreenBounds.getWidth() - scene.getWidth()) / 2);
                        newStage.setY((primaryScreenBounds.getHeight() - scene.getHeight()) / 2);
                        newStage.show();
                        break;
                    }
                }
            } catch (Exception edb) {
                edb.printStackTrace();
            }
        }
    }

    public void onClickUpdateEventButton(ActionEvent e) throws Exception
    {
        PostponeEvent.eventID = eventID.getText();
        EventValidator eventValidator = new EventValidator(eventID.getText());
        if(eventValidator.isValidEventID()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();
                String query1 = "select event_id,club_id from EventParent";
                ResultSet resultSet1 = statement.executeQuery(query1);
                boolean eventIDMatch = false;
                while (resultSet1.next()) {
                    if (resultSet1.getString(1).equals(PostponeEvent.eventID)) {
                        PostponeEvent.clubID = resultSet1.getString(2);
                        resultSet1.close();
                        eventIDMatch = true;
                        break;
                    }
                }
                if (eventIDMatch) {
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("postpone-event-ui.fxml"));
                    stage.setScene(new Scene(root, 800, 600));
                    TextField eventIDTextField = (TextField) root.lookup("#eventID");
                    eventIDTextField.setText(PostponeEvent.eventID);
                    TextField clubIDTextField = (TextField) root.lookup("#clubID");
                    clubIDTextField.setText(PostponeEvent.clubID);
                }
                connection.close();
            } catch (Exception edb) {
                edb.printStackTrace();
            }
        }
    }

    public void onClickPostponeEventButton(ActionEvent e) throws Exception
    {

        PostponeEvent postponeEvent = new PostponeEvent(eventYear.getText(), eventMonth.getText(), eventDay.getText(),
                startHour.getText(), startMin.getText(), endHour.getText(), endMin.getText());
        postponeEvent.values = new String[3];
        postponeEvent.createEvent();
        if (EventValidator.isValidYear() && EventValidator.isValidMonth() && EventValidator.isValidDay())
            {
                postponeEvent.values[0] = eventYear.getText() + "/" + eventMonth.getText() + "/" + eventDay.getText();
                validPoints++;
            } else {
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
            if (EventValidator.isValidStartHour() && EventValidator.isValidStartMin())
            {
                postponeEvent.values[1] = startHour.getText() + ":" + startMin.getText();
                validPoints++;
            } else
            {
                startHour.clear();
                startHour.setPromptText("IN");
                startHour.setStyle("-fx-prompt-text-fill: #b22222");
                startMin.clear();
                startMin.setPromptText("IN");
                startMin.setStyle("-fx-prompt-text-fill: #b22222");
            }

            if (EventValidator.isValidEndHour() && EventValidator.isValidEndMin())
            {
                if (Integer.parseInt(startHour.getText()) < Integer.parseInt(endHour.getText()) ||
                        (Integer.parseInt(startHour.getText()) == Integer.parseInt(endHour.getText()) &&
                                (Integer.parseInt(startMin.getText()) < Integer.parseInt(endMin.getText()))))
                {
                    postponeEvent.values[2] = endHour.getText() + ":" + endMin.getText();
                    validPoints++;
                } else
                {
                    endHour.clear();
                    endHour.setPromptText("IN");
                    endHour.setStyle("-fx-prompt-text-fill: #b22222");
                    endMin.clear();
                    endMin.setPromptText("IN");
                    endMin.setStyle("-fx-prompt-text-fill: #b22222");
                }
            }
            else
            {
                endHour.clear();
                endHour.setPromptText("IN");
                endHour.setStyle("-fx-prompt-text-fill: #b22222");
                endMin.clear();
                endMin.setPromptText("IN");
                endMin.setStyle("-fx-prompt-text-fill: #b22222");
            }
            if(validPoints == 3) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, user, password);
                    String query2 = "UPDATE EventParent SET event_date = ?, start_time = ?, end_time = ? WHERE event_id = ?";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(query2);
                    preparedStatement1.setString(4, eventID.getText());
                    for (int i = 0; i < 3; i++) {
                        preparedStatement1.setString(i + 1, postponeEvent.values[i]);
                    }
                    preparedStatement1.executeUpdate();
                    connection.close();
                    Stage newStage = new Stage();
                    Stage previousStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    newStage.initOwner(previousStage);
                    Parent root = FXMLLoader.load(getClass().getResource("successful-ui.fxml"));
                    Scene scene = new Scene(root, 400, 73);
                    newStage.setScene(scene);
                    newStage.setResizable(false);
                    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
                    newStage.setX((primaryScreenBounds.getWidth() - scene.getWidth()) / 2);
                    newStage.setY((primaryScreenBounds.getHeight() - scene.getHeight()) / 2);
                    newStage.show();
                    //
                } catch (Exception edb) {
                    edb.printStackTrace();
                }
            }
        }

        public void onClickGenerateEventReportButton(ActionEvent e) throws Exception
        {
        }
    }
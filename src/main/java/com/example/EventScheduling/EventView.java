package com.example.EventScheduling;

public class EventView
{
    // attributes to be shown in the table
    private String eventID;
    private String clubID;
    private String type;
    private String date;

    // default constructor
    public EventView(){}

    // parametarized constructor
    public EventView(String eventID, String clubID, String type, String date)
    {
        this.eventID = eventID;
        this.clubID = clubID;
        this.type = type;
        this.date = date;
    }

    // getters
    public String getEventID() {
        return eventID;
    }

    public String getClubID() {
        return clubID;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}

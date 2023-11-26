package com.example.sacms.EventScheduling;

import com.example.sacms.EventController;

public class EventView
{
    private String eventID;
    private String clubID;
    private String type;
    private String date;

    public EventView(String eventID, String clubID, String type, String date) {
        this.eventID = eventID;
        this.clubID = clubID;
        this.type = type;
        this.date = date;
    }

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

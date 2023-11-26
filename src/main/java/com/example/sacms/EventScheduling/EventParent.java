package com.example.sacms.EventScheduling;

abstract class EventParent
{
    protected String clubID;
    protected String eventID;
    protected String year;
    protected String month;
    protected String day;
    protected String startHour;
    protected String startMinute;
    protected String endHour;
    protected String endMinute;

    public EventParent() {}

    public EventParent(String year, String month, String day, String startHour, String startMinute, String endHour,
                       String endMinute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    public EventParent(String clubID, String eventID, String year, String month, String day, String startHour,
                       String startMinute, String endHour, String endMinute) {
        this.clubID = clubID;
        this.eventID = eventID;
        this.year = year;
        this.month = month;
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    abstract void createEvent();

}
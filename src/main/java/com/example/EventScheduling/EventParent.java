package com.example.EventScheduling;

abstract class EventParent
{
    // common attributes
    protected String clubID;
    protected String eventID;
    protected String year;
    protected String month;
    protected String day;
    protected String startHour;
    protected String startMinute;
    protected String endHour;
    protected String endMinute;

    // default constructor
    public EventParent() {}

    // parametarised constructor (overloading the constructor)
    public EventParent(String year, String month, String day, String startHour, String startMinute, String endHour,
                       String endMinute)
    {
        // inititalizing common attributes for update
        this.year = year;
        this.month = month;
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    // parametarised constructor (overloading the constructor)
    public EventParent(String clubID, String eventID, String year, String month, String day, String startHour,
                       String startMinute, String endHour, String endMinute)
    {
        // initializing common attributes
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

    // creating abstract method to be implemented in all child classes
    abstract void createEvent();

}
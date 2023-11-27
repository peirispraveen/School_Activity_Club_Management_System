package com.example.EventScheduling;

public class Activity extends EventParent
{
    // special attributes
    private String type;
    private String link;
    private String activityName;
    private String activityNumber;
    public String[] values;

    // default constructor
    public Activity(){}

    // parametarised constructor
    public Activity(String clubID, String eventID, String year, String month, String day, String startHour,
                    String startMinute, String endHour, String endMinute, String type, String link, String activityName,
                    String activityNumber)
    {
        // calling the parent class constructor
        super(clubID, eventID, year, month, day, startHour, startMinute, endHour, endMinute);
        // initializing special attributes
        this.type = type;
        this.link = link;
        this.activityName = activityName;
        this.activityNumber = activityNumber;
    }

    // implementing the parent class abstract method
    @Override
    public void createEvent()
    {
        EventValidator validate = new EventValidator();
        // creating threads
        Thread thread1 = new Thread(() -> validate.validateClubID(this.clubID));
        Thread thread2 = new Thread(() -> validate.validateEventID(this.eventID));
        Thread thread3 = new Thread(() -> validate.validateStartHour(this.startHour));
        Thread thread4 = new Thread(() -> validate.validateStartMinute(this.startMinute));
        Thread thread5 = new Thread(() -> validate.validateEndHour(this.endHour));
        Thread thread6 = new Thread(() -> validate.validateEndMinute(this.endMinute));
        Thread thread7 = new Thread(() -> validate.validateType(this.type));
        Thread thread8 = new Thread(() -> validate.validateLink(this.link));
        Thread thread9 = new Thread(() -> validate.validateDate(this.year));
        Thread thread10 = new Thread(() -> validate.validateDate(this.year, this.month));
        Thread thread11 = new Thread(() -> validate.validateDate(this.year, this.month, this.day));
        Thread thread12 = new Thread(() -> validate.validateName(this.activityName));
        Thread thread13 = new Thread(() -> validate.validateActivityNo(this.activityNumber));
        // starting threads excluding thread 11
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
        thread12.start();
        thread13.start();
        try
        {
            // waiting to complete thread 9 and 10
            thread9.join();
            thread10.join();
            // start thread 11 if year and month are valid
            if(validate.validYear && validate.validMonth)
            {
                thread11.start();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            // waiting to complete all other threads
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
            thread7.join();
            thread8.join();
            thread11.join();
            thread12.join();
            thread13.join();
        }
        catch
        (Exception e)
        {
            e.printStackTrace();
        }

    }
}

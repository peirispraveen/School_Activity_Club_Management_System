package com.example.sacms.EventScheduling;

public class Event extends EventParent
{
    private String name;
    private String place;
    public String[] values;

    public Event(){}

    public Event(String clubID, String eventID, String year, String month, String day, String startHour,
                 String startMinute, String endHour, String endMinute, String name, String place) {
        super(clubID, eventID, year, month, day, startHour, startMinute, endHour, endMinute);
        this.name = name;
        this.place = place;
    }

    @Override
    public void createEvent()
    {
        EventValidator validate = new EventValidator();
        Thread thread1 = new Thread(() -> validate.validateClubID(this.clubID));
        Thread thread2 = new Thread(() -> validate.validateEventID(this.eventID));
        Thread thread3 = new Thread(() -> validate.validateStartHour(this.startHour));
        Thread thread4 = new Thread(() -> validate.validateStartMinute(this.startMinute));
        Thread thread5 = new Thread(() -> validate.validateEndHour(this.endHour));
        Thread thread6 = new Thread(() -> validate.validateEndMinute(this.endMinute));
        Thread thread7 = new Thread(() -> validate.validateName(this.name));
        Thread thread8 = new Thread(() -> validate.validatePlace(this.place));
        Thread thread9 = new Thread(() -> validate.validateDate(this.year));
        Thread thread10 = new Thread(() -> validate.validateDate(this.year, this.month));
        Thread thread11 = new Thread(() -> validate.validateDate(this.year, this.month, this.day));
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
        try
        {
            thread9.join();
            thread10.join();
            if(validate.validYear && validate.validMonth)
            {
                thread11.start();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        try
        {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
            thread7.join();
            thread8.join();
            thread11.join();
        }
        catch
        (Exception e)
        {
            System.out.println(e);
        }

    }

}

package com.example.EventScheduling;

public class PostponeEvent extends EventParent
{
    // variables to sotre clubID and eventID statically
    public static String eventID;
    public static String clubID;
    // array to store all attributes dynamically
    public String values[];

    // default constructor
    public PostponeEvent(){}

    // parametarized constructor
    public PostponeEvent(String year, String month, String day, String startHour,
                       String startMinute, String endHour, String endMinute)
    {
        // calling parent class constructor
        super(year, month, day, startHour, startMinute, endHour, endMinute);
    }

    // implementing parent class abstract method
    public void createEvent()
    {
        EventValidator validate = new EventValidator();
        // creating threads
        Thread thread1 = new Thread(() -> validate.validateStartHour(this.startHour));
        Thread thread2 = new Thread(() -> validate.validateStartMinute(this.startMinute));
        Thread thread3 = new Thread(() -> validate.validateEndHour(this.endHour));
        Thread thread4 = new Thread(() -> validate.validateEndMinute(this.endMinute));
        Thread thread5 = new Thread(() -> validate.validateDate(this.year));
        Thread thread6 = new Thread(() -> validate.validateDate(this.year, this.month));
        Thread thread7 = new Thread(() -> validate.validateDate(this.year, this.month, this.day));

        // starting threads excluding thread 7
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        try
        {
            thread5.join();
            thread6.join();
            // if year and month are valid start thread 7
            if(validate.validYear && validate.validMonth)
            {
                thread7.start();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            // waiting other threads to be completed
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread7.join();
        }
        catch
        (Exception e)
        {
            e.printStackTrace();
        }


    }
}

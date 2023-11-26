package com.example.sacms.EventScheduling;

public class PostponeEvent extends EventParent
{
    public static String eventID;
    public static String clubID;
    public String values[];
    public PostponeEvent(String year, String month, String day, String startHour,
                       String startMinute, String endHour, String endMinute) {
        super(year, month, day, startHour, startMinute, endHour, endMinute);
    }

    public void createEvent()
    {
        EventValidator validate = new EventValidator();
        Thread thread1 = new Thread(() -> validate.validateStartHour(this.startHour));
        Thread thread2 = new Thread(() -> validate.validateStartMinute(this.startMinute));
        Thread thread3 = new Thread(() -> validate.validateEndHour(this.endHour));
        Thread thread4 = new Thread(() -> validate.validateEndMinute(this.endMinute));
        Thread thread5 = new Thread(() -> validate.validateDate(this.year));
        Thread thread6 = new Thread(() -> validate.validateDate(this.year, this.month));
        Thread thread7 = new Thread(() -> validate.validateDate(this.year, this.month, this.day));

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
            if(validate.validYear && validate.validMonth)
            {
                thread7.start();
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
            thread7.join();
        }
        catch
        (Exception e)
        {
            System.out.println(e);
        }


    }
}

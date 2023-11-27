package com.example.EventScheduling;

// interface to be implemented where the validations needed to be done
public interface EventValidation
{
    void validateClubID(String clubID);
    void validateEventID(String eventID);
    void validateDate(String year);
    void validateDate(String year, String month);
    void validateDate(String year, String month, String day);
    boolean validateHour(String hour);
    boolean validateMinute(String minute);
}

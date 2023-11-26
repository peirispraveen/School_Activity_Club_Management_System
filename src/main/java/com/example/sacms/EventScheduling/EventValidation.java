package com.example.sacms.EventScheduling;

import java.util.regex.Matcher;

public interface EventValidation
{
    void validateDate(String year);
    void validateDate(String year, String month);
    void validateDate(String year, String month, String day);
    boolean validateHour(String hour);
    boolean validateMinute(String minute);
    boolean validateInt(String intValue);
    boolean validateString(String str);
    void validateLink(String link);

}

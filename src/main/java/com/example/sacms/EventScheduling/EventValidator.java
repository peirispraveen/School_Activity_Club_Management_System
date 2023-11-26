package com.example.sacms.EventScheduling;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventValidator implements EventValidation
{

    private String eventID;
    private static boolean validClubID;
    private static boolean validEventID;
    protected static boolean validYear;
    protected static boolean validMonth;
    private static boolean validType;
    private static boolean validPlace;
    private static boolean validPlatform;
    private static boolean validDay;
    private static boolean validStartHour;
    private static boolean validStartMin;
    private static boolean validLink;
    private static boolean validEndHour;
    private static boolean validEndMin;
    private static boolean validNum;
    private static boolean validName;
    private static boolean validActivityNo;


    public EventValidator(){}
    public EventValidator(String eventID)
    {
        this.eventID = eventID;
        validateEventID(eventID);
    }

    public static boolean isValidClubID() {
        return validClubID;
    }

    public static boolean isValidEventID() {
        return validEventID;
    }

    public static boolean isValidYear() {
        return validYear;
    }

    public static boolean isValidMonth() {
        return validMonth;
    }

    public static boolean isValidType() {
        return validType;
    }

    public static boolean isValidPlace() {
        return validPlace;
    }

    public static boolean isValidPlatform() {
        return validPlatform;
    }

    public static boolean isValidDay() {
        return validDay;
    }

    public static boolean isValidStartHour() {
        return validStartHour;
    }

    public static boolean isValidStartMin() {
        return validStartMin;
    }

    public static boolean isValidEndHour() {
        return validEndHour;
    }

    public static boolean isValidEndMin() {
        return validEndMin;
    }

    public static boolean isValidNum() {
        return validNum;
    }

    public static boolean isValidLink() {
        return validLink;
    }

    public static boolean isValidName() {
        return validName;
    }

    public static boolean isValidActivityNo() {
        return validActivityNo;
    }

    public synchronized boolean validateInt(String integer)
    {
        try
        {
            int integerInt = Integer.parseInt(integer);
            if((integer.equals(Integer.toString(integerInt))) && integerInt > -1)
            {
                return true;
            }
            return false;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public void validateActivityNo(String activityNo)
    {
        if(validateInt(activityNo))
        {
            validActivityNo = true;
        }
        else
        {
            validActivityNo = false;
        }
    }
    public void validateClubID(String clubID)
    {
        if(validateInt(clubID))
        {
            validClubID = true;
        }
        else
        {
            validClubID = false;
        }
    }

    public void validateEventID(String eventID)
    {
        if(validateInt(eventID))
        {
            validEventID = true;
        }
        else {
            validEventID = false;
        }
    }

    public void validateNum(String num)
    {
        if(validateInt(num))
        {
            validNum = true;
        }
        else
        {
            validNum = false;
        }
    }
    public void validateStartHour(String startHour)
    {
        if(validateHour(startHour))
        {
            validStartHour = true;
        }
        else
        {
            validStartHour = false;
        }
    }

    public void validateEndHour(String endHour)
    {
        if(validateHour(endHour))
        {
            validEndHour = true;
        }
        else
        {
            validEndHour = false;
        }
    }

    public void validateStartMinute(String startMinute)
    {
        if(validateMinute(startMinute))
        {
            validStartMin = true;
        }
        else
        {
            validStartMin = false;
        }
    }

    public void validateEndMinute(String endMinute)
    {
        if(validateMinute(endMinute))
        {
            validEndMin = true;
        }
        else
        {
            validEndMin = false;
        }
    }

    public void validateDate(String year)
    {
        if (validateInt(year))
        {
            int yearInt = Integer.parseInt(year);
            if(yearInt >= 2023)
            {
                validYear = true;
            }
            else
            {
                validYear = false;
            }
        }
        else
        {
            validYear = false;
        }
    }


    public void validateDate(String year, String month)
    {
        if(validateInt(month))
        {
                int monthInt = Integer.parseInt(month);
                if (monthInt >= 1 && monthInt <= 12)
                {
                    validMonth = true;
                }
                else
                {
                    validMonth = false;
                }
        }
        else
        {
            validMonth = false;
        }
    }
    public void validateDate(String yearStr, String monthStr, String day)
    {
        if(isValidYear() && isValidMonth())
        {
            int year = Integer.parseInt(yearStr);
            int month = Integer.parseInt(monthStr);
            if (validateInt(day))
            {
                int dayInt = Integer.parseInt(day);
                {
                    if (year % 4 == 0) {
                        if (month == 2) {
                            if (dayInt > 0 && dayInt < 30) {
                                validDay = true;
                            }
                        }
                    } else {
                        if (month == 2) {
                            if (dayInt > 0 && dayInt < 29) {
                                validDay = true;
                            }
                        }
                    }
                    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 ||
                            month == 10 || month == 12) {
                        if (dayInt > 0 && dayInt < 32) {
                            validDay = true;
                        }
                    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                        if (dayInt > 0 && dayInt < 31) {
                            validDay = true;
                        }
                    }
                }
            } else
            {
                validDay = false;
            }
        }
    }

    public synchronized boolean validateHour(String hour)
    {
        if(validateInt(hour)) {
            int hourInt = Integer.parseInt(hour);
            if((hourInt > -1) && (hourInt < 25))
            {
                return true;
            }
            return false;
        }
        return false;
    }
    public synchronized boolean validateMinute(String minute)
    {
        if(validateInt(minute)) {
            int minInt = Integer.parseInt(minute);
            if((minInt > -1) && (minInt < 61))
            {
                return true;
            }
            return false;
        }
        return false;
    }

    public synchronized boolean validateString(String str)
    {
        try
        {
            double strDouble = Double.parseDouble(str);
            return false;
        }
        catch(Exception e)
        {
            if(str.isEmpty() || str.equals(" ")) {
                return false;
            }
            else
            {
                return true;
            }

        }
    }

    public void validateName(String name)
    {
        if(validateString(name))
        {
            validName = true;
        }
        else
        {
            validName = false;
        }
    }

    public void validateType(String type)
    {
        if(validateString(type))
        {
            validType = true;
        }
        else
        {
            validType = false;
        }
    }

    public void validatePlace(String place)
    {
        if(validateString(place))
        {
            validPlace = true;
        }
        else
        {
            validPlace = false;
        }
    }

    public void validatePlatform(String platform)
    {
        if(validateString(platform))
        {
            validPlatform = true;
        }
        else
        {
            validPlatform = false;
        }
    }

    public void validateLink(String link)
    {
        String urlRegex = "^(https?|ftp):\\/\\/[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+(\\/[^\\s]*)?$";

        Pattern pattern = Pattern.compile(urlRegex);
        Matcher matcher = pattern.matcher(link);
        if(matcher.matches())
        {
            validLink = true;
        }
        else
        {
            validLink = false;
        }
    }
}



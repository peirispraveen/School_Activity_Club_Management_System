package com.example.implementation;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// Validate each inputs from the user
public class ClubValidator implements ClubValidation {
    String id;
    String name;
    String description;
    ClubAdvisor advisor;
    Date date;

    @Override
    public boolean validateId(String id) {
        String regex="^[^;]{1,5}$";
        return id.matches(regex);

    }
    @Override
    public boolean validateName(String name){
        String regex="^[^;]{1,20}$";
        return name.matches(regex);
    }

    @Override
    public boolean validateDescription(String description){
        String regex="^[^;]{1,60}$";
        return description.matches(regex);
    }

    @Override
    public boolean validateAdvisor(String advisor){
        String regex="^[^;]{1,20}$";
        return advisor.matches(regex);
    }

    @Override
    public boolean validateMaxParticipants(String maxParticipants){
        String regex = "^[1-9]\\d{0,2}$";
        return maxParticipants.matches(regex);
    }
    @Override
    public boolean validateDate(String date) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";

        if (!date.matches(regex) ) {
            return false;
        }
        try {
            LocalDate givenDate = LocalDate.parse(date);
            LocalDate currentDate = LocalDate.now();
            return !givenDate.isAfter(currentDate);
        }catch (DateTimeParseException ignored){
            return false;
        }
    }

}

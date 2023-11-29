package com.example.implementation;

public interface ClubValidation {
    boolean validateId(String id);
    boolean validateName(String name);
    boolean validateDescription(String description);

    boolean validateAdvisor(String advisor);
    boolean validateMaxParticipants(String maxParticipants);
    boolean validateDate (String date);
}

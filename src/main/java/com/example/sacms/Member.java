package com.example.sacms;

public interface Member {

    // An interface that represents a student and an advisor as a member of SACMS
    String getId();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPassword();
    DateOfBirth getDateOfBirth();

    // Interface Testing
}

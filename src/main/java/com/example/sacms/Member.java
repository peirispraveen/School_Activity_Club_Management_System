package com.example.sacms;

import java.io.Serializable;

public interface Member extends Serializable {

    String getId();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPassword();
    DateOfBirth getDateOfBirth();

    // Interface Testing
}

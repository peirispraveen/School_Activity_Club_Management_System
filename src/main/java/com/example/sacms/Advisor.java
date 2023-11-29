package com.example.sacms;

public class Advisor implements Member {  // Member class is an interface
    // advisor implements certain characteristics of a member

    // Advisor details parameters
    private String advisorId;
    private String advisorFirstName;
    private String advisorLastName;
    private String advisorEmail;
    private DateOfBirth dateOfBirth;
    private String advisorPassword;

    public Advisor() {  // Default constructor
    }

    // Proper constructor
    public Advisor(String advisorId, String advisorFirstName, String advisorLastName, String advisorEmail, DateOfBirth dateOfBirth, String advisorPassword) {
        this.advisorId = advisorId;
        this.advisorFirstName = advisorFirstName;
        this.advisorLastName = advisorLastName;
        this.advisorEmail = advisorEmail;
        this.dateOfBirth = dateOfBirth;
        this.advisorPassword = advisorPassword;
    }

    // getters are overridden
    @Override
    public String getId() {
        return advisorId;
    }

    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
    }

    @Override
    public String getFirstName() {
        return advisorFirstName;
    }

    public void setAdvisorFirstName(String advisorFirstName) {
        this.advisorFirstName = advisorFirstName;
    }

    @Override
    public String getLastName() {
        return advisorLastName;
    }

    public void setAdvisorLastName(String advisorLastName) {
        this.advisorLastName = advisorLastName;
    }

    @Override
    public String getEmail() {
        return advisorEmail;
    }

    public void setAdvisorEmail(String advisorEmail) {
        this.advisorEmail = advisorEmail;
    }

    public String getPassword() {
        return advisorPassword;
    }

    public void setAdvisorPassword(String advisorPassword) {
        this.advisorPassword = advisorPassword;
    }

    @Override
    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}

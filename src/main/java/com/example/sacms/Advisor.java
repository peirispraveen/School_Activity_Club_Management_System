package com.example.sacms;

public class Advisor implements Member {
    private String advisorId;
    private String advisorFirstName;
    private String advisorLastName;
    private String advisorEmail;
    private DateOfBirth dateOfBirth;
    private String advisorPassword;

    public Advisor() {
    }

    public Advisor(String advisorId, String advisorFirstName, String advisorLastName, String advisorEmail, DateOfBirth dateOfBirth, String advisorPassword) {
        this.advisorId = advisorId;
        this.advisorFirstName = advisorFirstName;
        this.advisorLastName = advisorLastName;
        this.advisorEmail = advisorEmail;
        this.dateOfBirth = dateOfBirth;
        this.advisorPassword = advisorPassword;
    }

    public String getAdvisorId() {
        return advisorId;
    }

    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
    }

    public String getAdvisorFirstName() {
        return advisorFirstName;
    }

    public void setAdvisorFirstName(String advisorFirstName) {
        this.advisorFirstName = advisorFirstName;
    }

    public String getAdvisorLastName() {
        return advisorLastName;
    }

    public void setAdvisorLastName(String advisorLastName) {
        this.advisorLastName = advisorLastName;
    }

    public String getAdvisorEmail() {
        return advisorEmail;
    }

    public void setAdvisorEmail(String advisorEmail) {
        this.advisorEmail = advisorEmail;
    }

    public String getAdvisorPassword() {
        return advisorPassword;
    }

    public void setAdvisorPassword(String advisorPassword) {
        this.advisorPassword = advisorPassword;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static Advisor parseAdvisor(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid data format in the file.");
        }

        String advisorId = parts[0].trim();
        String advisorFirstName = parts[1].trim();
        String advisorLastName = parts[2].trim();
        String advisorEmail = parts[3].trim();

        // Extracting date of birth components
        String[] dobParts = parts[4].trim().split("/");
        if (dobParts.length != 3) {
            throw new IllegalArgumentException("Invalid date of birth format.");
        }

        int day = Integer.parseInt(dobParts[0]);
        int month = Integer.parseInt(dobParts[1]);
        int year = Integer.parseInt(dobParts[2]);

        DateOfBirth dateOfBirth = new DateOfBirth(day, month, year);

        String advisorPassword = parts[5].trim();

        return new Advisor(advisorId, advisorFirstName, advisorLastName, advisorEmail, dateOfBirth, advisorPassword);
    }
}

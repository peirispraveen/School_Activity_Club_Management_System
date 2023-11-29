package com.example.demo;

// Class implementing validations interface for student attendance records
public class Att_Validate implements validations {

    // Method to check and validate student ID
    @Override
    public void stuIdCheck(String studentId) {
        if (studentId.startsWith("S")) {
            System.out.println("Student ID is valid.");
        } else {
            System.out.println("Invalid Student ID. It should start with 'S'.");
        }
    }

    // Method to check and validate advisor ID
    @Override
    public void advidCheck(String teacherId) {
        if (teacherId.startsWith("T")) {
            System.out.println("Teacher ID is valid.");
        } else {
            System.out.println("Invalid Teacher ID. It should start with 'T'.");
        }
    }

    // Method to check and validate club name
    @Override
    public void clubnameCheck(String clubName) {
        if (clubName.matches("[a-zA-Z]+")) {
            System.out.println("Club name is valid.");
        } else {
            System.out.println("Invalid Club name. It should contain only letters.");
        }
    }
}


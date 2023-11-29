package com.example.demo;

// Interface for validation methods related to student records
public interface validations {

    // Method to check and validate student ID
    void stuIdCheck(String studentId);

    // Method to check and validate advisor ID
    void advidCheck(String teacherId);

    // Method to check and validate club name
    void clubnameCheck(String clubName);
}


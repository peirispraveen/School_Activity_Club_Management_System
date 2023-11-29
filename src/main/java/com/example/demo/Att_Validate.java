package com.example.demo;

public class Att_Validate implements validations {
    @Override
    public void stuIdCheck(String studentId) {
        if (studentId.startsWith("S")) {
            System.out.println("Student ID is valid.");
        } else {
            System.out.println("Invalid Student ID. It should start with 'S'.");
        }
    }

    @Override
    public void advidCheck(String teacherId) {
        if (teacherId.startsWith("T")) {
            System.out.println("Teacher ID is valid.");
        } else {
            System.out.println("Invalid Teacher ID. It should start with 'T'.");
        }
    }

    @Override
    public void clubnameCheck(String clubName) {
        if (clubName.matches("[a-zA-Z]+")) {
            System.out.println("Club name is valid.");
        } else {
            System.out.println("Invalid Club name. It should contain only letters.");
        }
    }
}

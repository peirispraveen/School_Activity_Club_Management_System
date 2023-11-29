package com.example.sacms;

public class Student implements Member{  // student is a member
    // student implements certain characteristics of a member

    // student details parameters
    private String studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
    private DateOfBirth dateOfBirth;
    private String studentPassword;

    public Student() {  // empty constructor for good practice
    }

    public Student(String studentId, String studentFirstName, String studentLastName, String studentEmail, DateOfBirth dateOfBirth, String studentPassword) {
        this.studentId = studentId;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentEmail = studentEmail;
        this.dateOfBirth = dateOfBirth;
        this.studentPassword = studentPassword;
    }

    @Override
    public String getId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String getFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    @Override
    public String getLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    @Override
    public String getEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    @Override
    public String getPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    @Override
    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}

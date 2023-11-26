package com.example.sacms;

public class Student implements Member{
    private String studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
    private DateOfBirth dateOfBirth;
    private String studentPassword;

    public Student() {
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

//    public static Student parseStudent(String line) {
//        String[] parts = line.split(",");
//        if (parts.length != 6) {
//            throw new IllegalArgumentException("Invalid data format in the file.");
//        }
//
//        String studentId = parts[0].trim();
//        String studentFirstName = parts[1].trim();
//        String studentLastName = parts[2].trim();
//        String studentEmail = parts[3].trim();
//
//        // Extracting date of birth components
//        String[] dobParts = parts[4].trim().split("/");
//        if (dobParts.length != 3) {
//            throw new IllegalArgumentException("Invalid date of birth format.");
//        }
//
//        int day = Integer.parseInt(dobParts[0]);
//        int month = Integer.parseInt(dobParts[1]);
//        int year = Integer.parseInt(dobParts[2]);
//
//        DateOfBirth dateOfBirth = new DateOfBirth(day, month, year);
//
//        String studentPassword = parts[5].trim();
//
//        return new Student(studentId, studentFirstName, studentLastName, studentEmail, dateOfBirth, studentPassword);
//    }
}

package com.example.sacms;

import java.util.regex.Pattern;

public class Regex {
    private String email;
    private String name;
    private String password;

    private static final String regexStId = "^ST\\d{3}$"; // Student ID Regex validator
    private static final String regexAdId = "^AD\\d{3}$"; // Advisor ID Regex validator
    private static final String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";  // Email Regex validator

    // Name regex validator
    // No special characters or numbers
//    private static final String regexName = "^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)";
    private static final String regexName2 = "^[A-Za-z]+$";

    // Password regex validator
    private static final String regexPass = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[^\\s]{8,}$";


    public static boolean stIdPatternMatches(String id) {  // method for student id validation
        return Pattern.compile(regexStId)
                .matcher(id)
                .matches();
    }

    public static boolean adIdPatternMatches(String id) {  // method for advisor id validation
        return Pattern.compile(regexAdId)
                .matcher(id)
                .matches();
    }

    public static boolean emailPatternMatches(String email) {  // email validation method
        return Pattern.compile(regexEmail)
                .matcher(email)
                .matches();
    }

    public static boolean namePatternMatches(String name) {  // name validation method
        return Pattern.compile(regexName2)
                .matcher(name)
                .matches();
    }

    public static boolean passwordPatternMatches(String password) {  // password validation method
        return Pattern.compile(regexPass)
                .matcher(password)
                .find();
    }

    public static void main(String[] args) {  // testing
        if(stIdPatternMatches("ST3225")){
            System.out.println("Success");
        }else {
            System.out.println("nop");
        }
    }

}

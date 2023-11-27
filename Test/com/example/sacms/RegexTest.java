package com.example.sacms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegexTest {

    @Test
    void emailPatternMatches() {
        boolean test1 = Regex.emailPatternMatches("praveen@example.com");
        boolean test2 = Regex.emailPatternMatches("sample.email.com");  // No @ symbol
        boolean test3 = Regex.emailPatternMatches("something.332@iit.ac.lk");
        boolean test4 = Regex.emailPatternMatches("amazing@example");  // No .com or .lk, etc.
        assertTrue(test1); // Correct email
        assertFalse(test2); // Wrong email
        assertTrue(test3); // Correct email
        assertFalse(test4); // Wrong email
    }

    @Test
    void namePatternMatches() {
        boolean test1 = Regex.namePatternMatches("Praveen");
        boolean test2 = Regex.namePatternMatches("John99");  // Must not contain special characters
        boolean test3 = Regex.namePatternMatches("Fernando");
        assertTrue(test1); // Correct name
        assertFalse(test2); // Wrong name
        assertTrue(test3); // Correct name
    }

    @Test
    void passwordPatternMatches() {
        boolean test1 = Regex.passwordPatternMatches("Hdeec33msss");
        boolean test2 = Regex.passwordPatternMatches("sdfdsfse32"); // No upper case letter
        boolean test3 = Regex.passwordPatternMatches("DSDssdefii"); // No number
        boolean test4 = Regex.passwordPatternMatches("sdf32W"); // Lesser than 8 characters
        assertTrue(test1); // Correct password
        assertFalse(test2); // Wrong password
        assertFalse(test3); // Wrong password
        assertFalse(test4); // Wrong password
    }
}
package com.example.sacms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegexTest {

    @Test
    void emailPatternValid1() {
        boolean test = Regex.emailPatternMatches("praveen@example.com");
        assertTrue(test);
    }

    @Test
    void emailPatternValid2() {
        boolean test = Regex.emailPatternMatches("something.332@iit.ac.lk");
        assertTrue(test);
    }

    @Test
    void emailPatternInt()
    {
        boolean test = Regex.emailPatternMatches("2");
        assertFalse(test);
    }

    @Test
    void emailPatternDouble()
    {
        boolean test = Regex.emailPatternMatches("2.2");
        assertFalse(test);
    }

    @Test
    void emailPatternString()
    {
        boolean test = Regex.emailPatternMatches("str");
        assertFalse(test);
    }

    @Test
    void emailPatternEmpty()
    {
        boolean test = Regex.emailPatternMatches("");
        assertFalse(test);
    }

    @Test
    void emailPatternNoAt()
    {
        boolean test = Regex.emailPatternMatches("sample.email.com");
        assertFalse(test);
    }

    @Test
    void emailPatternNoDomainType()
    {
        boolean test = Regex.emailPatternMatches("amazing@example");
        assertFalse(test);
    }

    @Test
    void namePatternValid1() {
        boolean test = Regex.namePatternMatches("Praveen");
        assertTrue(test);
    }

    @Test
    void namePatternValid2() {
        boolean test = Regex.namePatternMatches("Fernando");
        assertTrue(test);
    }
     @Test
    void namePatternInvalidStr() {
        boolean test = Regex.namePatternMatches("Fernando99");
        assertFalse(test);
    }

    @Test
    void namePatternInt() {
        boolean test = Regex.namePatternMatches("99");
        assertFalse(test);
    }

    @Test
    void namePatternDouble() {
        boolean test = Regex.namePatternMatches("99.9");
        assertFalse(test);
    }

    @Test
    void namePatternEmpty() {
        boolean test = Regex.namePatternMatches("");
        assertFalse(test);
    }

    @Test
    void namePatternEmail() {
        boolean test = Regex.namePatternMatches("example@example.com");
        assertFalse(test);
    }
    @Test
    void passwordPatternValid() {
        boolean test = Regex.passwordPatternMatches("Hdeec33msss");
        assertTrue(test);

    }

    @Test
    void passwordPatternCoUpperCase() {
        boolean test = Regex.passwordPatternMatches("sdfdsfse32");
        assertFalse(test);
    }
    @Test
    void passwordPatternNoInt() {
        boolean test = Regex.passwordPatternMatches("DSDssdefii");
        assertFalse(test);
    }
    @Test
    void passwordPatternEmpty() {
        boolean test = Regex.passwordPatternMatches("");
        assertFalse(test);
    }

    @Test
    void passwordPatternInt() {
        boolean test = Regex.passwordPatternMatches("99");
        assertFalse(test);
    }

    @Test
    void passwordPatternDouble() {
        boolean test = Regex.passwordPatternMatches("99.9");
        assertFalse(test);
    }

    @Test
    void passwordPatternSpecialChar() {
        boolean test = Regex.passwordPatternMatches("exAmplEexample21$");
        assertTrue(test);
    }
}
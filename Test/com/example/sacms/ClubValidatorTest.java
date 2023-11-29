package com.example.sacms;

import com.example.implementation.ClubValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClubValidatorTest {
    @Test
    public void validCode1(){
        String code = "C0001";
        boolean expected = true;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateId(code);
        assertEquals(expected,actual);
    }
    @Test
    public void validCode2(){
        String code = "C001";
        boolean expected = true;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateId(code);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidCode1(){
        String code ="C00007";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateId(code);
        assertEquals(expected,actual);
    }
    @Test
    public void inValidCode2(){
        String code ="C005;";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateId(code);
        assertEquals(expected,actual);
    }
    @Test
    public void inValidCode3(){
        String code ="";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateId(code);
        assertEquals(expected,actual);
    }

    @Test
    public void validName1(){
        String name="Gryffindor";
        boolean expected = true;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateName(name);
        assertEquals(expected,actual);
    }
    @Test
    public void validName2(){
        String name="Gryffindor 23";
        boolean expected = true;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateName(name);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidName1(){
        String name="Gryffindor;";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateName(name);
        assertEquals(expected,actual);
    }
    @Test
    public void inValidName2(){
        String name="The Club which has more than 20 characters";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateName(name);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidName3(){
        String name="";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateName(name);
        assertEquals(expected,actual);
    }

    @Test
    public void validDescription1(){
        String description="The club which organizes something";
        boolean expected = true;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateDescription(description);
        assertEquals(expected,actual);
    }

    @Test
    public void validDescription2(){
        String description="The club which contains number 123";
        boolean expected = true;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateDescription(description);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidDescription1(){
        String description="The club which contains semi colon ;";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateDescription(description);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidDescription2(){
        String description="The club which contains semi colon which may contain more than 60 characters. But i think it doesn't have sixty characters until now";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateDescription(description);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidDescription3(){
        String description="";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateDescription(description);
        assertEquals(expected,actual);
    }

    @Test
    public void validAdvisor1(){
        String advisor="Harry Potter";
        boolean expected = true;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateAdvisor(advisor);
        assertEquals(expected,actual);
    }
    @Test
    public void validAdvisor2(){
        String advisor="With number234";
        boolean expected = true;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateAdvisor(advisor);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidAdvisor1(){
        String advisor="Included semicolon ;";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateAdvisor(advisor);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidAdvisor2(){
        String advisor="This is for the large characters greater than count of 20";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateAdvisor(advisor);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidAdvisor3(){
        String advisor="";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateAdvisor(advisor);
        assertEquals(expected,actual);
    }

    @Test
    public void validMaxParticipants1(){
        String maxNumber="10";
        boolean expected = true;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateMaxParticipants(maxNumber);
        assertEquals(expected,actual);
    }

    @Test
    public void validMaxParticipants2(){
        String maxNumber="999";
        boolean expected = true;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateMaxParticipants(maxNumber);
        assertEquals(expected,actual);
    }


    @Test
    public void inValidMaxParticipants1(){
        String maxNumber="";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateMaxParticipants(maxNumber);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidMaxParticipants2(){
        String maxNumber="-45";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateMaxParticipants(maxNumber);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidMaxParticipants3(){
        String maxNumber="1000";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateMaxParticipants(maxNumber);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidMaxParticipants4(){
        String maxNumber="five";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateMaxParticipants(maxNumber);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidMaxParticipants5(){
        String maxNumber="45;";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateMaxParticipants(maxNumber);
        assertEquals(expected,actual);
    }


    @Test
    public void validDate(){
        String date="2022-03-05";
        boolean expected = true;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateDate(date);
        assertEquals(expected,actual);
    }


    @Test
    public void inValidDate1(){
        String date="05-03-2022";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateDate(date);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidDate2(){
        String date="number";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateDate(date);
        assertEquals(expected,actual);
    }
    @Test
    public void inValidDate3(){
        String date="543";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateDate(date);
        assertEquals(expected,actual);
    }
    @Test
    public void inValidDate4(){
        String date="2024-12-12";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateDate(date);
        assertEquals(expected,actual);
    }

    @Test
    public void inValidDate5(){
        String date="2022-02-30";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateDate(date);
        assertEquals(expected,actual);
    }
    @Test
    public void inValidDate6(){
        String date="2022-13-30";
        boolean expected = false;
        ClubValidator validator=new ClubValidator();
        boolean actual=validator.validateDate(date);
        assertEquals(expected,actual);
    }


}
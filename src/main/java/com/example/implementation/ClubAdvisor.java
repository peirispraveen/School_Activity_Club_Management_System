package com.example.implementation;

import java.util.ArrayList;
import java.util.List;

public class ClubAdvisor {
    private String advisorId;
    private String fName;
    private String lName;
    private String email;
    private String contactNumber;
    private List<Club> advisingClubs;


    public ClubAdvisor(String fName,String lName){
        this.fName=fName;
        this.lName=lName;
    }

    public ClubAdvisor(String advisorId, String fName, String lName, String email, String contactNumber) {
        this.advisorId = advisorId;
        this.fName = fName;
        this.lName = lName;
        this.email=email;
        this.contactNumber = contactNumber;
    }

    public String getAdvisorId() {
        return advisorId;
    }
    public void setAdvisorId(String advisorId) {
        this.advisorId = advisorId;
    }
    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<Club> getAdvisingClubs() {
        return advisingClubs;
    }

    public void setAdvisingClubs(List<Club> advisingClubs) {
        this.advisingClubs = advisingClubs;
    }

    public void addClub(Club club){

    }
    public void removeClub(Club club){}

    @Override
    public String toString(){
        return getFName()+" "+getLName();
    }
}

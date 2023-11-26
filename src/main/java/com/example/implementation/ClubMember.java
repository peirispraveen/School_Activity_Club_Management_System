package com.example.implementation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ClubMember implements Comparable<ClubMember>{
    private String memberId;
    private String fName;
    private String lName;
    private Date dob;
    private Date joinedDate;
    private ArrayList<Club> joinedClubs=new ArrayList<>();
    private String contactNumber;
    static ObservableList<ClubMember> availableMembers = FXCollections.observableArrayList();

    public ClubMember(String id, String fName, String lName,Date dob,Date joinedDate, String contactNumber){
        this.memberId = id;
        this.fName = fName;
        this.lName=lName;
        this.dob=dob;
        this.joinedDate=joinedDate;
        this.contactNumber = contactNumber;
    }

    public ClubMember(String memberId, String fName, String lName) {
        this.memberId = memberId;
        this.fName=fName;
        this.lName = lName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public void setLName(String lName) {
        this.lName = lName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void viewClubDetails(){}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ClubMember that = (ClubMember) obj;

        return fName.equals(that.fName) && lName.equals(that.lName);
    }
    @Override
    public String toString() {
        return getFName()+" "+getLName();
    }

    @Override
    public int compareTo(ClubMember other) {
        return this.memberId.compareTo(other.memberId);
    }
}

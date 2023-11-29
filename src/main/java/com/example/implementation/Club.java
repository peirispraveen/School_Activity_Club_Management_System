package com.example.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// This is my Club
public class Club implements Comparable<Club> {
    private String clubId;
    private String clubName;
    private String clubDescription;
    private ClubAdvisor clubAdvisor;
    private int maxParticipants;
    private int currentParticipants;

    private Date createdDate;

    private List<ClubMember> clubMembers=new ArrayList<>();

    public Club(String clubId, String clubName, String clubDescription, ClubAdvisor advisor, int maxNumber, Date createdDate, List<ClubMember> clubMembers) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
        this.clubAdvisor = advisor;
        this.maxParticipants = maxNumber;
        this.createdDate = createdDate;
    }

    public Club(String clubId, String clubName, String clubDescription, ClubAdvisor advisor, int maxNumber, int currentParticipants, Date createdDate) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
        this.clubAdvisor = advisor;
        this.maxParticipants = maxNumber;
        this.currentParticipants = currentParticipants;
        this.createdDate = createdDate;
    }

    public Club(String clubId, String clubName, String clubDescription, ClubAdvisor advisor, int maxNumber, Date createdDate) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubDescription = clubDescription;
        this.clubAdvisor = advisor;
        this.maxParticipants = maxNumber;
        this.createdDate = createdDate;
    }

    public Club(String clubId, String clubName) {
        this.clubId = clubId;
        this.clubName = clubName;
    }

    public Club(String clubId) {
        this.clubId=clubId;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubDescription() {
        return clubDescription;
    }

    public void setClubDescription(String clubDescription) {
        this.clubDescription = clubDescription;
    }

    public ClubAdvisor getClubAdvisor() {
        return clubAdvisor;
    }

    public void setClubAdvisor(ClubAdvisor clubAdvisor) {
        this.clubAdvisor = clubAdvisor;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public int getCurrentParticipants() {
        return currentParticipants;
    }

    public void setCurrentParticipants(int currentParticipants) {
        this.currentParticipants = currentParticipants;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<ClubMember> getClubMembers() {
        return clubMembers;
    }

    public void setClubMembers(List<ClubMember> clubMembers) {
        this.clubMembers = clubMembers;
    }


//    To add member from each row
    public void addMember(ClubMember member) {
        clubMembers.add(member);
    }

//    To sort in the Observable list
    @Override
    public int compareTo(Club other) {
        return this.clubId.compareTo(other.clubId);
    }

    @Override
    public String toString() {
        return getClubName();
    }


}

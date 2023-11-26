package com.example.sacms;

public class Club {
    private String clubId;
    private String clubName;
    private Advisor clubAdvisor;

    public Club(String clubId, String clubName, Advisor clubAdvisor) {
        this.clubId = clubId;
        this.clubName = clubName;
        this.clubAdvisor = clubAdvisor;
    }

    public Club() {
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

    public Advisor getClubAdvisor() {
        return clubAdvisor;
    }

    public void setClubAdvisor(Advisor clubAdvisor) {
        this.clubAdvisor = clubAdvisor;
    }
}

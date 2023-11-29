package com.example.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.sql.*;


public class Storage {
    public static List<Club> availableClubs=new ArrayList<>();
    public static List<ClubAdvisor> availableClubAdvisor=new ArrayList<>();
    public static List<ClubMember> availableMembers=new ArrayList<>();


    public static List<Club> getAvailableClubs(){
        return availableClubs;
    }

    public static List<ClubAdvisor> getAvailableClubAdvisor(){

        return Storage.availableClubAdvisor;
    }
    public static List<ClubMember> getAvailableClubMembers(){

        return Storage.availableMembers;
    }

    public static final String url = "jdbc:mysql://localhost:3306/sacms";
    public static final String user = "root";
    public static final String password = "";

    public static void allAvailables() throws SQLException {

        Connection con = DriverManager.getConnection(url, user, password);
        Statement stmt = con.createStatement();

        String query = "SELECT * FROM student";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            availableMembers.add(new ClubMember(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6)));
        }


        query = "SELECT * FROM Club";
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            availableClubs.add(new Club(rs.getString(1), rs.getString(2), rs.getString(3), new ClubAdvisor(rs.getString(4)), rs.getInt(5), rs.getDate(6)));
        }

        query = "SELECT * FROM Advisor";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            availableClubAdvisor.add(new ClubAdvisor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        fillMembers();
    }

    public static void fillMembers() throws SQLException {
        String query = "SELECT sc.club_id,sc.student_id,s.first_name,s.last_name FROM `student_club` AS sc \n" +
                "JOIN student AS s\n" +
                "ON sc.student_id=s.student_id";
        Connection con=DriverManager.getConnection(url,user,password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            for (Club club : availableClubs) {
                if (club.getClubId().equals(rs.getString(1))) {
                    club.addMember(new ClubMember(rs.getString(2), rs.getString(3), rs.getString(4)));
                }
            }
        }
    }
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println("Class not found");
            e.printStackTrace();
        }

        System.out.println("Driver class registered");
        Connection sample = null;

        try {
            sample = DriverManager.getConnection(url, user, password);
        }catch (SQLException e2) {
            System.out.println("sql exception found");
            e2.printStackTrace();
            return;
        }

        if (sample != null){
            System.out.println("success");
        }else {
            System.out.println("failed to connect");
        }
    }

}

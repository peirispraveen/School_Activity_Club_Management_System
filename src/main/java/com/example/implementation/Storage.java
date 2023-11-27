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
    public static void allAvailables() throws SQLException {
        String url = "jdbc:mysql://localhost:3307/Club_Management";
        String user = "root";
        String password = "";

        Connection con = DriverManager.getConnection(url, user, password);
        Statement stmt = con.createStatement();


        String query = "SELECT * FROM Club_Members";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            availableMembers.add(new ClubMember(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getString(6)));
        }


        query = "SELECT * FROM Club";
        rs = stmt.executeQuery(query);

        while (rs.next()) {

            String[] name = rs.getString(4).split("\\s+");
            availableClubs.add(new Club(rs.getString(1), rs.getString(2), rs.getString(3), new ClubAdvisor(name[0], name[1]), rs.getInt(5), rs.getDate(6), Club.parseClubMembers(rs.getString(7))));
        }

        query = "SELECT * FROM Club_Advisor";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
            availableClubAdvisor.add(new ClubAdvisor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
    }

}

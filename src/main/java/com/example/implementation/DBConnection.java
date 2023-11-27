package com.example.implementation;

import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;

public class DBConnection {

    private static final String url = "jdbc:mysql://localhost:3307/Club_Management";
    private static final String username = "root";
    private static final String password = "";
    private static Connection con;
    private static Statement stmt;
    private static String sql;

    public static Statement getConnection() throws SQLException {
        con=DriverManager.getConnection(url, username, password);
        stmt=con.createStatement();
        return stmt;
    }


    public static void insertToDatabase(String clubId, String clubName, String clubDescription, ClubAdvisor advisor, int maxParticipants, Date createdDate) throws SQLException {

        stmt=getConnection();
        sql="INSERT INTO Club "+
                "Values("+"'"+clubId+"','"+clubName+"','"+clubDescription+
                "','"+advisor+"','"+maxParticipants+"','"+createdDate+"','"+"');";
        stmt.execute(sql);
        con.close();
    }

    public static void updateDatabaseClubMember(ObservableList<ClubMember> memberList, String clubId) throws SQLException {
        stmt=getConnection();
        String sql="UPDATE Club "+
                "SET `Club Members`= '"+String.valueOf(memberList).replaceAll("[\\[\\]]", "")
                +"' "+ "WHERE `Club ID`= '"+clubId+"' ";
        stmt.executeUpdate(sql);
        con.close();
    }

    public static void updateDatabaseClub(String clubId, String clubName, String clubDescription,
                                          String advisor, int maxParticipants, Date createdDate, String updateID) throws SQLException {
        stmt=getConnection();
        String sql="UPDATE Club "+
                "SET `Club id`= '"+clubId+"',`Club Name`= '"+clubName+
                "',`Club Description`= '"+clubDescription+"',`Club Advisor`= '"+advisor+
                "',`Max Number`= "+maxParticipants+",`Created Date`= '"+createdDate+
                "' WHERE `Club Id`= '"+updateID+"';";
        stmt.execute(sql);
        con.close();

    }

    public static void deleteClubDatabaseClub(String clubId) throws SQLException {
        stmt=getConnection();
        String sql="DELETE FROM Club "+
                "WHERE `Club Id` = '"+clubId+"'";
        stmt.execute(sql);
        con.close();
    }

}

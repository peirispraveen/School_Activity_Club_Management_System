package com.example.implementation;

import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DBConnection {

    private static final String url = "jdbc:mysql://localhost:3306/sacms";  //Muaadh port eka hadapan!!
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
                "','"+advisor+"','"+maxParticipants+"','"+createdDate+"'"+");";
        stmt.execute(sql);
        sql="INSERT INTO advisor_club Values('"+advisor+"','"+clubId+"');";
        stmt.execute(sql);

        con.close();
    }


    public static void updateDatabaseClubMember(ArrayList<String> memberList, String clubId) throws SQLException {
        stmt=getConnection();
        sql="DELETE FROM student_club WHERE club_id= '"+clubId+"'";
        stmt.execute(sql);
            for (String member : memberList) {
                try {
                String sql = "INSERT INTO student_club " +
                        "VALUES ('"+ member +
                        "','" + clubId + "') ";
                stmt.executeUpdate(sql);
            }catch(SQLIntegrityConstraintViolationException ignored){}
        }

        con.close();
    }

    public static void updateDatabaseClub(String clubId, String clubName, String clubDescription,
                                          String advisor, int maxParticipants, Date createdDate, String updateID) throws SQLException {
        stmt=getConnection();
        try {
            sql = "INSERT INTO Club VALUES('"+clubId+"','"+clubName+
                    "', '"+clubDescription+"','"+advisor+
                    "', '"+maxParticipants+"','"+createdDate+"');";
            System.out.println("Trying to insert "+sql);
            stmt.execute(sql);
            System.out.println("Inserted");

            sql="UPDATE advisor_club SET club_id= '"+clubId+"' WHERE club_id='"+updateID+"';";
            stmt.execute(sql);
            sql="UPDATE student_club SET club_id= '"+clubId+"' WHERE club_id='"+updateID+"';";
            stmt.executeUpdate(sql);
            sql="UPDATE eventparent SET club_id= '"+clubId+"' WHERE club_id='"+updateID+"';";
            stmt.executeUpdate(sql);
            System.out.println("All the tables are updated");
            for(Club club:Storage.availableClubs){
                System.out.println(club.getClubId());
                if (club.getClubId().equals(clubId)){
                    System.out.println("id found at " + club.getClubId());
                    club.setClubId(updateID);
                }
            }
            sql ="DELETE FROM Club WHERE club_id ='"+updateID+"';";
            System.out.println("Trying to delete");
            stmt.execute(sql);
            System.out.println("deleted");

        }catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            sql="UPDATE Club SET `club_Name`= '"+clubName+
                    "',`club_Description`= '"+clubDescription+"',`club_advisor_id`= '"+advisor+
                    "',`max_number`= '"+maxParticipants+"',`created_Date`= '"+createdDate+
                    "' WHERE `club_id`= '"+updateID+"';";
            stmt.execute(sql);
        }
        con.close();

    }

    public static void deleteDatabaseClub(String clubId) throws SQLException {
        stmt=getConnection();

        sql ="DELETE FROM advisor_club WHERE club_id='"+clubId+"';";
        stmt.execute(sql);
        sql="DELETE FROM student_club WHERE club_id='"+clubId+"';";
        stmt.execute(sql);
        sql="DELETE FROM eventparent WHERE club_id='"+clubId+"';";
        sql="DELETE FROM Club "+
                "WHERE `club_Id` = '"+clubId+"'";
        stmt.execute(sql);
        con.close();
    }

}

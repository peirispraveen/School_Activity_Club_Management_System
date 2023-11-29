package com.example.implementation;

import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DBConnection {

    private static final String url = "jdbc:mysql://localhost:3306/sacms";
    private static final String username = "root";
    private static final String password = "";
    private static Connection con;
    private static Statement stmt;
    private static String sql;

    // Connect the database
    public static Statement getConnection() throws SQLException {
        con=DriverManager.getConnection(url, username, password);
        stmt=con.createStatement();
        return stmt;
    }
    // Insert into the database
    public static void insertToDatabase(String clubId, String clubName, String clubDescription, ClubAdvisor advisor, int maxParticipants, Date createdDate) throws SQLException {
        stmt=getConnection();
        sql="INSERT INTO Club "+
                "Values("+"'"+clubId+"','"+clubName+"','"+clubDescription+
                "','"+advisor+"','"+maxParticipants+"','"+createdDate+"'"+");";
        stmt.execute(sql);
        sql="INSERT INTO advisor_club Values('"+advisor+"','"+clubId+"');"; // insert the club advisor to the foreign key table
        stmt.execute(sql);

        con.close();
    }

    // Update the members of the club in the database
    public static void updateDatabaseClubMember(ArrayList<String> memberList, String clubId) throws SQLException {
        stmt=getConnection();
        sql="DELETE FROM student_club WHERE club_id= '"+clubId+"'"; // First delete the table which has foreign key constraint
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

    // Update the profile of the club in the database
    public static void updateDatabaseClub(String clubId, String clubName, String clubDescription,
                                          String advisor, int maxParticipants, Date createdDate, String updateID) throws SQLException {
        stmt=getConnection();
        try {
            sql = "INSERT INTO Club VALUES('"+clubId+"','"+clubName+  // First try to insert the data given so that the previous would be deleted
                    "', '"+clubDescription+"','"+advisor+
                    "', '"+maxParticipants+"','"+createdDate+"');";
            stmt.execute(sql);

            sql="UPDATE advisor_club SET club_id= '"+clubId+"' WHERE club_id='"+updateID+"';"; // If it is inserted the the previous data from all table would be deleted
            stmt.execute(sql);
            sql="UPDATE student_club SET club_id= '"+clubId+"' WHERE club_id='"+updateID+"';";
            stmt.executeUpdate(sql);
            sql="UPDATE eventparent SET club_id= '"+clubId+"' WHERE club_id='"+updateID+"';";
            stmt.executeUpdate(sql);
            for(Club club:Storage.availableClubs){
                if (club.getClubId().equals(clubId)){
                    club.setClubId(updateID);
                }
            }
            sql ="DELETE FROM Club WHERE club_id ='"+updateID+"';";
            stmt.execute(sql);

        }catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            sql="UPDATE Club SET `club_Name`= '"+clubName+                                        // If an error occurred while inserting due to club name
                    "',`club_Description`= '"+clubDescription+"',`club_advisor_id`= '"+advisor+   // or id conflict update other data except id or name
                    "',`max_number`= '"+maxParticipants+"',`created_Date`= '"+createdDate+
                    "' WHERE `club_id`= '"+updateID+"';";
            stmt.execute(sql);
        }
        con.close();

    }

    // delete a club from the database
    public static void deleteDatabaseClub(String clubId) throws SQLException {
        stmt=getConnection();

        sql ="DELETE FROM advisor_club WHERE club_id='"+clubId+"';"; //deleting other tables which have the same
        stmt.execute(sql);                                           // id so that it doesn't conflict
        sql="DELETE FROM student_club WHERE club_id='"+clubId+"';";
        stmt.execute(sql);
        sql="DELETE FROM eventparent WHERE club_id='"+clubId+"';";
        sql="DELETE FROM Club "+
                "WHERE `club_Id` = '"+clubId+"'";
        stmt.execute(sql);
        con.close();
    }

}

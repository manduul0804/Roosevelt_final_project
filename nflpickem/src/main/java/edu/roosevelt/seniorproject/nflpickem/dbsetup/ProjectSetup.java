/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.roosevelt.seniorproject.nflpickem.dbsetup;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author merut
 */
public class ProjectSetup {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nflpickem",
                    "user", "user");

            System.out.println("Create USER/GAME (bases)");
            //USER goes first
            String sql = "CREATE TABLE USER (";
            sql = sql + " USERNAME VARCHAR(30) PRIMARY KEY,";
            sql = sql + " PASSWORD VARCHAR(20),";
            sql = sql + " ADMIN BOOLEAN,";
            sql = sql + " NAME VARCHAR(50),";
            sql = sql + " EMAIL VARCHAR(50) UNIQUE)";
            //call db
            conn.createStatement().execute(sql);
            System.out.println("Table USER created!");

            //GAME goes next
            sql = "CREATE TABLE GAME (";
            sql = sql + " GAMEID INTEGER PRIMARY KEY,";
            sql = sql + " TEAM1 VARCHAR(50),";
            sql = sql + " TEAM2 VARCHAR(50),";
            sql = sql + " TEAM1SN VARCHAR(3),";
            sql = sql + " TEAM2SN VARCHAR(3),";
            sql = sql + " TEAM1REC VARCHAR(10),";
            sql = sql + " TEAM2REC VARCHAR(10),";
            sql = sql + " KICKOFF TIMESTAMP,";
            sql = sql + " SPREAD DOUBLE,";
            sql = sql + " T1ML INTEGER,";
            sql = sql + " T2ML INTEGER,";
            sql = sql + " TEAM1SCORE INTEGER,";
            sql = sql + " TEAM2SCORE INTEGER,";
            sql = sql + " WEEK INTEGER,";
            sql = sql + " LINK VARCHAR(150))";

            //call db
            conn.createStatement().execute(sql);
            System.out.println("Table GAME created!");

            System.out.println("Create Group (depends on USER)");
            /*
            groups
group name -> PK
group type
group admin FK

             */

            System.out.println("DB Complete!");
        } catch (Exception e) {
            System.out.println("oops:" + e);
        }

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nflpickem",
                    "user", "user");
            //GROUP goes next
            String sql = "CREATE TABLE PICKEMGROUP (";
            sql = sql + "NAME VARCHAR(25) PRIMARY KEY,";
            sql = sql + " TYPE VARCHAR(4),";
            sql = sql + " ADMIN VARCHAR(50),";
            sql = sql + " FOREIGN KEY (ADMIN) REFERENCES USER(USERNAME))";
            //admin must be a real user

            System.out.println(sql);
            conn.createStatement().execute(sql);
            System.out.println("Table GROUP created!");

        } catch (Exception e) {
            System.out.println("oops:" + e);
        }

        try {
            System.out.println("Create GROUPUSER (depends on USER/GROUP");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nflpickem",
                    "user", "user");
            //GROUPUSER goes next

            String sql = "CREATE TABLE PICKEMGROUPUSER (";
            sql = sql + " GUID INTEGER PRIMARY KEY,";
            sql = sql + " USERNAME VARCHAR(50),";

            sql = sql + " GRPNAME VARCHAR(25),";
            sql = sql + " STATUS VARCHAR(50),";
            sql = sql + " SCORE INTEGER,";
            //necessary for survivor league!
            sql = sql + " DONE BOOLEAN,";
            //now we need to enforce referential integrity
            sql = sql + " FOREIGN KEY (USERNAME) REFERENCES USER(USERNAME),";
            sql = sql + " FOREIGN KEY (GRPNAME) REFERENCES PICKEMGROUP(NAME))";

            System.out.println(sql);
            conn.createStatement().execute(sql);
            System.out.println("Table PICKEMGROUPUSER created!");

        } catch (Exception e) {
            System.out.println("oops:" + e);
        }

        System.out.println("Create Pick (depends on GAME/USER/GROUP");
        try {
            System.out.println("Create GROUPUSER (depends on USER/GROUP");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nflpickem",
                    "user", "user");
            //GROUPUSER goes next
            /*
            pickid
User -> FK
Gameid -> FK
GroupName -> FK
Teamshortname 

             */

            String sql = "CREATE TABLE PICKS (";
            sql = sql + " PID INTEGER PRIMARY KEY,";
            sql = sql + " USERNAME VARCHAR(50),";
            sql = sql + " GRPNAME VARCHAR(25),";
            sql = sql + " GAMEID INTEGER,";
            sql = sql + " WEEK INTEGER,";
            sql = sql + " SELECTION VARCHAR(30),";
            //now we need to enforce referential integrity
            sql = sql + " FOREIGN KEY (USERNAME) REFERENCES USER(USERNAME),";
            sql = sql + " FOREIGN KEY (GAMEID) REFERENCES GAME(GAMEID),";
            sql = sql + " FOREIGN KEY (GRPNAME) REFERENCES PICKEMGROUP(NAME))";

            System.out.println(sql);
            conn.createStatement().execute(sql);
            System.out.println("Table PICKEMGROUPUSER created!");

        } catch (Exception e) {
            System.out.println("oops:" + e);
        }
    }
}

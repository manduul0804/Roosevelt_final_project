/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.dbsetup;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author merut
 */
public class InitGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, ParseException, SQLException {
        // TODO code application logic here
//        Scanner scanner = new Scanner(new File("week1matchups.txt"));
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/nflpickem",
                "user", "user");

        System.out.println("Create USER/GAME (bases)");
        int counter = 10000;

         // Isidro's Code start       

        Scanner scanner = new Scanner(new File("week1.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //System.out.println("line: " + line);
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            int i = 0;

            while (lineScanner.hasNext()) {
                //0 token is home team full name
                String team1Name = lineScanner.next().trim();
                System.out.println("team 1 name ="+team1Name);
                //then their short name
                String team1SN = lineScanner.next().trim();
                //2 token is team 2's full name (with vs added on)
                String team2Name = lineScanner.next().trim();

   
                //System.out.println("team2Name" + team2Name);
                String team2SN = lineScanner.next().trim();
                //TEAM1REC from text file
                String team1Rec = lineScanner.next().trim();
                String team2Rec = lineScanner.next().trim();
                //System.out.println(team1Name + " (" + team1SN + ") vs " + team2Name + " (" + team2SN + ")");
                //timestamp set for noon on 9/11
//                Timestamp ts = Timestamp.valueOf("2023-09-11 12:00:00.000");
//                String teamRec = "0-0";
                //Getting date from text file
                String ts = lineScanner.next().trim();

                int week = 1;

                String sql = "INSERT INTO GAME VALUES (";
                sql = sql + counter++ + ",";
                sql = sql + "'" + team1Name + "',";
                sql = sql + "'" + team2Name + "',";
                sql = sql + "'" + team1SN + "',";
                sql = sql + "'" + team2SN + "',";
                sql = sql + "'" + team1Rec + "',";
                sql = sql + "'" + team2Rec + "',";
                sql = sql + "'" + ts + "',";
                sql = sql + "" + 0.0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + week + ",";
                sql = sql + "'')";

                System.out.println("SQL data ="+sql);

                try {
                    conn.createStatement().execute(sql);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        }
        scanner = new Scanner(new File("week2.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //System.out.println("line: " + line);
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            int i = 0;

            while (lineScanner.hasNext()) {
                //0 token is home team full name
                String team1Name = lineScanner.next().trim();
                System.out.println("team 1 name ="+team1Name);
                //then their short name
                String team1SN = lineScanner.next().trim();
                //2 token is team 2's full name (with vs added on)
                String team2Name = lineScanner.next().trim();

   
                //System.out.println("team2Name" + team2Name);
                String team2SN = lineScanner.next().trim();
                //TEAM1REC from text file
                String team1Rec = lineScanner.next().trim();
                String team2Rec = lineScanner.next().trim();
                //System.out.println(team1Name + " (" + team1SN + ") vs " + team2Name + " (" + team2SN + ")");
                //timestamp set for noon on 9/11
//                Timestamp ts = Timestamp.valueOf("2023-09-11 12:00:00.000");
//                String teamRec = "0-0";
                //Getting date from text file
                String ts = lineScanner.next().trim();

                int week = 2;

                String sql = "INSERT INTO GAME VALUES (";
                sql = sql + counter++ + ",";
                sql = sql + "'" + team1Name + "',";
                sql = sql + "'" + team2Name + "',";
                sql = sql + "'" + team1SN + "',";
                sql = sql + "'" + team2SN + "',";
                sql = sql + "'" + team1Rec + "',";
                sql = sql + "'" + team2Rec + "',";
                sql = sql + "'" + ts + "',";
                sql = sql + "" + 0.0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + week + ",";
                sql = sql + "'')";

                System.out.println("SQL data ="+sql);

                try {
                    conn.createStatement().execute(sql);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        }
        scanner = new Scanner(new File("week3.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //System.out.println("line: " + line);
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            int i = 0;

            while (lineScanner.hasNext()) {
                //0 token is home team full name
                String team1Name = lineScanner.next().trim();
                System.out.println("team 1 name ="+team1Name);
                //then their short name
                String team1SN = lineScanner.next().trim();
                //2 token is team 2's full name (with vs added on)
                String team2Name = lineScanner.next().trim();

   
                //System.out.println("team2Name" + team2Name);
                String team2SN = lineScanner.next().trim();
                //TEAM1REC from text file
                String team1Rec = lineScanner.next().trim();
                String team2Rec = lineScanner.next().trim();
                //System.out.println(team1Name + " (" + team1SN + ") vs " + team2Name + " (" + team2SN + ")");
                //timestamp set for noon on 9/11
//                Timestamp ts = Timestamp.valueOf("2023-09-11 12:00:00.000");
//                String teamRec = "0-0";
                //Getting date from text file
                String ts = lineScanner.next().trim();

                int week = 3;

                String sql = "INSERT INTO GAME VALUES (";
                sql = sql + counter++ + ",";
                sql = sql + "'" + team1Name + "',";
                sql = sql + "'" + team2Name + "',";
                sql = sql + "'" + team1SN + "',";
                sql = sql + "'" + team2SN + "',";
                sql = sql + "'" + team1Rec + "',";
                sql = sql + "'" + team2Rec + "',";
                sql = sql + "'" + ts + "',";
                sql = sql + "" + 0.0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + week + ",";
                sql = sql + "'')";

                System.out.println("SQL data ="+sql);

                try {
                    conn.createStatement().execute(sql);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        }

    }

}
//Isidro's Code End
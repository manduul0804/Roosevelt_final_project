/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.dbsetup;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        Scanner scanner = new Scanner(new File("week1matchups.txt"));
        Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nflpickem",
                    "user", "user");

            System.out.println("Create USER/GAME (bases)");
            int counter = 10000;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //System.out.println("line: " + line);
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter("\\(|\\)");
            int i = 0;
            
            while (lineScanner.hasNext()) {
                //0 token is home team full name
                String team1Name = lineScanner.next().trim();
                //then their short name
                String team1SN = lineScanner.next().trim();
                //2 token is team 2's full name (with vs added on)
                String team2Name = lineScanner.next().trim();
                //remove the vs
                team2Name = team2Name.substring(3).trim();
                //System.out.println("team2Name" + team2Name);
                String team2SN =  lineScanner.next().trim();
                
                //System.out.println(team1Name + " (" + team1SN + ") vs " + team2Name + " (" + team2SN + ")");
                //timestamp set for noon on 9/11
                Timestamp ts = Timestamp.valueOf("2022-09-11 12:00:00.000");
                String teamRec = "0-0";
                int week = 1;
                
                
                
                String sql = "INSERT INTO GAME VALUES (";
                sql = sql + counter++ + ",";
                sql = sql + "'" + team1Name + "',";
                sql = sql + "'" + team2Name + "',";
                sql = sql + "'" + team1SN + "',";
                sql = sql + "'" + team2SN + "',";
                sql = sql + "'" + teamRec + "',";
                sql = sql + "'" + teamRec + "',";
                sql = sql + "'" + ts + "',";
                sql = sql + "" + 0.0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + 0 + ",";
                sql = sql + "" + week + ",";
                sql = sql + "'')";
                
                
                System.out.println(sql); 
                
                try {
                    conn.createStatement().execute(sql);
                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
                
                
            }
            
           
        }
        
        
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.dbsetup;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author merut
 */
public class InitPicks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nflpickem",
                    "user", "user");

            System.out.println("Create USER/GAME (bases)");
            
            String[] users = {"red", "blue"};
            String[] grps = {"bronze","gold","silver"};
//                      String sql = "CREATE TABLE PICKS (";
//            sql = sql + " PID INTEGER PRIMARY KEY,";
//            sql = sql + " USERNAME VARCHAR(50),";
//            sql = sql + " GRPNAME VARCHAR(25),";
//            sql = sql + " GAMEID INTEGER,";
//            sql = sql + " SELECTION VARCHAR(30),";
            
            int gameID = 10000;
            int pid = 10000;

            for (int i=0; i<users.length; i++) {
                for (int y=0; y<3; y++) {
                    String sql = "INSERT INTO PICKS VALUES (";
                    sql = sql + ++pid + ",";
                    sql = sql + "'" + users[i] + "',";
                    sql = sql + "'" + grps[i] + "',";
                    sql = sql + "" + ++gameID + ",";
                    sql = sql + "" + 1 + ",";
                    if (y == 0) {
                        sql = sql + "'NO')";
                    } else {
                        sql = sql + "'ATL')";
                    }
                    
                    
                    
                    System.out.println(sql); 
                    
                    try {
                        conn.createStatement().execute(sql);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }


                }
                
                
                
               
                
                
                
                
            }

                
                
                


            
            System.out.println("Table USER created!");
         } catch (Exception e) {
            System.out.println("oops:" + e);
        }
        
    }
    
}

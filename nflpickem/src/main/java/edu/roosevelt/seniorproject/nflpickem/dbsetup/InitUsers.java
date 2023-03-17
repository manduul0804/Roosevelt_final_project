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
public class InitUsers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nflpickem",
                    "user", "user");

            System.out.println("Create USER/GAME (bases)");
            
            String[] uns = {"admin", "red", "blue", "green", "yellow"};
//            //USER goes first
//            String sql = "CREATE TABLE USER (";
//            sql = sql + " USERNAME VARCHAR(30) PRIMARY KEY,";
//            sql = sql + " PASSWORD VARCHAR(20),";
//            sql = sql + " ADMIN BOOLEAN,";
//            sql = sql + " NAME VARCHAR(50),";
//            sql = sql + " EMAIL VARCHAR(50))";
            
            for (int i=0; i<uns.length; i++) {
                String sql = "INSERT INTO USER VALUES ('";
                sql = sql + uns[i] + "',";
                sql = sql + "'" + uns[i] + "',";
                if (i == 0) {
                    sql = sql + "" + true + ",";
                } else {
                    sql = sql + "" + false + ",";
                }
                String name = "";
                //even odd
                if (i % 2 == 0) {
                    name = "Mr. " + uns[i].substring(0, 1).toUpperCase() + uns[i].substring(1);
                } else {
                    name = "Ms. " + uns[i].substring(0, 1).toUpperCase() + uns[i].substring(1);
                }
                sql = sql + "'" + name + "',";
                sql = sql + "'" + uns[i] +"@mail.roosevelt.edu" + "')";
                
               
                System.out.println(sql); 
                
                try {
                    conn.createStatement().execute(sql);
                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
            }

                
                
                


            
            System.out.println("Table USER created!");
         } catch (Exception e) {
            System.out.println("oops:" + e);
        }
    }
    
}

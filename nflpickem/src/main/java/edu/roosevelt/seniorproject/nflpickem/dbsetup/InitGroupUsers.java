/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.dbsetup;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author mruth
 */
public class InitGroupUsers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nflpickem",
                    "user", "user");

            System.out.println("Create GROUP (not base - depends on user)");

            String[] usn = {"admin", "red", "blue", "green", "yellow"};
            String[] grps = {"bronze", "gold", "silver"};

            
            /*
            String sql = "CREATE TABLE PICKEMGROUPUSER (";
            sql = sql + " GUID INTEGER PRIMARY KEY,";
            sql = sql + " USERNAME VARCHAR(50),";
            
            sql = sql + " GRPNAME VARCHAR(25),";
            sql = sql + " STATUS VARCHAR(50),";
            sql = sql + " SCORE INTEGER,";
            //necessary for survivor league!
            sql = sql + " DONE BOOLEAN,";
            */
            
           
            
            String sql = "INSERT INTO PICKEMGROUPUSER VALUES (";
            sql = sql + 9999 + ",'";
            sql = sql + usn[0] + "','";
            sql = sql + grps[0] + "','";
            sql = sql + "invited" + "',";
            sql = sql + 100 + ",";
            sql = sql + false + ")";
            
             String sq2 = "INSERT INTO PICKEMGROUPUSER VALUES (";
            sq2 = sq2 + 9998 + ",'";
            sq2 = sq2 + usn[1] + "','";
            sq2 = sq2 + grps[1] + "','";
            sq2 = sq2 + "invited" + "',";
            sq2 = sq2 + 95 + ",";
            sq2 = sq2 + false + ")";
            
            String sq3 = "INSERT INTO PICKEMGROUPUSER VALUES (";
            sq3 = sq3 + 9997 + ",'";
            sq3 = sq3 + usn[2] + "','";
            sq3 = sq3 + grps[2] + "','";
            sq3 = sq3 + "invited" + "',";
            sq3 = sq3 + 70 + ",";
            sq3 = sq3 + false + ")";
            
            try {
                conn.createStatement().execute(sql);
                conn.createStatement().execute(sq2);
                conn.createStatement().execute(sq3); 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            
        } catch (Exception e) {
            System.out.println("oops:" + e);
            
        }
        
        
    }

}

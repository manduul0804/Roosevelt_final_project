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
            sql = sql + 0 + ",";
            sql = sql + false + ")";
           
            System.out.println(sql);

            try {
                conn.createStatement().execute(sql);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            
        } catch (Exception e) {
            System.out.println("oops:" + e);
        }
    }

}

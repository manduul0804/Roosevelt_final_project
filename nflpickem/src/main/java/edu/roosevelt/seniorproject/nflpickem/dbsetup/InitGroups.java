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
public class InitGroups {

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

            String[] uns = {"admin", "red", "blue", "green", "yellow"};
            String[] grps = {"bronze","gold","silver"};
          

//            String sql = "CREATE TABLE PICKEMGROUP (";
//            sql = sql + "NAME VARCHAR(25) PRIMARY KEY,";
//            sql = sql + " TYPE VARCHAR(3),";
//            sql = sql + " ADMIN VARCHAR(50),";
//            sql = sql + " FOREIGN KEY (ADMIN) REFERENCES USER(USERNAME))";
            
        


            for (int i = 0; i < grps.length; i++) {
                String sql = "INSERT INTO PICKEMGROUP VALUES ('";
                sql = sql + grps[i] + "','SU',";
                sql = sql + "'" + uns[i+1] + "')";
                

                System.out.println(sql);

                try {
                    conn.createStatement().execute(sql);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

            System.out.println("Table GROUPS created!");
        } catch (Exception e) {
            System.out.println("oops:" + e);
        }

    }

}

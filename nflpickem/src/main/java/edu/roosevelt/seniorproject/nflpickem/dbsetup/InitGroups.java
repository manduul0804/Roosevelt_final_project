/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.dbsetup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

            String[] grps = {"bronze", "gold", "silver", "diamond", "platinum",
                "copper", "nickel", "titanium", "iron", "cobalt", "tungsten", "mercury"};

//            String sql = "CREATE TABLE PICKEMGROUP (";
//            sql = sql + "NAME VARCHAR(25) PRIMARY KEY,";
//            sql = sql + " TYPE VARCHAR(3),";
//            sql = sql + " ADMIN VARCHAR(50),";
//            sql = sql + " FOREIGN KEY (ADMIN) REFERENCES USER(USERNAME))";
// **********************Manduul Code********************************
// **************************START***********************************
            String sql1 = "SELECT username FROM USER";
            //Create a names list to store names from USER table from DB
            List<String> names = new ArrayList();
            try {
                Statement st = conn.createStatement();

                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery(sql1);

                while (rs.next()) {
                    String userName = rs.getString(1);
                    names.add(userName);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println(names);

            for (int i = 0; i < grps.length; i++) {
                String type = new String();
                if ((grps[i] == "diamond") || (grps[i] == "copper")
                        || (grps[i] == "platinum") || (grps[i] == "nickel")) {
                    type = "ATS";
                } else if ((grps[i] == "titanium") || (grps[i] == "iron")
                        || (grps[i] == "cobalt") || (grps[i] == "tungsten")) {
                    type = "SURV";
                } else {
                    type = "SU";
                }
                String sql = "INSERT INTO PICKEMGROUP VALUES ('";
                sql = sql + grps[i] + "','" + type + "',";
                sql = sql + "'" + names.get(i + 1) + "')";
// **************************END*********************************** 
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

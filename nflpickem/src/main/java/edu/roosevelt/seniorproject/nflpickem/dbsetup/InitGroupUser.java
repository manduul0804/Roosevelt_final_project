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
import java.util.List;

/**
 *
 * @author merut
 */
public class InitGroupUser {

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

// String sql = "CREATE TABLE PICKEMGROUPUSER (";
//            sql = sql + " GUID INTEGER PRIMARY KEY,";
//            sql = sql + " USERNAME VARCHAR(50),";
//            
//            sql = sql + " GRPNAME VARCHAR(25),";
//            sql = sql + " STATUS VARCHAR(50),";
//            sql = sql + " SCORE INTEGER,";
//            //necessary for survivor league!
//            sql = sql + " DONE BOOLEAN,";
//            //now we need to enforce referential integrity
//            sql = sql + " FOREIGN KEY (USERNAME) REFERENCES USER(USERNAME),";
//            sql = sql + " FOREIGN KEY (GRPNAME) REFERENCES PICKEMGROUP(NAME))";
// **********************Manduul Code********************************
// **************************START***********************************
            //Empty list to store group names from pickemgroup table
            List<String> grpNames = new ArrayList();

            //Empty list to store names from user table
            List<String> names = new ArrayList();

            int guid = 10001;
            try {
                String sql = "SELECT name FROM pickemgroup";
                String sql1 = "SELECT username FROM user";
                Statement st_grpName = conn.createStatement();
                Statement st_userName = conn.createStatement();
                // execute the query, and get a java resultset
                ResultSet rs_grpName = st_grpName.executeQuery(sql);
                ResultSet rs_userName = st_userName.executeQuery(sql1);
                //Get group names from pickemgroup table
                while (rs_grpName.next()) {
                    String grpName = rs_grpName.getString(1);
                    grpNames.add(grpName);
                }
                while (rs_userName.next()) {
                    String userName = rs_userName.getString(1);
                    names.add(userName);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            //Removing names because it is used in initGroups.java
            names.remove("admin");
            names.remove("red");
            names.remove("blue");

            System.out.println(grpNames);
            System.out.println(names);

            //Add 4 users in 4 different groups
            // k tracks the size of grpNames
            int k = 0;
            for (int i = 0; i < names.size(); i++) {
                if (4 > i) {

                    for (int j = 0; j < 4; j++) {

                        String sql = "INSERT INTO PICKEMGROUPUSER VALUES (";
                        sql = sql + guid++ + ",";
                        sql = sql + "'" + names.get(i) + "','";
                        sql = sql + grpNames.get(j) + "',";
                        sql = sql + "'doesnt matter',";
                        sql = sql + "0,false)";
                        try {
                            conn.createStatement().execute(sql);

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        //Remove used names from the names list
                        System.out.println(sql);
                    }
                } // names.size() - 4 -> it leaves 4 users without a group
                else if ((4 <= i) && (i < names.size() - 4)) {
                    //it makes shure I don't get out of bound error
                    if (k >= grpNames.size()) {
                        k = 0;
                    }
                    String sql = "INSERT INTO PICKEMGROUPUSER VALUES (";
                    sql = sql + guid++ + ",";
                    sql = sql + "'" + names.get(i) + "','";
                    sql = sql + grpNames.get(k) + "',";
                    sql = sql + "'doesnt matter',";
                    sql = sql + "0,false)";
                    k++;
                    System.out.println(sql);
                    try {
                        conn.createStatement().execute(sql);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
// **************************END***********************************            

            System.out.println("Table GROUPS created!");
        } catch (Exception e) {
            System.out.println("oops:" + e);
        }

    }

}
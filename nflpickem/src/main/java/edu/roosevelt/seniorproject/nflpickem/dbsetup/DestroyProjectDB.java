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
public class DestroyProjectDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/nflpickem",
                    "root", "root");
            
            System.out.println("If PICKS Table Exists... drop it like it's hot");
            try {
                
                String sql = "DROP TABLE PICKS";
            
                conn.createStatement().execute(sql);
            
                System.out.println("Table Dropped!");
            } catch (Exception e) {
                System.out.println("Table Didn't Exist");
            }
            System.out.println("If PICKEMGROUPUSER Table Exists... drop it like it's hot");
            try {
                
                String sql = "DROP TABLE PICKEMGROUPUSER";
            
                conn.createStatement().execute(sql);
            
                System.out.println("Table Dropped!");
            } catch (Exception e) {
                System.out.println("Table Didn't Exist");
            }
            
            System.out.println("If GRP Table Exists... drop it like it's hot");
            try {
                
                String sql = "DROP TABLE GRP";
            
                conn.createStatement().execute(sql);
            
                System.out.println("Table Dropped!");
            } catch (Exception e) {
                System.out.println("Table Didn't Exist");
            }
            
            System.out.println("If PICKEMGROUP Table Exists... drop it like it's hot");
            try {
                
                String sql = "DROP TABLE PICKEMGROUP";
            
                conn.createStatement().execute(sql);
            
                System.out.println("Table Dropped!");
            } catch (Exception e) {
                System.out.println("Table Didn't Exist");
            }
            
        
            
            System.out.println("Base Tables Last: USER/GAME");
            System.out.println("If USER Table Exists... drop it like it's hot");
            try {
                
                String sql = "DROP TABLE USER";
            
                conn.createStatement().execute(sql);
            
                System.out.println("Table Dropped!");
            } catch (Exception e) {
                System.out.println("Table Didn't Exist " + e);
            }
            System.out.println("If GAME Table Exists... drop it like it's hot");
            try {
                
                String sql = "DROP TABLE GAME";
            
                conn.createStatement().execute(sql);
            
                System.out.println("Table Dropped!");
            } catch (Exception e) {
                System.out.println("Table Didn't Exist");
            }
            
            
            
        } catch (Exception e) {
            System.out.println("oops: " + e);
        }

    }
    
}

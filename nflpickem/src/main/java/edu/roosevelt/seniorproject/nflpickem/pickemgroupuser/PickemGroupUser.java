/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.pickemgroupuser;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 *
 * @author mruth
 */
@Entity
public class PickemGroupUser {
    @Id
    int guid;
    String username;
    String grpname;
    String status;
    int score;
    boolean done;
    
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

    public int getGuid() {
        return guid;
    }

    public void setGuid(int guid) {
        this.guid = guid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGrpname() {
        return grpname;
    }

    public void setGrpname(String grpname) {
        this.grpname = grpname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean getIsAdmin() {
        return false;
    }

    public boolean getIsLeader() {
        return false;
    }

    public boolean getIsMember() {
        return false;
    }

    public void setIsMember(boolean b) {
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.pick;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 *
 * @author mruth
 */
@Entity
@Table(name="PICKS")
public class Pick implements java.io.Serializable{
    
    @Id
    int pid;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.pid;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pick other = (Pick) obj;
        return this.pid == other.pid;
    }

    

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    @Override
    public String toString() {
        return "Pick{" + "pid=" + pid + ", username=" + username + ", grpname=" + grpname + ", gameid=" + gameid + ", selection=" + selection + '}';
    }

    public void setGrpname(String grpname) {
        this.grpname = grpname;
    }

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
    String username;
    String grpname;
    int gameid;
    String selection;
    int week;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    
    
    
}   
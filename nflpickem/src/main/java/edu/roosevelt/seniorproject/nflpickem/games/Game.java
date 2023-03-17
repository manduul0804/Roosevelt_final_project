/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.games;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;


/**
 *
 * @author mruth
 */
@Entity
public class Game {

    @Id
    int gameid;

    @Override
    public String toString() {
        return "Game{" + "gameid=" + gameid + ", team1sn=" + team1sn + ", team2sn=" + team2sn + ", spread=" + spread + ", t1ml=" + t1ml + ", t2ml=" + t2ml + ", team1score=" + team1score + ", team2score=" + team2score + '}';
    }
    String team1;
    String team2;
    String team1sn;
    String team2sn;
    String team1rec;
    String team2rec;
    Timestamp kickoff;
    double spread;
    int t1ml;
    int t2ml;
    int team1score;
    int team2score;
    int week;
    String link;

    /*
    //GAME goes next
            sql = "CREATE TABLE GAME (";
            sql = sql + " GAMEID INTEGER PRIMARY KEY,";
            sql = sql + " TEAM1 VARCHAR(50),";
            sql = sql + " TEAM2 VARCHAR(50),";
            sql = sql + " TEAM1SN VARCHAR(3),";
            sql = sql + " TEAM2SN VARCHAR(3),";
            sql = sql + " TEAM1REC VARCHAR(10),";
            sql = sql + " TEAM2REC VARCHAR(10),";
            sql = sql + " KICKOFF TIMESTAMP,";
            sql = sql + " SPREAD DOUBLE,";
            sql = sql + " T1ML INTEGER,";
            sql = sql + " T2ML INTEGER,";
            sql = sql + " TEAM1SCORE INTEGER,";
            sql = sql + " TEAM2SCORE INTEGER,";
            sql = sql + " WEEK INTEGER,";
            sql = sql + " LINK VARCHAR(150))";
     */

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.gameid;
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
        final Game other = (Game) obj;
        return this.gameid == other.gameid;
    }
    
  

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam1sn() {
        return team1sn;
    }

    public void setTeam1sn(String team1sn) {
        this.team1sn = team1sn;
    }

    public String getTeam2sn() {
        return team2sn;
    }

    public void setTeam2sn(String team2sn) {
        this.team2sn = team2sn;
    }

    public String getTeam1rec() {
        return team1rec;
    }

    public void setTeam1rec(String team1rec) {
        this.team1rec = team1rec;
    }

    public String getTeam2rec() {
        return team2rec;
    }

    public void setTeam2rec(String team2rec) {
        this.team2rec = team2rec;
    }

    public Timestamp getKickoff() {
        return kickoff;
    }

    public void setKickoff(Timestamp kickoff) {
        this.kickoff = kickoff;
    }

    public double getSpread() {
        return spread;
    }

    public void setSpread(double spread) {
        this.spread = spread;
    }

    public int getT1ml() {
        return t1ml;
    }

    public void setT1ml(int t1ml) {
        this.t1ml = t1ml;
    }

    public int getT2ml() {
        return t2ml;
    }

    public void setT2ml(int t2ml) {
        this.t2ml = t2ml;
    }

    public int getTeam1score() {
        return team1score;
    }

    public void setTeam1score(int team1score) {
        this.team1score = team1score;
    }

    public int getTeam2score() {
        return team2score;
    }

    public void setTeam2score(int team2score) {
        this.team2score = team2score;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}

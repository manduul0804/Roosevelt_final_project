/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.games;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author mruth
 */
public interface GameRepository extends CrudRepository<Game, Integer> {

    

    List<Game> findByWeek(int week);

    List<Game> findByKickoffAfterOrderByKickoffDesc(Timestamp ts);
    
    //Sergey's Code
    @Query(value="SELECT gameid, team1, team2, kickoff, week FROM game WHERE week = ?1", nativeQuery = true)
    List<DeadlineGame> findDeadlineByWeek(int week);
    
    @Query(value="SELECT week, team1, team2, kickoff, gameID FROM game WHERE gameID = ?1", nativeQuery = true)
    List<DeadlineGame> findDeadlineByGameID(int gameID);

    public interface DeadlineGame {
        public int getGameID();
        public String getTeam1();
        public String getTeam2();
        public Timestamp getKickoff();
        public int getWeek();
    }
//End of Sergey's Code
}

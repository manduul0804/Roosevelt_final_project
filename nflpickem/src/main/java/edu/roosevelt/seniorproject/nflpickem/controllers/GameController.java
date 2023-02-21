/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.controllers;

import edu.roosevelt.seniorproject.nflpickem.games.Game;
import edu.roosevelt.seniorproject.nflpickem.games.GameRepository;
import edu.roosevelt.seniorproject.nflpickem.groups.PickemGroupRepository;
import edu.roosevelt.seniorproject.nflpickem.pickemgroupuser.PickemGroupUserRepository;
import edu.roosevelt.seniorproject.nflpickem.user.User;
import edu.roosevelt.seniorproject.nflpickem.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mruth
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class GameController {
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);
    
  
    @Autowired
    GameRepository games;
    
    
    //Find ALL games
    @GetMapping("/nflpickem/games/allgames")
    public ResponseEntity<List<Game>> getallGames(HttpSession session){
        
        return new ResponseEntity(games.findAll(), HttpStatus.OK);
    }
    
    //simplifying code bit by bit
    private boolean isLoggedIn(HttpSession session) {
         if (session.getAttribute("user") != null) {
             return true;
         } else {
             return false;
         }
    }
    
    private boolean isAdmin(HttpSession session) {
        if (session.getAttribute("user") != null) {
            //user is logged in, will get data
            if (session.getAttribute("admin") != null) {
                return true;
            }
            
        } 
        return false;
    }
    
    
    // Find games by a given week
    @GetMapping("/nflpickem/games/{week}")
    public ResponseEntity<List<Game>> getGamesByWeek(@PathVariable("week") int week, HttpSession session) {
        if (isLoggedIn(session)) {
            return new ResponseEntity(games.findByWeek(week),HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    
    
    }
     
    //delete games based on a game id.
     @DeleteMapping("/nflpickem/games/{gameid}")
    public ResponseEntity<String> deleteGame(@PathVariable("gameid") Integer gameid) throws SQLException {
        
        if (games.existsById(gameid)) {
            //delete it!
            games.deleteById(gameid);
            //return result
            return new ResponseEntity(gameid, HttpStatus.OK);
        } else {
            //not there
            return new ResponseEntity(gameid, HttpStatus.NOT_FOUND);
        }
        
        
    }
    
    
  
    }
    
    
    
    
    
    
    

    
   
    
    
    
    
    
  
    //base url for all requests should be:
    // -> /nflpickem/games


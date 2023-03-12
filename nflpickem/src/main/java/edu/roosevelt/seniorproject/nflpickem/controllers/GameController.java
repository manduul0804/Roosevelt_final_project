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
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    UserRepository users;
    
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
    
    
    @Autowired
    GameRepository games;
    
    @Autowired
    PickemGroupRepository groups;
    
    @Autowired
    PickemGroupUserRepository groupusers;
//<<<<<<< Updated upstream
    
  //  @GetMapping("/nflpickem/games/{week}")
//=======

//      //Just a general get all games 
//    @GetMapping("/nflpickem/games/allgames2")
//    public ResponseEntity<List<Game>> getAllGames2(HttpSession session) {
//       //only use this for testing purposes 
//        
//            return new ResponseEntity(users.findAll(), HttpStatus.OK);
//       
//        
//    }
    //Just a general get all games 
    @GetMapping("/nflpickem/games/allgames")
    public ResponseEntity<List<Game>> getAllGames(HttpSession session) {
        //is user logged in?
            return new ResponseEntity(games.findAll(), HttpStatus.OK);

    }

    //Get games by a specific week. You need to be logged in for this to work.
   // @GetMapping("/nflpickem/games/byweek/{week}")
//>>>>>>> Stashed changes
    public ResponseEntity<List<Game>> getGamesByWeek(@PathVariable("week") int week, HttpSession session) {
        if (isLoggedIn(session)) {
            return new ResponseEntity(games.findByWeek(week),HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    
    
    }
    
    //base url for all requests should be:
    // -> /nflpickem/games
}

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
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mruth
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class GameController {
    
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    

    //simplifying code bit by bit
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
        if (this.isLoggedIn(session)) {
            return new ResponseEntity(games.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }

    //Get games by a specific week. You need to be logged in for this to work.
    @GetMapping("/nflpickem/games/byweek/{week}")
    public ResponseEntity<List<Game>> getGamesByWeek(@PathVariable("week") int week, HttpSession session) {
        if (isLoggedIn(session)) {
            return new ResponseEntity(games.findByWeek(week), HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
        
    }

    //delete games based on a game id.
    @DeleteMapping("/nflpickem/games/{gameid}")
    public ResponseEntity<String> deleteGame(@PathVariable("gameid") Integer gameid, HttpSession session) throws SQLException {
        
        if (this.isAdmin(session)) {
            if (games.existsById(gameid)) {
                //delete it!
                games.deleteById(gameid);
                //return result
                return new ResponseEntity(gameid, HttpStatus.OK);
            } else {
                //not there
                return new ResponseEntity(gameid, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(gameid, HttpStatus.UNAUTHORIZED);
        }
        
    }

    //update games score for team 1
    @PutMapping(value = "/nflpickem/games/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> updateGame(@RequestBody final Game g, HttpSession session) throws SQLException {
        
        if (this.isAdmin(session)) {
            if (games.existsById(g.getGameid())) {
                Optional<Game> opt = games.findById(g.getGameid());
                Game real = opt.get();
                //set all fields not GID/scores
                real.setKickoff(g.getKickoff());
                real.setLink(g.getLink());
                real.setSpread(g.getSpread());
                real.setT1ml(g.getT1ml());
                real.setT2ml(g.getT2ml());
                real.setTeam1(g.getTeam1());
                real.setTeam1rec(g.getTeam1rec());
                real.setTeam1sn(g.getTeam1sn());
                real.setTeam2(g.getTeam2());
                real.setTeam2rec(g.getTeam2rec());
                real.setTeam2sn(g.getTeam2sn());
                real.setWeek(g.getWeek());
                //save the real entry
                games.save(real);
                return new ResponseEntity(real, HttpStatus.OK);
                
            } else {
                return new ResponseEntity(g, HttpStatus.NOT_FOUND);
            }
            
        } else {
            //unauthorized
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }

    //update games score for team 1
    @PutMapping(value = "/nflpickem/games/updatescore", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> updateGameScores(@RequestBody final Game g, HttpSession session) throws SQLException {
        
        if (this.isAdmin(session)) {
            if (games.existsById(g.getGameid())) {
                Optional<Game> opt = games.findById(g.getGameid());
                Game real = opt.get();
                //set all fields not GID/scores
                real.setTeam1score(g.getTeam1score());
                real.setTeam2score(g.getTeam2score());
                //save the real entry
                games.save(real);
                return new ResponseEntity(real, HttpStatus.OK);
                
            } else {
                return new ResponseEntity(g, HttpStatus.NOT_FOUND);
            }
            
        } else {
            //unauthorized
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }
    
    
    
    //base url for all requests should be:
    // -> /nflpickem/games
}

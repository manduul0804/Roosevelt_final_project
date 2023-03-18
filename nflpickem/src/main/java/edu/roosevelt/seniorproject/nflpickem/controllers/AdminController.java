/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.controllers;

import edu.roosevelt.seniorproject.nflpickem.games.Game;
import edu.roosevelt.seniorproject.nflpickem.games.GameRepository;
import edu.roosevelt.seniorproject.nflpickem.groups.PickemGroupRepository;
import edu.roosevelt.seniorproject.nflpickem.pick.PickRepository;
import edu.roosevelt.seniorproject.nflpickem.pick.Pick;
import edu.roosevelt.seniorproject.nflpickem.pickemgroupuser.PickemGroupUser;
import edu.roosevelt.seniorproject.nflpickem.pickemgroupuser.PickemGroupUserRepository;
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
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

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

    @Autowired
    PickRepository picks;

    @GetMapping("/nflpickem/admin/numpicks/byweek/{week}")
    public ResponseEntity<List<Pick>> getPicksByweek(@PathVariable("week") int week, HttpSession session) {
        if (this.isAdmin(session)) {
            return new ResponseEntity(picks.countByWeek(week), HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }
    
    
    @GetMapping("/nflpickem/admin/numusers")
    public ResponseEntity<Long> getTotalNumberOfUsers(HttpSession session) {
        if (this.isAdmin(session)) {

            return new ResponseEntity(users.count(), HttpStatus.UNAUTHORIZED);

        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }
    
    
    
    @GetMapping("/nflpickem/admin/numpicks")
    public ResponseEntity<Long> getTotalNumberOfPicks(HttpSession session) {
        if (this.isAdmin(session)) {

            return new ResponseEntity(picks.count(), HttpStatus.UNAUTHORIZED);

        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }
    
    
    @GetMapping("/nflpickem/admin/numgroups")
    public ResponseEntity<Long> getTotalNumberOfGroups(HttpSession session) {
        if (this.isAdmin(session)) {

            return new ResponseEntity(groups.count(), HttpStatus.UNAUTHORIZED);

        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/nflpickem/admin/{group}")
    public ResponseEntity<List<Game>> getGroupLeaderboard(@PathVariable("group") String group, HttpSession session) {
        if (isLoggedIn(session)) {
            String username = (String) session.getAttribute("user");
            if (isAdmin(session) || (groupusers.existsByUsernameAndGrpname(username, group))) {
                List<PickemGroupUser> standings = groupusers.findByGrpname(group);
                return new ResponseEntity(standings, HttpStatus.OK);
            }
        }
        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
    }
    
    

    
     

    @GetMapping("/nflpickem/admin/allstandings")
    public ResponseEntity<List<PickemGroupUser>> getAllGroupUser(HttpSession session) {
        if (this.isAdmin(session)) {
            return new ResponseEntity(groupusers.findByStatusOrderByGrpnameAscScoreDesc("OK"), HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("/nflpickem/admin/highscores")
    public ResponseEntity<List<PickemGroupUser>> getHighScoresForGroup(HttpSession session) {
        if (isLoggedIn(session)) {
            return new ResponseEntity(groups.getHighScoresForEachGroupType(), HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);

    }

}

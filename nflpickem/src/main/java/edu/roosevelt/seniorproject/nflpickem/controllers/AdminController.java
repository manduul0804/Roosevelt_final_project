/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.controllers;

import edu.roosevelt.seniorproject.nflpickem.games.Game;
import edu.roosevelt.seniorproject.nflpickem.games.GameRepository;
import edu.roosevelt.seniorproject.nflpickem.groups.PickemGroupRepository;
import edu.roosevelt.seniorproject.nflpickem.pickemgroupuser.PickemGroupUser;
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
    
    
    
    
    @GetMapping("/nflpickem/admin/{group}")
    public ResponseEntity<List<Game>> getGroupLeaderboard(@PathVariable("group") String group, HttpSession session) {
        if (isLoggedIn(session)) {
            String username = (String)session.getAttribute("user");
            if (isAdmin(session) || (groupusers.existsByUsernameAndGrpname(username, group))) {
                List<PickemGroupUser> standings = groupusers.findByGrpname(group);
                return new ResponseEntity(standings, HttpStatus.OK);
            } 
        }
        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
    }
     @GetMapping("/nflpickem/users/allgroups")
    public ResponseEntity<List<User>> getAllgroups(HttpSession session) {
        
        if (this.isAdmin(session)) {
            return new ResponseEntity(users.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
        
        
        
        
        
    }
    
}

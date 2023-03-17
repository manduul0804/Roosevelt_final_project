/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.controllers;

import edu.roosevelt.seniorproject.nflpickem.games.GameRepository;
import edu.roosevelt.seniorproject.nflpickem.groups.PickemGroup;
import edu.roosevelt.seniorproject.nflpickem.groups.PickemGroupRepository;
import edu.roosevelt.seniorproject.nflpickem.pick.Pick;
import edu.roosevelt.seniorproject.nflpickem.pick.PickRepository;
import edu.roosevelt.seniorproject.nflpickem.pickemgroupuser.PickemGroupUser;
import edu.roosevelt.seniorproject.nflpickem.pickemgroupuser.PickemGroupUserRepository;
import edu.roosevelt.seniorproject.nflpickem.user.User;
import edu.roosevelt.seniorproject.nflpickem.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import static java.util.Collections.list;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mruth
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class PickController {

    private static final Logger logger = LoggerFactory.getLogger(PickController.class);

    @Autowired
    UserRepository users;

    @Autowired
    GameRepository games;

    @Autowired
    PickemGroupRepository groups;

    @Autowired
    PickemGroupUserRepository groupusers;

    //Karen Code Below
    @Autowired
    PickRepository picks;

    //checking if session is good
    @GetMapping("/nflpickem/picks")
    public String testGroups(HttpSession session) {
        if (session != null && session.getAttribute("user") != null) {
            return (String) session.getAttribute("groupusers");

        } else if (session != null) {
            return "good session, no att";
        } else {
            return "no session";
        }
    }

    //checking if user is logged in
    private boolean isLoggedIn(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return true;
        } else {
            return false;
        }
    }

    //checking if user is admin
    private boolean isAdmin(HttpSession session) {
        if (session.getAttribute("user") != null) {
            //user is logged in, will get data
            if (session.getAttribute("admin") != null) {
                return true;
            }

        }
        return false;
    }
    
    //checking if user is admin
    private String getUsername(HttpSession session) {
        
        return (String)session.getAttribute("user");
        
    }

 
    @GetMapping("/nflpickem/picks/{username}/group/{group}/week/{week}")
    public ResponseEntity<List<Pick>> getByUsername(@PathVariable("username") String username,@PathVariable("group") String grp, @PathVariable("week") int week, HttpSession session) {
    
        if (this.isLoggedIn(session)) {
            //get the username of the user logged in
            String myusername = this.getUsername(session);
            if (myusername.equals(username) || this.isAdmin(session)) {
                //is the user in the group in question?
                if (groupusers.existsByUsernameAndGrpname(username, grp)) {
                    //if so, go get the picks
                    return new ResponseEntity(picks.findByUsernameAndGrpnameAndWeek(username, grp, week), HttpStatus.UNAUTHORIZED);                    
                } else {
                    return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
                }
                
                
            } else {
                return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
            }
            
            
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
        
    }
    
    
   
}


////    //base url for all requests should be:
////    // -> /nflpickem/picks


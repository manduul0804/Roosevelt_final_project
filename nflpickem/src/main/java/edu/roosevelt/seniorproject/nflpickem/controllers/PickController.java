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

    //Code below for
    /* 
    As a user, I want to be able to see my picks for each group that I belong to for a given week
       
     */

    @GetMapping("/nflpickem/picks/{username}")
    public ResponseEntity<Pick> getByUsername (@PathVariable("username") String username, HttpSession session) {
       
        if (this.isLoggedIn(session)) {
            User you = (User)session.getAttribute("user");
            if ((you.getUsername().equals (username))) {
                if (picks.existsById(username)) {
                   //They Exist! Lets get them
                   Iterable<Pick> info = picks.findAll();
                   //give me them
//                
                    return new ResponseEntity(info, HttpStatus.OK);
                    }
               } 
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
                
            } else {
           return new ResponseEntity(null, HttpStatus.UNAUTHORIZED); 
            }
           
    }  
    
    
    //checking if the endpoint works
    //Doesn't work right.. need to look this up
    
//    @GetMapping("/nflpickem/picks/{username}/{grpname}/{week}")
//    public ResponseEntity<Pick> getByUsernameAndGrpnameAndWeek (@PathVariable("user") String username,(@PathVariable ("grpname") String group, (@PathVariable ("week") Integer week, HttpSession session) {
//      
//       //They Exist! Lets get them
//         Iterable<Pick> info = picks.findByUsernameAndGrpnameAndWeek(username, group, 0);
//        //give me them
//                    
//                          
//    }  
//    
    
    
    //base url for all requests should be:
    // -> /nflpickem/picks
}

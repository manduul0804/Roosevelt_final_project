/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.controllers;

import edu.roosevelt.seniorproject.nflpickem.games.GameRepository;
import edu.roosevelt.seniorproject.nflpickem.groups.PickemGroupRepository;
import edu.roosevelt.seniorproject.nflpickem.pickemgroupuser.PickemGroupUserRepository;
import edu.roosevelt.seniorproject.nflpickem.groups.PickemGroup;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mruth
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class PickemGroupController {
   private static final Logger logger = LoggerFactory.getLogger(PickemGroupController.class);
    
    @Autowired
    UserRepository users;
    
    @Autowired
    GameRepository games;
    
    @Autowired
    PickemGroupRepository groups;
    
    @Autowired
    PickemGroupUserRepository groupusers;
    
    //base url for all requests should be:
    // -> /nflpickem/groups
    
    // checks to see if user is logged in
    private boolean isLoggedIn(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return true;
        } else {
            return false;
        }
    }

    // checks for a valid session
    @GetMapping("/nflpickem/groups")
    public String testGroups(HttpSession session) {
        if (session != null && session.getAttribute("user") != null) {
            return (String)session.getAttribute("groupusers");
        } else if (session != null) {
            return "good session, att";
        } else {
            return "no session";
        }
    }
    
    // checks to see if user is an Admin
    private boolean isAdmin(HttpSession session) {
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("admin") != null) {
                return true;
            }
        }
        return false;
    }

    // create a group
    @PostMapping(value = "/nflpickem/groups/creategroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PickemGroup> createGroup (@RequestBody final PickemGroup grp, HttpSession session) {
        if (groups.existsById(grp.getName())) {
            // if user exists:
            return new ResponseEntity(grp, HttpStatus.FOUND);
        } else {
            // create new group
            groups.save(grp);
            return new ResponseEntity(grp, HttpStatus.OK);
        }
    }
    
    // edit/update a group
    @PutMapping(value = "/nflpickem/groups/editgroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PickemGroup> editGroup (@RequestBody final PickemGroup grp, HttpSession session) throws SQLException {
        if (this.isLoggedIn(session)) {
            if ((session.getAttribute("user").equals(grp.getName()) || this.isAdmin(session))) {
                if ((users.existsById(grp.getName()))) {
                    groups.save(grp);
                    return new ResponseEntity(grp, HttpStatus.OK);
                } else {
                    return new ResponseEntity(grp, HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }
    
    // delete a group
    @DeleteMapping("/nflpickem/groups/{name}")
    public ResponseEntity<String> deleteGroup(@PathVariable("name") String name, HttpSession session){

        if (this.isAdmin(session)) {
            if (groups.existsById(name)) {
                groups.deleteById(name);
                return new ResponseEntity(name, HttpStatus.OK);
            } else {
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(name, HttpStatus.UNAUTHORIZED);
        }
    }
    
    // log out
    @GetMapping("/nflpickem/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "OK";
    }   
}
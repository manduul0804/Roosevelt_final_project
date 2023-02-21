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
    
    // check for a valid session
    @GetMapping("/nflpickem/groups")
  public String testGroups(HttpSession session) {
    if (session != null && session.getAttribute("user") != null) {
      return (String)session.getAttribute("groupusers");

  } else if (session != null) {
      return "good session, no att";
  } else {
      return "no session";
  }
}
    
    // check to see if user is logged in
    private boolean isLoggedIn(HttpSession session) {
      if (session.getAttribute("user") != null) {
        return true;
      } else {
        return false;
      }
    }
    
    // check to see if user is an Admin
    private boolean isAdmin(HttpSession session) {
        if (session.getAttribute("user") != null) {
            if (session.getAttribute("admin") != null) {
                return true;
            }
        } 
        return false;
    }
    
    // create a group
    @PostMapping("/nflpickem/groups/create")
    public ResponseEntity<String> createGroup(HttpSession session, String name) {
        if (isLoggedIn(session)) {
            if (groups.existsByID(name) == null) {
                // if group doesn't exist, create a new one
                groups.save(new PickemGroup());
                return new ResponseEntity(name, HttpStatus.OK);
            } else {
                return ResponseEntity.badRequest().body("Group already exists");
            }
        } else {
            return ResponseEntity.badRequest().body("User not logged in");
        }
    }
    
    // edit/update a group
    @PutMapping(value = "/nflpickem/groups/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PickemGroup> editGroup (@RequestBody final PickemGroup name, HttpSession session) throws SQLException {
        // check if group exists
        if (groups.existsById(name.getName())) {
            // edit group after it's found
            groups.save(name);
            return new ResponseEntity(name, HttpStatus.OK);
        } else {
            return new ResponseEntity(name, HttpStatus.NOT_FOUND);
        }
    }
    
    // delete a group
    @DeleteMapping("/nflpickem/groups/delete")
    public ResponseEntity<String> deleteGroup(@PathVariable("name") String name){

        if (groups.existsById(name)){
            this.deleteGroup(name);
            return new ResponseEntity(name, HttpStatus.OK);
        } else {
            return new ResponseEntity(name, HttpStatus.NOT_FOUND);
        }
    }
    
    // view all groups as an admin
    @GetMapping("/nflpickem/groups/allgroups")
    public ResponseEntity<List<PickemGroup>> getAllGroups(HttpSession session) {
        
        if (this.isAdmin(session)) {
            return new ResponseEntity(groups.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }
    
    // view all groups as a regular user
    @GetMapping("/nflpickem/groups/allgroups2")
    public ResponseEntity<List<PickemGroup>> getAllGroups2(HttpSession session) {
        
        return new ResponseEntity(groups.findAll(), HttpStatus.OK);
    }
    
    // view a specific group
    @GetMapping("/nflpickem/groups/{name}")
    public ResponseEntity<List<PickemGroup>> findGroup(@PathVariable("name") String name){
        
        if (groups.existsById(name)){
            this.findGroup(name);
            return new ResponseEntity(name, HttpStatus.FOUND);
        } else {
            return new ResponseEntity(name, HttpStatus.NOT_FOUND);
        }
    }
}
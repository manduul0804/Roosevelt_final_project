/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.controllers;

import edu.roosevelt.seniorproject.nflpickem.games.GameRepository;
import edu.roosevelt.seniorproject.nflpickem.groups.PickemGroup;
import edu.roosevelt.seniorproject.nflpickem.groups.PickemGroupRepository;
import edu.roosevelt.seniorproject.nflpickem.pickemgroupuser.PickemGroupUser;
import edu.roosevelt.seniorproject.nflpickem.pickemgroupuser.PickemGroupUserRepository;
import edu.roosevelt.seniorproject.nflpickem.user.User;
import edu.roosevelt.seniorproject.nflpickem.user.UserRepository;
import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import edu.roosevelt.seniorproject.nflpickem.groups.PickemGroup;
import edu.roosevelt.seniorproject.nflpickem.pickemgroupuser.PickemGroupUser;
import edu.roosevelt.seniorproject.nflpickem.user.User;
import edu.roosevelt.seniorproject.nflpickem.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    //checking if user is admin
    // checks to see if user is an Admin
    private boolean isAdmin(HttpSession session) {
        if (session.getAttribute("user") != null) {
            //user is logged in, will get data
            if (session.getAttribute("admin") != null) {
                return true;
            }

        }
        return false;
    }

    
    
    // BEGINNING OF ETHAN'S CODE
    // create a group
    @PostMapping(value = "/nflpickem/groups/creategroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PickemGroup> createGroup(@RequestBody final PickemGroup grp, HttpSession session) {
        if (this.isLoggedIn(session)) {
            if (groups.existsById(grp.getName())) {
                // if group exists:
                return new ResponseEntity(grp, HttpStatus.FOUND);
            } else {
                if (!this.isAdmin(session)) {
                    grp.setAdmin((String) session.getAttribute("user"));
                }
                // create new group
                groups.save(grp);
                return new ResponseEntity(grp, HttpStatus.OK);
            }
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
    
    
    
            

    // edit/update a group
    @PutMapping(value = "/nflpickem/groups/editgroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PickemGroup> editGroup(@RequestBody final PickemGroup grp, HttpSession session) throws SQLException {
        if (this.isLoggedIn(session)) {
            if (groups.existsById(grp.getName())) {
                Optional<PickemGroup> og = groups.findById(grp.getName());
                PickemGroup g = og.get();
                if ((session.getAttribute("user").equals(g.getAdmin()) || this.isAdmin(session))) {
                    g.setType(grp.getType());
                    groups.save(grp);
                    return new ResponseEntity(grp, HttpStatus.OK);
                } else {
                    return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity(grp, HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/nflpickem/groups/allgroups/{username}")
    public ResponseEntity<List<PickemGroupUser>> getAllGroups(@PathVariable("username") String username, HttpSession session) {

        if (this.isLoggedIn(session)) {
            String user = (String) session.getAttribute("user");
            //either you are the user or you're an admin
            if (user.equals(username) || this.isAdmin(session)) {
                //returns groupuser entries for the given username...
                return new ResponseEntity(groupusers.findByUsername(username), HttpStatus.OK);
                
            }
        
        
        
        
        
        }
        
        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        
        
    }


//base url for all requests should be:
// -> /nflpickem/groups 

    // delete a group
    @DeleteMapping("/nflpickem/groups/{groupname}")
    public ResponseEntity<String> deleteGroup(@PathVariable("groupname") String groupname, HttpSession session) {
        if (this.isLoggedIn(session)) {
            if (this.isAdmin(session)) {
                if (groups.existsById(groupname)) {
                    groups.deleteById(groupname);
                    return new ResponseEntity(groupname, HttpStatus.OK);
                } else {
                    return new ResponseEntity(null, HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(groupname, HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity(groupname, HttpStatus.UNAUTHORIZED);
        }
    }

    // END OF ETHAN'S CODE
}

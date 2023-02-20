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
  
  //checking if session is good
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

//creating a group
@PostMapping("/nflpickem/groups/create")
  public ResponseEntity<String> createGroup(HttpSession session, String groupname) {
    if (isLoggedIn(session)) {
      if (groups.findByName(groupname) == null) {
        //group doesn't exist, create it
        groups.save(new PickemGroup());
        //add user to group
        User user = users.findByUsername((String)session.getAttribute("user"));
        groupusers.save(new PickemGroupUser());
        return ResponseEntity.ok("Group created");
      } else {
        return ResponseEntity.badRequest().body("Group already exists");
      }
    } else {
      return ResponseEntity.badRequest().body("User not logged in");
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
    
    //checking if user is in group
    private boolean isInGroup(HttpSession session, String groupname) {
      if (session.getAttribute("user") != null) {
        User user = users.findByUsername((String)session.getAttribute("user"));
        PickemGroupUser groupuser = groupusers.findByUserAndGroup(user, groups.findByName(groupname));
        if (groupuser != null) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
    
    //checking if user is group admin
    private boolean isGroupAdmin(HttpSession session, String groupname) {
      if (session.getAttribute("user") != null) {
        User user = users.findByUsername((String)session.getAttribute("user"));
        PickemGroupUser groupuser = groupusers.findByUserAndGroup(user, groups.findByName(groupname));
        if (groupuser != null && groupuser.getIsAdmin()) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
    
    //checking if user is group owner
    private boolean isGroupOwner(HttpSession session, String groupname) {
      if (session.getAttribute("user") != null) {
        User user = users.findByUsername((String)session.getAttribute("user"));
        PickemGroupUser groupuser = groupusers.findByUserAndGroup(user, groups.findByName(groupname));
        if (groupuser != null && groupuser.getIsLeader()) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
    
    //checking if user is group member
    private boolean isGroupMember(HttpSession session, String groupname) {
      if (session.getAttribute("user") != null) {
        User user = users.findByUsername((String)session.getAttribute("user"));
        PickemGroupUser groupuser = groupusers.findByUserAndGroup(user, groups.findByName(groupname));
        if (groupuser != null && groupuser.getIsMember()) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
      }

      //if user accepts invite, we need to set status to accepted
      final boolean acceptInvite(HttpSession session, String groupname) {
        if (isLoggedIn(session)) {
          User user = users.findByUsername((String)session.getAttribute("user"));
          PickemGroupUser groupuser = groupusers.findByUserAndGroup(user, groups.findByName(groupname));
          if (groupuser != null) {
            groupuser.setIsMember(true);
            groupusers.save(groupuser);
            return ResponseEntity.ok().build() != null;
          } else {
            return ResponseEntity.badRequest().build() != null;
          }
        } else {
          return ResponseEntity.badRequest().build() != null;
        }
      }

      //if user declines invite, we need to set status to declined
      final boolean declineInvite(HttpSession session, String groupname) {
        if (isLoggedIn(session)) {
          User user = users.findByUsername((String)session.getAttribute("user"));
          PickemGroupUser groupuser = groupusers.findByUserAndGroup(user, groups.findByName(groupname));
          if (groupuser != null) {
            groupuser.setIsMember(false);
            groupusers.save(groupuser);
            return ResponseEntity.ok().build() != null;
          } else {
            return ResponseEntity.badRequest().build() != null;
          }
        } else {
          return ResponseEntity.badRequest().build() != null;
        }
    }   
  }
    //base url for all requests should be:
    // -> /nflpickem/groups 


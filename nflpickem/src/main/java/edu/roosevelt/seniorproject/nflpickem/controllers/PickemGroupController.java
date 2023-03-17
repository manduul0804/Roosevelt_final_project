
/* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.controllers;

import edu.roosevelt.seniorproject.nflpickem.games.GameRepository;
import edu.roosevelt.seniorproject.nflpickem.groups.PickemGroupRepository;
import edu.roosevelt.seniorproject.nflpickem.pickemgroupuser.PickemGroupUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import edu.roosevelt.seniorproject.nflpickem.groups.PickemGroup;
import edu.roosevelt.seniorproject.nflpickem.pickemgroupuser.PickemGroupUser;
import edu.roosevelt.seniorproject.nflpickem.user.User;
import edu.roosevelt.seniorproject.nflpickem.user.UserRepository;
import jakarta.servlet.http.HttpSession;
//<<<<<<< HEAD
import java.util.List;

//=======
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
//>>>>>>> main
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//<<<<<<< HEAD
//=======
import org.springframework.http.MediaType;
//>>>>>>> main
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

//creating a group
    @PostMapping("/nflpickem/groups/create")
    public ResponseEntity<String> createGroup(HttpSession session, String groupname) {
        if (isLoggedIn(session)) {
            if (groups.findByName(groupname) == null) {
                //group doesn't exist, create it
                groups.save(new PickemGroup());
                //add user to group
                User user = users.findByUsername((String) session.getAttribute("user"));
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

    //base url for all requests should be:
    // -> /nflpickem/groups
    // checks to see if user is logged in
    private String getUserName(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return (String) session.getAttribute("user");
        } else {
            return null;
        }
    }

    //checking if user is admin
    // checks to see if user is an Admin
    //NATHAN HUERTA - MR
    @GetMapping(value = "/nflpickem/groups/{user}/join/{group}")
    public ResponseEntity<PickemGroup> joinGroupForUser(@PathVariable("user") final String user, @PathVariable("group") final String group, HttpSession session) {
        if (this.isLoggedIn(session)) {
            //what's your username?
            String username = this.getUserName(session);
            //is there an invitation
            if (groupusers.existsByUsernameAndGrpname(user, group)) {
                //authorization?
                if (username.equals(user) || this.isAdmin(session)) {
                    //get the record
                    PickemGroupUser myinvitation = groupusers.findByUsernameAndGrpname(user, group);
                    //set status to accepted
                    myinvitation.setStatus("OK");
                    //save the record
                    groupusers.save(myinvitation);
                    //do the return
                    return new ResponseEntity(null, HttpStatus.OK);
                }
            } else {
                //there is no invitation... so you no join :(
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);

    }

    //NATHAN HUERTA - MR
    @GetMapping(value = "/nflpickem/groups/join/{group}")
    public ResponseEntity<PickemGroup> joinGroup(@PathVariable("group") final String group, HttpSession session) {

        if (this.isLoggedIn(session)) {
            //what's your username?
            String username = this.getUserName(session);
            //is there an invitation
            if (groupusers.existsByUsernameAndGrpname(username, group)) {
                //get the record
                PickemGroupUser myinvitation = groupusers.findByUsernameAndGrpname(username, group);
                //set status to accepted
                myinvitation.setStatus("OK");
                //save the record
                groupusers.save(myinvitation);
                return new ResponseEntity(null, HttpStatus.OK);

            } else {
                //there is no invitation... so you no join :(
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);

    }
    
    //Stuart Manning - MR
    @DeleteMapping(value = "/nflpickem/groups/decline/{group}")
    public ResponseEntity<PickemGroup> declineGroup(@PathVariable("group") final String group, HttpSession session) {

        if (this.isLoggedIn(session)) {
            //what's your username?
            String username = this.getUserName(session);
            //is there an invitation
            if (groupusers.existsByUsernameAndGrpname(username, group)) {
                //get the record
                PickemGroupUser myinvitation = groupusers.findByUsernameAndGrpname(username, group);
                //get rid of the record
                groupusers.delete(myinvitation);
               
                return new ResponseEntity(null, HttpStatus.OK);

            } else {
                //there is no invitation... so you no join :(
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);

    }
    
    //Stuart Manning - MR
    @DeleteMapping(value = "/nflpickem/groups/{user}/decline/{group}")
    public ResponseEntity<PickemGroup> declineGroupForUser(@PathVariable("user") final String user, @PathVariable("group") final String group, HttpSession session) {
        if (this.isLoggedIn(session)) {
            //what's your username?
            String username = this.getUserName(session);
            //is there an invitation
            if (groupusers.existsByUsernameAndGrpname(user, group)) {
                //authorization?
                if (username.equals(user) || this.isAdmin(session)) {
                    //get the record
                    PickemGroupUser myinvitation = groupusers.findByUsernameAndGrpname(user, group);
                    //get rid of the record
                    groupusers.delete(myinvitation);
                    //return
                    return new ResponseEntity(null, HttpStatus.OK);
                }
            } else {
                //there is no invitation... so you no join :(
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);

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
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
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

    @GetMapping("/nflpickem/groups/leaderboard/{group}")
    public ResponseEntity<List<PickemGroupUser>> getGroupLeaderboard(@PathVariable("group") String group, HttpSession session) {

        if (isLoggedIn(session)) {

            String username = (String) session.getAttribute("user");

            if (isAdmin(session) || (groupusers.existsByUsernameAndGrpname(username, group))) {

                List<PickemGroupUser> standings = groupusers.findByGrpname(group);

                return new ResponseEntity(standings, HttpStatus.OK);

            }

        }

        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);

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
    
    
    @GetMapping("/nflpickem/groups/byinvite")
    public ResponseEntity<List<PickemGroupUser>> getGroupsByInvite(HttpSession session) {
        String status = "invited";
        String username = this.getUserName(session); //Gets the username of the current logged in user
        if (this.isLoggedIn(session)) {
            return new ResponseEntity(groupusers.findByUsernameAndStatus(username, status), HttpStatus.OK);

        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }

    }
    
    
    
    
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

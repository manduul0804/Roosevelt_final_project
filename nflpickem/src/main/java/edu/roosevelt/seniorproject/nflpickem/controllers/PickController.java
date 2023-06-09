/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.controllers;

import edu.roosevelt.seniorproject.nflpickem.games.GameRepository;
import edu.roosevelt.seniorproject.nflpickem.groups.PickemGroupRepository;
import edu.roosevelt.seniorproject.nflpickem.pick.Pick;
import edu.roosevelt.seniorproject.nflpickem.pick.PickRepository;
import edu.roosevelt.seniorproject.nflpickem.pickemgroupuser.PickemGroupUserRepository;
import edu.roosevelt.seniorproject.nflpickem.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    
    //get num of missing picks by week, group, user
    @GetMapping("/nflpickem/picks/missing/{username}/group/{group}/week/{week}")
    public ResponseEntity<Long> getNumberOfMissingPicks(@PathVariable("username") String username,@PathVariable("group") String grp, @PathVariable("week") int week, HttpSession session) { 
        
        if (this.isLoggedIn(session)) {
            //get the username
            String user = this.getUsername(session);
            //are you authorized to know?
            if (user.equals(username) || this.isAdmin(session)) {
                //get picks
                int picksin = picks.countByUsernameAndGrpnameAndWeek(username, grp, week);
                //get games
                int nogames = games.countByWeek(week);
                //return the difference
                return new ResponseEntity(nogames-picksin, HttpStatus.OK);
                
            }
        }

        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);

        
    }
    

    //get picks by week, group, user
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
    
    //make picks 
    @PutMapping(value = "/nflpickem/picks/makepicks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pick>> makePicks(@RequestBody final List<Pick> mypicks, HttpSession session) {
        if (this.isLoggedIn(session)) {
            //get first pick
            Pick first = mypicks.get(0);
            String uname = this.getUsername(session);
            //fix for admin
            if (this.isAdmin(session)) {
                uname = first.getUsername();
            }

            //check user
            //check if appropriate user
            if (uname.equals(first.getUsername())) {
                //now we have to check if group matches
                if (picks.existsByUsernameAndGrpname(uname, first.getGrpname())) {
                    //group exists.. does the pick already exist
                    List<Pick> resultingPicks = new ArrayList();
                    //loop through the picks
                    for (Pick p : mypicks) {
                        if (picks.existsByUsernameAndGrpnameAndGameid(uname, first.getGrpname(), p.getGameid())) {
                            Pick real = picks.findByUsernameAndGrpnameAndGameid(uname, first.getGrpname(), p.getGameid());
                            real.setSelection(p.getSelection());
                            picks.save(real);
                            resultingPicks.add(real);

                        } else {
                            p.setUsername(uname);
                            p.setGrpname(first.getGrpname());
                            picks.save(p);
                            resultingPicks.add(p);
                        }
                    }

                    return new ResponseEntity(resultingPicks, HttpStatus.OK);

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


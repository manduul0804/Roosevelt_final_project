/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.controllers;

import edu.roosevelt.seniorproject.nflpickem.user.User;
import edu.roosevelt.seniorproject.nflpickem.user.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author merut
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    UserRepository users;
    
    @Autowired
    UserRepository userService;
    
    @GetMapping("/home")
    public String testHome(HttpSession session) {
        if (session != null && session.getAttribute("user") != null) {
            return (String)session.getAttribute("user");
            
        } else if (session != null) {
            return "good session, no att";
        } else {
            return "no session";
        }
    }
    
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
    
    @PostMapping("/nflpickem/user/login")
    public ResponseEntity<User> login(@RequestBody final User user, HttpSession session) {
        //does user exist
        
        if (users.existsById(user.getUsername())) {
            Optional<User> opt = users.findById(user.getUsername());
            if (opt.isPresent()) {
                //real user
                User real = opt.get();
                //check password
                if (user.getPassword().equals(real.getPassword())) {
                    //user is logged in
                    session.setAttribute("user",real.getUsername());
                    if (real.isAdmin()) {
                        session.setAttribute("admin", "yes");
                    }
                    return new ResponseEntity(real, HttpStatus.OK);
                }
            }
        } 
        return new ResponseEntity(user, HttpStatus.UNAUTHORIZED);
    }
    
    @GetMapping("/nflpickem/user/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username, HttpSession session) {
        
        if (this.isLoggedIn(session)) {
            User you = (User)session.getAttribute("user");
            if ((you.getUsername().equals(username)) || this.isAdmin(session)) {
                if (users.existsById(username)) {
                    Optional<User> opt = users.findById(username);
                    if (opt.isPresent()) {
                        User user = opt.get();
                        user.setEmail("mruth@roosevelt.edu");
                        users.save(user);
                        
                        return new ResponseEntity(user, HttpStatus.OK);

                    }
                } 
                return new ResponseEntity(null, HttpStatus.NOT_FOUND);
                
            } else {
                return new ResponseEntity(null, HttpStatus.UNAUTHORIZED); 
            }
            
        } else {
           return new ResponseEntity(null, HttpStatus.UNAUTHORIZED); 
        }
     
    }
    
    
    
    
    @GetMapping("/nflpickem/users/allusers")
    public ResponseEntity<List<User>> getAllUsers(HttpSession session) {
        
        if (this.isAdmin(session)) {
            return new ResponseEntity(users.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
        
        
        
        
        
    }
    
    @GetMapping("/nflpickem/users/allusers2")
    public ResponseEntity<List<User>> getAllUsers2(HttpSession session) {
        
        
            return new ResponseEntity(users.findAll(), HttpStatus.OK);
        
       }  

            
    @DeleteMapping("/nflpickem/users/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username, HttpSession session){
                 
        //get the logged in user (maybe null if not logged in
        User loggedInUser = (User) session.getAttribute("user");
        //get user admin status
        boolean admin = session.getAttribute("admin") != null;
        //check for good return
        if (loggedInUser != null) {
            //does it exist?
            if (userService.existsById(username)) {
                userService.deleteById(username);
                 //already there
                    return new ResponseEntity(username, HttpStatus.OK);
                } else {
                    return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
                }
                
            } else {
                //add it
                return new ResponseEntity(username, HttpStatus.NOT_FOUND);
            }
       
        }
      
      
    @GetMapping("/nflpickem/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "OK";
    }
}


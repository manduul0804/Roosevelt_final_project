/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.controllers;

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
 * @author merut
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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

    @PostMapping(value = "/nflpickem/user/login", consumes = MediaType.APPLICATION_JSON_VALUE)
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
                    session.setAttribute("user", real.getUsername());
                    if (real.isAdmin()) {
                        session.setAttribute("admin", "yes");
                    }
                    return new ResponseEntity(real, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity(user, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/nflpickem/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> insertUser(@RequestBody final User u, HttpSession session) {
        if (users.existsById(u.getUsername())) {
            //If user exists
            return new ResponseEntity(u, HttpStatus.FOUND);
        } else {
            //add to your db
            users.save(u);
            return new ResponseEntity(u, HttpStatus.OK);
        }
    }

    @GetMapping("/nflpickem/user/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username, HttpSession session) {

        if (this.isLoggedIn(session)) {
            User you = (User) session.getAttribute("user");
            if ((you.getUsername().equals(username)) || this.isAdmin(session)) {
                if (users.existsById(username)) {
                    Optional<User> opt = users.findById(username);
                    if (opt.isPresent()) {
                        User user = opt.get();
                        return new ResponseEntity(user, HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity(null, HttpStatus.NOT_FOUND);
                }
            }
        }

        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/nflpickem/users/allusers")
    public ResponseEntity<List<User>> getAllUsers(HttpSession session) {

        if (this.isAdmin(session)) {
            return new ResponseEntity(users.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }

    }

    @DeleteMapping("/nflpickem/users/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username, HttpSession session) {
        //if admin
        if (this.isAdmin(session)) {
            //does it exist
            if (users.existsById(username)) {
                //it exists, get rid of it
                users.deleteById(username);
                //already there
                return new ResponseEntity(username, HttpStatus.OK);
            } else {
                //not there
                return new ResponseEntity(username, HttpStatus.NOT_FOUND);
            }

        }
        //not authorized
        return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);

    }

    @GetMapping("/nflpickem/user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "OK";
    }

    @PutMapping(value = "/nflpickem/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody final User u, HttpSession session) throws SQLException {
        // Checks if the user is logged in
        if (this.isLoggedIn(session)) {
            // Ensures the logged in user matches the user in which they want to update or if they have admin privileges. 
            if ((session.getAttribute("user").equals(u.getUsername()) || this.isAdmin(session))) {
                if ((users.existsById(u.getUsername()))) {
                    // User found, now update
                    users.save(u);
                    return new ResponseEntity(u, HttpStatus.OK);
                } else {
                    // User not found, return 404 error
                    return new ResponseEntity(u, HttpStatus.NOT_FOUND);
                }

            } else {
                return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }
}

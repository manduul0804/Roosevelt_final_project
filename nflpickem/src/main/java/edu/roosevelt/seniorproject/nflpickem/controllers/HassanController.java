/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.controllers;

import edu.roosevelt.seniorproject.nflpickem.pick.Pick;
import edu.roosevelt.seniorproject.nflpickem.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hmahm
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class HassanController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserRepository users;

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
//picks by week
//game controller 102-109
//use method pid or selection

    @GetMapping("/nflpickem/picks/allpicks")
    public ResponseEntity<List<Pick>> getAllPicks(HttpSession session) {

        if (this.isAdmin(session)) {
            return new ResponseEntity(users.findAll(), HttpStatus.OK);

        } else {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }

}

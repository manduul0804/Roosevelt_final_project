/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.pick;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 *
 * @author mruth
 */
@Entity
public class Pick {
    @Id
    int pick;
    String username;
    String grpname;
    int gameid;
    String selection;
    
}

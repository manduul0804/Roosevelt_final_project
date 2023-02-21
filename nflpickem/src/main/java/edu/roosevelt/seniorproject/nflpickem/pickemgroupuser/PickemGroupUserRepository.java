/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.pickemgroupuser;


import org.springframework.data.repository.CrudRepository;

import edu.roosevelt.seniorproject.nflpickem.user.User;

/**
 *
 * @author mruth
 */
public interface PickemGroupUserRepository extends CrudRepository<PickemGroupUser, Integer>{

    PickemGroupUser findByUserAndGroup(User user, Object findByName);
    
}

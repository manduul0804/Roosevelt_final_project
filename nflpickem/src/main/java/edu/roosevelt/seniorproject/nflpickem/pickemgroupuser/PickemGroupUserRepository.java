/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.pickemgroupuser;


import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author mruth
 */
public interface PickemGroupUserRepository extends CrudRepository<PickemGroupUser, Integer>{

    //PickemGroupUser findByUserAndGroup(User user, Object findByName);
    
    boolean existsByUsernameAndGrpname(String user, String group);
    
    List<PickemGroupUser> findByGrpname(String group);
    
    List<PickemGroupUser> findByUsername(String username);
    
    List<PickemGroupUser> findByUsernameAndStatus(String username, String status);
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.groups;


import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author mruth
 */
public interface PickemGroupRepository extends CrudRepository<PickemGroup, String>{

    PickemGroup findByName(String groupname);
    
    

}

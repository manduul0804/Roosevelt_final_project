/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.pick;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author mruth
 */
public interface PickRepository extends CrudRepository<Pick, Integer>{
    
    List<Pick> findByUsernameAndGrpname(String user, String group);
    
    List<Pick> findByUsernameAndGrpnameAndWeek(String user, String group, int week);
    
    int countByWeek(int week);
    
    
}

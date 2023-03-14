/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.groups;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author mruth
 */
public interface PickemGroupRepository extends CrudRepository<PickemGroup, String>{

    PickemGroup findByName(String groupname);
    
    @Query(value = "select distinct type from pickemgroup", nativeQuery = true)
    List<String> findGroupTypes();
    
    @Query(value = "select pickemgroup.type, pickemgroupuser.USERNAME, max(pickemgroupuser.SCORE) AS Score "
            + "from pickemgroupuser INNER JOIN pickemgroup ON pickemgroupuser.GRPNAME = pickemgroup.NAME "
            + "group by pickemgroup.type, pickemgroupuser.USERNAME", nativeQuery = true)
    List<HighScore> getHighScoresForEachGroupType();

    
    public interface HighScore {

        public String getType();
        public int getScore();
        public String getUsername();
    }
            
         
    
   

}

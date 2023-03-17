/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.pick;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author mruth
 */
public interface PickRepository extends CrudRepository<Pick, String> {

    List<Pick> findByUsernameAndGrpname(String user, String group);

    List<Pick> findByUsernameAndGrpnameAndWeek(String user, String group, int week);

//<<<<<<< HEAD
    //int countByWeek(int week);
    //Karen Code for picks/username
    @Query(value = "SELECT * FROM PICKS WHERE USERNAME = ?1", nativeQuery = true)
    List<Pick> findSpecialUserByUsername(String username);

    List<Pick> findBySelectionAndWeek(String sel, int week);

    int countByWeek(int week);

//>>>>>>> main
    public Iterable<Pick> findByUsername(String username);

}

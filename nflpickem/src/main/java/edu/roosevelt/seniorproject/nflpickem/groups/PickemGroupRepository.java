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
public interface PickemGroupRepository extends CrudRepository<PickemGroup, String> {

    PickemGroup findByName(String groupname);

    boolean existsByName(String groupname);

    @Query(value = "SELECT any_value(pickemgroupuser.USERNAME) AS USERNAME, max(score) AS SCORE, "
            + "pickemgroup.TYPE FROM pickemgroupuser, pickemgroup WHERE pickemgroup.NAME = "
            + "pickemgroupuser.GRPNAME GROUP BY pickemgroup.TYPE ", nativeQuery = true)
    List<HighScore> getHighScoresForEachGroupType();

    public interface HighScore {

        public String getUserName();

        public String getType();

        public int getScore();
    }

}

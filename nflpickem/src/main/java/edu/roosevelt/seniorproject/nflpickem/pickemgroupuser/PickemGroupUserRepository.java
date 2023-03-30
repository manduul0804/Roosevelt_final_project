/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.pickemgroupuser;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mruth
 */
public interface PickemGroupUserRepository extends CrudRepository<PickemGroupUser, Integer> {

    //PickemGroupUser findByUserAndGroup(User user, Object findByName);
    boolean existsByUsernameAndGrpname(String user, String group);

    PickemGroupUser findByUsernameAndGrpname(String user, String group);

    List<PickemGroupUser> findByGrpname(String group);

    void deleteByGrpname(String groupname);

    List<PickemGroupUser> findByUsername(String username);

    List<PickemGroupUser> findByUsernameAndStatus(String username, String status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE nflpickem.pickemgroupuser,nflpickem.picks,nflpickem.pickemgroup SET pickemgroupuser.score = pickemgroupuser.score + 1 WHERE "
            + "pickemgroupuser.username = picks.username AND pickemgroupuser.grpname = picks.grpname AND pickemgroup.name = pickemgroupuser.grpname "
            + "AND picks.selection = ?1 AND picks.gameid = ?2 AND pickemgroup.type = ?3",
            nativeQuery = true)
    void updateScoreForSUOrATSSelections(String sel, int gameid, String type);

    @Transactional
    @Modifying
    @Query(value = "UPDATE nflpickem.pickemgroupuser,nflpickem.picks,nflpickem.pickemgroup SET pickemgroupuser.score = pickemgroupuser.score + ?1 WHERE "
            + "pickemgroupuser.username = picks.username AND pickemgroupuser.grpname = picks.grpname AND pickemgroup.name = pickemgroupuser.grpname "
            + "AND picks.selection = ?2 AND picks.gameid = ?3 AND pickemgroup.type = 'ML'",
            nativeQuery = true)
    void updateScoreForMLSelections(int amt, String sel, int gameid);

    //update the winners for survivors!
    @Transactional
    @Modifying
    @Query(value = "UPDATE nflpickem.pickemgroupuser,nflpickem.picks,nflpickem.pickemgroup SET pickemgroupuser.score = pickemgroupuser.score + 1 WHERE "
            + "pickemgroupuser.username = picks.username AND pickemgroupuser.grpname = picks.grpname AND pickemgroup.name = pickemgroupuser.grpname "
            + "AND picks.selection = ?1 AND picks.gameid = ?2 AND pickemgroup.type = 'SURV' AND pickemgroupuser.done != true",
            nativeQuery = true)
    void updateScoreForSURVSelectionsWin(String sel, int gameide);

    //update the winners for survivors!
    @Transactional
    @Modifying
    @Query(value = "UPDATE nflpickem.pickemgroupuser,nflpickem.picks,nflpickem.pickemgroup SET pickemgroupuser.done = true WHERE "
            + "pickemgroupuser.username = picks.username AND pickemgroupuser.grpname = picks.grpname AND pickemgroup.name = pickemgroupuser.grpname "
            + "AND picks.selection != ?1 AND picks.gameid = ?2 AND pickemgroup.type = 'SURV' AND pickemgroupuser.done != true",
            nativeQuery = true)
    void updateScoreForSURVSelectionsLoss(String sel, int gameid);

    List<PickemGroupUser> findByStatusOrderByGrpnameAscScoreDesc(String status);

}

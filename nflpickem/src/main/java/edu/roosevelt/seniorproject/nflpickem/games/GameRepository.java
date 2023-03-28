/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.games;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author mruth
 */
public interface GameRepository extends CrudRepository<Game, Integer> {

    int countByWeek(int week);

    List<Game> findByWeek(int week);

    Game findFirstByKickoffAfterOrderByKickoff(Timestamp ts);

}

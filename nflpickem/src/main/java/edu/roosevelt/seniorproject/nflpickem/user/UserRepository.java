/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.user;


import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author mruth
 */
public interface UserRepository extends CrudRepository<User, String>{

    
    //review
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    
}

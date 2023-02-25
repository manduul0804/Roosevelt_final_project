/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.user;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;



/**
 *
 * @author mruth
 */
@Entity
public class User {
    @Id
    String username;
    String password;
    boolean admin = false;
    String name;
    String email;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.username, other.username);
    }
    
//         String sql = "CREATE TABLE USER (";
//            sql = sql + " USERNAME VARCHAR(30) PRIMARY KEY,";
//            sql = sql + " PASSWORD VARCHAR(20),";
//            sql = sql + " ADMIN BOOLEAN,";
//            sql = sql + " NAME VARCHAR(50),";
//            sql = sql + " EMAIL VARCHAR(50))";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
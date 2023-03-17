/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.roosevelt.seniorproject.nflpickem.groups;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 *
 * @author mruth
 */
@Entity
@Table(name = "PICKEMGROUP ")
public class PickemGroup {

    @Id
    String name;
    String type;
    String admin;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.name);
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
        final PickemGroup other = (PickemGroup) obj;
        return Objects.equals(this.name, other.name);
    }

    /*
    //GROUP goes next
            String sql = "CREATE TABLE PICKEMGROUP (";
            sql = sql + "NAME VARCHAR(25) PRIMARY KEY,";
            sql = sql + " TYPE VARCHAR(3),";
            sql = sql + " ADMIN VARCHAR(50),";
            sql = sql + " FOREIGN KEY (ADMIN) REFERENCES USER(USERNAME))";
            //admin must be a real user
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

}

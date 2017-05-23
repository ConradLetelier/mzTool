/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerzone.tool;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author conra
 */
public class DBTeam implements Serializable{
    
    @Id@GeneratedValue
    int id;
    String name;
    String manager;
    double balance;
    
    @OneToMany(mappedBy="team", cascade = CascadeType.REMOVE)
    List<Player> players;
    
    public DBTeam(){}

    public DBTeam(int id, String name, String manager, double balance) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    

    
   
}


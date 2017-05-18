/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerzone.tool;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class Team implements Serializable {

    private int id;
    private final StringProperty teamManager = new SimpleStringProperty();
    private final DoubleProperty teamBalance = new SimpleDoubleProperty();
    private final StringProperty teamName = new SimpleStringProperty();
    private ArrayList<Player> players = new ArrayList<Player>();

    public Team(String teamName, String teamManager, double teamBalance) {
        setTeamName(teamName);
        setTeamManager(teamManager);
        setTeamBalance(teamBalance);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName.get();
    }

    public void setTeamName(String value) {
        teamName.set(value);
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }

    public double getTeamBalance() {
        return teamBalance.get();
    }

    public void setTeamBalance(double value) {
        teamBalance.set(value);
    }

    public DoubleProperty teamBalanceProperty() {
        return teamBalance;
    }

    public String getTeamManager() {
        return teamManager.get();
    }

    public void setTeamManager(String value) {
        teamManager.set(value);
    }

    public StringProperty teamManagerProperty() {
        return teamManager;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void changePlayer(int playerIndex, String name, String url) {
        if (name.isEmpty()) {
            this.players.get(playerIndex).setImgURL(url);
        } else if (url.isEmpty()) {
            this.players.get(playerIndex).setName(name);
        } else if (name.isEmpty() && url.isEmpty()) {

        } else {
            this.players.get(playerIndex).setName(name);
            this.players.get(playerIndex).setImgURL(url);
        }

    }

    /*
    private String name;
    private String manager;
    private double balance;
    private ArrayList<Player> players = new ArrayList<Player>();
    StringProperty teamName = new SimpleStringProperty();
    StringProperty teamManager = new SimpleStringProperty();
    DoubleProperty teamBalance = new SimpleDoubleProperty();
    
    public Team(String name, String manager, double balance) {
        this.name = name;
        this.manager = manager;
        this.balance = balance;
        teamName.set(name);
        teamManager.set(manager);
        teamBalance.set(balance);
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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player playerToBeAdded) {
        this.players.add(playerToBeAdded);
    }
    
    public void changePlayer(int playerIndex, String name, String url){
        if(name.isEmpty()){
            this.players.get(playerIndex).setImgurl(url);
        }else if(url.isEmpty()){
            this.players.get(playerIndex).setName(name);
        }else if(name.isEmpty()&&url.isEmpty()){
            
        }
        else{
            this.players.get(playerIndex).setName(name);
            this.players.get(playerIndex).setImgurl(url);   
        }
        
    }
    
    public void removePlayer(int playerIndex){
        this.players.remove(playerIndex);
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
     */
}

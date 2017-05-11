/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerzone.tool;

import java.util.ArrayList;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class Team {
    private String name;
    private String manager;
    private double balance;
    private ArrayList<Player> players = new ArrayList<Player>();
    
    public Team(String name, String manager, double balance) {
        this.name = name;
        this.manager = manager;
        this.balance = balance;
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
    

    
    
    
    
}

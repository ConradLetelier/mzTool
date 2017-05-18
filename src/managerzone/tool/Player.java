/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerzone.tool;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class Player {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String imgURL;
    @ManyToOne
    Team team;

    public Player() {

    }

    public Player(int id, String name, String imgURL) {
        this.id = id;
        this.name = name;
        this.imgURL = imgURL;
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

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}

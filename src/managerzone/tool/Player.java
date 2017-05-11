/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managerzone.tool;

/**
 *
 * @author Conrad Letelier <Conrad@Letelier.email>
 */
public class Player {
    private String name;
    private String imgurl;

    public Player(String name, String imgurl) {
        this.name = name;
        this.imgurl = imgurl;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
    
    
}

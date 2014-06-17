package harusame.core.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import harusame.core.model.entity.Sprite;
import java.awt.Graphics2D;
import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author Hikari
 */
public class Controller {
    
    ArrayList<Sprite>   sprites = new ArrayList<Sprite> ();
    
    public void keyPressed(int keyCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void keyReleased(int keyCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void draw(Graphics2D g) {
        for (int i=0; i<sprites.size(); i++){
            sprites.get(i).draw(g);
        }
    }
}

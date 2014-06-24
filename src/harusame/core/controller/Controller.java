package harusame.core.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import harusame.core.model.entity.Player;
import harusame.core.model.entity.Sprite;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Hikari
 */
public class Controller {
    
    Player  player;
    ArrayList<Sprite>   sprites = new ArrayList<Sprite> ();
    
    public Controller () {
        player = new Player (100, 150);
    }
    
    public void keyPressed(int keyCode) {
        player.keyPressed(keyCode);
    }

    public void keyReleased(int keyCode) {
        player.keyReleased(keyCode);
    }

    public void update() {
        player.update();
    }

    public void draw(Graphics2D g) {
        player.draw(g, (int)player.getX(), (int)player.getY());
        
        for (int i=0; i<sprites.size(); i++){
            Sprite  s = sprites.get(i);
            s.draw(g, (int)s.getX(), (int)s.getY());
        }
    }
}

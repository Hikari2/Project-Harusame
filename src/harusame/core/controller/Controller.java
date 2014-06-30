package harusame.core.controller;

import harusame.core.model.collision.CollisionHandler;
import harusame.core.model.entity.Bee;
import harusame.core.model.entity.Player;
import harusame.core.model.entity.Sprite;
import harusame.core.model.map.MapLoader;
import harusame.core.model.map.Tile;
import harusame.core.model.map.TileMap;
import harusame.core.util.Observer;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Hikari
 */
public class Controller {
    
    private final MapLoader ml = new MapLoader(); 
    private final CollisionHandler ch = CollisionHandler.getCollisionHandler();
    
    private TileMap map;
    
    Player  player;
    ArrayList<Sprite>   sprites = new ArrayList<Sprite> ();
    
    public Controller () {
        loadMap("Level1");
    }
    
    public void loadMap (String level) {
        map = ml.loadMap(level);
        sprites = map.getSprites();
        player = map.getPlayer ();
    }
    
    public void addObserver (Observer observer) {
        //player.addObserver (observer);
    }
    
    public void keyPressed(int keyCode) {
        player.keyPressed(keyCode);
    }

    public void keyReleased(int keyCode) {
        player.keyReleased(keyCode);
    }

    public void update() {
        player.update();
        ch.CheckTileCollision(player, map);
        for(int i = 0; i < sprites.size(); i++)
        {
            sprites.get(i).update();
            ch.CheckEnemyCollision((Bee) sprites.get(i), map);
        }
    }
    
    public void draw(Graphics2D g) {  
        map.draw (g, (int)player.getX(), (int)player.getY());
        player.draw(g, (int)player.getX(), (int)player.getY());
        
        for(int i = 0; i < sprites.size(); i++)
            sprites.get(i).draw(g, sprites.get(i).getX(), sprites.get(i).getY());
    }
}

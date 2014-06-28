package harusame.core.controller;

import harusame.core.model.collision.CollisionHandler;
import harusame.core.model.entity.Player;
import harusame.core.model.entity.Sprite;
import harusame.core.model.map.MapLoader;
import harusame.core.model.map.TileMap;
import harusame.core.util.Observer;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Hikari
 */
public class Controller {
    
    private final MapLoader ml = new MapLoader(); 
    private final CollisionHandler ch = CollisionHandler.getCollisionHandler();
    private final TileMap map;
    
    Player  player;
    ArrayList<Sprite>   sprites = new ArrayList<Sprite> ();
    
    public Controller () {
        player = new Player (40, 40);
        map = ml.loadMap("Level1");
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
    }  
    
    public void draw(Graphics2D g) {
        player.draw(g, (int)player.getX(), (int)player.getY());  
        map.draw (g, (int)player.getX(), (int)player.getY());
    }
}

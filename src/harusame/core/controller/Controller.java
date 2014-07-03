package harusame.core.controller;

import harusame.core.model.DTO.GameLevelDTO;
import harusame.core.model.collision.CollisionHandler;
import harusame.core.model.entity.Bee;
import harusame.core.model.entity.Enemy;
import harusame.core.model.entity.Player;
import harusame.core.model.entity.Projectile;
import harusame.core.model.entity.Sprite;
import harusame.core.model.map.MapLoader;
import harusame.core.model.map.TileMap;
import harusame.core.util.Observer;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 *
 * @author Hikari
 */
public class Controller {
    
    private final MapLoader ml = new MapLoader(); 
    private final CollisionHandler ch = CollisionHandler.getCollisionHandler();
    
    private String CURRENT_LEVEL;
    private String LAST_CHECKPOINT;
    
    private TileMap map;
    private Player  player;
    private ArrayList<Enemy>   enemies;
    private ArrayList<Projectile>   projectiles;
    
    public Controller () {
        loadMap("Level1");
        CURRENT_LEVEL = "Level1";
    }
    
    public void loadMap (String level) {
        GameLevelDTO    gameLevel = ml.loadMap(level);
        map = gameLevel.getMap();
        enemies = gameLevel.getEnemies();
        player = gameLevel.getPlayer();
        projectiles = gameLevel.getProjectiles();
        
    }
    
    public void reloadLevel () {
        loadMap (CURRENT_LEVEL);
    }
    public void addObserver (Observer observer) {
        player.addObserver (observer);
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
        for(int i = 0; i < enemies.size(); i++)
        {
            enemies.get(i).update();
            ch.CheckEnemyCollision((Bee) enemies.get(i), map);
        }
    }
    
    public void draw(Graphics2D g) {  
        map.draw (g, (int)player.getX(), (int)player.getY());
        player.draw(g, (int)player.getX(), (int)player.getY());
        
        for(int i = 0; i < enemies.size(); i++)
            enemies.get(i).draw(g, enemies.get(i).getX(), enemies.get(i).getY());
    }
}

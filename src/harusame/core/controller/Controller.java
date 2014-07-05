package harusame.core.controller;

import harusame.core.model.DTO.GameLevelDTO;
import harusame.core.model.EntityManager;
import harusame.core.model.collision.CollisionHandler;
import harusame.core.model.entity.Enemy;
import harusame.core.model.Player;
import harusame.core.model.entity.Projectile;
import harusame.core.model.map.MapLoader;
import harusame.core.model.map.TileMap;
import harusame.core.util.Observer;
import java.util.ArrayList;

/**
 *
 * @author Hikari
 */
public class Controller {
    
    EntityManager   em;
    
    private final MapLoader ml; 
    
    private String CURRENT_LEVEL;
    private String LAST_CHECKPOINT;
    
    public Controller () {
        em = new EntityManager ();
        ml = new MapLoader(em); 
    }
    
    public void startGame () {
        loadMap("Level_01");
        CURRENT_LEVEL = "Level_01";
    }
    public void loadMap (String level) {
        ml.loadMap(level);
    }
    
    public void reloadLevel () {
        loadMap (CURRENT_LEVEL);
    }
    
    public void keyPressed(int keyCode) {
        em.getPlayer().keyPressed(keyCode);
    }

    public void keyReleased(int keyCode) {
        em.getPlayer().keyReleased(keyCode);
    }

    public void update() {
        em.update();
    }
    
    public void addObserver (Observer o) {
        em.addObserver(o);
    }
}

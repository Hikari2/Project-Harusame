package harusame.core.model;

import harusame.core.model.entity.Enemy;
import harusame.core.model.entity.Projectile;
import harusame.core.model.map.Tile;
import harusame.core.model.map.TileMap;
import harusame.core.util.Observer;
import java.util.ArrayList;

public class EntityManager {

    private Observer observer;
    
    private Player  player;
    private TileMap map;
    private ArrayList<Enemy>    enemies = new ArrayList ();
    private ArrayList<Projectile>   projectiles = new ArrayList ();
    
    public EntityManager () {
        
    }
    
    public void clear () {
        player = null;
        map = new TileMap (0, 0);
        enemies = new ArrayList (); 
        projectiles = new ArrayList ();
    }
    
    public void setPlayer(Player player) {
        this.player = player;
        observer.notifyNewPlayer(player);
    }
    
    public Player getPlayer () {
        return player;
    }
    
    public void setMap(TileMap map) {
        this.map = map;
        observer.notifyNewMap(map);
    }
    
    public void addEnemy(Enemy enemy) {
        enemies.add (enemy);
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }
    
    public void update () {
        player.update ();
    }
    
    public void addObserver (Observer o) {
        observer = o;
    }
}

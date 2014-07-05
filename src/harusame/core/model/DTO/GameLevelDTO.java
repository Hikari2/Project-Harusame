package harusame.core.model.DTO;

import harusame.core.model.entity.Enemy;
import harusame.core.model.Player;
import harusame.core.model.entity.Projectile;
import harusame.core.model.map.TileMap;
import java.util.ArrayList;

/**
 *
 * @author Hikari
 */
public class GameLevelDTO {
    
    private String LEVEL_CODE;
    private String NEXT_LEVEL_CODE;
    
    private TileMap map;
    private Player player;
    private ArrayList<Enemy>   enemies;
    private ArrayList<Projectile>   projectiles = new ArrayList ();

    public TileMap getMap() {
        return map;
    }

    public void setMap(TileMap map) {
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public String getLEVEL_CODE() {
        return LEVEL_CODE;
    }

    public void setLEVEL_CODE(String LEVEL_CODE) {
        this.LEVEL_CODE = LEVEL_CODE;
    }

    public String getNEXT_LEVEL_CODE() {
        return NEXT_LEVEL_CODE;
    }

    public void setNEXT_LEVEL_CODE(String NEXT_LEVEL_CODE) {
        this.NEXT_LEVEL_CODE = NEXT_LEVEL_CODE;
    }
}

package harusame.core.model.DTO;

import harusame.core.model.entity.Enemy;
import harusame.core.model.entity.Player;
import harusame.core.model.entity.Projectile;
import harusame.core.model.map.TileMap;
import java.util.ArrayList;

/**
 *
 * @author Hikari
 */
public class GameLevelDTO {
    
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
}

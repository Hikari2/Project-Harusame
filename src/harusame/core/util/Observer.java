package harusame.core.util;

import harusame.core.model.Player;
import harusame.core.model.Enemy;
import harusame.core.model.MovableSprite;
import harusame.core.model.Stone;
import harusame.core.model.map.TileMap;

/**
 *
 * @author Hikari
 */
public interface Observer {
    
    void notifyReset ();
    
    void notifyNewMap (TileMap  map);
    
    void notifyNewPlayer (Player p);
    
    void notifyNewEnemy (Enemy e);
    
    void notifyNewStone (Stone s);
    
    void notifyGameOver ();
    
    void notifyNewLevel (Level level);
}

package harusame.core.util;

import harusame.core.model.Player;
import harusame.core.model.Enemy;
import harusame.core.model.Interactable;
import harusame.core.model.MovableSprite;
import harusame.core.model.map.TileMap;

/**
 *
 * @author Hikari
 */
public interface Observer {
    
    void notifyReset ();
    
    void notifyNewMap (TileMap  map);
    
    void notifyNewLevel (Level level);
    
    void notifyNewPlayer (Player p);
    
    void notifyNewEnemy (Enemy e);
    
    void notifyGameOver ();

    public void notifyNewInteractable(Interactable i);
}

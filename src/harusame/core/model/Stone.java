package harusame.core.model;

import harusame.core.model.MovableSprite;
import harusame.core.model.map.Tile;
import static harusame.core.util.Direction.DOWN;

/**
 *
 * @author Hikari
 */
public class Stone extends MovableSprite {
    
    private boolean falling;
   

    public Stone(int x, int y, boolean larva) {
        super(x, y, larva);
        DIRECTION = DOWN;
        MOVE_SPEED = 15;        
    }

    public boolean isFalling() {
        return falling;
    }   
   
    public void setFalling(boolean falling) {
        this.falling = falling;
    }
    
    public void moveLeft (int playerX){
        lastX = x;
        x = playerX - Tile.WIDTH;
    }
    
    public void moveRight (int playerX){
        lastX = x;
        x = playerX + Tile.WIDTH;
    }
}

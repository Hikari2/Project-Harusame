package harusame.core.model;

import harusame.core.model.map.Tile;
import static harusame.core.util.Direction.DOWN;

/**
 *
 * @author Hikari
 */
public class Stone extends MovableSprite {
    
    private boolean falling;

    public Stone(int x, int y) {
        super(x, y);
        DIRECTION = DOWN;
        MOVE_SPEED = 15;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }
    
    public void moveLeft (){
        lastX = x;
        x = x - Tile.WIDTH;
    }
    
    public void moveRight (){
        lastX = x;
        x = x + Tile.WIDTH;
    }
}

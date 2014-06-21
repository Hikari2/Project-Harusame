package harusame.core.model.entity;

import harusame.core.util.Direction;
import static harusame.core.util.Direction.DOWN;
import static harusame.core.util.Direction.LEFT;
import static harusame.core.util.Direction.NEUTRAL;
import static harusame.core.util.Direction.RIGHT;
import static harusame.core.util.Direction.UP;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author Hikari
 */
public class Player extends Sprite{
    
    private Direction lastDirection;
    
    public Player(int width, int height) {
        super(width, height);
    }
    
    public void update () {
        switch (this.direction){
            case LEFT: this.x--;
                break;
            case RIGHT: this.x++;
                break;
            case UP: this.y--;
                break;
            case DOWN: this.y++;
                break;
            case NEUTRAL: 
                break;     
        }
    }
    
    public void keyPressed (int keyCode) {
        direction = KeyToDirection (keyCode);
    }
    
    private boolean isKeyHeld (Direction d) {
        return lastDirection == d;
    }
    
    public void keyReleased (int keyCode) {
        if (direction == KeyToDirection (keyCode))
            direction = NEUTRAL;
    }
    
    private Direction KeyToDirection (int keyCode) {
        
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                return LEFT;
            case KeyEvent.VK_RIGHT:
                return  RIGHT;
            case KeyEvent.VK_UP:
                return  UP;
            case KeyEvent.VK_DOWN:
                return  DOWN;
        }
        return null;
    }
}

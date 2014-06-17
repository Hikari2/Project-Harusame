package harusame.core.model.entity;

import harusame.core.util.Direction;
import static harusame.core.util.Direction.DOWN;
import static harusame.core.util.Direction.LEFT;
import static harusame.core.util.Direction.NEUTRAL;
import static harusame.core.util.Direction.RIGHT;
import static harusame.core.util.Direction.UP;
import java.awt.Graphics;

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
    
    public void keyPressed (Direction d) {
        switch (d) {
            case LEFT: this.direction = LEFT;
                break;
            case RIGHT: this.direction = RIGHT;
                break;
            case UP: this.direction = UP;
                break;
            case DOWN: this.direction = DOWN;
                break;
        }
    }
    
    private boolean isKeyHeld (Direction d) {
        return lastDirection == d;
    }
    
    public void keyReleased (Direction d) {
        if (lastDirection == d)
            direction = NEUTRAL;
    }
}

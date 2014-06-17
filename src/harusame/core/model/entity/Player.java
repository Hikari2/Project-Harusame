package harusame.core.model.entity;

import harusame.core.util.Direction;
import static harusame.core.util.Direction.DOWN;
import static harusame.core.util.Direction.LEFT;
import static harusame.core.util.Direction.NEUTRAL;
import static harusame.core.util.Direction.RIGHT;
import static harusame.core.util.Direction.UP;

/**
 *
 * @author Hikari
 */
public class Player extends Sprite{
    
    private Direction directionHeld;
    
    public Player(int width, int height) {
        super(width, height);
    }
    
    public void update () {
        switch (this.direction)
            case LEFT: this.x += this.
    }
    
    public void keyPressed (Direction d) {
        if (d == this.direction)
            directionHeld = d;
        
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
    
    public void keyReleased (Direction d) {
        switch (d) {
            case LEFT: this.direction = NEUTRAL;
                       if (directionHeld == d)
                            directionHeld = null;
                break;
            case RIGHT: this.direction = NEUTRAL;
                       if (directionHeld == d)
                            directionHeld = null;
                break;
            case UP: this.direction = NEUTRAL;
                       if (directionHeld == d)
                            directionHeld = null;
                break;
            case DOWN: this.direction = NEUTRAL;
                       if (directionHeld == d)
                            directionHeld = null;
                break;
        }
    }
}

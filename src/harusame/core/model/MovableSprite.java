 package harusame.core.model;

import harusame.core.util.Direction;
import static harusame.core.util.Direction.NEUTRAL;
import static harusame.core.util.Direction.OTHER;

/**
 *
 * @author Hikari
 */
public class MovableSprite extends Sprite {

    protected Direction  DIRECTION = NEUTRAL;
    protected Direction  LAST_DIRECTION = NEUTRAL;
    
    protected int MOVE_SPEED;
    
    protected int lock;
    
    public MovableSprite(int x, int y) {
        super(x, y);
    }
    
    public void update () {
        
    }
    
    public void kill () {
        setACTIVE (false);
        DIRECTION = OTHER;
        LAST_DIRECTION = OTHER;
        lock = 24;
    }
    
    
    public Direction getDIRECTION() {
        return DIRECTION;
    }

    public void setDIRECTION(Direction DIRECTION) {
        this.DIRECTION = DIRECTION;
    }

    public Direction getLAST_DIRECTION() {
        return LAST_DIRECTION;
    }

    public void setLAST_DIRECTION(Direction LAST_DIRECTION) {
        this.LAST_DIRECTION = LAST_DIRECTION;
    }
    
    
}

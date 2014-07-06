package harusame.core.model;

import harusame.core.util.Direction;
import static harusame.core.util.Direction.NEUTRAL;

/**
 *
 * @author Hikari
 */
public class MovableSprite extends Sprite {

    protected Direction  DIRECTION = NEUTRAL;
    protected Direction  LAST_DIRECTION = NEUTRAL;
    
    protected int MOVE_SPEED;
    
    public MovableSprite(int x, int y) {
        super(x, y);
    }
    
    public void update () {
        
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

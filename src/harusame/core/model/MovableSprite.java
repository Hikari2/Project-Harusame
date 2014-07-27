 package harusame.core.model;

import harusame.core.util.Direction;
import static harusame.core.util.Direction.DOWN;
import static harusame.core.util.Direction.NEUTRAL;
import static harusame.core.util.Direction.OTHER;
import harusame.core.util.MovableType;
import static harusame.core.util.MovableType.STONE;

/**
 *
 * @author Hikari
 */
public class MovableSprite extends Sprite {

    protected Direction  DIRECTION = NEUTRAL;
    protected Direction  LAST_DIRECTION = NEUTRAL;
    
    protected int MOVE_SPEED;
    
    protected int lock;
    
    private MovableType type;
    private boolean falling;
    
    public MovableSprite(int x, int y) {
        super(x, y);
        type = null;
        MOVE_SPEED = 5;
    }
    
    public MovableSprite(int x, int y, MovableType newType) {
        super(x, y);
        type = newType;
        if(type == STONE)
        {
            MOVE_SPEED = 15;
            DIRECTION = DOWN;
            falling = false;
        }
    }
    
    public void revert () {  
        x = lastX;
        y = lastY;       
    }
    
    public boolean isFalling()
    {
        return falling;
    }
    
    public void setFalling(boolean newFall)
    {
        falling = newFall;
    }
    
    public void update () 
    {
        lastX = x;
        lastY = y;
        
        switch (DIRECTION){
            case LEFT:                 
                this.x-=MOVE_SPEED;
                break;
                
            case RIGHT: 
                this.x+=MOVE_SPEED;     
                break;
                
            case UP: 
                this.y-=MOVE_SPEED;
                break;
                
            case DOWN: 
                this.y+=MOVE_SPEED;
                break; 
        }
        
        
    }
    
    public void kill () {
        setACTIVE (false);
        lock = 15;
        DIRECTION = OTHER;
        LAST_DIRECTION = OTHER;
    }
    
    public boolean isLocked () {
        if (lock > 0)
            return true;
        else return false;
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
    
     public MovableType  getType () {
        return type;
    }
}

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
    
    int life;
    boolean isLarva = false;

    public MovableSprite(int x, int y) {
        super(x, y);
        MOVE_SPEED = 5;
    }
    
    public MovableSprite(int x, int y, MovableType newType) {
        super(x, y);
    }
    
    public MovableSprite(int x, int y, boolean larva)
    {
        super(x, y);
        isLarva = larva;
    }
    
    public void revert () {  
        x = lastX;
        y = lastY;       
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
        life--;
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
    
     public boolean isLarva() {
        return isLarva;
    }
}

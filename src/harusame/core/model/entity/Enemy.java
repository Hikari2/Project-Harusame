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
public class Enemy extends Sprite{
    
    private Direction lastDirection;
    
    public Enemy(int x, int y) {
        super(x, y);
    }
 
    public void revert () {  
        x = lastX;
        y = lastY;       
        lastDirection = direction;
        
        switch (direction){
            case LEFT:  
                direction = UP;                
                break;
                
            case UP:     
                direction = RIGHT;                
                break;  
                
            case RIGHT:     
                direction = DOWN;               
                break;   
                    
            case DOWN:     
                direction = LEFT;                
                break;   
        }           
    }
        
    @Override
    public void update () {
        lastX = x;
        lastY = y;   
        

        switch (direction){
            case LEFT:                 
                this.x-=MOVE_SPEED;
                if(lastDirection == direction)
                    setAnimation (al.getFacingLeft());
                else
                    lastDirection = direction;
                break;
                
            case RIGHT: 
                this.x+=MOVE_SPEED;
                if(lastDirection == direction)
                    setAnimation (al.getFacingRight());
                else
                    lastDirection = direction;        
                break;
                
            case UP: 
                this.y-=MOVE_SPEED;
                if(lastDirection == direction)
                    setAnimation (al.getFacingUp());
                else
                    lastDirection = direction;
                break;
                
            case DOWN: 
                this.y+=MOVE_SPEED;
                if(lastDirection == direction)
                    setAnimation (al.getFacingDown());
                else
                    lastDirection = direction;
                break;
                
            case NEUTRAL: 
                break;     
        }
        if (direction != NEUTRAL) {
            updateAnimation ();
        }
        else 
            resetAnimation ();
    }
    
}

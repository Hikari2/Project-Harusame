package harusame.core.model.entity;


import harusame.core.model.animation.PlayerAnimationLoader;
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
public class Bee extends Sprite {
    
    private final PlayerAnimationLoader   pal = new PlayerAnimationLoader("Enemies/th_bee.png");
    private Direction lastDirection;
   
    public Bee(int x, int y) {
        super(x, y);
        direction = LEFT;
        setAnimation (pal.getFacingLeft());
        MOVE_SPEED = 3;
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
                    setAnimation (pal.getFacingLeft());
                else
                    lastDirection = direction;
                break;
                
            case RIGHT: 
                this.x+=MOVE_SPEED;
                if(lastDirection == direction)
                    setAnimation (pal.getFacingRight());
                else
                    lastDirection = direction;        
                break;
                
            case UP: 
                this.y-=MOVE_SPEED;
                if(lastDirection == direction)
                    setAnimation (pal.getFacingUp());
                else
                    lastDirection = direction;
                break;
                
            case DOWN: 
                this.y+=MOVE_SPEED;
                if(lastDirection == direction)
                    setAnimation (pal.getFacingDown());
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

package harusame.core.model.entity;


import harusame.core.model.animation.PlayerAnimationLoader;

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
    
   
    public Bee(int x, int y) {
        super(x, y);
        direction = LEFT;
        setAnimation (pal.getFacingLeft());
        MOVE_SPEED = 5;
    }
    
    public void revert () {  
        x = lastX;
        y = lastY;       
        
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
                setAnimation (pal.getFacingLeft());
                break;
                
            case RIGHT: 
                this.x+=MOVE_SPEED;
                setAnimation (pal.getFacingRight());           
                break;
                
            case UP: 
                this.y-=MOVE_SPEED;
                setAnimation (pal.getFacingUp());
                break;
                
            case DOWN: 
                this.y+=MOVE_SPEED;
                setAnimation (pal.getFacingDown());
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

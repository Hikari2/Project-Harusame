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
    
    private int count = 0;
    private final PlayerAnimationLoader   pal = new PlayerAnimationLoader("Enemies/th_bee.png");
    
    private int lastX;
    private int lastY;
    
    int lock;
    
    public Bee(int width, int height) {
        super(width, height);
        this.direction = LEFT;
        setAnimation (pal.getFacingLeft());
    }
    
    public void revert () {  
        if(lock > 0)
        {
            lock--;
            return;
        }
        
        this.x = lastX;
        this.y = lastY;       
        
        switch (this.direction){
            case LEFT:  
                lock = 5;
                this.direction = RIGHT;
                setAnimation (pal.getFacingRight());
                break;
                
            case RIGHT:     
                lock = 5;
                this.direction = LEFT;                
                setAnimation (pal.getFacingLeft());      
                break;         
        }        
               
    }
        
    @Override
    public void update () {
        
        
        lastX = this.x;
        lastY = this.y;        
         
        
        switch (this.direction){
            case LEFT:                 
                this.x-=2;
                setAnimation (pal.getFacingLeft());
                break;
                
            case RIGHT: 
                this.x+=2;
                setAnimation (pal.getFacingRight());           
                break;
                
            case UP: 
                this.y-=2;
                setAnimation (pal.getFacingUp());
                break;
                
            case DOWN: 
                this.y+=2;
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

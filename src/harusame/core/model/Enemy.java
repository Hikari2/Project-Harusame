package harusame.core.model;

import harusame.core.util.Direction;
import static harusame.core.util.Direction.DOWN;
import static harusame.core.util.Direction.LEFT;
import static harusame.core.util.Direction.RIGHT;
import static harusame.core.util.Direction.UP;
import harusame.core.util.EnemyType;

/**
 *
 * @author Hikari
 */
public class Enemy extends MovableSprite{
    
    private EnemyType   type;
    
    public Enemy(int x, int y, EnemyType    type) {
        super(x, y);
        this.type = type;
        MOVE_SPEED = 2;
        DIRECTION = LEFT;
    }
 
    public void revert () {  
        x = lastX;
        y = lastY;       
        
        switch (DIRECTION){
            case LEFT:  
                DIRECTION = UP;                
                break;
                
            case UP:     
                DIRECTION = RIGHT;                
                break;  
                
            case RIGHT:     
                DIRECTION = DOWN;               
                break;   
                    
            case DOWN:     
                DIRECTION = LEFT;                
                break;   
        }           
    }
        
    @Override
    public void update () {
        lastX = x;
        lastY = y;   
        
        LAST_DIRECTION = DIRECTION;
        
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
    
    public EnemyType    getType () {
        return type;
    }
}
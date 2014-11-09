package harusame.core.model;

import harusame.core.util.Direction;
import harusame.core.util.ProjectileType;

/**
 *
 * @author Hikari
 */
public class Projectile extends MovableSprite {

    ProjectileType type;
    
    public Projectile (int x, int y, ProjectileType p, Direction d) {
        super(x, y);
        MOVE_SPEED = 15;
        type  = p;
        DIRECTION = d;
    }
   
    @Override
    public void update () {
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
    
    public ProjectileType getType() {
        return type;        
    }
    
}

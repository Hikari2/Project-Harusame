package harusame.core.model.entity;

/**
 *
 * @author Hikari
 */
public class Projectile extends Sprite {

    public Projectile (int x, int y) {
        super(x, y);
        MOVE_SPEED = 8;
    }
    
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
}

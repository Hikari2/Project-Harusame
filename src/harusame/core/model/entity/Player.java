package harusame.core.model.entity;

import harusame.core.util.Direction;
import static harusame.core.util.Direction.DOWN;
import static harusame.core.util.Direction.LEFT;
import static harusame.core.util.Direction.NEUTRAL;
import static harusame.core.util.Direction.RIGHT;
import static harusame.core.util.Direction.UP;
import java.awt.event.KeyEvent;

/**
 *
 * @author Hikari
 */
public class Player extends Sprite{
    
    private final DirectionKey left = new DirectionKey (LEFT);
    private final DirectionKey right = new DirectionKey (RIGHT);
    private final DirectionKey up = new DirectionKey (UP);
    private final DirectionKey down = new DirectionKey (DOWN);
    
    private int priority = 4;
    
    
    public Player(int width, int height) {
        super(width, height);
    }
    
    public void update () {
        switch (this.direction){
            case LEFT: this.x--;
                break;
            case RIGHT: this.x++;
                break;
            case UP: this.y--;
                break;
            case DOWN: this.y++;
                break;
            case NEUTRAL: 
                break;     
        }
    }
    
    public void keyPressed (int keyCode) 
    {
        direction = KeyCodeToDirection (keyCode);
        
        holdDirection (direction);
                        System.out.println ("p = " + direction);
    }
    
    public void keyReleased (int keyCode) 
    {
        Direction d = KeyCodeToDirection (keyCode);
        releaseDirection (d);
        
        direction = MostRecentDirection ();
    }

    private Direction MostRecentDirection () {
        
        Direction   d = NEUTRAL;
        
        if (left.isHeld)
            d = LEFT;
        if (right.isHeld && right.getPriority() > left.getPriority())
            d = RIGHT;
        
        if (up.isHeld &&  up.getPriority() > right.getPriority())
            d = UP;
        
        if (down.isHeld && down.getPriority() > up.getPriority())
            d = DOWN;
        
        return d;
    }
    
    private void holdDirection (Direction d) 
    {
        switch (d) {
            case LEFT: 
                if (left.isHeld) 
                    return;
                left.hold();
                left.setPriority(priority);
                break;
                
            case RIGHT: 
                if (right.isHeld) 
                    return;
                right.hold();
                right.setPriority(priority);
                break;
                
            case UP: 
                if (up.isHeld) 
                    return;
                up.hold();
                up.setPriority(priority);
                break;
                
            case DOWN:
                if (down.isHeld) 
                    return;
                down.hold();
                down.setPriority(priority);
                break;
        }
        
        priority--;
    }    
    
    private void releaseDirection (Direction d) 
    {
        switch (d) {
            case LEFT: 
                left.release();
                break;
                
            case RIGHT: 
                right.release();
                break;
                
            case UP: 
                up.release();
                break;
                
            case DOWN: 
                down.release();
                break;
        }
        
        priority++;
    }    
    
    private Direction KeyCodeToDirection (int keyCode) {
        
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                return LEFT;
            case KeyEvent.VK_RIGHT:
                return  RIGHT;
            case KeyEvent.VK_UP:
                return  UP;
            case KeyEvent.VK_DOWN:
                return  DOWN;
        }
        return null;
    }
    
    private class DirectionKey {
        
        private final Direction d;
        private boolean isHeld;
        private int p;
        
        private DirectionKey (Direction d) {
            this.d = d;
            isHeld = false;
        }
        
        private boolean isHeld () {
            this.p = priority; 
            return isHeld;
        }
        
        private void hold () {
            isHeld = true;
        }
        
        private void release () {
            isHeld = false;
            p = 0;
        }
        
        private void setPriority (int i) {
            p = i;
        }
        
        private int getPriority () {
            return p;
        }
        
        private Direction getDirection () {
            return d;
        }
    }
}

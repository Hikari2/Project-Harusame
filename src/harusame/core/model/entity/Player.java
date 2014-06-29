package harusame.core.model.entity;

import harusame.core.model.animation.PlayerAnimationLoader;
import harusame.core.util.Direction;
import static harusame.core.util.Direction.DOWN;
import static harusame.core.util.Direction.LEFT;
import static harusame.core.util.Direction.NEUTRAL;
import static harusame.core.util.Direction.RIGHT;
import static harusame.core.util.Direction.UP;
import harusame.core.util.Observer;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Player extends Sprite {
    
    private final PlayerAnimationLoader   pal = new PlayerAnimationLoader("Player/PlayerSprites.png");
    private List<Observer> observers = new ArrayList<Observer>();
    
    private boolean isLeftHeld = false;
    private boolean isRightHeld = false;
    private boolean isUpHeld = false;
    private boolean isDownHeld = false;
    
    private int lastX;
    private int lastY;
   
    private int lock;
    private Direction lastDirection = NEUTRAL;
    private Direction lockedDirection = NEUTRAL;
    private DirectionQueue  dQueue = new DirectionQueue ();
    
    public Player(int x, int y) {
        super(x, y);
        setAnimation (pal.getFacingDown());
    }
    
    @Override
    public void update () {
        
        lockMovement (6, direction);
        lastX = x;
        lastY = y;
        
        if (lock == 0)
            return;
        
        switch (this.direction){
            case LEFT:                 
                this.x-=4;
                setAnimation (pal.getFacingLeft());
                break;
                
            case RIGHT: 
                this.x+=4;
                setAnimation (pal.getFacingRight());           
                break;
                
            case UP: 
                this.y-=4;
                setAnimation (pal.getFacingUp());
                break;
                
            case DOWN: 
                this.y+=4;
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
    
    private void lockMovement (int i, Direction d) { 
        
        if (lock > 0) {
            direction = lockedDirection;;
            lock--;
            System.out.println ("Is locked " + lock + ": " + x + ", " + y);
            if (lock == 0) {
                direction = dQueue.getMostRecentDirection();
                return;
            }
        }
        else if (d == NEUTRAL)
            return;
        
        else {
            lock = i;
            lockedDirection = d;
            System.out.println ("Locked as " + d + ": " + x + ", " + y);
        }
    }
    
    public void revert () {
        x = lastX;
        y = lastY;
    }
    
    public void keyPressed (int keyCode) 
    {
        Direction   d = KeyCodeToDirection (keyCode);
        holdDirection (d);
    }
    
    private void holdDirection (Direction d) 
    {
        
        switch (d) {
            case LEFT: 
                if (isLeftHeld) 
                    return;
                else 
                    isLeftHeld = true;
                break;
                
            case RIGHT: 
                if (isRightHeld) 
                    return;
                else 
                    isRightHeld = true;
                break;
                
            case UP: 
                if (isUpHeld) 
                    return;
                else 
                    isUpHeld = true;
                break;
                
            case DOWN:
                if (isDownHeld) 
                    return;
                else 
                    isDownHeld = true;
                break;
        }
        if (lock <= 0)
        direction = d;
        dQueue.addDirection(d);
    }  
    
    public void keyReleased (int keyCode) 
    {
        Direction d = KeyCodeToDirection (keyCode);
        releaseDirection (d);
        if (lock <= 0)
        direction = dQueue.getMostRecentDirection();
    }
    
    private void releaseDirection (Direction d) 
    {
        switch (d) {
            case LEFT: 
                isLeftHeld = false;
                break;
                
            case RIGHT: 
                isRightHeld = false;
                break;
                
            case UP: 
                isUpHeld = false;
                break;
                
            case DOWN: 
                isDownHeld = false;
                break;
        }
        dQueue.removeDirection(d);
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
    
    private class DirectionQueue {
        
        private Direction[] queue = new Direction [4];
        private Direction [] tmp;
        private Direction d;
        
        
        private void addDirection (Direction d) {
            for (int i=0; i<4; i++){
                if(queue[i] == null){
                    queue[i] = d;
                    return;
                }
            }
        }
        
        private void removeDirection (Direction d) {
            
            for (int i=0; i<4; i++){
                if (queue[i] == d){
                    tmp = queue.clone ();
                    queue = new Direction [4];
                    
                    for (int j=0; j<i; j++)
                        queue[j] = tmp[j];
                    
                    for (int y=i+1; y<4; y++)
                        queue[y-1] = tmp[y];
                }
            }
        }
        
        private Direction getMostRecentDirection () {
            if (queue[0] == null)
                return NEUTRAL;
            for (int i=3; ; i--)
                if (queue[i] != null)
                    return queue[i];
        }
    }
}

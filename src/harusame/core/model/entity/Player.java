package harusame.core.model.entity;

import harusame.core.model.animation.AnimationLoader;
import harusame.core.util.Direction;
import static harusame.core.util.Direction.DOWN;
import static harusame.core.util.Direction.LEFT;
import static harusame.core.util.Direction.NEUTRAL;
import static harusame.core.util.Direction.OTHER;
import static harusame.core.util.Direction.RIGHT;
import static harusame.core.util.Direction.UP;
import harusame.core.util.Observer;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Player extends Sprite {
    
    private List<Observer> observers = new ArrayList<Observer>();
    
    private boolean isLeftHeld = false;
    private boolean isRightHeld = false;
    private boolean isUpHeld = false;
    private boolean isDownHeld = false;
    
    private DirectionQueue  dQueue = new DirectionQueue ();
    
    private int lock;
    private Direction lockedDirection = NEUTRAL;
    
    public Player(int x, int y) {
        super(x, y);
        al = new AnimationLoader ("Player/Player");
        setAnimation (al.getFacingDown());
        MOVE_SPEED = 5;
    }
    
    public void revert () {
       x = lastX;
       y = lastY;
    }
    
    @Override
    public void update () {
        
        if (!isAlive () && !isLocked ())
            notifyDeath ();
        
        lockMovement (9, direction);
        lastX = x;
        lastY = y;
        
        if (!isLocked ())
            return;
        
        switch (this.direction){
            case LEFT:                 
                this.x-=MOVE_SPEED;
                setAnimation (al.getFacingLeft());
                break;
                
            case RIGHT: 
                this.x+=MOVE_SPEED;
                setAnimation (al.getFacingRight());           
                break;
                
            case UP: 
                this.y-=MOVE_SPEED;
                setAnimation (al.getFacingUp());
                break;
                
            case DOWN: 
                this.y+=MOVE_SPEED;
                setAnimation (al.getFacingDown());
                break;
                
            case NEUTRAL: 
                break;     
        }
        
        if (direction != NEUTRAL) 
            updateAnimation ();
        else 
            resetAnimation ();
    }
    
    public void addObserver (Observer   observer) {
        observers.add (observer);
    }
    
    private void notifyDeath () {
        for (int i=0; i<observers.size(); i++)
            observers.get(i).notifyFailure();
    }
    
    private void lockMovement (int i, Direction d) { 
        
        if (isLocked ()) {
            direction = lockedDirection;
            lock--;
            if (!isLocked ()) {
                direction = dQueue.getMostRecentDirection();
                return;
            }
        }
        else if (d == NEUTRAL)
            return;
        
        else {
            lock = i;
            lockedDirection = d;    
        }
    }
    
    private boolean isLocked () {
        if (lock > 0)
            return true;
        else return false;
    }
    
    public void kill () {
        dQueue = new DirectionQueue ();
        setAnimation (al.getDeath());
        lockedDirection = OTHER;
        lock = 24;
        ALIVE = false;
    }
    
    public void keyPressed (int keyCode) 
    {
        if (!isAlive ())
            return;
        
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
        dQueue.addDirection(d);
        
        if (!isLocked ())
            direction = d;
    }  
    
    public void keyReleased (int keyCode) 
    {
        if (!isAlive ())
            return;
        
        Direction d = KeyCodeToDirection (keyCode);
        releaseDirection (d);
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
        
        if (!isLocked ())
            direction = dQueue.getMostRecentDirection();
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

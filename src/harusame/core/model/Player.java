package harusame.core.model;

import harusame.core.util.Direction;
import static harusame.core.util.Direction.DOWN;
import static harusame.core.util.Direction.LEFT;
import static harusame.core.util.Direction.NEUTRAL;
import static harusame.core.util.Direction.OTHER;
import static harusame.core.util.Direction.RIGHT;
import static harusame.core.util.Direction.UP;
import java.awt.event.KeyEvent;

/**
 * 
 */
public class Player extends MovableSprite {
    
    private boolean isLeftHeld = false;
    private boolean isRightHeld = false;
    private boolean isUpHeld = false;
    private boolean isDownHeld = false;
    
    private int ammo = 3;
    
    private DirectionQueue  dQueue = new DirectionQueue ();
    
    private Direction lockedDirection = NEUTRAL;

    public Player(int x, int y) {
        super(x, y);
        life = 3;
        MOVE_SPEED = 5;
    }
    
    public Player(Player p, int x, int y) {
        super(x, y);
        life = p.getLife();
        MOVE_SPEED = 5;
    }
    
    public boolean isLifeLeft (){
        return life > 0;
    }
    
    @Override
    public void revert () {
       x = lastX;
       y = lastY;
       lock = 0;
    }
    
    @Override
    public void update () {

        lockMovement (9, DIRECTION);
        lastX = x;
        lastY = y;
        
        if (!isLocked ())
            return;
        
        if (!isLocked () && !isACTIVE ()) 
            return;
        
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
                
            case NEUTRAL: 
                break;     
        }
        LAST_DIRECTION = DIRECTION;
    }
    
    private void lockMovement (int i, Direction d) { 
        
        if (isLocked ()) {
            DIRECTION = lockedDirection;
            lock--;
            //System.out.println ("DIRECTION : " + DIRECTION + "   Lock : " + lock);
            if (!isLocked ()) {
                DIRECTION = dQueue.getMostRecentDirection();
                            //System.out.println ("DIRECTION : " + DIRECTION + "   Lock : " + lock);
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
        dQueue.addDirection(d);
        
        if (!isLocked ())
            DIRECTION = d;
    }  
    
    public void keyReleased (int keyCode) 
    {
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
            DIRECTION = dQueue.getMostRecentDirection();
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

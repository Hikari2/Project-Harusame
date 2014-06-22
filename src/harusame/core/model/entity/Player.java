package harusame.core.model.entity;

import harusame.core.model.animation.Animation;
import harusame.core.model.animation.PlayerAnimationLoader;
import harusame.core.util.Direction;
import static harusame.core.util.Direction.DOWN;
import static harusame.core.util.Direction.LEFT;
import static harusame.core.util.Direction.NEUTRAL;
import static harusame.core.util.Direction.RIGHT;
import static harusame.core.util.Direction.UP;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 
 */
public class Player extends Sprite{
    
    private final PlayerAnimationLoader   pal = PlayerAnimationLoader.getPAL();
    
    private boolean isLeftHeld = false;
    private boolean isRightHeld = false;
    private boolean isUpHeld = false;
    private boolean isDownHeld = false;
    
    private DirectionQueue  dQueue = new DirectionQueue ();
    
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
        Direction   d = KeyCodeToDirection (keyCode);
        holdDirection (d);
    }
    
    private void holdDirection (Direction d) 
    {
        switch (d) {
            case LEFT: 
                if (isLeftHeld) 
                    return;
                else isLeftHeld = true;
                break;
                
            case RIGHT: 
                if (isRightHeld) 
                    return;
                else isRightHeld = true;
                break;
                
            case UP: 
                if (isUpHeld) 
                    return;
                else isUpHeld = true;
                break;
                
            case DOWN:
                if (isDownHeld) 
                    return;
                else isDownHeld = true;
                break;
        }
        direction = d;
        dQueue.addDirection(d);
    }  

    public void keyReleased (int keyCode) 
    {
        Direction d = KeyCodeToDirection (keyCode);
        releaseDirection (d);
        
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

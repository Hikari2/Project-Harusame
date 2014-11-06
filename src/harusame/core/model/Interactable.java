package harusame.core.model;

import harusame.core.model.map.Tile;
import static harusame.core.util.Direction.DOWN;
import harusame.core.util.ObjectType;


public class Interactable extends MovableSprite implements GravityAffectedObject {
    
    private final ObjectType type;
    
    private boolean falling;
    
    public Interactable(int x, int y, ObjectType t) {
        super(x, y);
        type = t;
        MOVE_SPEED = 15; 
        DIRECTION = DOWN;
    }
    
    // Immovable interactables, DIRT
    public Interactable(ObjectType t, int x, int y) {
        super(x, y);
        type = t;
        MOVE_SPEED = 0;        
    }

    @Override
    public boolean isFalling() {
        return falling;
    }   
   
    @Override
    public void setFalling(boolean falling) {
        this.falling = falling;
    }
    
    public void moveLeft (int playerX){
        lastX = x;
        x = playerX - Tile.WIDTH;
    }
    
    public void moveRight (int playerX){
        lastX = x;
        x = playerX + Tile.WIDTH;
    }

    public ObjectType getType() {
        return type;
    }
}

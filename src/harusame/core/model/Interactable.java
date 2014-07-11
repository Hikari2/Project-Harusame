package harusame.core.model;



/**
 * 
 */
public class Interactable extends MovableSprite {
    
    private boolean movable;
    
    public Interactable(int x, int y, boolean solid) {
        super(x, y);
        movable = solid;
    }
    
    public boolean isSolid()
    {
        return movable;
    }
    
    public void setSolid(boolean solid)
    {
        movable = solid;
    }
}

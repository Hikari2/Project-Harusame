package harusame.core.model.map;

/**
 *
 * @author Hikari
 */
import harusame.core.util.TileType;
import java.awt.Rectangle;

public class Tile {
    
    public static int WIDTH = 45;
        
    private TileType    type;
    private boolean BLOCKED;
    private int x;
    private int y;
    
    
    public Tile(TileType type, boolean isBlocked, int x, int y) {
        this.type = type;
        BLOCKED = isBlocked;  
        this.x = x;
        this.y = y;
    }
    
    public TileType getType () {
        return type;
    }
    
    public boolean isBlocked () {
        return BLOCKED;
    }
    
    public int getX () {
        return x;
    }
    
    public int getY () {
        return y;
    }
    
    public Rectangle getBound () {
        return new Rectangle (x, y, WIDTH, WIDTH);
    }
}

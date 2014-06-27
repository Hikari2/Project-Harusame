package harusame.core.model.map;

/**
 *
 * @author Hikari
 */
import java.awt.Image;

public class Tile {
    
    public static int WIDTH = 10;
        
    private Image image;
    private boolean BLOCKED;
    private int x;
    private int y;
    
    
    public Tile(Image image, boolean type, int x, int y) {
        this.image = image;
        BLOCKED = type;  
        this.x = x;
        this.y = y;
    }
    
    public boolean isBlocked () {
        return BLOCKED;
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public int getX () {
        return x;
    }
    
    public int getY () {
        return y;
    }
}

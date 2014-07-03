package harusame.core.model.map;

/**
 *
 * @author Hikari
 */
import java.awt.Image;
import java.awt.Rectangle;

public class Tile {
    
    public static int WIDTH = 45;
        
    private char symbol;
    private Image image;
    private boolean BLOCKED;
    private int x;
    private int y;
    
    
    public Tile(Image image, boolean type, char newSymbol, int x, int y) {
        this.image = image;
        BLOCKED = type;  
        symbol = newSymbol;
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
    
    public void setSymbol(char newSymbol)
    {
        symbol = newSymbol;
    }
    
    public char getSymbol()
    {
        return symbol;
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

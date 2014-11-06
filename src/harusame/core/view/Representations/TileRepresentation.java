package harusame.core.view.Representations;

import harusame.core.model.map.Tile;
import harusame.core.util.TileType;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Hikari
 */
public class TileRepresentation {
    
    private Tile tile;
    private BufferedImage   image;    
    private TileType type;
    
    public TileRepresentation (Tile t) {
        tile = t;
        type = tile.getType ();        
    }
    
    public void setImage (BufferedImage newImage) {
        image = newImage;
    }
    
    public TileType getType()
    {
        return type;
    }
    
    public void draw (Graphics g) {
        g.drawImage(image, tile.getX(), tile.getY(), Tile.WIDTH, Tile.WIDTH, null);
    }
}

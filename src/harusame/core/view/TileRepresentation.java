package harusame.core.view;

import harusame.core.model.map.Tile;
import harusame.core.util.TileType;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Hikari
 */
public class TileRepresentation {
    
    private Tile tile;
    private BufferedImage   image;
    
    public TileRepresentation (Tile t) {
        tile = t;
        TileType    type = tile.getType ();
        typeToImage (type);
    }
    
    private void typeToImage (TileType type) {
        try {
            image = ImageIO.read(new File ("Resources/Tilesets/D.png"));
        } catch (IOException ex) {
            Logger.getLogger(TileRepresentation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void draw (Graphics g) {
        g.drawImage(image, tile.getX(), tile.getY(), Tile.WIDTH, Tile.WIDTH, null);
    }
}

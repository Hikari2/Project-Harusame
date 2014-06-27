package harusame.core.model.entity;

import harusame.core.model.animation.Animatable;
import harusame.core.model.animation.Animation;
import harusame.core.model.map.Tile;
import static harusame.core.model.map.Tile.WIDTH;
import harusame.core.util.Direction;
import static harusame.core.util.Direction.NEUTRAL;
import java.awt.Graphics;
import java.awt.Rectangle;
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
public class Sprite extends Animatable{
   
   protected Direction  direction = NEUTRAL;

   protected boolean isCollidable = true;
   private BufferedImage    image;
  
   protected int x;
   protected int y;
   protected int dx;
   protected int dy;
   
   public Sprite (int x, int y) {
       this.x = x;
       this.y = y;
       dx = 2 * Tile.WIDTH;
       dy = 2 * Tile.WIDTH;
   }
   
   public void setPosition (int x, int y) {
       this.x = x;
       this.y = y;
   }
    
   public void update () {
       
   }
   
   public Rectangle getBount () {
        return new Rectangle (x, y, dx, dy);
    }
   
   @Override
    public float getX () {
        return x;
    }
    
   @Override
    public float getY () {
        return y;
    }

    @Override
    public float getDX() {
        return dx;
    }

    @Override
    public float getDY() {
        return dy;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
       Animation    a = getAnimation ();
       image = a.getFrame();
       try {
           g.drawImage(ImageIO.read(new File("Resources/Sprites/Player/blank.png")), x, y, (int) dx, (int) dy, null); //BLANKFRAME
       } catch (IOException ex) {
           Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       g.drawImage(image, x, y, (int) dx, (int) dy, null);
    }

    @Override
    public void draw(Graphics g, int x, int y, int dx, int dy) {
    }
}

package harusame.core.model.entity;

import harusame.core.model.animation.Animatable;
import harusame.core.model.animation.Animation;
import harusame.core.model.map.Tile;
import harusame.core.util.Direction;
import static harusame.core.util.Direction.NEUTRAL;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Hikari
 */
public class Sprite extends Animatable{
   
   protected Direction  direction = NEUTRAL;

   protected boolean isCollidable;
   private BufferedImage    image;
  
   protected float x;
   protected float y;
   protected float dx;
   protected float dy;
   
   public Sprite (int width, int height) {
       dx = width * Tile.WIDTH;
       dy = height * Tile.WIDTH;
   }
   
   public void setPosition (float x, float y) {
       this.x = x;
       this.y = y;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getDY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics g, int x, int y) {
       Animation    a = getAnimation ();
       image = a.getFrame();
       g.drawImage(image, x, y, null);
    }

    @Override
    public void draw(Graphics g, int x, int y, int dx, int dy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

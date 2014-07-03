package harusame.core.model.entity;

import harusame.core.model.animation.Animatable;
import harusame.core.model.animation.Animation;
import harusame.core.model.animation.AnimationLoader;
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
   protected    AnimationLoader   al;
   protected boolean isCollidable = true;
   private BufferedImage    image;
  
   protected int x;
   protected int y;
   protected int dx;
   protected int dy;
   protected int lastX;
   protected int lastY;
   
   protected int MOVE_SPEED;
   protected boolean ALIVE = true;
   protected int ANIMATION_FRAME_COUNTER;
   
   public Sprite (int x, int y) {
       this.x = x;
       this.y = y;
       dx = 1 * Tile.WIDTH;
       dy = 1 * Tile.WIDTH;
   }
   
   public void setPosition (int x, int y) {
       this.x = x;
       this.y = y;
   }
    
   public void update () {
       
   }
   
   public void kill () {
       
   }
   
   public void setImage (BufferedImage newImage)
   {
       image = newImage;
   }
   
   protected boolean isAlive () {
       return ALIVE;
   }
   public Rectangle getBound () {
        return new Rectangle (x, y, dx, dy);
    }
   
   @Override
    public int getX () {
        return x;
    }
    
   @Override
    public int getY () {
        return y;
    }

    @Override
    public int getDX() {
        return dx;
    }

    @Override
    public int getDY() {
        return dy;
    }    

    @Override
    public void draw(Graphics g, int x, int y) {
       Animation    a = getAnimation ();
       image = a.getFrame();
      
       g.drawImage(image, x, y, dx, dy, null);
       g.drawString("x: "+x+ " y: "+y, x, y);
    }

    @Override
    public void draw(Graphics g, int x, int y, int dx, int dy) {
    }
}

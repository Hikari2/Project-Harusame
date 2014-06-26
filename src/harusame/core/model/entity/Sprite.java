package harusame.core.model.entity;

import harusame.core.model.animation.Animatable;
import harusame.core.model.animation.Animation;
import harusame.core.model.map.Tile;
import harusame.core.util.Direction;
import static harusame.core.util.Direction.NEUTRAL;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
       g.drawImage(image, x, y, (int) dx, (int) dy, null);
    }

    @Override
    public void draw(Graphics g, int x, int y, int dx, int dy) {
    }
}

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

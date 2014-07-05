package harusame.core.model.entity;

import harusame.core.view.Animation;
import harusame.core.view.AnimationLoader;
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
public class Sprite{
   
   protected Direction  DIRECTION = NEUTRAL;
   protected Direction  LAST_DIRECTION = NEUTRAL;
           
   protected boolean isCollidable = true;
  
   protected int x;
   protected int y;
   protected int dx;
   protected int dy;
   protected int lastX;
   protected int lastY;
   
   protected int MOVE_SPEED;
   
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
    
   public void preUpdate () {
       
   }
   
   public void update () {
       
   }
   
   public Direction  getDirection () {
       return DIRECTION;
   }

   public Rectangle getBound () {
        return new Rectangle (x, y, dx, dy);
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public int getDX() {
        return dx;
    }

    public int getDY() {
        return dy;
    }    
}

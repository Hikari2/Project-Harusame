package harusame.core.model.entity;

import harusame.core.model.map.Tile;
import java.awt.Rectangle;

/**
 *
 * @author Hikari
 */
public class Sprite{
           
   protected boolean isCollidable = true;
  
   protected int x;
   protected int y;
   
   protected int dx;
   protected int dy;
   
   protected int lastX;
   protected int lastY;
   
   public Sprite (int x, int y) {
       this.x = x;
       this.y = y;
       dx = Tile.WIDTH;
       dy = Tile.WIDTH;
   }

   public Rectangle getBound () {
        return new Rectangle (x, y, dx, dy);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDX() {
        return dx;
    }

    public void setDX(int dx) {
        this.dx = dx;
    }

    public int getDY() {
        return dy;
    }

    public void setDY(int dy) {
        this.dy = dy;
    }
}

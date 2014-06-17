package harusame.core.model.entity;

import harusame.core.model.map.Tile;

/**
 *
 * @author Hikari
 */
public abstract class MovableObject {
     
   protected boolean isCollidable;
   
   protected float x;
   protected float y;
   protected float dx;
   protected float dy;
   
   public MovableObject (int width, int height) {
       dx = width * Tile.WIDTH;
       dy = height * Tile.WIDTH;
   }
   
   public void setPosition (float x, float y) {
       this.x = x;
       this.y = y;
   }
}

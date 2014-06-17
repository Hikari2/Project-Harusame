package harusame.core.model.entity;

import harusame.core.model.animation.Animatable;
import harusame.core.model.map.Tile;
import harusame.core.util.Direction;
import static harusame.core.util.Direction.NEUTRAL;

/**
 *
 * @author Hikari
 */
public abstract class Sprite extends Animatable{
   
   protected Direction  direction = NEUTRAL;

   protected boolean isCollidable;
   
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
    
    public void setDirection (Direction d) {
       direction = d;
   }
}

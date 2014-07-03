package harusame.core.model.entity;


import harusame.core.model.animation.AnimationLoader;
import harusame.core.util.Direction;

import static harusame.core.util.Direction.LEFT;

/**
 *
 * @author Hikari
 */
public class Bee extends Enemy {
    
      
    public Bee(int x, int y) {
        super(x, y);
        direction = LEFT;
        al = new AnimationLoader ("Enemies/th_bee");
        setAnimation (al.getFacingLeft());
        MOVE_SPEED = 3;
    }
}

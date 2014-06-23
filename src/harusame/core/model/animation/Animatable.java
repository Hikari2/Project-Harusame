package harusame.core.model.animation;

import java.awt.Graphics;

/**
 * <class>Animatable</class> is a abstract class that should be extended by 
 * a class, that should be animated.
 */
abstract public class Animatable {
    
    private Animation activeAnimation;
    
    public Animation getAnimation () {
        return activeAnimation;
    }
    
    public void setAnimation (Animation a) {
        activeAnimation = a;
    }
    
    public void updateAnimation () {
        activeAnimation.nextFrame();
    }
    
    public void resetAnimation () {
        activeAnimation.reset();
    }
    
    public abstract void draw(Graphics g, int x, int y);
    public abstract void draw (Graphics g, int x, int y, int dx, int dy);
            
    public abstract float getX ();
    public abstract float getY ();
    
    public abstract float getDX ();
    public abstract float getDY ();
}

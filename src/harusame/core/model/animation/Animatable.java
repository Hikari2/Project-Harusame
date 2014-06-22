/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package harusame.core.model.animation;

import java.awt.Graphics;

/**
 *
 * @author Hikari
 */
abstract public class Animatable {
    
    private Animation activeAnimation;
    
    public Animation getAnimation () {
        return activeAnimation;
    }
    
    public void setAnimation (Animation a) {
        activeAnimation = a;
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

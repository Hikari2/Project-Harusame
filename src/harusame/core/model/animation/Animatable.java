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
    
    public abstract void draw(Graphics g, int pixelX, int pixelY);
    
    public abstract int getHeight();
    public abstract int getWidth();
}

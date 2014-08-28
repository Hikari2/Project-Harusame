package harusame.core.view;

import harusame.core.model.MovableSprite;
import harusame.core.util.Direction;
import harusame.core.util.MovableType;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Hikari
 */
public class MovableRepresentation {
    
    private AnimationLoader al;
    private BufferedImage image;    
    private MovableSprite   movable;
    private int x;
    private int y;    
    
    private Animation   ACTIVE_ANIMATION;
    
    public MovableRepresentation (MovableSprite e) {
        movable = e;
        init ();
    }
    
    private void init () {
        try
        {
            if (movable.getType() == MovableType.STONE) {
                image = ImageIO.read(new File("Resources/Sprites/Misc/Stone.png"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }        
        
        x = movable.getX();
        y = movable.getY();
    }
    
    public void update () {
        
        if (movable.isACTIVE() == false) {
            ACTIVE_ANIMATION = al.getDeath();
            ACTIVE_ANIMATION.nextFrame ();
            return;
        }
        x = movable.getX();
        y = movable.getY();  
        
    }
    
    public void draw(Graphics g) {       
       g.drawImage(image, movable.getX(), movable.getY(), movable.getDX(), movable.getDY(), null);
       g.drawString("x: "+movable.getX()+ " y: "+movable.getY(), movable.getX(), movable.getY()-5);
    }
}

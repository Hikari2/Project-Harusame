package harusame.core.view.Representations;

import harusame.core.model.Interactable;
import harusame.core.model.MovableSprite;
import harusame.core.util.Direction;
import harusame.core.util.MovableType;
import harusame.core.util.ObjectType;
import harusame.core.view.Animations.Animation;
import harusame.core.view.Animations.AnimationLoader;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Hikari
 */
public class InteractableRepresentation {
    
    private AnimationLoader al;
    private BufferedImage image;    
    private Interactable   interactable;
    private int x;
    private int y;    
    private boolean ACTIVE;
    
    private Animation   ACTIVE_ANIMATION;
    
    public InteractableRepresentation (Interactable e) {
        interactable = e;
        init ();
    }
    
    private void init () {
        ACTIVE = true;
        
        try
        {
            ObjectType type = interactable.getType();
            
            switch (type){
                case STONE: image = ImageIO.read(new File("Resources/Sprites/Misc/Stone.png"));
                    break;
                case DIRT: image = ImageIO.read(new File("Resources/Tilesets/EARTH/dirt.png"));
                    break;
                case LARVA: image = ImageIO.read(new File("Resources/Sprites/Misc/Larva.png"));
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }        
        
        x = interactable.getX();
        y = interactable.getY();
    }
    
    public void update () {
        
        if (interactable.isACTIVE() == false) {
            ACTIVE = false;
            return;
        }
        x = interactable.getX();
        y = interactable.getY();  
    }
    
    public boolean isACTIVE() {
        return ACTIVE;
    }
    
    public void draw(Graphics g) { 

       g.drawImage(image, x, y, interactable.getDX(), interactable.getDY(), null);
       //g.drawRect(x, y, interactable.getDX(), interactable.getDY());
       //g.drawString("x: "+x+ " y: "+y, interactable.getX(), interactable.getY()-5);
    }
}

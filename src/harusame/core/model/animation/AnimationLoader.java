package harusame.core.model.animation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Hikari
 */
public class AnimationLoader {
    
    Animation   facingRight = new Animation ();
    Animation   facingLeft = new Animation ();
    Animation   facingUp = new Animation ();
    Animation   facingDown = new Animation ();
    
    Animation death = new Animation ();
            
    String path;
    
    public AnimationLoader (String newPath) {
        path = newPath;
        loadMovementAnimation ();
        loadDeathAnimation ();
    }
    
    public  Animation   getFacingRight () {
        return facingRight;
    }
    
    public Animation    getFacingLeft () {
        return facingLeft;
    }
    
    public  Animation   getFacingUp () {
        return facingUp;
    }
    
    public Animation    getFacingDown () {
        return facingDown;
    }
    
    public Animation getDeath () {
        return death;
    }
    
    private void loadMovementAnimation () {
        try {
            BufferedImage   spriteSheet = ImageIO.read(new File("Resources/Sprites/" + path + ".png"));
            
            loadSubImage (facingLeft, spriteSheet, 1);
            loadSubImage (facingRight, spriteSheet, 2);
            loadSubImage (facingUp, spriteSheet, 3);
            loadSubImage (facingDown, spriteSheet, 0);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadSubImage (Animation    a, BufferedImage  sheet, int row) {
        for (int i=0; i<3; i++){
            BufferedImage   image = sheet.getSubimage(32*i, 32*row, 32, 32);
            a.addFrame(image);
            a.addFrame(image);
            a.addFrame(image);
            a.addFrame(image);
            a.addFrame(image);
        }
    }
    
    
    private void loadDeathAnimation () {
        try {
            BufferedImage   spriteSheet = ImageIO.read(new File("Resources/Sprites/" + path + "_Death.png"));
        
            for (int i=0; i<3; i++){
                BufferedImage   image = spriteSheet.getSubimage(i*30, 0 , 30, 30);
                death.addFrame(image);
                death.addFrame(image);
                death.addFrame(image);
                death.addFrame(image);
                death.addFrame(image);
                death.addFrame(image);
                death.addFrame(image);
                death.addFrame(image);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
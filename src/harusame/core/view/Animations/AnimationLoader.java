package harusame.core.view.Animations;

import harusame.core.view.Animations.Animation;
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
    
    
    // When no movementanimation is needed
    public AnimationLoader (String newPath, boolean check) {
        path = newPath;
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
            BufferedImage   image = sheet.getSubimage((sheet.getWidth()/3)*i, (sheet.getHeight()/4)*row, (sheet.getWidth()/3), (sheet.getHeight()/4));
            a.addFrame(image);
            a.addFrame(image);
            a.addFrame(image);
        }
    }
    
    
    private void loadDeathAnimation () {
        try {
            BufferedImage   sheet = ImageIO.read(new File("Resources/Sprites/" + path + "_Death.png"));
        
            for (int i=0; i<5; i++){
            BufferedImage   image = sheet.getSubimage((sheet.getWidth()/5)*i, 0, (sheet.getWidth()/5), (sheet.getHeight()/2));
                death.addFrame(image);
                death.addFrame(image);
            }
            
            for (int i=0; i<5; i++){
            BufferedImage   image = sheet.getSubimage((sheet.getWidth()/5)*i, (sheet.getHeight()/2), (sheet.getWidth()/5), (sheet.getHeight()/2));
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

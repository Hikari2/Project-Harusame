package harusame.core.model.animation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Hikari
 */
public class PlayerAnimationLoader {
    
    private static PlayerAnimationLoader    pal = new PlayerAnimationLoader ();
    
    Animation   facingRight = new Animation ();
    Animation   facingLeft = new Animation ();
    Animation   facingUp = new Animation ();
    Animation   facingDown = new Animation ();
    
    private PlayerAnimationLoader () {
        loadMovementAnimation ();
    }
    
    public static PlayerAnimationLoader getPAL () {
        return pal;
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
    
    private void loadMovementAnimation () {
        try {
            BufferedImage   spriteSheet = ImageIO.read(new File("Resources/Sprites/Player/PlayerSprites.png"));
            
            loadSubImage (facingLeft, spriteSheet, 1);
            loadSubImage (facingRight, spriteSheet, 2);
            loadSubImage (facingUp, spriteSheet, 3);
            loadSubImage (facingDown, spriteSheet, 0);
            
        } catch (IOException ex) {
            Logger.getLogger(PlayerAnimationLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadSubImage (Animation    a, BufferedImage  sheet, int row) {
        for (int i=0; i<3; i++){
            BufferedImage   image = sheet.getSubimage(32*i, 32*row, 32, 32);
            a.addFrame(image);
        }
    }
}

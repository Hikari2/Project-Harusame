package harusame.core.model.animation;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Hikari
 */
public class PlayerAnimationLoader {
    
    private static PlayerAnimationLoader    pal;
    
    Animation   facingRight = new Animation ();
    Animation   facingLeft = new Animation ();
    
    private PlayerAnimationLoader () throws IOException {
        
        facingRight = new Animation ();
        facingRight.addFrame(ImageIO.read(new File("Resources/Sprites/Player/RightStill.png")));
        facingLeft = new Animation ();
        facingLeft.addFrame(ImageIO.read(new File("Resources/Sprites/Player/LeftStill.png")));
    }
    
    public static PlayerAnimationLoader getPAL () {
        return pal;
    }
    
    public Animation    getFacingRight () {
        return facingRight;
    }
    
    public Animation    getFacingLeft () {
        return facingLeft;
    }
}

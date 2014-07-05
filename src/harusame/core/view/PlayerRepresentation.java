package harusame.core.view;

import harusame.core.model.Player;
import harusame.core.util.Direction;
import static harusame.core.util.Direction.NEUTRAL;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Hikari
 */
public class PlayerRepresentation {
    
    private AnimationLoader al = new AnimationLoader ("Player/Player");
    
    private Player player;
    private Direction   LAST_DIRECTION;
    
    private Animation   ACTIVE_ANIMATION;
    
    public PlayerRepresentation(Player p) {
        player = p;
        ACTIVE_ANIMATION = al.getFacingDown();
    }
    
    public void update () {
        Direction   DIRECTION = player.getDirection();
        
        if (DIRECTION != LAST_DIRECTION) {
            switch (DIRECTION){
                case LEFT:                 
                    ACTIVE_ANIMATION = al.getFacingLeft();
                    break;

                case RIGHT: 
                    ACTIVE_ANIMATION = al.getFacingRight();   
                    break;

                case UP: 
                    ACTIVE_ANIMATION = al.getFacingUp();
                    break;

                case DOWN: 
                    ACTIVE_ANIMATION = al.getFacingDown();
                    break;

                case NEUTRAL: 
                    break;     
            }
        }
        else if (DIRECTION != NEUTRAL)   
            ACTIVE_ANIMATION.nextFrame ();
        else 
            ACTIVE_ANIMATION.reset();

        LAST_DIRECTION = DIRECTION;
    }
    
    public void draw(Graphics g) {
       BufferedImage    image = ACTIVE_ANIMATION.getFrame();
       
       g.drawImage(image, player.getX(), player.getY(), player.getDX(), player.getDY(), null);
       g.drawString("x: "+player.getX()+ " y: "+player.getY(), player.getDX(), player.getDY());
    }
}

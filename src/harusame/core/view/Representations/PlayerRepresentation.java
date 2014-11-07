package harusame.core.view.Representations;

import harusame.core.view.Animations.AnimationLoader;
import harusame.core.view.Animations.Animation;
import harusame.core.model.Player;
import harusame.core.model.map.Tile;
import harusame.core.util.Direction;
import static harusame.core.util.Direction.NEUTRAL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Hikari
 */
public class PlayerRepresentation {
    
    private AnimationLoader al = new AnimationLoader ("Player/Player");
    
    private Player player;
    private int x;
    private int y;

    private Direction   LAST_DIRECTION;
    
    private Animation   ACTIVE_ANIMATION;
    
    public PlayerRepresentation(Player p) {
        player = p;
        ACTIVE_ANIMATION = al.getFacingDown();
        x = player.getX();
        y = player.getY();
    }
    
    public void update () {
        
        if (player.isACTIVE() == false) {
            ACTIVE_ANIMATION = al.getDeath();
            ACTIVE_ANIMATION.nextFrame ();
            return;
        }
        
        Direction   DIRECTION = player.getDIRECTION();
        
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
        x = player.getX();
        y = player.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getLife (){
        return player.getLife();
    }
    
    public void draw(Graphics g) {
       BufferedImage    image = ACTIVE_ANIMATION.getFrame();

       g.drawImage(image, x, y, player.getDX(), player.getDY(), null);
       
       if (x % Tile.WIDTH != 0 || y % Tile.WIDTH != 0)
           g.setColor(Color.red);
       
       g.drawString("x: "+x+ " y: "+y, player.getX(), player.getY()-5);        
    }
}

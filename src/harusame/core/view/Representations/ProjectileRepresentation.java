package harusame.core.view.Representations;

import harusame.core.model.Projectile;
import harusame.core.util.Direction;
import harusame.core.util.ProjectileType;
import harusame.core.view.Animations.Animation;
import harusame.core.view.Animations.AnimationLoader;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * This class handles the visual representation of a enemy
 */
public class ProjectileRepresentation {
    
    private AnimationLoader animationLoader;
    private Projectile projectile;
    private int x;
    private int y;
    private Direction   DIRECTION;
    private Direction   LAST_DIRECTION;
    private boolean ACTIVE;
    private Animation   ACTIVE_ANIMATION;
    
    private int counter = 20;
   
    /**
     * Constructor for the class ProjectileRepresentation
     * @param p The actual entity this class represent
     */
    public ProjectileRepresentation (Projectile p) {
        projectile = p;
        init ();
    }
    
    private void init () {
        ACTIVE = true;
        
        if (projectile.getType() == ProjectileType.PLAYER) {
            animationLoader = new AnimationLoader ("/Projectile/player_projectile.png", 0);
        }
        
        ACTIVE_ANIMATION = animationLoader.getRolling();
        x = projectile.getX();
        y = projectile.getY();
        
      
    }
    
    /**
     * Change to the next frame corresponding to the current status of the sprite
     */
    public void update () {
         if (projectile.isACTIVE() == false) {            
            ACTIVE = false;
            return;
        }        
      
        ACTIVE_ANIMATION.nextFrame();
        
        x = projectile.getX();
        y = projectile.getY();             
    }
    
    public boolean isACTIVE() {
        return ACTIVE;
    }
    
    /**
     * Draw the current active animation frame
     * @param g 
     */
    public void draw(Graphics g) {
       BufferedImage    image = ACTIVE_ANIMATION.getFrame();
       g.drawImage(image, x, y, projectile.getDX(), projectile.getDY(), null);      
    }
}

package harusame.core.view;

import harusame.core.model.Enemy;
import harusame.core.util.Direction;
import harusame.core.util.EnemyType;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * This class handles the visual representation of a enemy
 */
public class EnemyRepresentation {
    
    private AnimationLoader animationLoader;
    private Enemy   enemy;
    private int x;
    private int y;
    private Direction   DIRECTION;
    private Direction   LAST_DIRECTION;
    private boolean ACTIVE;
    private Animation   ACTIVE_ANIMATION;
    
    private int counter = 20;
   
    /**
     * Constructor for the class EnemyRepresentation
     * @param e The actual entity this class represent
     */
    public EnemyRepresentation (Enemy e) {
        enemy = e;
        init ();
    }
    
    private void init () {
        ACTIVE = true;
        
        if (enemy.getEnemyType() == EnemyType.BEE) {
            animationLoader = new AnimationLoader ("Enemy/th_bee");
        }
        
        if (enemy.getEnemyType() == EnemyType.BEE_LARVA) {
            animationLoader = new AnimationLoader ("Enemy/th_bee_larva");
        }
        
        ACTIVE_ANIMATION = animationLoader.getFacingLeft();
        x = enemy.getX();
        y = enemy.getY();
    }
    
    /**
     * Change to the next frame corresponding to the current status of the sprite
     */
    public void update () {
        
        if (enemy.isACTIVE() == false) {
            counter--;
            ACTIVE_ANIMATION = animationLoader.getDeath();
            ACTIVE_ANIMATION.nextFrame ();
            if (counter == 0)
                ACTIVE = false;
            return;
        }
        
        DIRECTION = enemy.getDIRECTION();
        LAST_DIRECTION = enemy.getLAST_DIRECTION();
        
        if (LAST_DIRECTION != DIRECTION)
            ACTIVE_ANIMATION.reset();
        
        switch (DIRECTION){
            case LEFT:                 
                ACTIVE_ANIMATION = animationLoader.getFacingLeft();
                break;
                
            case RIGHT: 
                ACTIVE_ANIMATION = animationLoader.getFacingRight();
                break;
                
            case UP: 
                ACTIVE_ANIMATION = animationLoader.getFacingUp();
                break;
                
            case DOWN: 
                ACTIVE_ANIMATION = animationLoader.getFacingDown();
                break;
        }
        ACTIVE_ANIMATION.nextFrame();
        
        x = enemy.getX();
        y = enemy.getY();
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
       g.drawImage(image, x, y, enemy.getDX(), enemy.getDY(), null);
       g.drawString("x: "+x+ " y: "+y, enemy.getX(), enemy.getY()-5);
    }
}

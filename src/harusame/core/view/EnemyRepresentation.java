package harusame.core.view;

import harusame.core.model.Enemy;
import harusame.core.util.Direction;
import harusame.core.util.EnemyType;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Hikari
 */
public class EnemyRepresentation {
    
    private AnimationLoader al;
    private Enemy   enemy;
    private int x;
    private int y;
    private Direction   DIRECTION;
    private Direction   LAST_DIRECTION;
    
    private Animation   ACTIVE_ANIMATION;
    
    public EnemyRepresentation (Enemy e) {
        enemy = e;
        init ();
    }
    
    private void init () {

        if (enemy.getType() == EnemyType.BEE) {
            al = new AnimationLoader ("Enemy/th_bee");
        }
        
        ACTIVE_ANIMATION = al.getFacingLeft();
        x = enemy.getX();
        y = enemy.getY();
    }
    
    public void update () {
        
        DIRECTION = enemy.getDIRECTION();
        LAST_DIRECTION = enemy.getLAST_DIRECTION();
        
        if (LAST_DIRECTION != DIRECTION)
            ACTIVE_ANIMATION.reset();
        
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
        }
        ACTIVE_ANIMATION.nextFrame();
        
        x = enemy.getX();
        y = enemy.getY();
    }
    
    public void draw(Graphics g) {
       BufferedImage    image = ACTIVE_ANIMATION.getFrame();
       g.drawImage(image, x, y, enemy.getDX(), enemy.getDY(), null);
       g.drawString("x: "+x+ " y: "+y, enemy.getDX(), enemy.getDY());
    }
}

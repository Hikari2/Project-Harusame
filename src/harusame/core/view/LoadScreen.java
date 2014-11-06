package harusame.core.view;

import harusame.core.util.Level;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Hikari
 */
public class LoadScreen implements Menu {
    
    private BufferedImage backGround;
    private Color titleColor;
    private Font titleFont;
    private Font font;
    
    private String nextLevel;
    private int playerLife;
    
    public LoadScreen (Level level, int life) {
        
        nextLevel = level.toString();
        playerLife = life;
        
        try {    
            backGround = ImageIO.read(new File("Resources/Menu/Loadscreen.jpg"));
           
            titleColor = new Color(128, 0, 0);
            titleFont = new Font(
                            "Century Gothic",
                            Font.PLAIN,
                            90);

            font = new Font("Arial", Font.PLAIN, 32);

        }
        catch(Exception e) {
                e.printStackTrace();
        }
    }
    
    public void draw (Graphics g) {
        g.drawImage(backGround, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);

        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString(nextLevel + "  Life: " + playerLife , GamePanel.WIDTH/4, GamePanel.HEIGHT/6);
    }
}

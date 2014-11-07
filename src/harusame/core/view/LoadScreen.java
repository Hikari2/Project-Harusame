package harusame.core.view;

import harusame.core.util.Level;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    
    private final String nextLevel;
    private final int playerLife;
    
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
        catch(IOException e) {
        }
    }
    
    @Override
    public void draw (Graphics g) {
        g.drawImage(backGround, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);

        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString(nextLevel + "  Life: " + playerLife , GamePanel.WIDTH/4, GamePanel.HEIGHT/6);
    }
}

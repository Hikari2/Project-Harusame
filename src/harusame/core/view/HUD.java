package harusame.core.view;

import harusame.core.util.Level;
import harusame.core.view.Representations.PlayerRepresentation;
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
public class HUD {
    
    private BufferedImage backGround;
    private Color titleColor;
    private Font titleFont;
    private Font font;
    
    private PlayerRepresentation player;
    
    public HUD (PlayerRepresentation player) {
        
        this.player = player;
        
      try {    
            backGround = ImageIO.read(new File("Resources/HUD/hud.gif"));
           
            titleColor = new Color(128, 0, 0);
            titleFont = new Font(
                            "Century Gothic",
                            Font.PLAIN,
                            45);

            font = new Font("Arial", Font.PLAIN, 32);

        }
        catch(IOException e) {
        }
    }
    
  
    public void draw (Graphics g, int x, int y) {
        g.drawImage(backGround, x, y, GamePanel.WIDTH/8, GamePanel.HEIGHT/8, null);

        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString(" "+player.getLife(), x+50, y+40);
    }
}

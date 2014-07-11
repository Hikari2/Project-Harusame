package harusame.core.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Admin
 */
public class GameOverScreen implements Menu {
    private BufferedImage backGround;

    private int currentChoice = 0;
    
    private Color titleColor;
    private Font titleFont;
	
    private Font font;
    
    private String[] options = {
            "Retry",
            "Menu",
            "Quit"
    };

    public GameOverScreen() {
        try {    
            backGround = ImageIO.read(new File("Resources/Menu/GameOver_Background.png"));

            titleColor = new Color(128, 0, 0);
            titleFont = new Font(
                            "Century Gothic",
                            Font.PLAIN,
                            46);

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
        g.drawString("It appear you suck", GamePanel.WIDTH/4, GamePanel.HEIGHT/6);

        // draw menu options
        g.setFont(font);
        for(int i = 1; i < options.length+1; i++) {
                if(i == currentChoice) {
                        g.setColor(Color.BLACK);
                }
                else {
                        g.setColor(Color.WHITE);
                }
                g.drawString(options[i-1], GamePanel.WIDTH/4, GamePanel.HEIGHT/4 + 70 * i);
        }
    }
}

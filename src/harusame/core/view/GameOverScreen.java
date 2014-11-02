package harusame.core.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Admin
 */
public class GameOverScreen implements Menu {
    private BufferedImage backGround;

    private int currentChoice = 1;
    
    private Color titleColor;
    private Font titleFont;
	
    private Font font;
    
    private String[] options = {
            "Restart",
            "Main Menu",
            "Quit"
    };

    public GameOverScreen() {
        try {    
            backGround = ImageIO.read(new File("Resources/Menu/GameOver_Background.png"));
           
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
        g.drawString("GAMERU OVERU", GamePanel.WIDTH/4, GamePanel.HEIGHT/6);

        // draw menu options
        g.setFont(font);
        for(int i = 1; i < options.length+1; i++) {
                g.setColor(Color.WHITE);
                int stringLen = (int)g.getFontMetrics().getStringBounds(options[i - 1], g).getWidth();  
                int xPos = GamePanel.WIDTH/2 - stringLen/2;  
                if(i == currentChoice) {                        
                        g.drawRect(GamePanel.WIDTH/2 - 103, GamePanel.HEIGHT/4 + 70 * i - 50, 205, 70);
                        g.setColor(Color.YELLOW);
                }
              
                g.drawString(options[i-1], xPos, GamePanel.HEIGHT/4 + 70 * i);                
        }
    }
    
    public void KeyPressed(int KeyCode)
    {
        if(KeyCode == KeyEvent.VK_UP)
        {
            currentChoice--;
            if(currentChoice == 0)
                currentChoice = options.length;
        }
        else if(KeyCode == KeyEvent.VK_DOWN)
        {
            currentChoice++;
            if(currentChoice == options.length+1)
                currentChoice = 1;
        }
        else if(KeyCode == KeyEvent.VK_ENTER)
        {
            // Retry
            if(currentChoice == 1)
            {
                
            }
            // Main Menu
            else if(currentChoice == 2)
            {
                
                
            }
            // Quit
            else
                System.exit(0);
        }
    }
    
    
}

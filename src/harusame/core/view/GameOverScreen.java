package harusame.core.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Admin
 */
public class GameOverScreen implements Menu {
    private BackGround bg;

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
            
            bg = new BackGround("Resources/Menu/GameOver_Background.png", 1);
            bg.setVector(-0.1, 0);

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
        bg.draw(g);

        // draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Fuck you", GamePanel.WIDTH/4, GamePanel.HEIGHT/6);

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

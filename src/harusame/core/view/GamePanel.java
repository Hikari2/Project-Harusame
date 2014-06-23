package harusame.core.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JPanel;
import harusame.core.controller.Controller;
import harusame.core.model.MapLoader;

@SuppressWarnings("serial")
public class GamePanel extends JPanel 
	implements Runnable, KeyListener{
	
        // controller
        private Controller ctrl;
        
	// dimensions
	public static final int WIDTH = 300;
	public static final int HEIGHT = 200;
	public static final int SCALE = 4;
	
	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// image
	private BufferedImage image;
	private Graphics2D g;
        
        // Map Loader
        private MapLoader map = new MapLoader(GamePanel.WIDTH, GamePanel.HEIGHT);
        char[] level;
	
	public GamePanel(Controller ctrl) {
		super();
                this.ctrl = ctrl;
		setPreferredSize(
			new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
                level = map.readMap("Level1.txt");                
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init() {
            image = new BufferedImage(WIDTH, HEIGHT, 
                    BufferedImage.TYPE_INT_RGB);
            
            g = (Graphics2D) image.getGraphics();
            
            running = true;
	}
	
	public void run() {
            init();
		
            long start;
            long elapsed;
            long wait;
		
            // game loop
            while(running) {
                start = System.nanoTime();
			
		update();
		draw();
		drawToScreen();
			
		elapsed = System.nanoTime() - start;
			
		wait = targetTime - elapsed / 1000000;
		if(wait < 0) wait = 5;
			
		try {
                    Thread.sleep(wait);
		}
		catch(Exception e) {
                    e.printStackTrace();
		}	
            }	
        }
	
	private void update() {
		ctrl.update();
	}
	private void draw() {
		ctrl.draw(g);
                
                int drawX = 0;
                int drawY = 0;
                for(int i = 0; i < level.length; i++)
                {
                    if(level[i] == '#')
                    {
                        g.setColor(Color.GREEN);                      
                        g.fillRect(drawX, drawY, 10, 10);
                    }
                    drawX += 10;
                    if(drawX == WIDTH)
                    {
                        drawX = 0;
                        drawY += 10;
                    }
                }
                
	}
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0,
				WIDTH * SCALE, HEIGHT * SCALE, null);
                
		g2.dispose();
                init ();
	}
	
	public void keyTyped(KeyEvent key) {}
        
	public void keyPressed(KeyEvent key) {
		ctrl.keyPressed(key.getKeyCode());
	}
        
	public void keyReleased(KeyEvent key) {
		ctrl.keyReleased(key.getKeyCode());
	}
}

















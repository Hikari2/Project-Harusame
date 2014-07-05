package harusame.core.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JPanel;
import harusame.core.controller.Controller;
import harusame.core.util.Observer;

/**
 * Class containing the game loop
 */

@SuppressWarnings("serial")
public class GamePanel extends JPanel 
	implements Runnable, KeyListener {
	
        // controller
        private Controller ctrl;
        private RepresentationManager   repManager;
        
	// dimensions
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 600;
	public static final int SCALE = 1;
	
	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// image
	private BufferedImage image;
	private Graphics2D g;   
	
	public GamePanel(Controller ctrl) {
		super();
                repManager = new RepresentationManager ();
                this.ctrl = ctrl;
                ctrl.addObserver(repManager);
                ctrl.startGame();
		setPreferredSize(
			new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);               
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
                repManager.update ();
	}
	private void draw() {
              repManager.draw(g);
                
	}
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0,
				WIDTH * SCALE, HEIGHT * SCALE, null);
                
		g2.dispose();
                init ();
	}
	
        @Override
	public void keyTyped(KeyEvent key) {}
        
        @Override
	public void keyPressed(KeyEvent key) {          
            ctrl.keyPressed(key.getKeyCode());
	}
        
        @Override
	public void keyReleased(KeyEvent key) {
            ctrl.keyReleased(key.getKeyCode());
	}
}

















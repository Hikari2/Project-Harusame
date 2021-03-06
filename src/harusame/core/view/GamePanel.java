package harusame.core.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JPanel;
import harusame.core.controller.Controller;
import harusame.core.util.Observer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 800;
	public static final int SCALE = 1;
	
	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 40;
	private long targetTime = 1000 / FPS;
	
	// image
	private BufferedImage image;
	private Graphics2D g;   
	
	public GamePanel(Controller ctrl) {
		super();
                repManager = new RepresentationManager (ctrl);
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
			
		try {
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
		catch(IOException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);	
	}	
            }	
        }
	
	private void update() throws IOException {
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
            if(repManager.getMenuState() != 1)
                 ctrl.keyPressed(key.getKeyCode());            
            else if(repManager.getMenuState() == 1)
                repManager.getGameOverMenu().KeyPressed(key.getKeyCode());
	}
        
        @Override
	public void keyReleased(KeyEvent key) {
            if(repManager.getMenuState() != 1)
                ctrl.keyReleased(key.getKeyCode());
	}
}

















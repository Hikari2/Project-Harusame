package harusame.core.startUp;

import harusame.core.controller.Controller;
import harusame.core.view.GamePanel;
import javax.swing.JFrame;

public class Main {
    
    public static void main(String[] args) {
        
        Controller  ctrl = new Controller ();
        JFrame window = new JFrame("Harusame");
	window.setContentPane(new GamePanel(ctrl));
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setResizable(false);
	window.pack();
        window.setVisible(true);
        
    }
}

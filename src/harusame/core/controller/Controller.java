package harusame.core.controller;

import harusame.core.model.EntityManager;
import harusame.core.model.map.MapLoader;
import harusame.core.util.Observer;
import java.io.IOException;

/**
 *
 * @author Hikari
 */
public class Controller {
    
    EntityManager   em;
    int counter = 0;
    
    
    public Controller () {
        em = new EntityManager ();
    }
    
    public void startGame () {
        em.startGame();
    }

    public void keyPressed(int keyCode) {
        em.getPlayer().keyPressed(keyCode);        
    }

    public void keyReleased(int keyCode) {
        em.getPlayer().keyReleased(keyCode);
    }

    public void update() throws IOException {
        if (counter == 0)
            em.update();
        else counter--;
    }
    
    public void addObserver (Observer o) {
        em.addObserver(o);
    }
    
    public void pause (int c){
        counter = c;
    }
}

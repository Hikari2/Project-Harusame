package harusame.core.controller;

import harusame.core.model.EntityManager;
import harusame.core.model.map.MapLoader;
import harusame.core.util.Observer;

/**
 *
 * @author Hikari
 */
public class Controller {
    
    EntityManager   em;
    
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

    public void update() {
        em.update();
    }
    
    public void addObserver (Observer o) {
        em.addObserver(o);
    }
}

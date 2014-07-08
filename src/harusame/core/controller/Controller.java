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
    
    private final MapLoader ml; 
    private int tempDeathCounterSkit;
    
    private String CURRENT_LEVEL;
    private String LAST_CHECKPOINT;
    
    public Controller () {
        em = new EntityManager ();
        ml = new MapLoader(em); 
    }
    
    public void startGame () {
        tempDeathCounterSkit = 0;
        loadMap("Level_01");
        CURRENT_LEVEL = "Level_01";
    }
    public void loadMap (String level) {
        ml.loadMap(level);
    }
    
    public void reloadLevel () {
        loadMap (CURRENT_LEVEL);
    }
    
    public void keyPressed(int keyCode) {
        em.getPlayer().keyPressed(keyCode);
    }

    public void keyReleased(int keyCode) {
        em.getPlayer().keyReleased(keyCode);
    }

    public void update() {
        em.update();
        if(em.getPlayer().isACTIVE() == false)
            tempDeathCounterSkit++;
        if(tempDeathCounterSkit == 40)
            startGame();
    }
    
    public void addObserver (Observer o) {
        em.addObserver(o);
    }
}

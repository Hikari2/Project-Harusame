package harusame.core.controller;

import harusame.core.model.entity.Player;
import harusame.core.model.entity.Sprite;
import harusame.core.model.map.MapLoader;
import harusame.core.model.map.TileMap;
import harusame.core.view.GamePanel;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Hikari
 */
public class Controller {
    
    private final MapLoader ml = new MapLoader(GamePanel.WIDTH, GamePanel.HEIGHT); 
    private final TileMap map;
    
    Player  player;
    ArrayList<Sprite>   sprites = new ArrayList<Sprite> ();
    
    public Controller () {
        player = new Player (100, 150);
        map = ml.loadMap("Level1");
    }
    
    public void keyPressed(int keyCode) {
        player.keyPressed(keyCode);
    }

    public void keyReleased(int keyCode) {
        player.keyReleased(keyCode);
    }

    public void update() {
        player.update();
    }  

    public void draw(Graphics2D g) {
        player.draw(g, (int)player.getX(), (int)player.getY());
        
        for (int i=0; i<sprites.size(); i++){
            Sprite  s = sprites.get(i);
            s.draw(g, (int)s.getX(), (int)s.getY());
        }
        
        map.draw (g, (int)player.getX(), (int)player.getY());
    }
}

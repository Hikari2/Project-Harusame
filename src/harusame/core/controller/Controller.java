package harusame.core.controller;

import harusame.core.model.collision.CollisionHandler;
import harusame.core.model.entity.Bee;
import harusame.core.model.entity.Player;
import harusame.core.model.entity.Sprite;
import harusame.core.model.map.MapLoader;
import harusame.core.model.map.Tile;
import harusame.core.model.map.TileMap;
import harusame.core.util.Observer;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Hikari
 */
public class Controller {
    
    private final MapLoader ml = new MapLoader(); 
    private final CollisionHandler ch = CollisionHandler.getCollisionHandler();
    private final TileMap map;
    
    int w;
    int h;
    
    Player  player;
    ArrayList<Sprite>   sprites = new ArrayList<Sprite> ();
    ArrayList<Bee> bees;
    
    public Controller () {
        player = new Player (24, 24);
        map = ml.loadMap("Level1");
        bees = loadEnemies("Level1");
    }
    
    public void addObserver (Observer observer) {
        //player.addObserver (observer);
    }
    
    public void keyPressed(int keyCode) {
        player.keyPressed(keyCode);
    }

    public void keyReleased(int keyCode) {
        player.keyReleased(keyCode);
    }

    public void update() {
        player.update();
        ch.CheckTileCollision(player, map);
        for(int i = 0; i < bees.size(); i++)
        {
            bees.get(i).update();
            ch.CheckEnemyCollision(bees.get(i), map);
        }
        
        
    }
    
    public ArrayList<Bee> loadEnemies(String level)
    {
        ArrayList<Bee> returnList = new ArrayList<Bee> ();
        BufferedReader br = null;
        
        try {        
            int x;
            int y;
            
            br = new BufferedReader(new FileReader("Resources/Maps/" +level +".txt"));
            
            w = Integer.parseInt(br.readLine());
            h = Integer.parseInt(br.readLine());            
          
            String line;
            
            for (int i=0; i<h; i++){                
                line = br.readLine();
                for (int j=0; j<line.length(); j++) {
                    if(line.charAt(j) == 'B')
                        returnList.add(new Bee(j*24,i*24));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }          

        return returnList;
    }
    
    public void draw(Graphics2D g) {  
        map.draw (g, (int)player.getX(), (int)player.getY());
        player.draw(g, (int)player.getX(), (int)player.getY());
        
        for(int i = 0; i < bees.size(); i++)
            bees.get(i).draw(g, bees.get(i).getX(), bees.get(i).getY());
    }
}

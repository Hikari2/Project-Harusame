package harusame.core.view;

import harusame.core.view.Representations.PlayerRepresentation;
import harusame.core.controller.Controller;
import harusame.core.view.Representations.TileRepresentation;
import harusame.core.view.Representations.InteractableRepresentation;
import harusame.core.view.Representations.EnemyRepresentation;
import harusame.core.model.Player;
import harusame.core.model.Enemy;
import harusame.core.model.Interactable;
import harusame.core.model.Projectile;
import harusame.core.model.map.Tile;
import harusame.core.model.map.TileMap;
import harusame.core.util.Level;
import harusame.core.util.Observer;
import harusame.core.view.Representations.ProjectileRepresentation;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * This class holds and handles all visual representations of entities in the 
 * model.
 */
public class RepresentationManager implements Observer{
    
    private boolean GAME_IS_ACTIVE;
    
    private int CAMERA_SIZE_X = GamePanel.WIDTH;
    private int CAMERA_SIZE_Y = GamePanel.HEIGHT;
    
    private int offsetMaxX;
    private int offsetMaxY;
    private int offsetMinX;
    private int offsetMinY;
    
    private int camX;
    private int camY;
    
    private int MENUSTATE = -1;
    private int lock = 0;
    
    private GameOverScreen  GAME_OVER_SCREEN;
    private LoadScreen LOAD_SCREEN;
    private HUD HUD;
    
    private Controller controller;
    
    private PlayerRepresentation    player;

    private ArrayList<EnemyRepresentation>  enemies = new ArrayList ();
    private ArrayList<InteractableRepresentation> interactables = new ArrayList ();
    private ArrayList<ProjectileRepresentation> projectiles = new ArrayList ();
    
    private TileRepresentation[][]  tiles;
    private TileImageLoader tl;
    
    public RepresentationManager(Controller ctrl)
    {
        controller = ctrl;
    }
    
    public void update () {
        
        if (!GAME_IS_ACTIVE)
            return;
        
        if (lock != 0) {
            lock--;
            if (lock == 0)
                unpause ();
        }
        
        
        player.update ();
        
        for (int i=0; i<enemies.size(); i++) {
            if (!enemies.get(i).isACTIVE()) {
                enemies.remove(i);
                continue;
            }
            enemies.get(i).update();
        }
        
        for (int i=0; i<projectiles.size(); i++) {
            if (!projectiles.get(i).isACTIVE()) {
                projectiles.remove(i);
                continue;
            }
            projectiles.get(i).update();
        }
        
        for (int j=0; j<interactables.size(); j++)
        {
            if (!interactables.get(j).isACTIVE()) {
                interactables.remove(j);
                continue;
            }
            interactables.get(j).update();
        }
    }
    
    @Override
    public void notifyNewPlayer(Player p) {
        player = new PlayerRepresentation (p);
    }

    @Override
    public void notifyNewEnemy(Enemy e) {
        enemies.add(new EnemyRepresentation (e));
    }
    
    @Override
    public void notifyNewProjectile(Projectile p) {
        projectiles.add(new ProjectileRepresentation(p));
    }
    
    @Override
    public void notifyNewInteractable (Interactable i) {
        interactables.add(new InteractableRepresentation (i));
    }
    
    public void draw(Graphics g) {
        
        if (isInMenu ()) {
            getCurrentMenu ().draw (g);
            return;
        }
        
        adjustCamera (g, player.getX (), player.getY ());
        
        for (int i=0; i<tiles.length; i++) {
            for (int j=0; j<tiles[i].length; j++){
                if (tiles[i][j] != null)
                    tiles[i][j].draw(g);
            }
        }
        
        for (int i=0; i<enemies.size(); i++) {
            enemies.get(i).draw(g);
        }
        
        for (int i=0; i<projectiles.size(); i++) {
            projectiles.get(i).draw(g);
        }
        
        for (int i=0; i<interactables.size(); i++) {
            interactables.get(i).draw(g);
        }
        
        player.draw (g);
        
        HUD.draw (g, camX, camY);
    }
    
    @Override
    public void notifyNewMap(TileMap map) {
        int h = map.getHeight();
        int w = map.getWidth();
        tiles = new TileRepresentation[h][w];        
        for (int i=0; i<h; i++) {
            
            for (int j=0; j<w; j++){
                if (map.getTile(j, i) != null)
                {                    
                    tiles[i][j] = new TileRepresentation (map.getTile(j, i));
                    tiles[i][j].setImage(tl.getImage(tiles[i][j].getType()));
                }
            }
        }
        
        offsetMaxX = (w * Tile.WIDTH ) - CAMERA_SIZE_X;
        offsetMaxY = (h * Tile.WIDTH) - CAMERA_SIZE_Y;
        
        GAME_IS_ACTIVE = true;
    }
    
    private void adjustCamera (Graphics g, int x, int y) {
        
        camX = x - CAMERA_SIZE_X / 2;
        camY = y - CAMERA_SIZE_Y / 2;
        
        if (camX > offsetMaxX)
            camX = offsetMaxX;
        else if (camX < offsetMinX)
            camX = offsetMinX;
        
        if (camY > offsetMaxY)
            camY = offsetMaxY;
        else if (camY < offsetMinY)
            camY = offsetMinY;
        
        g.translate(-camX, -camY);
    }

    @Override
    public void notifyReset() {
        enemies = new ArrayList ();
        interactables = new ArrayList ();
        tiles = null;
    }
    
    @Override
    public void notifyGameOver () {       
        GAME_OVER_SCREEN = new GameOverScreen ();
        MENUSTATE = 1;
        notifyReset();
    }
    
    @Override
    public void notifyNewLevel (Level level) {
        tl = new TileImageLoader(level);
        LOAD_SCREEN = new LoadScreen (level, player.getLife ());
        HUD = new HUD (player);
        MENUSTATE = 2;
        controller.pause(50);
        pause (50);
    }
    
    private void pause (int c){
        lock = c;
    }
    
    private void unpause () {
        MENUSTATE = -1;
    }
    
    public int getMenuState()
    {
        return MENUSTATE;
    }
    
    public GameOverScreen getGameOverMenu()
    {
        return GAME_OVER_SCREEN;
    }
    
    private boolean isInMenu () {
        if (MENUSTATE != -1)
            return true;
        else
            return false;
    }
    
    private Menu getCurrentMenu () {
        switch (MENUSTATE) {
            case 1:
                return GAME_OVER_SCREEN;
            case 2:
                return LOAD_SCREEN;
        }
        return null;
    }
}

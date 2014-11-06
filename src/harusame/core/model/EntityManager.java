package harusame.core.model;

import harusame.core.model.map.MapLoader;
import harusame.core.model.map.Tile;
import harusame.core.model.map.TileMap;
import static harusame.core.util.Direction.LEFT;
import static harusame.core.util.Direction.RIGHT;
import harusame.core.util.Level;
import static harusame.core.util.Level.Level_1;
import static harusame.core.util.ObjectType.DIRT;
import harusame.core.util.Observer;
import java.awt.Rectangle;
import java.util.ArrayList;

public class EntityManager {
    
    private boolean GAME_PAUSED;
    
    private Observer observer;
    
    private MapLoader   mapLoader;
    private CollisionHandler collisionHandler;
    
    private Level CURRENT_LEVEL = Level_1;
    
    private Player  player = new Player (0, 0);
    
    private TileMap map;
    private ArrayList<Enemy>    enemies = new ArrayList ();
    private ArrayList<Projectile>   projectiles = new ArrayList ();
    
    private ArrayList<Interactable> interactables = new ArrayList ();
    
    public EntityManager () {
        mapLoader = new MapLoader (this);
        collisionHandler = new CollisionHandler ();
    }
    
    /**
     * Update all objects in the model
     */
    public void update () {
        
        if (GAME_PAUSED)
            return;
        
        player.update ();
        
        if (!player.isACTIVE ()) {
            
            if (!player.isLocked() && player.isLifeLeft()) 
                reloadGame ();
                
            else if (!player.isLocked() && !player.isLifeLeft()) 
                gameOver ();
            
            return;
        }
        
        for (int i=0; i<enemies.size(); i++) 
            enemies.get(i).update();
        
        collisionHandler.checkPlayerTileCollision(player, map);
        collisionHandler.checkPlayerEnemyCollision(player, enemies);
        
        collisionHandler.checkPlayerStoneCollision (player, map, interactables, enemies);
        
        for (int i=0; i<interactables.size(); i++){          
            Interactable interactable = interactables.get (i);
            if(interactable.getType() != DIRT)
            {
                if (interactable.isFalling()){
                    interactable.update();
                    collisionHandler.checkFallingStoneCollision (player, interactable, map, interactables, enemies);
                }
                else {
                    handleGravity (interactable);
                } 
            }
        }   
        
        collisionHandler.checkEnemyCollision (enemies, interactables, map); 
    }
        
    public void startGame () {
        mapLoader.loadMap(Level.Level_1);
        GAME_PAUSED = false;
    }
    
    private void reloadGame (){
        reset ();
        observer.notifyReset();
        mapLoader.loadMap(CURRENT_LEVEL);
    }
    
    private void gameOver () {
        reset ();
        player = null;
        observer.notifyGameOver ();
        GAME_PAUSED = true;
    }
    
    private void reset (){
        map = new TileMap (0, 0);
        enemies = new ArrayList (); 
        projectiles = new ArrayList ();
        interactables = new ArrayList ();
    }
    
    private void handleGravity (Interactable interactable){
        int y = interactable.getY();   
        int x = interactable.getX();
        for (int i=0; i<interactables.size(); i++){
            if (y == interactables.get(i).getY()-Tile.WIDTH && x == interactables.get(i).getX())
                return;
        }
        
        interactable.update();
        if (interactable.getBound().intersects(player.getBound())){
            interactable.revert();
            return;
        }
        interactable.revert();
        
        int COLUMN = interactable.getX() / Tile.WIDTH;
        int ROW = interactable.getY() / Tile.WIDTH;
        
        Tile t = map.getTile(COLUMN, ROW+1);
        
        if (t == null && interactable.getX() % Tile.WIDTH == 0)
            interactable.setFalling(true);
    }
    
    public void setPlayer(int x, int y) {
        player = new Player(player, x, y);
        observer.notifyNewPlayer(player);
    }
    
    public Player getPlayer () {
        return player;
    }
    
    public void setMap(TileMap map) {
        this.map = map;
        observer.notifyNewMap(map);
    }
    
    public void addEnemy(Enemy enemy) {
        enemies.add (enemy);
        observer.notifyNewEnemy(enemy);
    }
    
    public void addInteractable (Interactable i){
        interactables.add(i);
        observer.notifyNewInteractable (i);
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }
    
    public void addObserver (Observer o) {
        observer = o;
        collisionHandler.setObserver(o);
        observer.notifyNewLevel (CURRENT_LEVEL);
    }
}

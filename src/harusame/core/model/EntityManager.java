package harusame.core.model;

import harusame.core.model.map.MapLoader;
import harusame.core.model.map.Tile;
import harusame.core.model.map.TileMap;
import static harusame.core.util.Direction.LEFT;
import static harusame.core.util.Direction.RIGHT;
import harusame.core.util.Level;
import static harusame.core.util.Level.Level_1;
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
    
    private ArrayList<Stone> stones = new ArrayList ();
    
    private ArrayList<MovableSprite> objectives = new ArrayList ();
    
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
        
        collisionHandler.checkPlayerStoneCollision (player, map, stones, enemies);
        
        for (int i=0; i<stones.size(); i++){
            Stone stone = stones.get (i);
            if (stone.isFalling()){
                stone.update();
                collisionHandler.checkFallingStoneCollision (player, stone, map, stones, enemies);
            }
            else {
                handleGravityOnStones (stone);
            } 
        }
        
        collisionHandler.checkEnemyCollision (enemies, stones, map); 
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
        stones = new ArrayList ();
    }
    
    private void handleGravityOnStones (Stone stone){
        int y = stone.getY();   
        int x = stone.getX();
        for (int i=0; i<stones.size(); i++){
            if (y == stones.get(i).getY()-Tile.WIDTH && x == stones.get(i).getX())
                return;
        }
        
        stone.update();
        if (stone.getBound().intersects(player.getBound())){
            stone.revert();
            return;
        }
        stone.revert();
        
        int COLUMN = stone.getX() / Tile.WIDTH;
        int ROW = stone.getY() / Tile.WIDTH;
        
        Tile t = map.getTile(COLUMN, ROW+1);
        
        if (t == null && stone.getX() % Tile.WIDTH == 0)
            stone.setFalling(true);
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
    
    public void addStone(Stone stone)
    {
        stones.add(stone);
        observer.notifyNewStone(stone);       
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

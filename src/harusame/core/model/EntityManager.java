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
        collisionHandler.checkEnemyCollision (enemies, stones, map); 
        
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
        
        int COLUMN = stone.getX() / Tile.WIDTH;
        int ROW = stone.getY() / Tile.WIDTH;
        
        Tile t = map.getTile(COLUMN, ROW+1);
        
        if (t == null)
            stone.setFalling(true);
        
    }
    /*
    private void checkMovableFallCollision (MovableSprite movable) {
        
        Rectangle movableBound = movable.getBound();
        int colum = movable.getX() / Tile.WIDTH;
        int row = movable.getY() / Tile.WIDTH;
                        
        Tile[]  tiles = new Tile[3];
        Tile tile;
        
        // Checks Tile collision on bottom
        tiles[0] = map.getTile(colum-1, row+1);
        tiles[1] = map.getTile(colum, row+1);
        tiles[2] = map.getTile(colum+1, row+1);
        
        
         for (int i=0; i<tiles.length; i++) {
            tile = tiles[i];                       
            if (tile != null && movableBound.intersects(tile.getBound()))
            {
                movable.revert();
                movable.setFalling(false);
                return;
            }
        }
         // Checks other movable objecs collision 
        if(checkMovableInternalCollision(movable) == false)
        {
            movable.revert();
            movable.setFalling(false);
            return;
        }
        // Checks player collision
        Rectangle playerBound = player.getBound();       
        if (playerBound.intersects(movableBound))
        {
            if(movable.isFalling() == false)
                movable.revert();
            else
            {
                movable.kill();
                player.kill();
            }
            return;
        }
        // Checks Enemy collision
        Rectangle enemyBound;
        for(int i=0; i<enemies.size(); i++)
        {
            enemyBound = enemies.get(i).getBound();
            if(movableBound.intersects(enemyBound))
            {
                if(movable.isFalling() == true)
                {
                     enemies.get(i).kill();
                     enemies.remove(i);
                     //movable.kill();
                     return;
                }
                else
                {
                    movable.revert();
                    return;
                }
            }
        }        
        movable.setFalling(true);        
    }
   /*
    private void checkPlayerMovableCollision (MovableSprite movable) {
        Rectangle playerBound = player.getBound();
        Rectangle movableBound = movable.getBound();
        int colum = movable.getX() / Tile.WIDTH;
        int row = movable.getY() / Tile.WIDTH;
       
      
        if (playerBound.intersects(movableBound)){
            if(player.getDIRECTION() == RIGHT)
            {
                if(movable.isFalling() == false)
                {
                    movable.setX(player.getX()+ 45);
                    if(checkMovableInternalCollision(movable) == false)
                    {                            
                         movable.revert();
                         player.revert();
                         return;
                    }                    
                    Rectangle enemyBound;        
                    for(int i=0; i<enemies.size(); i++)
                    {            
                        enemyBound = enemies.get(i).getBound();
                        if(movableBound.intersects(enemyBound))           
                        {                            
                            colum = enemies.get(i).getX() / Tile.WIDTH;
                            row = enemies.get(i).getY() / Tile.WIDTH;                            
                            if(map.getTile(colum + 1, row) == null)                          
                                enemies.get(i).setX(movable.getX() + 45); 
                            else
                            {
                                movable.revert();
                                player.revert();
                            }
                        }
                    }
                    
                }
                else                
                    player.revert();                 
                // TESTA KOMMENTERA BORT ELSE SATSEN MED REVERT
                // BLir skönare kontroll men kan skapa problem då
                // man vill hindra playern från att gå igenom fallande block från sidan               
            }            
            else if(player.getDIRECTION() == LEFT)
            {
                if(movable.isFalling() == false)
                {
                    movable.setX(player.getX()- 45);
                    if(checkMovableInternalCollision(movable) == false)
                        {                            
                            movable.revert();
                            player.revert();   
                            return;
                        } 
                }
                else                
                    player.revert();
            }
            else
                 player.revert();
        }
    }
    
    private boolean checkMovableInternalCollision(MovableSprite movable)
    {       
        Rectangle movableBound = movable.getBound();
        Rectangle tempBound;
        
        
        for(int i = 0; i < movables.size(); i++)
        {            
            tempBound = movables.get(i).getBound();
            if(movable != movables.get(i) && movableBound.intersects(tempBound)) 
                return false;                                    
        }
        return true;
    }    
    */
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

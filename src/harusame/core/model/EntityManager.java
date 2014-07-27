package harusame.core.model;

import harusame.core.model.map.Tile;
import harusame.core.model.map.TileMap;
import static harusame.core.util.Direction.LEFT;
import static harusame.core.util.Direction.RIGHT;
import harusame.core.util.Observer;
import java.awt.Rectangle;
import java.util.ArrayList;

public class EntityManager {

    private Observer observer;
    
    private Player  player;
    private TileMap map;
    private ArrayList<Enemy>    enemies = new ArrayList ();
    private ArrayList<Projectile>   projectiles = new ArrayList ();
    private ArrayList<MovableSprite> movables = new ArrayList ();
    
    public EntityManager () {
        
    }
    
    public void reset () {
        player = null;
        map = new TileMap (0, 0);
        enemies = new ArrayList (); 
        projectiles = new ArrayList ();
        movables = new ArrayList ();
        observer.notifyReset ();
    }
    
    public void setPlayer(Player player) {
        this.player = player;
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
    
    public void addMovable(MovableSprite movable)
    {
        movables.add(movable);
        observer.notifyNewMovable(movable);       
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }
    
    public void update () {
        
        if (!player.isACTIVE () && !player.isLocked())
            observer.notifyGameOver();
        
        player.update ();
        
        for (int i=0; i<enemies.size(); i++) {
            enemies.get(i).update();
        }
        
        checkPlayerTileCollision ();
        
        for (int i=0; i<enemies.size(); i++) {
            if (!enemies.get(i).isACTIVE ())
                    continue;
            checkEnemyTileCollision (enemies.get(i));
            checkPlayerEnemyCollision (enemies.get(i));            
        }
        
        for(int i=0; i<movables.size(); i++)
        {
            if (!movables.get(i).isACTIVE ())
                    continue;
            checkPlayerMovableCollision (movables.get(i));
            movables.get(i).update();
            checkMovableFallCollision(movables.get(i));            
        }
    }
    
    public void addObserver (Observer o) {
        observer = o;
    }
    
    private void checkPlayerTileCollision () {
        
        Rectangle playerBound = player.getBound();
        int colum = player.getX() / Tile.WIDTH;
        int row = player.getY() / Tile.WIDTH;
        
        Tile[]  tiles = getSourroundingTiles (colum, row);
        Tile    tile;
        
        for (int i=0; i<tiles.length; i++) {
            tile = tiles[i];
            if (tile != null && playerBound.intersects(tile.getBound()))
                player.revert();
        }
    }
    
    private void checkEnemyTileCollision (Enemy enemy) {
        
        Rectangle enemyBound = enemy.getBound();
        int colum = enemy.getX() / Tile.WIDTH;
        int row = enemy.getY() / Tile.WIDTH;
        
        Tile[]  tiles = getSourroundingTiles (colum, row);
        Tile    tile;
        
        for (int i=0; i<tiles.length; i++) {
            tile = tiles[i];
            if (tile == null);
            else if (tile != null && enemyBound.intersects(tile.getBound()))
                enemy.revert();
        }
    }
    
    
    // Falling
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
        
        movable.setFalling(true);        
    }
    
    // GODRIKE ENGRISH DETECTED
    private Tile[]  getSourroundingTiles (int colum, int row) {
        Tile[]  tiles = new Tile[5];
        tiles[0] = map.getTile(colum, row);
        tiles[1] = map.getTile(colum+1, row);
        tiles[2] = map.getTile(colum-1, row);
        tiles[3] = map.getTile(colum, row+1);
        tiles[4] = map.getTile(colum, row-1);
                                        
        return tiles;
    }
    
    private void checkPlayerEnemyCollision (Enemy enemy) {
        Rectangle playerBound = player.getBound();
        Rectangle enemyBound = enemy.getBound();
        
        if (playerBound.intersects(enemyBound)){
            player.kill();
            enemy.kill();
        }
    }
    
    private void checkPlayerMovableCollision (MovableSprite movable) {
        Rectangle playerBound = player.getBound();
        Rectangle movableBound = movable.getBound();
       
       
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
}

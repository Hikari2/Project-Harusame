package harusame.core.model;

import harusame.core.model.map.Tile;
import harusame.core.model.map.TileMap;
import harusame.core.util.Observer;
import java.awt.Rectangle;
import java.util.ArrayList;

public class EntityManager {

    private Observer observer;
    
    private Player  player;
    private TileMap map;
    private ArrayList<Enemy>    enemies = new ArrayList ();
    private ArrayList<Projectile>   projectiles = new ArrayList ();
    private ArrayList<Interactable> interactables = new ArrayList ();
    
    public EntityManager () {
        
    }
    
    public void reset () {
        player = null;
        map = new TileMap (0, 0);
        enemies = new ArrayList (); 
        projectiles = new ArrayList ();
        interactables = new ArrayList ();
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
    
    public void addInteractable(Interactable interactable)
    {
        interactables.add(interactable);
        //observer.
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
            checkEnemyTileCollision (enemies.get(i));
            checkPlayerEnemyCollision (enemies.get(i));
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
            if (tile != null && enemyBound.intersects(tile.getBound()))
                enemy.revert();
        }
    }
    
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
        
        if (playerBound.intersects(enemyBound))
            player.kill();
    }
}

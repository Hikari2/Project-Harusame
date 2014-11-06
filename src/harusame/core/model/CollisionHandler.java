package harusame.core.model;

import harusame.core.model.map.Tile;
import harusame.core.model.map.TileMap;
import static harusame.core.util.Direction.LEFT;
import static harusame.core.util.Direction.RIGHT;
import static harusame.core.util.ObjectType.STONE;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * This class contain method used for detecting and handling collisions 
 * between objects in the game world.
 */
public class CollisionHandler {
    
    void checkPlayerTileCollision (Player player, TileMap map) {

        Rectangle playerBound = player.getBound();
        int colum = player.getX() / Tile.WIDTH;
        int row = player.getY() / Tile.WIDTH;

        Tile[]  tiles = getSurroundingTiles (map, colum, row);
        Tile    tile;

        for (int i=0; i<tiles.length; i++) {
            tile = tiles[i];
            if (tile != null && playerBound.intersects(tile.getBound())){
                player.revert();
                return;
            }
        }
    }
    
    void checkPlayerStoneCollision (Player player, TileMap map, ArrayList<Interactable> interactables, ArrayList<Enemy> enemies){
        Rectangle playerBound = player.getBound();
        Rectangle interactableBound;
        Interactable interactable;
        
        for (int i=0; i<interactables.size(); i++){
            if (interactables.get(i).isFalling())
                continue;
            
            interactable = interactables.get(i);
            interactableBound = interactable.getBound();
            
            if (playerBound.intersects(interactableBound)){
                
                switch (interactable.getType()){
                    case STONE:
                        if (player.getDIRECTION() == RIGHT)
                            interactable.moveRight(player.getX());
                
                        else if (player.getDIRECTION() == LEFT)
                            interactable.moveLeft(player.getX());
                        else {
                            player.revert();
                            return;
                        }
                        handlePushedStoneCollision (interactable, player, map, interactables, enemies);
                        break;
                        
                    case LARVA:
                        interactable.kill();
                        interactables.remove(i);
                        return;
                }
            }
        }
    }
    
    private void handlePushedStoneCollision (Interactable interactable, Player player, TileMap map, ArrayList<Interactable> interactables, ArrayList<Enemy> enemies) {
       
        Rectangle interactableBound = interactable.getBound();
        
        for (int i=0; i<interactables.size(); i++)
            if (interactableBound.intersects(interactables.get(i).getBound()) && interactable != interactables.get(i)){
                interactable.revert();
                player.revert();
                return;
            }
        
        int COLUMN = interactable.getX() / Tile.WIDTH;
        int ROW = interactable.getY() / Tile.WIDTH;
        
        Tile t = null;
        
        if (player.getDIRECTION() == LEFT)
            t = map.getTile(COLUMN, ROW);
        else if (player.getDIRECTION() == RIGHT)
            t = map.getTile(COLUMN+1, ROW);

        if (t != null && interactableBound.intersects(t.getBound())){
            interactable.revert();
            player.revert();
            return;
        }
        
        Rectangle   enemyBound;
        Enemy   enemy;
        for (int i=0; i<enemies.size(); i++){
            enemy = enemies.get(i);
            enemyBound = enemy.getBound();
            
            if (interactableBound.intersects(enemyBound)){
                enemy.pushBack();
                enemyBound = enemy.getBound();
                if (interactableBound.intersects(enemyBound)){
                    enemy.kill();
                    enemies.remove(i);
                }
            }
        }
    }
    
    void checkFallingStoneCollision (Player player, Interactable interactable, TileMap map, ArrayList<Interactable> interactables, ArrayList<Enemy> enemies){

        Rectangle interactableBound = interactable.getBound();
        
        for (int i=0; i<interactables.size(); i++)
            if (interactableBound.intersects(interactables.get(i).getBound()) && interactable != interactables.get (i)){
                interactable.revert();
                interactable.setFalling(false);
            }
        
        for (int i=0; i<enemies.size(); i++)
            if (interactableBound.intersects(enemies.get(i).getBound())){
                enemies.get(i).kill();
                enemies.remove(i);
            }
        
        if (interactableBound.intersects(player.getBound()) && (Math.abs(player.getY() - interactable.getY())) < 30 && interactable.getType() == STONE)
            player.kill();
        
        int COLUMN = interactable.getX() / Tile.WIDTH;
        int ROW = (interactable.getY() - interactable.getY() % Tile.WIDTH)/ Tile.WIDTH;
        
        Tile t = map.getTile(COLUMN, ROW+1);
        
        if (t != null && interactableBound.intersects(t.getBound())){
            interactable.revert();
            interactable.setFalling(false);
        }
    }
    
    void checkPlayerEnemyCollision (Player player, ArrayList<Enemy>    enemies) {
        for (int i=0; i<enemies.size(); i++){
            Rectangle playerBound = player.getBound();
            Rectangle enemyBound = enemies.get(i).getBound();

            if (playerBound.intersects(enemyBound)){
                player.kill();
            }
        }
    } 
    
    void checkEnemyCollision (ArrayList<Enemy>    enemies, ArrayList<Interactable> interactables, TileMap map) {
        
        Enemy enemy;

        for (int i=0; i<enemies.size(); i++){
            enemy = enemies.get(i);
            Rectangle enemyBound = enemy.getBound();
            int colum = enemy.getX() / Tile.WIDTH;
            int row = enemy.getY() / Tile.WIDTH;

            Tile[]  tiles = getSurroundingTiles (map, colum, row);
            Tile    tile;
            
            for (Tile t : tiles) {
                tile = t;
                if (tile != null && enemyBound.intersects(tile.getBound()))
                {
                    enemy.revert();
                    break;
                }
            }

            Rectangle stoneBound;
            
            for(int j=0; j<interactables.size(); j++)
            {
                stoneBound = interactables.get(j).getBound();
                if(enemyBound.intersects(stoneBound))
                    enemy.revert();
            } 
        }       
    }
    
    private Tile[]  getSurroundingTiles (TileMap map, int colum, int row) {
        Tile[]  tiles = new Tile[5];
        tiles[0] = map.getTile(colum, row);
        tiles[1] = map.getTile(colum+1, row);
        tiles[2] = map.getTile(colum-1, row);
        tiles[3] = map.getTile(colum, row+1);
        tiles[4] = map.getTile(colum, row-1);
                                        
        return tiles;
    }
}

package harusame.core.model;

import harusame.core.model.map.Tile;
import harusame.core.model.map.TileMap;
import harusame.core.util.Direction;
import static harusame.core.util.Direction.LEFT;
import static harusame.core.util.Direction.RIGHT;
import harusame.core.util.Observer;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Hikari
 */
public class CollisionHandler {
    
    private Observer observer;
    
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
    
    void checkPlayerStoneCollision (Player player, TileMap map, ArrayList<Stone> stones, ArrayList<Enemy> enemies){
        Rectangle playerBound = player.getBound();
        Rectangle stoneBound;
        Stone stone;
        
        for (int i=0; i<stones.size(); i++){
            if (stones.get(i).isFalling())
                continue;
            
            stone = stones.get(i);
            stoneBound = stone.getBound();
            
            if (playerBound.intersects(stoneBound)){
                if (player.getDIRECTION() == RIGHT)
                    stone.moveRight(player.getX());
                else if (player.getDIRECTION() == LEFT)
                    stone.moveLeft(player.getX());
                else {
                    player.revert();
                }
                
                if (checkStoneEnviromentCollision (stone, player.getDIRECTION(), map, stones, enemies)){
                    stone.revert();
                    player.revert();
                }
            }
        }
    }
    
    private boolean checkStoneEnviromentCollision (Stone stone, Direction direction, TileMap map, ArrayList<Stone> stones, ArrayList<Enemy> enemies){
        
        Rectangle stoneBound = stone.getBound();
        
        for (int i=0; i<stones.size(); i++)
            if (stoneBound.intersects(stones.get(i).getBound()) && stone != stones.get(i)){
                return true;
            }
        
        for (int i=0; i<enemies.size(); i++)
            if (stoneBound.intersects(enemies.get(i).getBound())){
                stone.revert();
                return true;
            }
       
        int COLUMN = stone.getX() / Tile.WIDTH;
        int ROW = stone.getY() / Tile.WIDTH;
        Tile t = map.getTile(COLUMN, ROW);

        return (t != null && stoneBound.intersects(t.getBound()));
    }
    
    void checkFallingStoneCollision (Player player, Stone stone, TileMap map, ArrayList<Stone> stones, ArrayList<Enemy> enemies){

        Rectangle stoneBound = stone.getBound();
        
        for (int i=0; i<stones.size(); i++)
            if (stoneBound.intersects(stones.get(i).getBound()) && stone != stones.get (i)){
                stone.revert();
                stone.setFalling(false);
            }
        
        for (int i=0; i<enemies.size(); i++)
            if (stoneBound.intersects(enemies.get(i).getBound())){
                enemies.get(i).kill();
                enemies.remove(i);
            }
        /*
        if (stoneBound.intersects(player.getBound()) && (Math.abs(player.getY() - stone.getY())) < 20)
            player.kill();
        */
        int COLUMN = stone.getX() / Tile.WIDTH;
        int ROW = (stone.getY() - stone.getY() % Tile.WIDTH)/ Tile.WIDTH;
        
        Tile t = map.getTile(COLUMN, ROW+1);
        
        if (t != null && stoneBound.intersects(t.getBound())){
            stone.revert();
            stone.setFalling(false);
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
    
    void checkEnemyCollision (ArrayList<Enemy>    enemies, ArrayList<Stone> stones, TileMap map) {
        
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
                    return;
                }
            }
            
            Rectangle movableBound;
            
            for(int j=0; j<stones.size(); j++)
            {
                movableBound = stones.get(j).getBound();
                if(enemyBound.intersects(movableBound))
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

    public void setObserver(Observer observer) {
        this.observer = observer;
    }
}

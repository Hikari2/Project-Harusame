package harusame.core.model.collision;

import harusame.core.model.entity.Bee;
import harusame.core.model.entity.Player;
import harusame.core.model.map.Tile;
import harusame.core.model.map.TileMap;
import java.awt.Rectangle;

/**
 *
 * @author Hikari
 */
public class CollisionHandler {
    
    private static CollisionHandler ch = new CollisionHandler ();
    private CollisionHandler () {
        
    }
    
    public static CollisionHandler getCollisionHandler () {
        return ch;
    }
    
    public void CheckTileCollision (Player p, TileMap map) {
        int x = p.getX();
        int y = p.getY();
        
        int camX = map.getCamX();
        int camY = map.getCamY();
        
        Rectangle   playerBound = p.getBound();
        
        int rowCount = map.getRowCount();
        Tile    tile;
        Tile[]  row;
        for (int i=0; i<rowCount; i++) {
            row = map.getRow(i);
            for (int j=0; j<row.length; j++) {
                if (row[j] == null)
                    continue;
                tile = row[j];
                if (playerBound.intersects(tile.getBound()))
                    p.revert();
            }
        }
    }
    
    public void CheckEnemyCollision (Bee b, TileMap map) {
        int x = b.getX();
        int y = b.getY();
        
        int camX = map.getCamX();
        int camY = map.getCamY();
        
        Rectangle   playerBound = b.getBound();
        
        int rowCount = map.getRowCount();
        Tile    tile;
        Tile[]  row;
        for (int i=0; i<rowCount; i++) {
            row = map.getRow(i);
            for (int j=0; j<row.length; j++) {
                if (row[j] == null)
                    continue;
                tile = row[j];
                if (playerBound.intersects(tile.getBound()))                 
                    b.revert();               
               
            }
        }
    }
}

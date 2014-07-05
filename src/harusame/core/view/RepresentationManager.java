package harusame.core.view;

import harusame.core.model.Player;
import harusame.core.model.entity.Enemy;
import harusame.core.model.map.Tile;
import harusame.core.model.map.TileMap;
import harusame.core.util.Observer;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Hikari
 */
public class RepresentationManager implements Observer{

    private PlayerRepresentation    player;

    private ArrayList<EnemyRepresentation>  enemies = new ArrayList ();
    
    private TileRepresentation[][]  tiles;
    
    public void update () {
        player.update ();
    }
    
    
    @Override
    public void notifyNewPlayer(Player p) {
        player = new PlayerRepresentation (p);
    }

    @Override
    public void notifyNewEnemy(Enemy e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void draw(Graphics g) {
        
        for (int i=0; i<tiles.length; i++) {
            for (int j=0; j<tiles[i].length; j++){
                if (tiles[i][j] != null)
                    tiles[i][j].draw(g);
            }
        }
        
        player.draw (g);
    }

    @Override
    public void notifyNewMap(TileMap map) {
        int h = map.getHeight();
        int w = map.getWidth();
        tiles = new TileRepresentation[h][w];
        
        for (int i=0; i<h; i++) {
            
            for (int j=0; j<w; j++){
                if (map.getTile(j, i) != null)
                    tiles[i][j] = new TileRepresentation (map.getTile(j, i));
            }
        }
    }
}

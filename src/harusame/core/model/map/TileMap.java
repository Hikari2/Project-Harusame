package harusame.core.model.map;

import java.awt.Graphics;

/**
 *
 * @author Hikari
 */
public class TileMap 
{
    private Tile[][] tiles;
    
    
    public TileMap(int w, int h)
    {
        tiles = new Tile [h][w];
    }
    
    public Tile getTile (int x, int y) {
        return tiles[y][x];
    }
    
    public void setTile (Tile t, int x, int y) {
        tiles[y][x] = t;
    }
    
    public void draw (Graphics g, int x, int y) {
        Tile tile;
        for (int i=0; i<tiles.length; i++) {
            
            for (int j=0; j<tiles[i].length; j++) {
                tile = tiles[i][j];
                g.drawImage (tile.getImage(), tile.getX (), tile.getY(), null);
            }
        }
    }
}

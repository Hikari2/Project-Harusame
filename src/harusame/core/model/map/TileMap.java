package harusame.core.model.map;

import harusame.core.view.GamePanel;
import java.awt.Graphics;

/**
 *
 * @author Hikari
 */
public class TileMap 
{
    private Tile[][] tiles;
    
    private int CAMERA_SIZE_X = GamePanel.WIDTH;
    private int CAMERA_SIZE_Y = GamePanel.HEIGHT;
    
    private int offsetMaxX;
    private int offsetMaxY;
    private int offsetMinX;
    private int offsetMinY;
    
    private int camX;
    private int camY;
    
    public TileMap(int w, int h)
    {
        tiles = new Tile [h][w];
        offsetMaxX = (w * Tile.WIDTH ) - CAMERA_SIZE_X;
        offsetMaxY = (h * Tile.WIDTH) - CAMERA_SIZE_Y;
    }
    
    public int getHeight () {
        return tiles.length;
    }
    
    public int getWidth () {
        return tiles[0].length;
    }
    
    public Tile getTile (int x, int y) {
        if (y >= tiles.length ||    y < 0 ||    x < 0   || x >= tiles[y].length )
            return null;
        return tiles[y][x];
    }
    
    public void setTile (Tile t, int x, int y) {
        tiles[y][x] = t;
    }
    
    public Tile[] getRow (int i) {
        return tiles[i];
    }
    
    public int getRowCount () {
        return tiles.length;
    }
    
    public int getCamX () {
        return camX;
    }
    public int getCamY () {
        return camY;
    }
}

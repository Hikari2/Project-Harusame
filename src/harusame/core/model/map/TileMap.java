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
    
    private void adjustCamera (Graphics g, int x, int y) {
        
        camX = x - CAMERA_SIZE_X / 2;
        camY = y - CAMERA_SIZE_Y / 2;
        
        if (camX > offsetMaxX)
            camX = offsetMaxX;
        else if (camX < offsetMinX)
            camX = offsetMinX;
        
        if (camY > offsetMaxY)
            camY = offsetMaxY;
        else if (camY < offsetMinY)
            camY = offsetMinY;
        
        g.translate(-camX, -camY);
    }
}

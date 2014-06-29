package harusame.core.model.map;

import harusame.core.model.entity.Player;
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
    
    /**
     * Draw all tiles in this TileMap
     * @param g Graphics of target panel
     * @param x X coordinate of player
     * @param y Y coordinate of player
     */
    public void draw (Graphics g, int x, int y) {
        
        adjustCamera (g, x, y);
        
        Tile tile;
        
        for (int row=0; row<tiles.length; row++) {
            for (int colum=0; colum<tiles[row].length; colum++) {
                tile = tiles[row][colum];
                if (tile != null){
                    g.drawImage (tile.getImage(), tile.getX (), tile.getY(), Tile.WIDTH, Tile.WIDTH, null);
                                              g.drawString("" + tile.getY (), tile.getX ()+30, tile.getY()+15);
                                              g.drawString("" + camX, camX+80, camY+80);
                                              g.drawString("" + camY, camX+80, camY+90);
                }
            }
        }
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

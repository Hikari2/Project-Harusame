/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package harusame.core.model.map;

import harusame.core.model.map.TileMap;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Hikari
 */
public class MapLoader 
{
    private String  stoneWall = "Resources/Tilesets/Stonewall1.jpg";
    
    char[][] map;
    private TileMap tileMap;
    int w;
    int h;
    
    
    public MapLoader(int width, int height)
    {
        map = new char[height/10][width/10];
        w = width;
        h = height;
    }
    
    public TileMap loadMap(String level)
    {
        BufferedReader br = null;
        
        try {        
            String sCurrentLine;
            int x = 0;
            int y = 0;
            
            br = new BufferedReader(new FileReader("Resources/Maps/" +level +".txt"));
            
            w = Integer.parseInt(br.readLine());
            h = Integer.parseInt(br.readLine());
            
            tileMap = new TileMap (w, h);
            
            Tile    tile;
            String line;
            
            for (int i=0; i<h; i++){
                
                line = sCurrentLine = br.readLine();
                
                for (int j=0; j<w; i++) {
                    tile = symbolToTile(line.charAt(j), j, i);
                    tileMap.setTile(tile, x, y);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }          

        return tileMap;
    }
    
    private Tile symbolToTile (char symbol, int colum, int row) throws IOException {
        
        String path;
        BufferedImage   image;
        Tile    tile;
        
        switch (symbol) {
            case '#': 
                path = stoneWall;
                image = ImageIO.read(new File(path));
                tile = new Tile (image, true, colum*Tile.WIDTH, row*Tile.WIDTH);
                return tile;
    }
        return null;
    }
}

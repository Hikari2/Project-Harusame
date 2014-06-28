/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package harusame.core.model.map;

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
    private TileMap tileMap;
    int w;
    int h;
    
    public TileMap loadMap(String level)
    {
        BufferedReader br = null;
        
        try {        
            int x;
            int y;
            
            br = new BufferedReader(new FileReader("Resources/Maps/" +level +".txt"));
            
            w = Integer.parseInt(br.readLine());
            h = Integer.parseInt(br.readLine());
            
            tileMap = new TileMap (w, h);
            
            Tile    tile;
            String line;
            
            for (int i=0; i<h; i++){
                
                line = br.readLine();

                for (int j=0; j<line.length(); j++) {
                    tile = symbolToTile(line.charAt(j), j, i);
                    tileMap.setTile(tile, j, i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        finally {
            try {
                if (br != null)
                    br.close();
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

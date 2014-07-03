/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package harusame.core.model.map;

import harusame.core.model.entity.Bee;
import harusame.core.model.entity.Player;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Hikari
 */
public class MapLoader 
{
    private TileMap tileMap;
    private ArrayList<Tile> tempTiles = new ArrayList<Tile>();
    int w;
    int h;
    
    public TileMap loadMap(String level)
    {
        BufferedReader br = null;
        
        try {
            // Read in all the existing tiles
            br = new BufferedReader(new FileReader("Resources/Maps/" +level +"-tiles.txt"));
            BufferedImage   image;
            
            String pathLine;
            Boolean blockedLine;
            char symbolLine;
            
            pathLine = br.readLine();
            while(pathLine != null)
            {
                blockedLine = Boolean.valueOf(br.readLine());
                symbolLine = br.readLine().charAt(0);
                image = ImageIO.read(new File(pathLine));
                tempTiles.add(new Tile(image, blockedLine, symbolLine, 0, 0));
                pathLine = br.readLine();
            }
            
            br = new BufferedReader(new FileReader("Resources/Maps/" +level +".txt"));
            
            String line;
            
            w = Integer.parseInt(br.readLine());
            h = Integer.parseInt(br.readLine());
            
            tileMap = new TileMap (w, h);
            
            Tile    tile;
            
            
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
        Tile    tile;
        
        for(int i = 0; i < tempTiles.size(); i++)
        {
            if(symbol == tempTiles.get(i).getSymbol())
            {
                tile = new Tile(tempTiles.get(i).getImage(), tempTiles.get(i).isBlocked(), 
                                tempTiles.get(i).getSymbol(), colum*Tile.WIDTH, row*Tile.WIDTH);
                return tile;
            }
            else
                symbolToSprite (symbol, colum, row);                       
        }
        return null; 
    }
    
    private void symbolToSprite (char symbol, int colum, int row){
        
        switch (symbol){
            case 'P':
                tileMap.setPlayer(new Player (colum*Tile.WIDTH, row*Tile.WIDTH));
                break;
            case 'B':
                tileMap.addSprite(new Bee (colum*Tile.WIDTH, row*Tile.WIDTH));
                break;
            case 'S':               
                //tileMap.addSprite(new MovableObject (colum*Tile.WIDTH, row*Tile.WIDTH));
        }
    }
}

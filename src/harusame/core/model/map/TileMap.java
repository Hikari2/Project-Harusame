/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package harusame.core.model.map;

import harusame.core.model.animation.PlayerAnimationLoader;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Hikari
 */
public class TileMap 
{
    private Tile[] tiles = new Tile[100];
    
    
    public TileMap(String level)
    {
        
           Image in;
           Boolean blocked;
           char symbol;
                 
           BufferedReader br = null;
                 
           try {                     
                    String sCurrentLine;
                    int counter = 0;
 
                    br = new BufferedReader(new FileReader("Resources/Maps/" + level + "-tiles.txt"));
 
                    while ((sCurrentLine = br.readLine()) != null) 
                    {        
                         blocked = false;
                         in = ImageIO.read(new File(sCurrentLine));
                         sCurrentLine = br.readLine();
                              
                         if(sCurrentLine.equals("true"))
                             blocked = true;
                              
                         symbol = br.readLine().charAt(0);
                              
                         tiles[counter] = new Tile(in, true, symbol);  
                         counter++;
                    }
                System.out.println(sCurrentLine);
               }
 
	 catch (IOException e) {
		e.printStackTrace();
		} finally 
                {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}         
     
    }
    
    public Image getTile(char symbol)
    {       
        for(int i = 0; i < tiles.length; i++)
        {
            if(tiles[i].isChar(symbol) == true)
                return tiles[i].getTile();
        }
        return null;
    }
    
}

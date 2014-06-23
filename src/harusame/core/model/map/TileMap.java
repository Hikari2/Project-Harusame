/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package harusame.core.model.map;

import harusame.core.model.animation.PlayerAnimationLoader;
import java.awt.Image;
import java.io.File;
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
    private Tile[] tiles = new Tile[10];
    
    
    public TileMap()
    {
        try
        {
                 Image in = ImageIO.read(new File("Resources/Tilesets/Stonewall1.jpg"));
                 tiles[0] = new Tile(in, true, '#');                 
        }
        catch (IOException ex) 
        {
                 Logger.getLogger(PlayerAnimationLoader.class.getName()).log(Level.SEVERE, null, ex);
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

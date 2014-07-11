package harusame.core.view;

import harusame.core.util.TileType;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.imageio.ImageIO;

/**
 *
 * @author Admin
 */
public class TileImageLoader {
        
    private BufferedReader br;    
    private String currentLevel;
    public TileImageLoader (String level)
    {
        currentLevel = level;        
    }
    
    public BufferedImage getImage(TileType type)
    {
        try
        {       
            br = new BufferedReader(new FileReader("Resources/Maps/" + currentLevel +"/TileTypeToImage.txt")); 
            String line = br.readLine();
            while (line != null) 
            {
                if(line.equals(type.toString()))
                {
                    line = br.readLine();                    
                    return ImageIO.read(new File ("Resources/Tilesets/" + line));                    
                }                    
                line = br.readLine();                
            }            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    
}

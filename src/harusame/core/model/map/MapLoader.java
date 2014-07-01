package harusame.core.model.map;

import harusame.core.model.DTO.GameLevelDTO;
import harusame.core.model.entity.Bee;
import harusame.core.model.entity.Enemy;
import harusame.core.model.entity.Player;
import harusame.core.model.entity.Projectile;
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
    private final String  stoneWall = "Resources/Tilesets/Stonewall1.jpg";
    private final String  blank = "Resources/Sprites/Player/blank.png";
    
    private GameLevelDTO    gameLevel;
    private TileMap tileMap;
    private Player player;
    private ArrayList<Enemy>   enemies;
    private ArrayList<Projectile>   projectiles;
    
    int w;
    int h;
    
    public GameLevelDTO loadMap(String level)
    {
        gameLevel = new GameLevelDTO ();
        enemies = new ArrayList ();
        
        BufferedReader br = null;
        
        try {        
            
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
        
        gameLevel.setMap(tileMap);
        gameLevel.setEnemies(enemies);
        gameLevel.setPlayer(player);
        return gameLevel;
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
            default:
                symbolToSprite (symbol, colum, row);
                break;
    }
        return null;
    }
    
    private void symbolToSprite (char symbol, int colum, int row){
        
        switch (symbol){
            case 'P':
                player = new Player (colum*Tile.WIDTH, row*Tile.WIDTH);
                break;
            case 'B':
                enemies.add(new Bee (colum*Tile.WIDTH, row*Tile.WIDTH));
                break;
        }
    }
}

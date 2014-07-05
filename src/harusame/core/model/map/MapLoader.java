package harusame.core.model.map;

import harusame.core.model.EntityManager;
import harusame.core.model.entity.Bee;
import harusame.core.model.Player;
import harusame.core.util.TileType;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Hikari
 */
public class MapLoader 
{
    private EntityManager   entityManager;

    private TileMap map;
    
    private ArrayList<Tile> tempTiles = new ArrayList<Tile>();
    
    private HashMap<String, Object[]> symbolMap;
    
    int w;
    int h;

    public MapLoader(EntityManager em) {
       entityManager = em;
    }
    
    public void loadMap(String level)
    {
        entityManager.clear ();
        
        BufferedReader br = null;
        
        try {
            
            br = new BufferedReader(new FileReader("Resources/Maps/" +level +"/SymbolMap.txt"));
            LoadSymbolMap (level, br);
            
            br = new BufferedReader(new FileReader("Resources/Maps/" +level +"/TileMap.txt"));
            
            String line;
            
            w = Integer.parseInt(br.readLine());
            h = Integer.parseInt(br.readLine());
            
            map = new TileMap (w, h);
            
            Tile    tile;
            
            for (int i=0; i<h; i++){
                
                line = br.readLine();

                for (int j=0; j<line.length(); j++) {
                    tile = symbolToTile(line.charAt(j), j, i);
                    map.setTile(tile, j, i);
                }
            }
            
            entityManager.setMap(map);
            
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
    }
    
    private void LoadSymbolMap (String level, BufferedReader br) throws FileNotFoundException, IOException {
        symbolMap = new HashMap ();
        br = new BufferedReader(new FileReader("Resources/Maps/" +level +"/SymbolMap.txt"));
        
        Object[]    data;
        String symbol = " ";
        String type = " ";
        String isBlocked = " ";
        String line = " ";
        while (line != null) {
            symbol = br.readLine();
            
            data = new Object [2];

            type = br.readLine();
            data[0] = TileType.valueOf(type);
            
            isBlocked = br.readLine();
            data[1] = Boolean.valueOf(isBlocked);
            
            symbolMap.put(symbol, data);
            line = br.readLine();
        }
    }
    
    private Tile symbolToTile (char symbol, int colum, int row) throws IOException {
        Tile tile;
        
        if (symbolMap.containsKey(symbol+"")) {
            Object[]    data;
            data = symbolMap.get(symbol+"");
            tile = new Tile ( TileType.valueOf (data[0].toString()), (boolean)data[1], colum*Tile.WIDTH, row*Tile.WIDTH);
            return tile;
        }
        else 
            symbolToSprite (symbol, colum, row);
        return null;
    }
    
    private void symbolToSprite (char symbol, int colum, int row){
        
        switch (symbol){
            case 'P':
                entityManager.setPlayer(new Player (colum*Tile.WIDTH, row*Tile.WIDTH));               
                break;
            case 'E':
                entityManager.addEnemy(new Bee (colum*Tile.WIDTH, row*Tile.WIDTH));
                break;
            case 'S':               
                //tileMap.addSprite(new MovableObject (colum*Tile.WIDTH, row*Tile.WIDTH));
        }
    }
}

package harusame.core.model.map;

import harusame.core.model.EntityManager;
import harusame.core.model.Enemy;
import harusame.core.model.Interactable;
import harusame.core.util.EnemyType;
import harusame.core.util.Level;
import harusame.core.util.ObjectType;
import harusame.core.util.TileType;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 *
 * @author Hikari
 */
public class MapLoader 
{
    private final EntityManager   entityManager;

    private TileMap map;
    
    private HashMap<String, Object[]> symbolMap;
    
    int w;
    int h;

    public MapLoader(EntityManager em) {
       entityManager = em;
    }
    
    public void loadNextMap (Level level){
        String line = "";
        try {
            
            BufferedReader br = new BufferedReader(new FileReader("Resources/Maps/LevelPath.txt"));
            
            while (!line.equals(level.toString())){
                line = br.readLine();
            }
            line = br.readLine();

            loadMap (Level.valueOf(line));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapLoader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MapLoader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public void loadMap(Level level)
    {
        BufferedReader br = null;
        
        try {
            
            br = new BufferedReader(new FileReader("Resources/Maps/" +level +"/SymbolMap.txt"));
            LoadSymbolMap (br);
            
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
            entityManager.setCurrentLevel(level);
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
    
    private void LoadSymbolMap (BufferedReader br) throws FileNotFoundException, IOException {
        symbolMap = new HashMap ();
        
        Object[]    data;
        String symbol;
        String type;
        String isBlocked;
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
                entityManager.setPlayer(colum*Tile.WIDTH, row*Tile.WIDTH);               
                break;
            case 'E':
                entityManager.addEnemy(new Enemy (colum*Tile.WIDTH, row*Tile.WIDTH, EnemyType.BEE));
                break;
            case 'e':
                entityManager.addEnemy(new Enemy (colum*Tile.WIDTH, row*Tile.WIDTH, EnemyType.BEE_LARVA));
                break;
            case 'S':               
                entityManager.addInteractable(new Interactable (colum*Tile.WIDTH, row*Tile.WIDTH, ObjectType.STONE));
                break;
            case 'D':               
                entityManager.addInteractable(new Interactable (ObjectType.DIRT, colum*Tile.WIDTH, row*Tile.WIDTH));
                break;
            case 'L':               
                entityManager.addInteractable(new Interactable (colum*Tile.WIDTH, row*Tile.WIDTH, ObjectType.LARVA));
        }
    }
}

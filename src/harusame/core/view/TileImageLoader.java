package harusame.core.view;

/**
 *
 * @author Admin
 */
public class TileImageLoader {
    
    private static  TileImageLoader  instance = new TileImageLoader ();
    
    private TileImageLoader (){
        
    }
    
    public static TileImageLoader   getInstance () {
        return instance;
    }
}

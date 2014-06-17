/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package harusame.core.model.map;

/**
 *
 * @author Hikari
 */
import java.awt.image.BufferedImage;

public class Tile {
    
    public static float WIDTH = 6;
        
    private BufferedImage image;
    private boolean BLOCKED;
    
    public Tile(BufferedImage image, boolean type) {
        this.image = image;
        BLOCKED = type;
    }
    
    public boolean isBlocked () {
        return BLOCKED;
    }
}

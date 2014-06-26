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
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Tile {
    
    public static int WIDTH = 10;
        
    private Image image;
    private boolean BLOCKED;
    private char symbol;
    
    public Tile(Image image, boolean type, char newSymbol) {
        this.image = image;
        BLOCKED = type;
        symbol = newSymbol;        
    }
    
    public boolean isBlocked () {
        return BLOCKED;
    }
    
    public boolean isChar(char checkSymbol) {
        if(symbol == checkSymbol)
            return true;
        return false;
    }
    
    public Image getTile()
    {
        return image;
    }
}

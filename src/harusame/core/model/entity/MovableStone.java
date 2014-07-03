/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package harusame.core.model.entity;

/**
 *
 * @author Calle
 */
public class MovableStone extends MovableObject{
    
    private final String  image = "Resources/Sprites/Misc/Stone.png";
    
    public MovableStone(int width, int height)
    {
            super(width, height);
    }
    
}

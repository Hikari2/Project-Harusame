package harusame.core.model.animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Hikari
 */
public class Animation {
    
    private ArrayList<BufferedImage>    frames;
    private BufferedImage   currentFrame;
    private int position = 0;
    
    public void addFrame (BufferedImage image){
        frames.add (image);
    }
    
    public BufferedImage nextFrame () {
        if (position >= frames.size())
            position = 0;
        
        return frames.get(position);
    }
}

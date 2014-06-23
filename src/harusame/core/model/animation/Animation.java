package harusame.core.model.animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hikari
 */
public class Animation {
    
    private ArrayList<BufferedImage>    frames = new ArrayList ();
    private BufferedImage   currentFrame;
    private int position = 0;
    
    public void addFrame (BufferedImage image){
        frames.add (image);
    }
    
    public BufferedImage getFrame () {
        if (position == frames.size())
            position = 0;
        
        return frames.get(position);
    }
    
    public void  nextFrame () {
        position++;
    }
    
    public void reset () {
        position = 1;
    }
}

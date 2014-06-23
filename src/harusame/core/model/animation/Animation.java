package harusame.core.model.animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <class>Animation</class> represents a particular animation and contain each 
 * frame of the animation.
 */
public class Animation {
    
    private ArrayList<BufferedImage>    frames = new ArrayList ();
    private BufferedImage   currentFrame;
    private int position = 0;
    
    /**
     * Add a new frame to this Animation
     * @param image A frame to be added
     */
    public void addFrame (BufferedImage image){
        frames.add (image);
    }
    
    /**
     * Get the current frame of the animation and reset the counter if it was 
     * the last frame
     * @return The current frame
     */
    public BufferedImage getFrame () {
        if (position == frames.size())
            position = 0;
        
        return frames.get(position);
    }
    
    /**
     * Increment the counter
     */
    public void  nextFrame () {
        position++;
    }
    
    /**
     * Reset the counter
     */
    public void reset () {
        position = 0;
    }
}

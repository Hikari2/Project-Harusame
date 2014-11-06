package harusame.core.view.Animations;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <class>Animation</class> represents a particular animation and contain each 
 * frame of the animation.
 */
public class Animation {
    
    private ArrayList<BufferedImage>    frames = new ArrayList ();
    private BufferedImage   currentFrame;
    private int count = 0;
    
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
        if (count >= frames.size())
            count = 0;
        
        return frames.get(count);
    }
    
    /**
     * Increment the counter
     */
    public void  nextFrame () {
        count++;
    }
    
    /**
     * Reset the counter
     */
    public void reset () {
        count = 0;
    }
    
    /*
    Get the length of the animation
    */
    public int getLength()
    {
        return frames.size();
    }
    
    /*
    Get the the counter value
    */
    public int getCounter()
    {
        return count;
    }
}

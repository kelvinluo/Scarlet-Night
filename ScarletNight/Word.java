import greenfoot.*;
public class Word extends Cover
{
/**
 * Part of the animation, just pictures
 */
    private int frames = 0;
    private int counter = 20;
    private GreenfootImage gunImage = new GreenfootImage("Cover//Word.png");
    private int width = getImage().getWidth(), length = getImage().getHeight();
    private boolean secondImage = false;
    public void act() 
    {
        if (counter > 1)
        {
            counter--;
        }
        frames+= counter;
        if (secondImage)
        {
            gunImage = new GreenfootImage("Cover//Word2.png");
        }
        else
        {
            gunImage = new GreenfootImage("Cover//Word.png");
        }
        if (frames <200)
        {
            gunImage.scale(width+ (width - (int)((((double)frames)/200.0)*(double)width)),length + (length - (int)((((double)frames)/200.0)*(double)length)));
        } 
        setImage(gunImage);
    }

    public void second()
    {
        secondImage = true;
    }
}

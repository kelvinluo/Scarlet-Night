import greenfoot.*;
/**
 * Part of the animation, just pictures
 */
public class EnterButton extends Cover
{
    private int frames = 0;
    private GreenfootImage buttonImage = new GreenfootImage("Cover//EnterButton1.png");
    public void act() 
    {
        frames++;
        if (frames >= 60)
        {
            frames = 0;
        }
        setImage(buttonImage);
    }
    public void start(boolean started)
    {
        if (!started)
        {
            buttonImage = new GreenfootImage("Cover//EnterButton6.png");
        }
        else
        {
        buttonImage = new GreenfootImage("Cover//EnterButton"+((frames/5) + 1)+".png");
        }
    }
}

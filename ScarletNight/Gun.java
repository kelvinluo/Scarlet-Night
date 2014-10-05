import greenfoot.*;
/**
 * Part of the animation, just pictures
 */
public class Gun extends Cover
{
    private int rotation = 0;
    private int width = getImage().getWidth(), length = getImage().getHeight();
    private GreenfootImage gunImage = new GreenfootImage("Cover//gun.png");
    public void act()  { gunImage = new GreenfootImage("Cover//gun.png");
        if (rotation < 210)
        {
            rotation+= 10;
            setRotation(rotation);
            gunImage.scale(width+ (width - (int)((((double)rotation)/210.0)*(double)width)),length + (length - (int)((((double)rotation)/210.0)*(double)length)));
            setImage(gunImage);
        } 
    }
}

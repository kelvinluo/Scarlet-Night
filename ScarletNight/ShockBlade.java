import greenfoot.*; 
public class ShockBlade extends Weapon
{
    int imageFrames = 0;
    int imageFrameMax = 5;
    boolean direction = true;
    public ShockBlade (boolean getDirection)
    {
        direction = getDirection;
    }

    public void act() 
    {
        GreenfootImage bladeImage = new GreenfootImage("whiteSwordMan//whiteAbilityOne//shockBlade" + (imageFrames/2 + 1) + ".png");
        if (!direction)
        {
            bladeImage.mirrorHorizontally();; 
        }
        setImage(bladeImage);
        if (imageFrames >= imageFrameMax)
        {
            getWorld().removeObject(this);
        }
        imageFrames++;
    }    
}

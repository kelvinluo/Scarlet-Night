import greenfoot.*;
public class HealingRam extends Weapon
{
    GreenfootImage ramImage = new GreenfootImage("whiteSwordMan//whiteAbilityThree//Halo1.png");
    int imageFrames = 0;
    int imageFramesMax = 18;
    public void act() 
    {
       imageFrames++;
       if (imageFrames >= imageFramesMax)
       {
       imageFrames = 0; 
       }
       ramImage = new GreenfootImage("whiteSwordMan//whiteAbilityThree//Halo" + (imageFrames/3 + 1) + ".png");
      ramImage.scale(150,150);
       setImage(ramImage);
    }  
    public void endObject()
    {
        getWorld().removeObject(this);
    }
}

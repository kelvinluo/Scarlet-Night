import greenfoot.*; 
public class GreenShadow extends Weapon
{
    int width = 16, length = 16;
    GreenfootImage greenShadow = new GreenfootImage("GreenShadow.png");
    public void act() 
    {
        setImage(greenShadow);
        greenShadow.scale(30,length--);
        if (length <= 1)
        {
            getWorld().removeObject(this);
        }
    }    
}

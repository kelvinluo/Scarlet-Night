import greenfoot.*;
/**
 * Part of the animation, just pictures
 */
public class CoverSword extends Cover
{
    private GreenfootImage swordImage = new GreenfootImage("Cover//Sword.png");
    private int counter = 100;
    public void act() 
    {
        if (counter > 0)
        {
            counter-=6;
        }
        setImage(swordImage);
        setRotation(130);
        if (getY() <= 300)
        {
            move(10+counter);
        }
    }    
}

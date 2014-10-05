import greenfoot.*; 
public class RedShadow extends Weapon
{
    private int width = 120, length = 40;
    private boolean direction = true;
    private boolean jumpattack = false;
    private GreenfootImage redShadow = new GreenfootImage("Ninja//ninjaAbilityR//redShadow.png");
    public RedShadow(boolean getDirection, boolean ifJumpattack)
    {
        direction = getDirection;
        jumpattack = ifJumpattack;
    }

    public void act() 
    {
        if (!direction)
        {
            redShadow.mirrorHorizontally();
        }
        redShadow = new GreenfootImage("Ninja//ninjaAbilityR//redShadow.png");
        if (direction)
        {
            setRotation(30);
        }
        else
        {
            setRotation(150);
        }
        if (jumpattack)
        {
            setRotation(90);
        }
        setImage(redShadow);
        redShadow.scale(width-=3,length-=3);
        if (length <= 3)
        {
            getWorld().removeObject(this);
        }
    }    
}

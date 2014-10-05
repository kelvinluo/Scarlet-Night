import greenfoot.*;
public class Sword extends Weapon
{
    GreenfootImage swordImage = new GreenfootImage("whiteSwordMan//whiteAbilityTwo//sword.png");
    int groundHeight = 450;
    int speed = 10;
    int counter = 0;
    String character = "P1";
    public Sword(String getCharacter)
    {
        character = getCharacter;
    }

    public void act() 
    {
        setRotation(90);
        setImage(swordImage);
        if (getY() < groundHeight)
        {
            move(speed++);
            if (getY() >= groundHeight)
            {
                swordImage = new GreenfootImage("whiteSwordMan//whiteAbilityTwo//swordDone.png");
                setImage(swordImage);
            }
            if (character == "P1")
            {
                getWorld().addObject(new P1AttackArea(getX(),getY(),9,75,1,0,1.0,0,true),getX(),getY());
            }
            else
            {
                getWorld().addObject(new P2AttackArea(getX(),getY(),9,75,1,0,1.0,0,true),getX(),getY());
            }
        }
        else
        {
            if (counter == 0)
            {
                Greenfoot.playSound("Sound//White//WhiteAbilityR.mp3");
            }
            swordImage = new GreenfootImage("whiteSwordMan//whiteAbilityTwo//swordDone.png");
            counter++;
        }
        if (counter >= 100)
        {
            getWorld().removeObject(this);
        }
    }
}

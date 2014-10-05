import greenfoot.*;
import java.util.Random;
public class Kuwu extends Weapon
{
    Random ranGen = new Random();
    GreenfootImage kuwuImage = new GreenfootImage ("Ninja//Kuwu//KuWu.png");
    int rotation = 0;
    int counter = 100;
    boolean direction = false;
    boolean over = false;
    String character = "P1";
    public Kuwu(boolean getDirection,int getRotation, String getCharacter)
    {
        direction = getDirection;
        rotation = getRotation;
        character = getCharacter;
    }

    public void act() 
    {
        setImage(kuwuImage);
        if (getY() >= 470 && !over)
        {
            setLocation(getX(),470);
            setRotation(0);
            kuwuImage = new GreenfootImage ("Ninja//Kuwu//KuWuDone"+ (((rotation-10)/20) + 1) +".png");
            if (!direction)
            {
                kuwuImage.mirrorHorizontally();
            }
            over = true;
            Greenfoot.playSound("Sound//Ninja//ninjaAbilityH.mp3");
        }
        else if (!over)
        {
            move(30);
            if (direction)
            {
                this.setRotation(rotation);
            }
            else
            {
                this.setRotation((90 - rotation) + 90);
                kuwuImage.mirrorHorizontally();
            }
            if (character.equals("P2"))
            {
                getWorld().addObject(new P2AttackArea(getX(),getY(),3,3,2,0,4.0,5,direction),getX(),getY());
            }
            else
            {
                getWorld().addObject(new P1AttackArea(getX(),getY(),3,3,2,0,4.0,5,direction),getX(),getY());
            }
            kuwuImage = new GreenfootImage ("Ninja//Kuwu//KuWu.png");
        }
        if (over && counter > 0)
        {
            counter--;
        }
        else if (over)
        {
            kuwuImage.clear();  
            getWorld().removeObject(this);
        }
    }
}
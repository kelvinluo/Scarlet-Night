import greenfoot.*;
public class WindBlade extends Weapon
{
    private GreenfootImage bladeImage = new GreenfootImage("whiteSwordMan//whiteAbilityUlt//windBlade1.png");
    private int imageFrames = 0;
    private String character;
    public WindBlade (String getCharacter)
    {
        character = getCharacter;
    }

    public void act() 
    {
        dealtDamage(0,0,200,100,1,0,1.0,0);
        setImage(bladeImage);
        imageFrames++;
        if (imageFrames >= 45)
        {
            getWorld().removeObject(this);
        }
        else
        {
            bladeImage = new GreenfootImage("whiteSwordMan//whiteAbilityUlt//windBlade"+((imageFrames/5)+1)+".png");
        }
    }

    private void dealtDamage(int getX, int getY, int getWidth, int getLength, int getTime, int getDelay,double getDamage, int applyForce)
    {
        if (character.equals("P1"))
        {
            getWorld().addObject(new P1AttackArea(getX() + getX, getY() + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,true),getX() + getX, getY() + getY);
        }
        else  if (character.equals("P2"))
        {
            getWorld().addObject(new P2AttackArea(getX() - getX, getY() + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,true),getX() - getX, getY() + getY);
        }
    }
}


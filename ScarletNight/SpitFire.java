import greenfoot.*;
public class SpitFire extends Weapon
{
    private int imageFrames = 4;
    private boolean direction = true;
    private boolean hitGround = false;
    private GreenfootImage fireImage = new GreenfootImage("Ringer//ringerAbilityH//bomb1.png");
    private String character;
    public SpitFire(boolean getDirection, String getCharacter)
    {
        direction = getDirection;
        character = getCharacter;
    }

    public void act() 
    {
        setImage(fireImage);
        imageChange();
    }    

    public void imageChange()
    {
        if (getY() > 470 && !hitGround)
        {
            setRotation(0);
            hitGround = true;
            fireImage = new GreenfootImage("Ringer//ringerAbilityH//bomb2.png");
            Greenfoot.playSound("Sound//Ringer//ringerAbilityH.mp3");
        }
        else if (!hitGround)
        {
            move(5);
            fireImage = new GreenfootImage("Ringer//ringerAbilityH//bomb1.png");
            if (direction)
            {
                setRotation(25);
            }
            else
            {
                setRotation(155);
            }
            dealtDamage(0,0,20,20,1,0,0.5,0);
        }
        if (hitGround && imageFrames < 16)
        {
            fireImage = new GreenfootImage("Ringer//ringerAbilityH//bomb"+((imageFrames/4)+1)+".png");
            imageFrames++;
            dealtDamage(0,0,20,20,1,0,0.5,0);
        }
        else if (hitGround)
        {
            getWorld().removeObject(this);
        }
    }
            private void dealtDamage(int getX, int getY, int getWidth, int getLength, int getTime, int getDelay,double getDamage, int applyForce)
    {
        if (character.equals("P1"))
        {
            if (direction)
            {
                getWorld().addObject(new P1AttackArea(getX() + getX, getY() + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,direction),getX() + getX, getY() + getY);
            } 
            else
            {   
                getWorld().addObject(new P1AttackArea(getX() - getX, getY() + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,direction),getX() - getX, getY() + getY);
            }
        }
        else  if (character.equals("P2"))
        {
            if (direction)
            {
                getWorld().addObject(new P2AttackArea(getX() + getX, getY() + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,direction),getX() + getX, getY() + getY);
            } 
            else
            {   
                getWorld().addObject(new P2AttackArea(getX() - getX, getY() + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,direction),getX() - getX, getY() + getY);
            }
        }
    }
}

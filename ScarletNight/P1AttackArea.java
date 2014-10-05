import greenfoot.*;

public class P1AttackArea extends SystemCore
{
/**
 * This is an important class which creates an area where the player will be damage if he touches it.
 * This is used for creating the collision detection of between many different kinds of skills.
 */
    private GreenfootImage theImage = new GreenfootImage("ballsI.png");
    int time,x,y,width,length,delay,applyForce;
    boolean forceDirection = true;;
    double damage = 0.1;
    public P1AttackArea(int xCoordinate, int yCoordinate, int getWidth, int getLength, int timeLast, int getDelay,double getDamage,int getForce,boolean getForceDirection)
    {
        this.time = timeLast;
        this.x = xCoordinate;
        this.y = yCoordinate;
        this.width = getWidth;
        this.length = getLength;
        this.damage = getDamage;
        applyForce = getForce;
        forceDirection = getForceDirection;
        theImage.scale(width,length);
        setImage(theImage);
    }

    public void act()
    {
        if (delay == 0)
        {
            if (time != 0)
            {
                time--;
                theImage.scale(width,length);
                setImage(theImage);
                setLocation(x,y);
            }
            else
            {
                getWorld().removeObject(this);
            }
        }
        else
        {
            delay--;
        }
    }

    public double damageDealt()
    {
        return damage;
    }

    public double dealForce()
    {
        return applyForce;
    }

    public boolean returnForceDirection()
    {
        return forceDirection;
    }
}
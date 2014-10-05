import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class FireBall here.
 * 
 * @author Joshua Tsai
 * @version March, 2013
 */
public class FinalSpark extends Weapon
{
    // Greenfoot Image
    private GreenfootImage spark;

    // Player
    private String player;

    // Movement
    private int speed;
    private int x;
    private int y;
    private boolean direction;

    // Frames
    private int sparkFrame = 10;
    private int maxSparkFrame = 29;

    public FinalSpark(boolean mageDirection, String p, int mageX, int mageY)
    {
        x = mageX + 600;
        y = mageY + 50;
        direction = mageDirection;
        if (!mageDirection)
        {
            setRotation(180);
            x = mageX - 600;
        }
        player = p;
    }

    public void act() 
    {
        dealtDamage();
        sparkAnimation();
    }   

    public void sparkAnimation()
    {
        // Animation of the ball
        if (sparkFrame > maxSparkFrame)
        {
            Mage.abilityROver();
            getWorld().removeObject(this);
        }
        else if (!this.direction)
        {
            spark = new GreenfootImage ("Mage//MageAbilityR//railgun" + (sparkFrame/10) + ".png");
            dealtDamage();
        }
        else if (this.direction)
        {
            spark = new GreenfootImage ("Mage//MageAbilityR//railgun" + (sparkFrame/10) + ".png");
            spark.mirrorHorizontally();
            dealtDamage();
        }

        sparkFrame++;
        setImage(spark);
    }

    private void dealtDamage()
    {
        if (player.equals("P1"))
        {
            if (direction)
            {
                getWorld().addObject(new P1AttackArea(x,y,1000,50,1,0,2.0,2,direction),x, y);
            } 
            else
            {   
                getWorld().addObject(new P1AttackArea(x,y,1000,50,1,0,2.0,2,direction),x, y);
            }
        }
        else  if (player.equals("P2"))
        {
            if (direction)
            {
                getWorld().addObject(new P2AttackArea(x,y,1000,50,1,0,2.0,2,direction),x, y);
            } 
            else
            {   
                getWorld().addObject(new P2AttackArea(x,y,1000,50,1,0,2.0,2,direction),x, y);
            }
        }
    }
}


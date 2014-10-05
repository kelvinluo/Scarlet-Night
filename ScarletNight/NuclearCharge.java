import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class FireBall here.
 * 
 * @author Joshua Tsai
 * @version March, 2013
 */
public class NuclearCharge extends Weapon
{
    // Greenfoot Image
    private GreenfootImage charge;

    // Player
    private String player;

    // Movement
    private int speed;
    private int x;
    private int y;
    private boolean direction;
    private int groundHeight = 470;

    // Frames
    private int chargeFrames = 2;
    private int maxChargeFrames = 20;

    public NuclearCharge(boolean mageDirection, String p, int mageX, int mageY)
    {
        x = mageX;
        y = mageY;
        setRotation(45);
        direction = mageDirection;
        if (!mageDirection)
        {
            setRotation(135);
        }
        player = p;
    }

    public void act() 
    {
        if (chargeFrames > 9)
        {
            move(15);
        }
        chargeAnimation();
    }   

    public void chargeAnimation()
    {
        // Animation of the ball
        if (this.getY() > groundHeight)
        {
            Mage.abilityHOver();
            getWorld().removeObject(this);
        }
        else if (!this.direction)
        {
            charge = new GreenfootImage ("Mage//MageAbilityH//singularity" + (chargeFrames/2) + ".png");
            dealtDamage();
        }
        else if (this.direction)
        {
            charge = new GreenfootImage ("Mage//MageAbilityH//singularity" + (chargeFrames/2) + ".png");
            charge.mirrorHorizontally();
            dealtDamage();
        }

        chargeFrames++;

        if (chargeFrames > maxChargeFrames)
        {
            chargeFrames = 16;
        }
        setImage(charge);
    }

    private void dealtDamage()
    {
        if (player.equals("P1"))
        {
            if (direction)
            {
                getWorld().addObject(new P1AttackArea(this.getX(), this.getY(), 100, 100 , 1, 0, 2.0,5,direction), x, y);
            } 
            else
            {   
                getWorld().addObject(new P1AttackArea(this.getX(), this.getY(), 100, 100 , 1, 0, 2.0,5,direction), x, y);
            }
        }
        else  if (player.equals("P2"))
        {
            if (direction)
            {
                getWorld().addObject(new P2AttackArea(this.getX(), this.getY(), 100, 100 , 1, 0, 2.0,5,direction), x, y);
            } 
            else
            {   
                getWorld().addObject(new P2AttackArea(this.getX(), this.getY(), 100, 100 , 1, 0, 2.0,5,direction), x, y);
            }
        }
    }
}
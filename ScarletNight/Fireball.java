import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class FireBall here.
 * 
 * @author Joshua Tsai
 * @version March, 2013
 */
public class Fireball extends Weapon
{
    // Greenfoot Image
    private GreenfootImage fireBall;

    // Movement
    private int speed;
    private int x;
    private int y;
    private boolean direction;
    private String player;

    // Frames
    private int ballFrames = 1;
    private int maxBallFrames = 19;

    public Fireball(boolean mageDirection, String p, int mageX, int mageY)
    {
        x = mageX;
        y = mageY;
        direction = mageDirection;
        player = p;

        if (!direction)
        {
            setRotation(180);
        }
    }

    public void act() 
    {
        ballAnimation();
        move(10);
        if (this.getX() <= 1 || this.getX() >= 999)
        {
            getWorld().removeObject(this);
            Mage.attackOver();
        }
    }   

    public void ballAnimation()
    {
        // Animation of the ball
        if (ballFrames > maxBallFrames)
        {
            ballFrames = 1;
        }
        else if (!this.direction)
        {
            fireBall = new GreenfootImage ("Mage//FireBall//fireBall" + (ballFrames/4 + 1) + ".png");
            dealtDamage();
        }
        else if (this.direction)
        {
            fireBall = new GreenfootImage ("Mage//FireBall//fireBall" + (ballFrames/4 + 1) + ".png");
            dealtDamage();
        }
        ballFrames ++;
        setImage(fireBall);
    }    

    private void dealtDamage()
    {
        if (player.equals("P1"))
        {
            if (direction)
            {
                getWorld().addObject(new P1AttackArea(this.getX() + 50, this.getY(), 10, 20 , 1, 0, 1.0,5,direction), this.getX() + 50, this.getY());
            } 
            else
            {   
                getWorld().addObject(new P1AttackArea(this.getX() - 50, this.getY(), 10, 20 , 1, 0, 1.0,5,direction), this.getX() - 50, this.getY());
            }
        }
        else if (player.equals("P2"))
        {
            if (direction)
            {
                getWorld().addObject(new P2AttackArea(this.getX() + 50, this.getY(), 10, 20 , 1, 0, 1.0,5,direction), this.getX() + 50, this.getY());
            }
            else
            {   
                getWorld().addObject(new P2AttackArea(this.getX() - 50, this.getY(), 10, 20 , 1, 0, 1.0,5,direction), this.getX() - 50, this.getY());
            }
        }
    }
}
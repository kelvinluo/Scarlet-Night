import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
public class Bullet extends Weapon
{
    // Greenfoot Image
    private GreenfootImage bullet;   
    //     Bullet bullet2;
    private int x, y, x1, y1;
    private boolean direction;
    private boolean down;
    private int speed = 50 + new Random().nextInt(100);
    private String character;
    private boolean set = false;

    public Bullet(boolean getDirection, String getCharacter)
    {
        x = 300;
        y = 10;
        character = getCharacter;
        this.direction = getDirection;
        bullet = new GreenfootImage ("Ranged//bullet.png");
        if (!this.direction)
        {
            bullet.mirrorHorizontally();
        }
        this.setImage(bullet);
    }

    public Bullet(String getCharacter)
    {
        Random r = new Random();
        this.down = true;
        character = getCharacter;
        bullet = new GreenfootImage ("Ranged//bullet.png");
        int i = r.nextInt(11);
        setRotation(85 + i);
        this.setImage(bullet);
    }

    public void act()
    {
        if (this.down == true)
        {
            move(20);
            dealtDamage(this.getX(), this.getY(), 5, 300, 1, 0, 0.6,0);
        }
        else
        {
            if (this.direction == true)
            {
                setLocation(this.getX() + speed,getY());
                dealtDamage (this.getX(), this.getY(),700,5,1,0,0.5,1);
            }
            else if (this.direction == false)
            {
                setLocation(this.getX() - speed,getY());
                dealtDamage (this.getX(), this.getY(),700,5,1,0,0.5,1);
            }
        }

        if (getX() < 0 || getX() > 1000)
        {
            getWorld().removeObject(this);
        }
        else if (getY() > 400)
        {
            getWorld().removeObject(this);
        }
    }

    private void dealtDamage(int getX, int getY, int getWidth, int getLength, int getTime, int getDelay, double getDamage, int applyForce)
    {
        if (character.equals("P1"))
        {
            if (direction)
            {
                getWorld().addObject(new P1AttackArea(getX + x, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,direction),x + getX, y + getY);
            } 
            else
            {   
                getWorld().addObject(new P1AttackArea(getX - x, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,direction), this.getX() - x, y + getY);
            }
        }
        else  if (character.equals("P2"))
        {
            if (direction)
            {
                getWorld().addObject(new P2AttackArea(getX + x, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,direction), x + getX, y + getY);
            } 
            else
            {   
                getWorld().addObject(new P2AttackArea(getX - x, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,direction), this.getX() - x, y + getY);
            }
        }
    }
}
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Grenade extends Weapon
{
    private GreenfootImage grenade = new GreenfootImage ("Ranged//gunnerAbilityT//genade1.png");
    private String character;
    private boolean direction;
    private int x;
    private int y;
    private int distance;
    private boolean moveUp = false;
    private int explosiveFrame = 0;
    public Grenade(boolean movementDirection, String player, int bondX, int bondY)
    {
        character = player;
        this.direction = movementDirection;
        if (movementDirection)
        {
            this.setRotation(330);
        }
        else
        {
            this.setRotation(210);
        }
        this.x = bondX;
        this.y = bondY;
        distance = 470 - this.y;
    }

    public void act() 
    {
        setImage(grenade);
        if (distance <= 0)
        {
            if (distance == 0)
            {
                dealtDamage(this.x, this.y, 200, 200, 1, 0, 10.0, 200);
                Greenfoot.playSound("Sound//Bond//Grenade.mp3");
            }
            y = 400;
            setRotation(0);
            explosiveFrame++;
            this.setLocation(this.x, this.y);
            grenade = new GreenfootImage ("Ranged//explosive//explosive00"+explosiveFrame+".png");
            if (explosiveFrame == 26)
            {
                getWorld().removeObject(this);
            }
        }
        else
        {
            this.setLocation(this.x, this.y);
            if (this.direction == true)
            {
                this.x = this.x + 7;
            }
            else
            {
                this.x = this.x - 7;
            }

            if (this.y >= 470)
            {
                distance = distance/2;
            }

            if (this.y <= 470 - distance)
            {
                moveUp = false;
            }
            else if (this.y >= 470)
            {
                moveUp = true;
            }

            if (moveUp)
            {
                this.y = this.y - 3;
            }
            else
            {
                this.y = this.y + 3;
            }
        }

    }
    private void dealtDamage(int getX, int getY, int getWidth, int getLength, int getTime, int getDelay, double getDamage, int applyForce)
    {
        if (character.equals("P1"))
        {
            if (this.direction)
            {
                getWorld().addObject(new P1AttackArea(x, y, getWidth, getLength, getTime, getDelay, getDamage, applyForce, direction),x, y);
            } 
            else
            {   
                getWorld().addObject(new P1AttackArea(x, y, getWidth, getLength, getTime, getDelay, getDamage, applyForce, direction),x, y);
            }
        }
        else  if (character.equals("P2"))
        {
            if (this.direction)
            {
                getWorld().addObject(new P2AttackArea(x, y, getWidth, getLength, getTime, getDelay, getDamage, applyForce, direction),x, y);
            } 
            else
            {   
                getWorld().addObject(new P2AttackArea(x, y, getWidth, getLength, getTime, getDelay, getDamage, applyForce, direction),x, y);
            }
        }
    }
}

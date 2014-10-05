import greenfoot.*;
public class Ring extends Weapon
{
    GreenfootImage ringImage = new GreenfootImage("Ringer//ring//ring1.png");
    boolean direction = true;
    boolean hit = false;
    int imageFrames = 0;
    int originalX, originalY;
    int distance = 0;
    int speed;
    String character = "P1";
    public Ring(int getX,int getY,boolean getDirection, int getDistance, String getCharacter)
    {
        originalX = getX;
        originalY = getY;
        direction = getDirection;
        distance = getDistance;
        speed = distance/13;
        character = getCharacter;
    }

    public void act() 
    {
        imageFrames++;
        if (imageFrames >= 16)
        {
            imageFrames = 0;
        }

        if (!hit && speed > 4)
        {
            speed -= distance/350;
        }
        else if (hit && speed < 30)
        {
            speed += distance/350;
        }
        ringImage = new GreenfootImage("Ringer//ring//ring"+((imageFrames/4)+1)+".png");
        setImage(ringImage);
        getWorld().addObject(new GreenShadow(), getX(),getY());
        if (direction && !hit)
        {
            if ((getX()+ speed) > (originalX+distance))
            {
                hit = true;
            }
            move(speed);
            dealtDamage(0,0,30,10,1,0,0.5,5);
        }
        else if (!hit)
        {
            if ((getX() - speed) < (originalX-distance))
            {
                hit = true;
            }
            move(-speed);
            dealtDamage(0,0,30,10,1,0,0.5,5);
        }
        
    }

    public boolean returned()
    {
        return hit;
    }
    private boolean oppsiteDirection;
    public void follow(int getX, int getY)
    {
        if (hit)
        {
            dealtDamage(0,0,30,10,1,0,0.5,-5);
            if (getX > getX())
            {
                setLocation(getX()+speed,getY());
            }
            else if (getX < getX())
            {
                setLocation(getX()-speed,getY());
            }
            if (getY > getY())
            {
                setLocation(getX(),getY()+2);
            }
            else if (getY < getY())
            {
                setLocation(getX(),getY()-2);
            }
        }
    }

    public void remove()
    {
        getWorld().removeObject(this);
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

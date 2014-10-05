import greenfoot.*;
public class Meteor extends Weapon
{
    private GreenfootImage meteor = new GreenfootImage("Mage//MageAbilityUlt//Meteor.png");
    private String character;
    private boolean direction;

    public Meteor(String player, boolean mageDirection)
    {
        character = player;
        this.direction = mageDirection;
        if (this.direction == true)
        {
            this.setRotation(60);
        }
        else
        {
            this.setRotation(120);
        }
        meteor.scale(500,200);
        setImage(meteor);
        Greenfoot.playSound("Sound//Mage//mageUlt1.mp3");
    }

    public void act() 
    {
        if (this.getY() < 250)
        {
            this.move(5);
        }
        else
        {
            dealtDamage();
            Greenfoot.playSound("Sound//Mage//mageUlt2.mp3");
            getWorld().addObject(new FlashShot("Red",true),500,300);
            getWorld().removeObject(this);
        }    
    }

    private void dealtDamage()
    {
        if (character.equals("P1"))
        {
            if (direction)
            {
                getWorld().addObject(new P1AttackArea(this.getX() + 115, this.getY() + 230, 500,500 , 1, 0, 20.0 , 5, direction), this.getX() + 115, this.getY() + 230);
            } 
            else
            {   
                getWorld().addObject(new P1AttackArea(this.getX() - 115, this.getY() + 230, 500, 500 , 1, 0,20.0 , 5, direction), this.getX() - 115, this.getY() + 230);
            }
        }
        else  if (character.equals("P2"))
        {
            if (direction)
            {
                getWorld().addObject(new P2AttackArea(this.getX() + 115, this.getY() + 230,500,500 , 1, 0, 20.0 , 5, direction), this.getX() + 115, this.getY() + 230);
            } 
            else
            {   
                getWorld().addObject(new P2AttackArea(this.getX() - 115, this.getY() + 230, 500, 500 , 1, 0, 20.0 , 5, direction), this.getX() - 115, this.getY() + 230);
            }
        }
    }
}
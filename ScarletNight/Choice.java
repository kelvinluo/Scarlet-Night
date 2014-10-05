import greenfoot.*;
public class Choice extends Cover
{
    /**
     * 
     * This method used to let the player to choose rather to stay in the game or not after the match
     * 
     */
    private boolean ready = false;
    private boolean choice = true;
    public Choice()
    {
        this.setImage(new GreenfootImage("BattleItem//rematch1.png"));
    }

    public void act() 
    {
        control();
    }

    public void control()
    {
        if (Greenfoot.isKeyDown("right"))
        {
            choice = false;
            setImage(new GreenfootImage("BattleItem//rematch2.png"));
        }
        else if (Greenfoot.isKeyDown("left"))
        {
            choice = true;
            setImage(new GreenfootImage("BattleItem//rematch1.png"));
        }
        if (Greenfoot.isKeyDown("enter"))
        {
            ready = true;
        }
    }

    public boolean go()
    {
        return ready;
    }

    public boolean endGame()
    {
        return choice;
    }
}

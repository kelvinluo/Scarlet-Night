import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class End here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class End extends World
{
    private Choice choice = new Choice();
    public End(boolean start,String winner)
    {    
        super(1000, 600, 1);
        addObject(new Winner(winner),500,100);
        addObject(choice,500,500);
    }
    public void act()
    {
        if (choice.go())
        {
            if (choice.endGame() == true)
            {
                 Greenfoot.setWorld(new CharacterSelection(true));
            }
            else if (choice.endGame() ==false)
            {
               System.exit(0);
            }
        }
    }
}

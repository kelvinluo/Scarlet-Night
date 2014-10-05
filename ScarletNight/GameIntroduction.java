import greenfoot.*;
/**
 * This class is used to display the instructions
 */
public class GameIntroduction extends World
{
    private IntroSelectionButton introSelection = new IntroSelectionButton();
    private boolean getIntroduction = false;
    private boolean start = false;

    public GameIntroduction(boolean getStarted)
    {
        super(1000, 600, 1); 
        setBackground(new GreenfootImage("Introduction//IntroductionBackground.png"));
        addObject(introSelection,500,300);
    }

    public void act()
    {
        if (Greenfoot.isKeyDown("enter") && !start)
        {
            start = true;
            getIntroduction = introSelection.returnSelection();
            removeObject(introSelection);
        }
        if (start)
        {
            if (getIntroduction)
            {
               Greenfoot.setWorld(new Instructions(true));
            }
            else
            {
                Greenfoot.setWorld(new CharacterSelection(true));
            }
        }
    }
}
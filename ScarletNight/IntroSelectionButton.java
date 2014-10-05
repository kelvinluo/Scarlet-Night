import greenfoot.*;
public class IntroSelectionButton extends IntroductionItems
{
    /**
     * A user interface where allows the player to choose rather to watch the intro or not
     */
    private boolean selection = true;
    public void act() 
    {
        setImage(new GreenfootImage("Introduction//SelectButton"+selection+".png"));
        selectionScreen();
    }

    public void selectionScreen()
    {
        if (Greenfoot.isKeyDown("left"))
        {
            selection = true;
        }
        else if (Greenfoot.isKeyDown("right"))
        {
            selection = false;
        }
    }

    public boolean returnSelection()
    {
        return selection;
    }
}

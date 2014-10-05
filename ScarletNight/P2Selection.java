import greenfoot.*;
public class P2Selection extends CharacterSelectionItems
{
    /**
     * This class is used by the player to choose their characters during the character selection
     */
    private int x = 555,y = 505;
    private int selection = 5;
    private int p1Choice = 5;
    private boolean keyDown = false;
    private boolean choiceSumbission = false;
    private GreenfootImage selectionImage = new GreenfootImage("CharacterSelection//P2.png");
    public void act()
    {
        setLocation(x,y);
    }

    public void userInterface (int getp1Choice,boolean p1submission)
    {
        setImage(selectionImage);
        if (Greenfoot.isKeyDown("up") && !keyDown)
        {        
            keyDown = true;
            if (selection > 1)
            {
                selection--;
            }
        }
        else if (Greenfoot.isKeyDown("down") && !keyDown)
        {
            keyDown = true;
            if (selection < 5)
            {
                selection++;
            }
        }
        else if (!Greenfoot.isKeyDown("up") && !Greenfoot.isKeyDown("down"))
        {
            keyDown = false;
        }
        p1Choice = getp1Choice;
        y = selection*102 - 5;
        if (p1Choice == selection && !p1submission)
        {
            selectionImage = new GreenfootImage("CharacterSelection//P2Combo.png");
        }
        else
        {
            selectionImage = new GreenfootImage("CharacterSelection//P2.png");
        }
        
        if (selection == getp1Choice)
        {
            if (!p1submission && Greenfoot.isKeyDown("/") && !keyDown)
            {
                characterSound();
                choiceSumbission = true;
                x-= 4;
                setImage(new GreenfootImage("CharacterSelection//P2Choosen.png"));
            }
        }
        else if (Greenfoot.isKeyDown("/") && !(selection == getp1Choice) && !keyDown)
        {
            characterSound();
            choiceSumbission = true;
            x-= 4;
            setImage(new GreenfootImage("CharacterSelection//P2Choosen.png"));
        }
    }
    public void characterSound()
    {
        if (selection == 1)
        {
            Greenfoot.playSound("Sound//White//whiteChosen.mp3");
        }
        else if (selection == 2)
        {
            Greenfoot.playSound("Sound//Bond//bondChosen.mp3");
        }
                else if (selection == 3)
        {
        Greenfoot.playSound("Sound//Ninja//ninjaChosen.mp3");
        }
                else if (selection == 4)
        {
        
        }
                else if (selection == 5)
        {
            Greenfoot.playSound("Sound//Ringer//ringerChosen.mp3");
        }
    }
    public int returnChoice()
    {
        return selection;
    }

    public boolean returnSubmission()
    {
        return choiceSumbission;
    }
}

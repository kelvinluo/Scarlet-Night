import greenfoot.*;
public class P1Selection extends CharacterSelectionItems
{
    /**
     * This class is used by the player to choose their characters during the character selection
     */
    private int x = 555,y = 100;
    private int selection = 1;
    private int p2Choice = 5;
    private boolean keyDown = false;
    private boolean choiceSumbission = false;
    private GreenfootImage selectionImage = new GreenfootImage("CharacterSelection//P1.png");
    public void act()
    {
        setLocation(x,y);
    }

    public void userInterface (int getP2Choice,boolean p2submission)
    {
        setImage(selectionImage);
        if (Greenfoot.isKeyDown("w") && !keyDown)
        {        
            keyDown = true;
            if (selection > 1)
            {
                selection--;
            }
        }
        else if (Greenfoot.isKeyDown("s") && !keyDown)
        {
            keyDown = true;
            if (selection < 5)
            {
                selection++;
            }
        }
        else if (!Greenfoot.isKeyDown("w") && !Greenfoot.isKeyDown("s"))
        {
            keyDown = false;
        }
        p2Choice = getP2Choice;
        y = selection*102 - 5;
        if (p2Choice == selection && !p2submission)
        {
            selectionImage = new GreenfootImage("CharacterSelection//P1Combo.png");
        }
        else
        {
            selectionImage = new GreenfootImage("CharacterSelection//P1.png");
        }
        
        if (selection == getP2Choice)
        {
            if (!p2submission && Greenfoot.isKeyDown("f") && !keyDown)
            {
                characterSound();
                choiceSumbission = true;
                x-= 4;
                setImage(new GreenfootImage("CharacterSelection//P1Choosen.png"));
            }
        }
        else if (Greenfoot.isKeyDown("f") && !(selection == getP2Choice) && !keyDown)
        {
            characterSound();
            choiceSumbission = true;
            x-= 4;
            setImage(new GreenfootImage("CharacterSelection//P1Choosen.png"));
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
 import greenfoot.*;
 /**
  * 
  * This class let the users to choose their charater to play
  * 
  */
public class CharacterSelection extends World
{
    private String[] p1CharacterChoices = new String[6];
    private String[] p2CharacterChoices = new String[6];
    private P1Selection p1Selection = new P1Selection(); 
    private P2Selection p2Selection = new P2Selection(); 
    private int p1Character = 1;
    private boolean p1submission = false;
    private int p2Character = 5;
    private boolean p2submission = false;
    private int endCountDown = 0;
     private GreenfootSound backgroundMusic = new GreenfootSound("Sound//System//characterSelection.mp3");

    public CharacterSelection(boolean getStarted)
    {    
        super(1000, 600, 1,false);
        p1CharacterChoices[1] = "White";
        p1CharacterChoices[2] = "Bond";
        p1CharacterChoices[3] = "Ninja";
        p1CharacterChoices[4] = "Mage";
        p1CharacterChoices[5] = "Ringer";
        for (int counter = 1; counter < 6; counter++)
        {
            addObject(new CharacterIcons(p1CharacterChoices[counter]),552,counter*102-5);
        }
        p2CharacterChoices[1] = "White";
        p2CharacterChoices[2] = "Bond";
        p2CharacterChoices[3] = "Ninja";
        p2CharacterChoices[4] = "Mage";
        p2CharacterChoices[5] = "Ringer";
        setBackground(new GreenfootImage("CharacterSelection//CharacterSelectionBackground.png"));
        addObject(p1Selection,500,100);
        addObject(p2Selection,500,550);
        backgroundMusic.playLoop();
    }

    public void act()
    {
        p1submission = p1Selection.returnSubmission();
        p2submission = p2Selection.returnSubmission();
        
        if (!p1Selection.returnSubmission())
        {
            p1Selection.userInterface(p2Selection.returnChoice(),p2submission);
            p1Character = p1Selection.returnChoice();
        }
        
        if (!p2Selection.returnSubmission())
        {
            p2Selection.userInterface(p1Selection.returnChoice(),p1submission);
            p2Character = p2Selection.returnChoice();
        }
        submission();
    }
    /**
     * This method will receive user choices from the decision from the actor p1Selection or p2Selection,
     */
    public void submission()
    {
        if (p1submission && p2submission)
        {
            endCountDown++;
            if (endCountDown == 1)
            {
                addObject(new FlashScreen("Black",false),500,300);
            }
        }
        if (endCountDown == 200)
        {
            backgroundMusic.stop();
            Greenfoot.setWorld(new Campaigh(true,p1CharacterChoices[p1Character],p2CharacterChoices[p2Character]));
        }
    }
}

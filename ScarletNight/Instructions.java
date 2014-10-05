import greenfoot.*;
/**
 * 
 * This world will ask you if you need instruction
 * 
 */
public class Instructions extends World
{
    private GreenfootImage instructionImage = new GreenfootImage("Instructions//1.png");
    private int selection = 1;
    private boolean isKeyDowned = false;
    private GreenfootSound backgroundMusic = new GreenfootSound("Sound//System//instruction.mp3");

    public Instructions(boolean getStarted)
    {
        super(1000, 600, 1,false);
        setBackground(instructionImage);
        backgroundMusic.playLoop();
    }
    /*
     * Detect Decision from the user and lauches the decision
     */
    public void act()
    {
        setBackground(instructionImage);
        if (Greenfoot.isKeyDown("right") && selection < 3 && !isKeyDowned)
        {
            selection++;
            instructionImage = new GreenfootImage("Instructions//"+selection+".png");
            isKeyDowned = true;
        }
        else if (Greenfoot.isKeyDown("left") && selection > 1 && !isKeyDowned)
        {
            selection--;
            instructionImage = new GreenfootImage("Instructions//"+selection+".png");
            isKeyDowned = true;
        }
        else if (Greenfoot.isKeyDown("right") && !isKeyDowned)
        {
            backgroundMusic.stop();
            Greenfoot.setWorld(new CharacterSelection(true));
        }
        else if (!Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("left") && isKeyDowned)
        {
            isKeyDowned = false;
        }
    }
}

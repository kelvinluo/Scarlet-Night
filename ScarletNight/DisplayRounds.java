import greenfoot.*;
public class DisplayRounds extends SystemCore
{
    private GreenfootImage roundImage = new GreenfootImage("BattleItem//round1.png");
    private int imageFrames = 0;
    public DisplayRounds (int round)
    {
         roundImage = new GreenfootImage("BattleItem//round"+round+".png");
    }
    public void act() 
    {
        imageFrames++;
        setImage(roundImage);
        if (imageFrames == 100)
        {
            roundImage = new GreenfootImage("BattleItem//fight.png");
        }
        else if (imageFrames == 150)
        {
            getWorld().removeObject(this);
        }
    }    
}

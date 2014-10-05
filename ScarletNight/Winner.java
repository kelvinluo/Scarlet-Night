import greenfoot.*;
public class Winner extends Cover
{
    /**
     * Images which display the winner
     */
    String winnerName;
    public Winner(String getWinner)
    {
        winnerName = getWinner;
    }

    public void act() 
    {
        if (winnerName.equals("White"))
        {
            setImage(new GreenfootImage("BattleItem//FuShuWon.png"));
        }
        else if (winnerName.equals("Bond"))
        {
            setImage(new GreenfootImage("BattleItem//BondWon.png"));
        }
        else if (winnerName.equals("Mage"))
        {
            setImage(new GreenfootImage("BattleItem//GajiwalaWon.png"));
        }
        else if (winnerName.equals("Ninja"))
        {
            setImage(new GreenfootImage("BattleItem//NinjaWon.png"));
        }
        else if (winnerName.equals("Ringer"))
        {
            setImage(new GreenfootImage("BattleItem//MarkGreenWon.png"));
        }
    }    
}

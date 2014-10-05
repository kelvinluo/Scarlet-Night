import greenfoot.*;
import java.awt.Color;
public class FlashScreen extends SystemCore
{
    private int transparency = 0;
    private boolean style = true;
    private GreenfootImage flashScreen = new GreenfootImage(1000,600);
    public FlashScreen(String getColour,boolean fade)
    {
        style = fade;
        if (fade)
        {
            transparency = 255;
        }
        if (getColour.equals("Black"))
        {
            flashScreen.setColor(Color.BLACK);
        }
        else if (getColour.equals("White"))
        {
            flashScreen.setColor(Color.WHITE);
        }
        else if (getColour.equals("Red"))
        {
            flashScreen.setColor(Color.RED);
        }
        flashScreen.fill();
    }

    public void act() 
    {
        setImage(flashScreen);
        if (style)
        {
            if (transparency > 0)
            {
                getImage().setTransparency(transparency--);
            }
            else
            {
                getWorld().removeObject(this);
            }
        }
        else
        {
            transparency++;
            if (transparency < 255)
            {
                getImage().setTransparency(transparency);
            }
            else if (transparency == 300)
            {
                getWorld().removeObject(this);
            }
        }
    }
}

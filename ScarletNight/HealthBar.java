import greenfoot.*;
public class HealthBar extends SystemCore
{
    private GreenfootImage healthBarImage = new GreenfootImage("SystemItem//HealthBar.png");
    private int x = 0, y = 0;
    public HealthBar(int getXCoordinate, int getYCoordinate)
    {
        x = getXCoordinate;
        y = getYCoordinate;
    }

    public void setSize(double getHp)
    {
        healthBarImage = new GreenfootImage("SystemItem//HealthBar.png");
        setImage(healthBarImage);
        setLocation(x,y);
        if (getHp <= 0.0)
        {
            getHp = 0.01;
        }
        healthBarImage.scale((int)(((double)800)*getHp),30);
    }
}

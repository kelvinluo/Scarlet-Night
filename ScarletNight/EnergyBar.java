import greenfoot.*;
public class EnergyBar extends SystemCore
{
    private GreenfootImage energyBarBarImage = new GreenfootImage("SystemItem//EnergyBar.png");
    private int x = 0, y = 0;
    public EnergyBar(int getXCoordinate, int getYCoordinate)
    {
        x = getXCoordinate;
        y = getYCoordinate;
    }

    public void setSize(double getEnergy)
    {
        energyBarBarImage = new GreenfootImage("SystemItem//EnergyBar.png");
        setImage(energyBarBarImage);
        setLocation(x,y);
        if (getEnergy <= 0.0)
        {
            getEnergy = 0.01;
        }
        energyBarBarImage.scale((int)(((double)780)*getEnergy) + 1,13);
    }
}
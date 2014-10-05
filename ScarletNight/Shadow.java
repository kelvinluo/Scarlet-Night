import greenfoot.*;
public class Shadow extends SystemCore
{
    int x = 0,y = 0;
    double zoomRate = 0;
    int groundHeight = 0;
    boolean direction = true;
    int displacement = 0;
    public void act() 
    {
        if (direction)
        {
            setLocation(x-displacement,groundHeight);
        }
        else
        {
            setLocation(x+displacement,groundHeight);
        }
    }

    public void getPosition(int getX,int getY, boolean getDirection, int getDisplacement)
    {
        y = getY;
        x = getX;
        direction = getDirection;
        displacement = getDisplacement;
    }

    public void getSize(int getGroundHeight,int width, int length)
    {
        groundHeight = getGroundHeight;
        setImage(new GreenfootImage("SystemItem//Shadow.png"));
        zoomRate = 1.0 - ((double)(getGroundHeight - y))/((double)(getGroundHeight));
        if (zoomRate <= 0.0)
        {
            zoomRate = 0.01;
        }
        getImage().scale((int)(((double)width)*zoomRate) + 1,(int)(((double)length)*zoomRate) + 1);
        
    }
}

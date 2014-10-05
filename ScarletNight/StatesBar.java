import greenfoot.*;  
public class StatesBar extends SystemCore
{
    public StatesBar(boolean direction)
    {
        if (direction)
        {
            this.getImage().mirrorHorizontally();
        }
    }    
}

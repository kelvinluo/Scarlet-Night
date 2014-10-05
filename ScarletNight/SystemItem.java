import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SystemItem extends SystemCore
{

    private StatesBar p1StatesBar = new StatesBar(false);
    private StatesBar p2StatesBar = new StatesBar(true);
    private HealthBar p1HealthBar = new HealthBar(100,30);
    private HealthBar p2HealthBar = new HealthBar(508,30);
    private EnergyBar p1EnergyBar = new EnergyBar(108,57);
    private EnergyBar p2EnergyBar = new EnergyBar(516,57);
    private boolean set = false;

    public SystemItem()
    {

    }

    public void act()
    {
        setUp();
    }

    public void getSystemInfo(double getP1Hp, double getP2Hp, double getP1Energy, double getP2Energy)
    {
        p1HealthBar.setSize(getP1Hp);
        p1EnergyBar.setSize(getP1Energy);
        p2HealthBar.setSize(getP2Hp);
        p2EnergyBar.setSize(getP2Energy);
    }

    public void setUp()
    {
        if (!set)
        {
            set = true;
            
            getWorld().addObject(p1HealthBar,80,30);
            getWorld().addObject(p1EnergyBar,88,57);
            getWorld().addObject(p1StatesBar,300,40);
            
            getWorld().addObject(p2HealthBar,520,30);
            getWorld().addObject(p2EnergyBar,528,57);
            getWorld().addObject(p2StatesBar,710,40);
        }
    }
}

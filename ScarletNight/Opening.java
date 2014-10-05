/**
 * 
 * 
 * ScarletNight
 * Created by Kelvin Luo, Eddie Zhang, and Joshua Tusi
 * Submitted on June 11, 2013
 * As final project
 * 
 */

/**
 * 
 * This class is used to display the openning animation
 * 
 */

import greenfoot.*;
public class Opening extends World
{
    private Background background = new Background();
    private Gun gun = new Gun();
    private Word title = new Word();
    private CoverSword sword = new CoverSword();
    private EnterButton enterButton = new EnterButton();
    private boolean start = false;
    private int frames = 0;
    private GreenfootSound backgroundMusic = new GreenfootSound("Sound//System//openning.mp3");
    public Opening()
    {
        super(1000,600,1,false);
        setPaintOrder(FlashShot.class,FlashScreen.class,EnterButton.class,Word.class,Gun.class,CoverSword.class,Background.class);
        addObject(background,500,300);
    }
    // Use frames to follow the progress, and runs the animation
    public void act()
    {
        frames++;
        if (frames == 2)
        {
            addObject(gun,500,350);
            backgroundMusic.play();
            Greenfoot.playSound("Sound//White//attack1.mp3");
        }
        if (frames == 30)
        {
            addObject(sword,1150,-500);
        }
        if (frames == 35)
            Greenfoot.playSound("Sound//White//WhiteAbilityH.mp3");
        if (frames == 70)
        {
            addObject(title,500,300);
            Greenfoot.playSound("Sound//White//attack2.mp3");
        }
        if (frames == 150)
        {
            addObject(new FlashScreen("Red",true),500,300);
            Greenfoot.playSound("Sound//White//whiteAbilityUlt.mp3");
            title.second();
        }
        if (frames == 500)
        {
            addObject(new FlashScreen("Black",false),500,300);
        }
        if (frames == 799)
        {
            addObject(new FlashScreen("Black",true),500,300);
        }
        if (frames >= 799)
        {
            addObject(enterButton,800,530);
        }
        if (frames >= 800 && !start)
        {
            frames = 800;
            if (Greenfoot.isKeyDown("enter"))
            {
                Greenfoot.playSound("Sound//White//whiteAbilityUlt.mp3");
                 Greenfoot.playSound("Sound//System//scarletNight.mp3");
                addObject(new FlashShot("White",true),500,300);
                start = true;
            }
            enterButton.start(start);
        }
        if (frames == 850)
        {
            addObject(new FlashScreen("Black",false),500,300);
        }
        if (start && frames == 1100)
        {
            backgroundMusic.stop();
            Greenfoot.setWorld(new GameIntroduction(true));
        }
    }
}

import greenfoot.*;
import java.util.Random;
/**
 * This is the program controller where the characters's information will be organized and process through this class
 */
public class Campaigh extends World
{
    private Random ranGen = new Random();
    private P1Character p1;
    private P2Character p2;
    private String p1Character;
        private String p2Character;
    private boolean roundEnds = false;
    private int startCountDown = 0;
    private int endCountDown = 0;
    private int p1Score = 0;
    private int p2Score = 0;
    private boolean endRound = false;
    private double p1Hp = 0;
    private double p2Hp = 0;
    private double p1Energy = 0;
    private double p2Energy = 0;
    private boolean setedUp = false;
    private boolean reset = false;
    private GreenfootSound backgroundMusic = new GreenfootSound("Background Music//Battle"+(ranGen.nextInt(2)+1)+".mp3");
    private SystemItem systemItem = new SystemItem();
    private GreenfootImage background = new GreenfootImage("Castle.png");
    private Load loadingPics;
    public Campaigh(boolean getStart, String getP1Character,String getP2Character)
    {
        super(1000,600,1,false);
        p1Character = getP1Character;
        p2Character = getP2Character;
        setPaintOrder(Load.class,LoadingScreen.class,FlashShot.class,FlashScreen.class,Vsigh.class,DisplayRounds.class,StatesBar.class,CharacterIcons.class,HealingRam.class,Mage.class,White.class,Ringer.class,Ninja.class,Shadow.class);
        setBackground(background);
        loadingPics = new Load(getP1Character,getP2Character);
        p1 = new P1Character(getP1Character);
        p2 = new P2Character(getP2Character);
        addObject(new CharacterIcons(getP1Character),50,50);
        addObject(new CharacterIcons(getP2Character),950,50);
        addObject(systemItem,-100,-100);
        addObject(loadingPics,500,300);
    }

    public void act()
    {
        if (loadingPics.returnResult() && !endRound)
        {
            setUp();
            characterStates();
            statesChange();
        }
        if (endRound)
        {
            resetGame();
        }
    }
    //Set up the fight
    public void setUp()
    {
        if (!setedUp)
        {
            backgroundMusic.playLoop();
            addObject(p1,-100,-400);
            addObject(p2,-900,-400);
            addObject(new DisplayRounds(1),500,300);
            setedUp = true;
        }
        if (reset)
        {
            if (p1Score == 2 || p2Score == 2)
            {
                backgroundMusic.stop();
                if (p1Score == 2)
                    Greenfoot.setWorld(new End(true,p1Character));
                else if (p2Score == 2)
                    Greenfoot.setWorld(new End(true,p2Character));
            }
            if (p1Score == 1)
            {
                addObject(new Vsigh(),400,100);
            }
            else if (p1Score == 2)
            {
                addObject(new Vsigh(),350,100);
            }
            if (p2Score == 1)
            {
                addObject(new Vsigh(),600,100);
            }
            else if (p2Score == 2)
            {
                addObject(new Vsigh(),650,100);
            }
            if (p1Score == 1 && p2Score == 1)
            {
                addObject(new DisplayRounds(3),500,300);
            }
            else if (p1Score == 1 || p2Score == 1)
            {
                addObject(new DisplayRounds(2),500,300);
            }
            reset = false;
        }
        if (startCountDown == 150)
        {
            p1.startGame();
            p2.startGame();
        }
        else
        {
            startCountDown++;
        }
    }

    public void becakgroundAnimation()
    {
        setBackground(background);
    }
//Return character state and control the hp bars
    public void characterStates()
    {
        if (p1.dead() && !roundEnds)
        {
            p2Score++;
            roundEnds = true;
            backgroundMusic.stop();
            endCountDown = 0;
        }
        else if (p2.dead() && !roundEnds)
        {
            p1Score++;
            roundEnds = true;
            backgroundMusic.stop();
            endCountDown = 0;
        }
        if (roundEnds)
        {
            resetCountDown();
        }
        p1Hp = p1.characterHitpoint();
        p1Energy = p1.characterEnergy();
        p2Hp = p2.characterHitpoint();
        p2Energy = p2.characterEnergy();
    }

    public void statesChange()
    {
        systemItem.getSystemInfo(p1Hp,p2Hp,p1Energy,p2Energy);
    }

    public void resetGame()
    {
        reset = true;
        p1.resetPlplayer();
        p2.resetP2player();
    }
    //Reset game
    public void resetCountDown()
    {
        if (roundEnds)
        {
            endCountDown++;
        }
        if (endCountDown == 300)
        {
            roundEnds = false;
            startCountDown = 0;
            resetGame();
            backgroundMusic.playLoop();
        }
        else if (endCountDown == 1)
        {
            addObject(new FlashScreen("Black",false),500,300);
        }
    }
}

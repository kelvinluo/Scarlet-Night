import greenfoot.*;
public class P2Character extends Characters
{
    /**
     * Character One interface, where all the controls of player 2 will 
     * process through this interface and send to the character which player 2 had chosen
     */
    private White whiteKnight = new White("P2",900,450);
    private Bond jamesBond = new Bond ("P2",900,450);
    private Ninja redNinja = new Ninja("P2",900,450);
    private Mage purpleMage = new Mage("P2",900,450);
    private Ringer markGreen = new Ringer("P2",900,450);
    private boolean  start = false;
    private String P2character;
    private boolean setUp = false;
    public P2Character(String getP2Character)
    {
        P2character = getP2Character;
    }

    public void act() 
    {
        getSetUp();
        if (start)
            P2keyDetection();
    }
    /**
     * Set up which character P2 is going to use
     */
    public void getSetUp()
    {
        if (setUp == false)
        {
            if (P2character.equals("White"))
            {
                getWorld().addObject(whiteKnight,100,400);
            }
            else if (P2character.equals("Bond"))
            {
                getWorld().addObject(jamesBond,100,400);
            }
            else if (P2character.equals("Ninja"))
            {
                getWorld().addObject(redNinja,100,400);
            }
            else if (P2character.equals("Mage"))
            {
                getWorld().addObject(purpleMage,100,400);
            }
            else if (P2character.equals("Ringer"))
            {
                getWorld().addObject(markGreen,100,400);
            }
            setUp = true;
        }
    }
    /**
     * Check keys from the keybroad
     */
    public void P2keyDetection()
    {   
        if (Greenfoot.isKeyDown("shift"))
        {
            if (P2character.equals("White"))
                whiteKnight.control("jump");
            if (P2character.equals("Bond"))
                jamesBond.control("jump");
            if (P2character.equals("Ninja"))
                redNinja.control("jump");
            if (P2character.equals("Mage"))
                purpleMage.control("jump");
            if (P2character.equals("Ringer"))
                markGreen.control("jump");
        }
        else if (Greenfoot.isKeyDown("/"))
        {
            if (P2character.equals("White"))
                whiteKnight.control("attack");
            if (P2character.equals("Bond"))
                jamesBond.control("attack");
            if (P2character.equals("Ninja"))
                redNinja.control("attack");
            if (P2character.equals("Mage"))
                purpleMage.control("attack");
            if (P2character.equals("Ringer"))
                markGreen.control("attack");
        }
        else if (Greenfoot.isKeyDown("up"))
        {
            if (P2character.equals("White"))
                whiteKnight.control("moveUp");
            if (P2character.equals("Bond"))
                jamesBond.control("moveUp");
            if (P2character.equals("Ninja"))
                redNinja.control("moveUp");
            if (P2character.equals("Mage"))
                purpleMage.control("moveUp");
            if (P2character.equals("Ringer"))
                markGreen.control("moveUp");
        }
        else if (Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("j"))
        {
            if (P2character.equals("White"))
                whiteKnight.control("moveLeft");
            if (P2character.equals("Bond"))
                jamesBond.control("moveLeft");
            if (P2character.equals("Ninja"))
                redNinja.control("moveLeft");
            if (P2character.equals("Mage"))
                purpleMage.control("moveLeft");
            if (P2character.equals("Ringer"))
                markGreen.control("moveLeft");
        }
        else if (Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("j"))
        {
            if (P2character.equals("White"))
                whiteKnight.control("moveRight");
            if (P2character.equals("Bond"))
                jamesBond.control("moveRight");
            if (P2character.equals("Ninja"))
                redNinja.control("moveRight");
            if (P2character.equals("Mage"))
                purpleMage.control("moveRight");
            if (P2character.equals("Ringer"))
                markGreen.control("moveRight");
        }
        else
        {
            if (P2character.equals("White"))
                whiteKnight.control("null");
            if (P2character.equals("Bond"))
                jamesBond.control("null");
            if (P2character.equals("Ninja"))
                redNinja.control("null");
            if (P2character.equals("Mage"))
                purpleMage.control("null");
            if (P2character.equals("Ringer"))
                markGreen.control("null");
        }

        if (Greenfoot.isKeyDown("."))
        {
            if (P2character.equals("White"))
                whiteKnight.control("abilityH");
            if (P2character.equals("Bond"))
                jamesBond.control("abilityH");
            if (P2character.equals("Ninja"))
                redNinja.control("abilityH");
            if (P2character.equals("Mage"))
                purpleMage.control("abilityH");
            if (P2character.equals("Ringer"))
                markGreen.control("abilityH");
        }
        else if (Greenfoot.isKeyDown(","))
        {
            if (P2character.equals("White"))
                whiteKnight.control("abilityR");
            if (P2character.equals("Bond"))
                jamesBond.control("abilityR");
            if (P2character.equals("Ninja"))
                redNinja.control("abilityR");
            if (P2character.equals("Mage"))
                purpleMage.control("abilityR");
            if (P2character.equals("Ringer"))
                markGreen.control("abilityR");
        }
        else if (Greenfoot.isKeyDown("m"))
        {
            if (P2character.equals("White"))
                whiteKnight.control("abilityT");
            if (P2character.equals("Bond"))
                jamesBond.control("abilityT");
            if (P2character.equals("Ninja"))
                redNinja.control("abilityT");
            if (P2character.equals("Mage"))
                purpleMage.control("abilityT");
            if (P2character.equals("Ringer"))
                markGreen.control("abilityT");
        }
        else if (Greenfoot.isKeyDown("n"))
        {
            if (P2character.equals("White"))
                whiteKnight.control("abilityUlt");
            if (P2character.equals("Bond"))
                jamesBond.control("abilityUlt");
            if (P2character.equals("Ninja"))
                redNinja.control("abilityUlt");
            if (P2character.equals("Mage"))
                purpleMage.control("abilityUlt");
            if (P2character.equals("Ringer"))
                markGreen.control("abilityUlt");
        }
    }
    /**
     * Check if the charater is dead
     */
    public boolean dead()
    {
        if (P2character.equals("White"))
        {
            return whiteKnight.ifDead();
        }
        else if (P2character.equals("Bond"))
        {
            return jamesBond.ifDead();
        }
        else if (P2character.equals("Ninja"))
        {
            return redNinja.ifDead();
        }
        else if (P2character.equals("Mage"))
        {
            return purpleMage.ifDead();
        }
        return markGreen.ifDead();
    }
    /**
     * reseting characters
     */
    public boolean reset()
    {
        if (P2character.equals("White"))
        {
            return whiteKnight.ifDead();
        }
        else if (P2character.equals("Bond"))
        {
            return jamesBond.ifDead();
        }
        else if (P2character.equals("Ninja"))
        {
            return redNinja.ifDead();
        }
        else if (P2character.equals("Mage"))
        {
            return purpleMage.ifDead();
        }
        return markGreen.ifDead();
    }

    public void resetP2player()
    {
        start = false;
        if (P2character.equals("White"))
        {
            whiteKnight.reset(900,450,false);
        }
        else if (P2character.equals("Bond"))
        {
            jamesBond.reset(900,450,false);
        }
        else if (P2character.equals("Ninja"))
        {
            redNinja.reset(900,450,false);
        }
        else if (P2character.equals("Mage"))
        {
            purpleMage.reset(900,450,false);
        }
        markGreen.reset(900,450,false);
    }
    /**
     * send the character states to the world and let it process
     */
    public double characterHitpoint()
    {
        if (P2character.equals("White"))
        {
            return whiteKnight.characterHitpoint();
        }
        else if (P2character.equals("Bond"))
        {
            return jamesBond.characterHitpoint();
        }
        else if (P2character.equals("Ninja"))
        {
            return redNinja.characterHitpoint();
        }
        else if (P2character.equals("Mage"))
        {
            return purpleMage.characterHitpoint();
        }
        return markGreen.characterHitpoint();
    }

    public double characterEnergy()
    {
        if (P2character.equals("White"))
        {
            return whiteKnight.characterEnergy();
        }
        else if (P2character.equals("Bond"))
        {
            return jamesBond.characterEnergy();
        }
        else if (P2character.equals("Ninja"))
        {
            return redNinja.characterEnergy();
        }
        else if (P2character.equals("Mage"))
        {
            return purpleMage.characterEnergy();
        }
        return markGreen.characterEnergy();
    }

    public void startGame()
    {
        start = true;
    }
}

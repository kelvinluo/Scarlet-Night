import greenfoot.*;
public class P1Character extends Characters
{
    /**
     * Character One interface, where all the controls of player 1 will 
     * process through this interface and send to the character which player 1 had chosen
     */
    private White whiteKnight = new White("P1",100,450);
    private Bond jamesBond = new Bond ("P1",100,450);
    private Ninja redNinja = new Ninja("P1",100,450);
    private Mage purpleMage = new Mage("P1",100,450);
    private Ringer markGreen = new Ringer("P1",100,450);
    private boolean  start = false;
    private String P1character;
    private boolean setUp = false;
    public P1Character(String getP1Character)
    {
        P1character = getP1Character;
    }

    public void act() 
    {
        getSetUp();
        if (start)
            p1keyDetection();
    }
    /**
     * Set up which character P1 is going to use
     */
    public void getSetUp()
    {
        if (setUp == false)
        {
            if (P1character.equals("White"))
            {
                getWorld().addObject(whiteKnight,100,400);
            }
            else if (P1character.equals("Bond"))
            {
                getWorld().addObject(jamesBond,100,400);
            }
            else if (P1character.equals("Ninja"))
            {
                getWorld().addObject(redNinja,100,400);
            }
            else if (P1character.equals("Mage"))
            {
                getWorld().addObject(purpleMage,100,400);
            }
            else if (P1character.equals("Ringer"))
            {
                getWorld().addObject(markGreen,100,400);
            }
            setUp = true;
        }
    }
    /**
     * Check keys from the keybroad
     */
    public void p1keyDetection()
    {   
        if (Greenfoot.isKeyDown("g"))
        {
            if (P1character.equals("White"))
                whiteKnight.control("jump");
            if (P1character.equals("Bond"))
                jamesBond.control("jump");
            if (P1character.equals("Ninja"))
                redNinja.control("jump");
            if (P1character.equals("Mage"))
                purpleMage.control("jump");
            if (P1character.equals("Ringer"))
                markGreen.control("jump");
        }
        else if (Greenfoot.isKeyDown("f"))
        {
            if (P1character.equals("White"))
                whiteKnight.control("attack");
            if (P1character.equals("Bond"))
                jamesBond.control("attack");
            if (P1character.equals("Ninja"))
                redNinja.control("attack");
            if (P1character.equals("Mage"))
                purpleMage.control("attack");
            if (P1character.equals("Ringer"))
                markGreen.control("attack");
        }
        else if (Greenfoot.isKeyDown("w"))
        {
            if (P1character.equals("White"))
                whiteKnight.control("moveUp");
            if (P1character.equals("Bond"))
                jamesBond.control("moveUp");
            if (P1character.equals("Ninja"))
                redNinja.control("moveUp");
            if (P1character.equals("Mage"))
                purpleMage.control("moveUp");
            if (P1character.equals("Ringer"))
                markGreen.control("moveUp");
        }
        else if (Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("j"))
        {
            if (P1character.equals("White"))
                whiteKnight.control("moveLeft");
            if (P1character.equals("Bond"))
                jamesBond.control("moveLeft");
            if (P1character.equals("Ninja"))
                redNinja.control("moveLeft");
            if (P1character.equals("Mage"))
                purpleMage.control("moveLeft");
            if (P1character.equals("Ringer"))
                markGreen.control("moveLeft");
        }
        else if (Greenfoot.isKeyDown("d") && !Greenfoot.isKeyDown("j"))
        {
            if (P1character.equals("White"))
                whiteKnight.control("moveRight");
            if (P1character.equals("Bond"))
                jamesBond.control("moveRight");
            if (P1character.equals("Ninja"))
                redNinja.control("moveRight");
            if (P1character.equals("Mage"))
                purpleMage.control("moveRight");
            if (P1character.equals("Ringer"))
                markGreen.control("moveRight");
        }
        else
        {
            if (P1character.equals("White"))
                whiteKnight.control("null");
            if (P1character.equals("Bond"))
                jamesBond.control("null");
            if (P1character.equals("Ninja"))
                redNinja.control("null");
            if (P1character.equals("Mage"))
                purpleMage.control("null");
            if (P1character.equals("Ringer"))
                markGreen.control("null");
        }

        if (Greenfoot.isKeyDown("h"))
        {
            if (P1character.equals("White"))
                whiteKnight.control("abilityH");
            if (P1character.equals("Bond"))
                jamesBond.control("abilityH");
            if (P1character.equals("Ninja"))
                redNinja.control("abilityH");
            if (P1character.equals("Mage"))
                purpleMage.control("abilityH");
            if (P1character.equals("Ringer"))
                markGreen.control("abilityH");
        }
        else if (Greenfoot.isKeyDown("r"))
        {
            if (P1character.equals("White"))
                whiteKnight.control("abilityR");
            if (P1character.equals("Bond"))
                jamesBond.control("abilityR");
            if (P1character.equals("Ninja"))
                redNinja.control("abilityR");
            if (P1character.equals("Mage"))
                purpleMage.control("abilityR");
            if (P1character.equals("Ringer"))
                markGreen.control("abilityR");
        }
        else if (Greenfoot.isKeyDown("t"))
        {
            if (P1character.equals("White"))
                whiteKnight.control("abilityT");
            if (P1character.equals("Bond"))
                jamesBond.control("abilityT");
            if (P1character.equals("Ninja"))
                redNinja.control("abilityT");
            if (P1character.equals("Mage"))
                purpleMage.control("abilityT");
            if (P1character.equals("Ringer"))
                markGreen.control("abilityT");
        }
                else if (Greenfoot.isKeyDown("y"))
        {
            if (P1character.equals("White"))
                whiteKnight.control("abilityUlt");
            if (P1character.equals("Bond"))
                jamesBond.control("abilityUlt");
            if (P1character.equals("Ninja"))
                redNinja.control("abilityUlt");
            if (P1character.equals("Mage"))
                purpleMage.control("abilityUlt");
            if (P1character.equals("Ringer"))
                markGreen.control("abilityUlt");
        }
    }
    /**
     * Check if the charater is dead
     */
    public boolean dead()
    {
        if (P1character.equals("White"))
        {
            return whiteKnight.ifDead();
        }
        else if (P1character.equals("Bond"))
        {
            return jamesBond.ifDead();
        }
        else if (P1character.equals("Ninja"))
        {
            return redNinja.ifDead();
        }
        else if (P1character.equals("Mage"))
        {
            return purpleMage.ifDead();
        }
        return markGreen.ifDead();
    }
    /**
     * reseting characters
     */
    public void resetPlplayer()
    {
        start = false;
        if (P1character.equals("White"))
        {
            whiteKnight.reset(100,450,true);
        }
        else if (P1character.equals("Bond"))
        {
            jamesBond.reset(100,450,true);
        }
        else if (P1character.equals("Ninja"))
        {
            redNinja.reset(100,450,true);
        }
        else if (P1character.equals("Mage"))
        {
            purpleMage.reset(100,450,true);
        }
        markGreen.reset(100,450,true);
    }
    /**
     * send the character states to the world and let it process
     */
    public double characterHitpoint()
    {
        if (P1character.equals("White"))
        {
            return whiteKnight.characterHitpoint();
        }
        else if (P1character.equals("Bond"))
        {
            return jamesBond.characterHitpoint();
        }
        else if (P1character.equals("Ninja"))
        {
            return redNinja.characterHitpoint();
        }
        else if (P1character.equals("Mage"))
        {
            return purpleMage.characterHitpoint();
        }
        return markGreen.characterHitpoint();
    }

    public double characterEnergy()
    {
        if (P1character.equals("White"))
        {
            return whiteKnight.characterEnergy();
        }
        else if (P1character.equals("Bond"))
        {
            return jamesBond.characterEnergy();
        }
        else if (P1character.equals("Ninja"))
        {
            return redNinja.characterEnergy();
        }
        else if (P1character.equals("Mage"))
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

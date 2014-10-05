import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Mage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mage extends Characters
{
    // Initialize positon variables
    private Random ranGen = new Random();
    private int x = 100;
    private int groundHeight = 450;
    private int y = groundHeight;
    private static int MageY = 0;
    private Shadow shadow = new Shadow();
    // Pictures
    private GreenfootImage mage = new GreenfootImage ("Mage//mageNormal//mageNormal1.png");
    private String flip;

    // Attributes
    private String character;
    private double hitpointMax = 100;
    private double hitpoint = hitpointMax;

    private double energyMax = 100;
    private double energy = energyMax;

    private boolean dead = false;

    // Frames
    private int maxNormalFrames = 8;
    private int frameCount = 0;
    private int normalFrames = 1;
    private int maxMoveFrames = 15;
    private int moveFrames = 1;
    private int attackFrames = 4;
    private int maxAttackFrames = 23;
    private int abilityHFrames = 5;
    private int maxAbilityHFrames = 54;
    private int abilityRFrames = 5;
    private int maxAbilityRFrames = 114;
    private int abilityTFrames = 1;
    private int maxAbilityTFrames = 57;
    private int dieFrame = 0;
    private int maxDieFrame = 30;

    // Initialize movement varibles
    private int maxSpeed = 4;
    private int speed = 0;
    private boolean movement = false;
    private boolean movementDirection = true;
    private boolean moveUp = false; 

    // Attacks
    private boolean attack = false;
    private static boolean attackDuration;
    private boolean abilityH = false;
    private static boolean abilityHDuration;
    private boolean abilityR = false;
    private static boolean abilityRDuration;
    private boolean abilityT = false;
    private static boolean abilityTDuration;
    private boolean hurt = false;

    // Cool down
    private boolean flashCD = false;
    private int flashCountDown;
    private boolean attackCD = false;

    /**
     * Act - do whatever the Mage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public Mage(String getCharacter,int getX,int getY)
    {
        character = getCharacter;
        if (getCharacter.equals("P2"))
        {
            movementDirection = false;
        }
        dead = false;       
        x = getX;
        y = getY;
    }

    public void act() 
    {
        if (!dead)
        {
            getLocation();
            animation();
            damageDetection();
            if (moveUp && y < groundHeight && !abilityR && !abilityH && !hurt)
            {
                y += 3;
            }
        }
        else
        {
            if (maxDieFrame > dieFrame)
            {
                dieFrame++;
                mage = new GreenfootImage ("Mage//mageDie//mageDie" + ((dieFrame/6) + 1) + ".png");
                if (!movementDirection)
                {
                    mage.mirrorHorizontally();
                }
                setImage(mage);
            }
            else if (maxDieFrame == dieFrame)
            {
                setImage(new GreenfootImage ("ballsI.png"));
                getWorld().removeObject(shadow);
                dieFrame++;
            }
        }
    }

    public void getLocation()
    {
        setLocation(x, y);
        setImage(mage);

        // Speed control
        if(speed > maxSpeed)
        {
            speed = maxSpeed;
        }
        else if (movement)
        {   
            for(int i = 0; i <= 100; i++)
            {
                if (i == 100)
                {
                    speed++;
                }
            }
        }
        else
        {
            speed = 0;
        }
        if (energy < energyMax)
        {
            energy+= 0.1;
        }
        if (hitpoint <= 0.0)
        {
            hitpoint = 0.0;
            dead = true;
        }
    }

    // Detects user's movement input
    public void control(String command)
    {
        // Flash movements
        // Left
        if (command.equals("jump") && !flashCD)
        {
            if (!movementDirection)
            {
                x -= 300;
            }
            else
            {
                x += 300;
            }

            flashCD = true;
            flashCountDown = 150;
            movement = true;
            if (x < 1)
            {
                x = 1;
            }
            else if (x > 999)
            {
                x = 999;
            }
        }

        // Attack
        if (command.equals("attack") && !attackDuration && energy >= 10  && !abilityRDuration && !abilityHDuration)
        {
            attack = true;
            attackDuration = true;
            energy = energy - 10;
            Greenfoot.playSound("Sound//Mage//mageAttack.mp3");
        }

        if (command.equals("abilityH") && !abilityHDuration && energy >= 20  && !abilityRDuration && !attackDuration && !abilityTDuration)
        {
            abilityH = true;
            abilityHDuration = true;
            energy = energy - 20;
        }

        if (command.equals("abilityR") && !abilityRDuration && energy >= 40  && !abilityHDuration && !abilityTDuration)
        {
            abilityR = true;
            abilityRDuration = true;
            energy = energy - 40;
            Greenfoot.playSound("Sound//Mage//mageAbilityR.mp3");
        }

        if (command.equals("abilityT") && !abilityRDuration && energy >= 50  && !abilityHDuration && !abilityTDuration)
        {
            abilityT = true;
            abilityTDuration = true;
            energy = energy - 50;
        }

        // Normal movement commands
        // Left
        if (command.equals("moveLeft") && !abilityRDuration && !hurt)
        {
            movement = true;
            if (x > speed)
            {
                x -= speed;
            }
            movementDirection = false;
        }
        // Right
        else if (command.equals("moveRight") && !abilityRDuration && !hurt)
        {
            movement = true;
            if (x < (1000 - speed))
            {
                x += speed;
            }
            movementDirection = true;
        }

        // Flying Command
        if (!abilityH && command.equals("moveUp") && y > 50 && !abilityRDuration && !hurt)
        {
            speed = 0;
            y-= 6;
            moveUp = true;
        }

        // Flash Count Down
        if (flashCD)
        {
            flashCountDown--;
            if (flashCountDown == 0)
            {
                flashCD = false;
            }
        }
        getWorld().addObject(shadow,x,y);
        if (movement)
        {
            shadow.getPosition(x,y,movementDirection,13);
            shadow.getSize(groundHeight+30,30,8);
        }
        else
        {
            shadow.getPosition(x,y,movementDirection,13);
            shadow.getSize(groundHeight+30,30,8);
        }
    }

    public void animation()
    {
        // If moving towards the left
        if (hurt)
        {
            normalFrames = 1;
            attack = false;
            movement = false;
            attackDuration = false;
            abilityH = false;
            abilityHDuration = false;
            abilityR = false;
            abilityRDuration = false;
            abilityT = false;
            abilityTDuration = false;
        }
        if (!movementDirection)
        {
            if (attack)
            {
                if (attackFrames > maxAttackFrames)
                {
                    attackFrames = 4;
                    Fireball ball = new Fireball(movementDirection, character, x, y - 14);
                    getWorld().addObject(ball, x, y - 14);
                    attack = false;
                }
                else
                {
                    mage = new GreenfootImage("Mage//mageAttack//mageAttack" + attackFrames/4 + ".png");
                    mage.mirrorHorizontally();
                }
                attackFrames ++;
            }
            else if (abilityH)
            {
                if (abilityHFrames > maxAbilityHFrames)
                {
                    abilityHFrames = 5;
                    NuclearCharge charge = new NuclearCharge(movementDirection, character, x, y - 50);
                    getWorld().addObject(charge, x, y - 50);
                    abilityH = false;
                    moveUp = true;
                }
                else
                {
                    if (abilityHFrames == 35)
                    {
                        Greenfoot.playSound("Sound//Mage//mageAbilityH.mp3");
                    }
                    mage = new GreenfootImage("Mage//MageAbilityH//mageAbilityH" + abilityHFrames/5 + ".png");
                    mage.mirrorHorizontally();
                    y = y - 3;
                }
                abilityHFrames ++;
            }
            else if (abilityR)
            {
                if (abilityRFrames == 70)
                {
                    FinalSpark spark = new FinalSpark(movementDirection, character, x, y - 50);
                    getWorld().addObject(spark, x - 600, y);
                }
                if (abilityRFrames > maxAbilityRFrames)
                {
                    abilityRFrames = 5;
                    abilityR = false;
                    abilityRDuration = false;
                }
                else
                {
                    mage = new GreenfootImage("Mage//MageAbilityR//MageAbilityR" + abilityRFrames/5 + ".png");
                    mage.mirrorHorizontally();
                }
                abilityRFrames ++;
            }
            else if (abilityT)
            {
                if (abilityTFrames == 57)
                {
                    getWorld().addObject(new Meteor(character, movementDirection), x-50 + ranGen.nextInt(100), y-1000);
                }

                if (abilityTFrames > maxAbilityTFrames)
                {
                    abilityTFrames = 1;
                    abilityT = false;
                    abilityTDuration = false;
                }
                else
                {
                    mage = new GreenfootImage("Mage//MageAbilityUlt//mageAbilityUlt00" + abilityTFrames + ".png");
                    mage.mirrorHorizontally();
                }
                abilityTFrames ++;
            }
            else if (movement)
            {
                if (moveFrames > maxMoveFrames)
                {
                    moveFrames = 1;
                }
                else
                {
                    mage = new GreenfootImage ("Mage//mageMove//mageMove" + ((moveFrames/4) + 1) + ".png");
                    mage.mirrorHorizontally();
                }
                moveFrames ++;
                movement = false;
            }
            else if (!movement)
            {
                if (normalFrames > maxNormalFrames)
                {
                    normalFrames = 1;
                }
                else if (frameCount > 10)
                {
                    frameCount = 0;
                }
                else if (frameCount == 10)
                {
                    normalFrames ++;
                }
                else 
                {
                    mage = new GreenfootImage ("Mage//mageNormal//mageNormal" + normalFrames + ".png");
                    mage.mirrorHorizontally();
                }
                movement = false;
            }
            frameCount ++;
        }

        // If moving towards the right
        else if (movementDirection)
        {
            if (attack)
            {
                if (attackFrames > maxAttackFrames)
                {
                    attackFrames = 4;
                    Fireball ball = new Fireball(movementDirection, character, x, y - 14);
                    getWorld().addObject(ball, x, y - 14);
                    attack = false;
                }
                else
                {
                    mage = new GreenfootImage("Mage//mageAttack//mageAttack" + attackFrames/4 + ".png");
                }
                attackFrames ++;
            }
            else if (abilityH)
            {
                if (abilityHFrames > maxAbilityHFrames)
                {
                    abilityHFrames = 5;
                    NuclearCharge charge = new NuclearCharge(movementDirection, character, x, y);
                    getWorld().addObject(charge, x, y - 50);
                    abilityH = false;
                    moveUp = true;
                }
                else
                {
                    mage = new GreenfootImage("Mage//MageAbilityH//mageAbilityH" + abilityHFrames/5 + ".png");
                    y = y - 3;
                }
                abilityHFrames ++;
            }
            else if (abilityR)
            {
                if (abilityRFrames == 70)
                {
                    FinalSpark spark = new FinalSpark(movementDirection, character, x, y - 50);
                    getWorld().addObject(spark, x + 600, y);
                }
                else if (abilityRFrames > maxAbilityRFrames)
                {
                    abilityRFrames = 5;
                    abilityR = false;
                    abilityRDuration = false;
                }
                else
                {
                    mage = new GreenfootImage("Mage//MageAbilityR//MageAbilityR" + abilityRFrames/5 + ".png");
                }
                abilityRFrames ++;
            }
            else if (abilityT)
            {
                if (abilityTFrames < 57 && abilityTFrames > 30)
                {
                    getWorld().addObject(new Meteor(character,movementDirection), (x-50)+ranGen.nextInt(100), y-1000);
                }
                if (abilityTFrames > maxAbilityTFrames)
                {
                    abilityTFrames = 1;
                    abilityT = false;
                    abilityTDuration = false;
                }
                else
                {
                    mage = new GreenfootImage("Mage//MageAbilityUlt//mageAbilityUlt00" + abilityTFrames + ".png");
                }
                abilityTFrames ++;
            }
            else if (movement)
            {
                if (moveFrames > maxMoveFrames)
                {
                    moveFrames = 1;
                }
                else
                {
                    mage = new GreenfootImage("Mage//mageMove//mageMove" + ((moveFrames/4) + 1) + ".png");
                }
                moveFrames ++;
                movement = false;
            }
            else if (!movement)
            {
                if (normalFrames > maxNormalFrames)
                {
                    normalFrames = 1;
                }
                else if (frameCount > 10)
                {
                    frameCount = 0;
                }
                else if (frameCount == 10)
                {
                    normalFrames ++;
                }
                else 
                {
                    mage = new GreenfootImage("Mage//mageNormal//mageNormal" + normalFrames + ".png");
                }
                movement = false;
            }
            frameCount ++;
        }
    }
    // Return current health
    public double characterHitpoint()
    {
        if (hitpoint > 0.0)
        {
            return hitpoint/hitpointMax;
        }
        else
        {
            dead = true;
            return 0.01;
        }
    }
    // Return current energy
    public double characterEnergy()
    {
        if (energy > 0.0)
        {
            return energy/energyMax;
        }
        else
        {
            return 0.01;
        }
    }
    // Detects incoming damage from the enemy
    private void dealtDamage(int getX, int getY, int getWidth, int getLength, int getTime, int getDelay, double getDamage,int applyForce)
    {
        if (character.equals("P1"))
        {
            if (movementDirection)
            {
                getWorld().addObject(new P1AttackArea(x + getX, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,movementDirection),x + getX, y + getY);
            } 
            else
            {   
                getWorld().addObject(new P1AttackArea(x - getX, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,movementDirection),x - getX, y + getY);
            }
        }
        else  if (character.equals("P2"))
        {
            if (movementDirection)
            {
                getWorld().addObject(new P2AttackArea(x + getX, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,movementDirection),x + getX, y + getY);
            } 
            else
            {   
                getWorld().addObject(new P2AttackArea(x - getX, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,movementDirection),x - getX, y + getY);
            }
        }
    }

    public void damageDetection ()
    {
        if (character.equals("P1"))
        {
            P2AttackArea getHit = (P2AttackArea)getOneIntersectingObject(P2AttackArea.class);
            hurt = false;
            if (getHit != null)
            {
                hitpoint-= getHit.damageDealt();
                hurt = true;
                if (getHit.returnForceDirection())
                    x+= getHit.dealForce();
                else
                    x-= getHit.dealForce();
            }
        }
        else  if (character.equals("P2"))
        {
            P1AttackArea getHit = (P1AttackArea)getOneIntersectingObject(P1AttackArea.class);
            hurt = false;
            if (getHit != null)
            {
                hurt = true;
                hitpoint-= getHit.damageDealt();
                if (getHit.returnForceDirection())
                    x+= getHit.dealForce();
                else
                    x-= getHit.dealForce();
            }
        }
    }
    // These methods are accessors for the weapon classes
    public static void attackOver()
    {
        attackDuration = false;
    }

    public static void abilityHOver()
    {
        abilityHDuration = false;
    }

    public static void abilityROver()
    {
        abilityRDuration = false;
    }

    public static void abilityTOver()
    {
        abilityTDuration = false;
    }

    public boolean ifDead()
    {
        return dead;
    }
    // The method resets all varibles to default
    public void reset(int getX, int getY,boolean getDirection)
    {
        movementDirection = getDirection;
        x = getX;
        y = getY;
        dead = false;
        moveFrames = 1;
        normalFrames = 1;
        abilityHFrames = 5;
        abilityRFrames = 5;
        abilityTFrames = 1;
        hitpoint = hitpointMax;
        energy = energyMax;
    }
}
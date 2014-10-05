import greenfoot.*; 
import java.util.Random;
public class White extends Characters
{
/**
 * This class's writing style is the same as Ninja and Ringer classes, so only have to watch this class to understand
 * how it works
 */
    /**
     * This character is used many different kinds of variable to control its health, energy, movement and picture changes.
     */
    private GreenfootImage whiteImage = new GreenfootImage("whiteSwordMan//whiteNormal//whiteNormal1.png");

    private Random ranGen = new Random();

    //States
    private Shadow shadow = new Shadow();
    private double hitpointMax = 100;
    private double hitpoint = hitpointMax;

    private double  energyMax = 100;
    private double  energy = energyMax;

    private boolean dead = false;

    private String character = "P1";
    private int groundHeight = 450;
    private int x = 100,y = groundHeight;
    private int imageFrames = 1;
    private int imageFramesMax = 4;
    private int attackStyle = 1;
    private int jumpingSpeed = 8;
    // true == right, false = left

    private int speed = 0;
    private int maxSpeed = 10;
    private double jumpHeight = 3.0;
    private double gravity = 0.0;
    private boolean moveDirection = true;
    private boolean movement = false;
    private boolean attack = false;
    private boolean jumpAttack = false;
    private boolean hurt = false;
    private boolean jump = false;
    private boolean inAir = false;
    private boolean holdingButton = false;
    private boolean flash = false;
    private boolean ability = false;
    private String getCommand;
    //Cold times
    private int jumpColdTime = 0;
    private int abilityOneColdtime = 0;
    private int abilityNumber = 0;
    private int deadImageFrames = 0;
    private int ultCounter = 45;
    //Get X and Y out from the user interface
    public White(String getPlayer,int getX,int getY)
    {
        this.character = getPlayer;
        if (getPlayer.equals("P2"))
        {
            moveDirection = false;
        }
        x = getX;
        y = getY;
    }
        /**
     * Act - do whatever the character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void act() 
    {
        if (!dead)
        {
            setPosition();
            imageChange();
            damageDetection ();
        }
        else
        {
            deadAnimation();
            getWorld().addObject(shadow,x,y);
            shadow.getPosition(x,y,moveDirection,-10);
            shadow.getSize(groundHeight+30,80,8);
        }
    }

    /**
     * Display the dying animation
     */
    public void deadAnimation()
    {
        setImage(whiteImage);
        if (deadImageFrames < 49)
        {
            deadImageFrames++;
        }
        whiteImage = new GreenfootImage("whiteSwordMan//WhiteDie//whiteDie" + (deadImageFrames/5 + 1) +".png");
        if (!moveDirection)
        {
            whiteImage.mirrorHorizontally();
        }
    }

    /**
     * Set the position of the character and make sure it does not leave the border of the map
     */
    public void setPosition()
    {
        setLocation(x,y);
        setImage(whiteImage);
        if (energy > energyMax)
        {
            energy = energyMax;
        }
        if (hitpoint > hitpointMax)
        {
            hitpoint = hitpointMax;
        }
        if (speed > maxSpeed)
        {
            speed = maxSpeed;
        }
        else if (movement)
        {
            speed++;
        }
        else
        {
            speed = 0;
        }
        if (jumpColdTime > 0)
        {
            jumpColdTime--;
        }
        if (energy < energyMax)
        {
            energy+= 0.3;
        }
        if (hitpoint <= 0.0)
        {
            hitpoint = 0.0;
            dead = true;
        }
        if(x < 0)
        {
            x = 0;
        }
        else if (x > 1000)
        {
            x = 1000;
        }
        getWorld().addObject(shadow,x,y);
        if (movement)
        {
            shadow.getPosition(x,y,moveDirection,-6);
            shadow.getSize(groundHeight+30,70,8);
        }
        else
        {
            shadow.getPosition(x,y,moveDirection,10);
            shadow.getSize(groundHeight+30,70,8);
        }
    }

    /**
     * Change the images base on the variable imageFrames
     */
    public void imageChange()
    {
        imageFrames++;
        if (hurt)
        {
            imageFrames = 0;
            ability = false;
            abilityNumber = 1;
            jump = false;
            jumpAttack = false;
            attack = false;
            movement = false;
            gravity = 0.0;
        }
        /**
         * Attack
         */
        /**
         * Jumping button release detection
         */

        if (jump && inAir && imageFrames >= imageFramesMax)
        {
            imageFrames = 0;
            if (jumpHeight < 20.0)
            {
                jumpHeight += 1.0;
            }
        }
        else if(imageFrames >= imageFramesMax && !inAir)
        {
            imageFrames = 1;
            attack = false; 
            jumpAttack = false;
            ability = false;
        }

        if (y >= groundHeight && jumpAttack && inAir)
        {
            y = groundHeight;
            inAir = false;
            Greenfoot.playSound("Sound//White//swordmanChargeDone.mp3");
        }
        else if (jumpAttack && inAir)
        {
            y += 15;
            positionChange(50);
            imageFrames = 0;
        }

        if (hurt)
        {
            whiteImage = new GreenfootImage("whiteSwordMan//whiteHurt//hurt.png");
        }
        else if (ability)
        {
            if (abilityNumber == 1)
            {
                whiteImage = new GreenfootImage("whiteSwordMan//whiteAbilityOne//whiteAttack" + (imageFrames/3 + 1) + "Ability1.png");
                if (imageFrames == 15)
                {
                    if (moveDirection)
                    {
                        getWorld().addObject(new ShockBlade(moveDirection),x+300,y);
                    }
                    else
                    {
                        getWorld().addObject(new ShockBlade(moveDirection),x-300,y);
                    }
                    dealtDamage(300,0,600,70,1,0,10.0,50);
                }
            }
            else if (abilityNumber == 2)
            {
                whiteImage = new GreenfootImage("whiteSwordMan//whiteAbilityTwo//whiteAttack" + (imageFrames/3 + 1) + "Ability2.png");
            }
            else if (abilityNumber == 3)
            {
                whiteImage = new GreenfootImage("whiteSwordMan//whiteAbilityThree//whiteAttack" + (imageFrames/4 + 1) + "Ability3.png");
            }
            else if (abilityNumber == 4)
            {
                whiteImage = new GreenfootImage("whiteSwordMan//whiteAbilityUlt//whiteAbilityUlt"+(imageFrames/4 + 1)+".png");
                if (imageFrames == 15)
                {
                    Greenfoot.playSound("Sound//White//whiteAbilityUlt.mp3");
                    if (moveDirection)
                        x+= 200;
                    else
                        x-= 200;
                    imageFrames = 16;
                    ultCounter--;
                    if (moveDirection)
                        getWorld().addObject(new WindBlade(character),x-100,y);
                    else
                        getWorld().addObject(new WindBlade(character),x+100,y);
                }
                else if ((imageFrames/4 + 1) == 5 && ultCounter > 0)
                {
                    imageFrames = 16;
                    ultCounter--;
                }
            }
        }
        else
        {
            if (attack)
            {
                whiteImage = new GreenfootImage("whiteSwordMan//whiteAttack//whiteAttack" + (imageFrames/5 + 1) + "Style" + attackStyle +".png");
            }
            else if (jumpAttack)
            {
                whiteImage = new GreenfootImage("whiteSwordMan//whiteJump//whiteJumpAttack" + (imageFrames/5 + 1)+".png");
                if (y == groundHeight)
                {
                    dealtDamage(50,20,50,50,1,0,0.5,10);
                }
                else
                {
                    dealtDamage(50,20,50,50,1,0,2.0,10);
                }
            }
            else if (flash)
            {
                whiteImage = new GreenfootImage("whiteSwordMan//WhiteJump//Flash" + imageFrames +".png");
                if (imageFrames == 3)
                {
                    imageFrames = 0;
                    flash = false;
                    imageFramesMax = 50;
                    jump = false;
                    inAir = false;
                    y = groundHeight;
                    positionChange(40);
                    jumpColdTime = 30;
                    positionChange(150);
                }
            }
            else if (jump)
            {
                whiteImage = new GreenfootImage("whiteSwordMan//WhiteJump//whiteJump" + (imageFrames + 1)  + ".png");
            }
            else if (inAir)
            {
                imageFrames = 0;
                whiteImage = new GreenfootImage("whiteSwordMan//WhiteJump//whiteJump6.png");
                gravity += 0.5;
                y -= (int)(jumpHeight) - (int)(gravity);
                if (y >= groundHeight && inAir && gravity > 5.0)
                {
                    inAir = false;
                    jumpHeight = 10.0;
                    gravity = 0;
                }
            }
            else if (movement && !jump && !inAir)
            {
                whiteImage = new GreenfootImage("whiteSwordMan//whiteRun\\whiteRun" + (imageFrames/2 + 1)  + ".png");
            }

            else if (hurt)
            {
                whiteImage = new GreenfootImage("whiteSwordMan//whiteNormal//whiteNormal" + (imageFrames/10 + 1)  + ".png");
            }
            else
            {
                whiteImage = new GreenfootImage("whiteSwordMan///whiteNormal/whiteNormal" +(imageFrames/10 + 1)  + ".png");
                imageFramesMax = 50;
                y = groundHeight;
                gravity = 0.0;
                jumpHeight = 10.0;
                flash = false;
            }
        }

        if (moveDirection == false)
        {
            whiteImage.mirrorHorizontally();
        }
        movement = false;
        getCommand = "";
    }

    /**
     * 
     * Receiving informations from the player interface and process different kinds of changes in the class
     * 
     */
    public void control(String command)
    {
        Random randomGen = new Random();
        boolean rest = false;
        if (!inAir && !flash && !jumpAttack && !attack && !ability)
        {
            rest = true;
        }
        if (!(command.equals("jump")) && inAir && jump && jumpColdTime == 0)
        {
            jump = false;
            movement = false;
        }
        if (command.equals("jump") && rest && jumpColdTime == 0)
        {
            jump = true;
            inAir = true;                                               
            imageFramesMax = 5;
            imageFrames = 0;
            gravity = 0.0;
        }
        else if (command.equals("jump") && inAir && flash == false && jump == false && !jumpAttack)
        {
            inAir = false;
            flash = true;
            imageFramesMax = 4;
            imageFrames = 0;
            gravity = 0.0;
        }
        else if (command.equals("attack") && inAir && !flash && !jump && !jumpAttack && energy > 20.0)
        {
            jumpAttack = true;
            attack = false;
            imageFramesMax = 25;
            imageFrames = 0;
            gravity = 0.0;
            Greenfoot.playSound("Sound//White//swordmanCharge.mp3");
            energy -= 20.0;
        }
        else if (command.equals("attack") && attack == false && inAir == false && jump == false && !jumpAttack)
        {
            attack = true;
            jump = false;
            imageFrames = 0;
            imageFramesMax = 25;
            int previousStyle = attackStyle;
            while (attackStyle == previousStyle)
            {
                attackStyle = randomGen.nextInt(3)+1;
            }
            Greenfoot.playSound("Sound//White//attack"+attackStyle+".mp3");
            dealtDamage(50,0,20,70,1,5,2.0,10);
        }
        else if (command.equals("abilityH") && rest && energy > 30.0)
        {
            ability = true;
            abilityNumber = 1;
            imageFrames = 0;
            movement = false;
            attack = false;
            jump = false;
            imageFramesMax = 45;
            energy-= 30.0;
            Greenfoot.playSound("Sound//White//WhiteAbilityH.mp3");
        }
        else if (command.equals("abilityR") && rest && energy > 30.0)
        {
            ability = true;
            abilityNumber = 2;
            imageFrames = 0;
            movement = false;
            attack = false;
            jump = false;
            imageFramesMax = 63;
            energy-= 10.0;
        }
        else if (command.equals("abilityR") && ability && abilityNumber == 2 && energy > 2.0)
        {
            if (imageFrames >= 36 && imageFrames < 39)
            {
                imageFrames = 36;
                energy-= 0.5;
                if (ranGen.nextInt(3) == 1)
                    getWorld().addObject(new Sword(character),ranGen.nextInt(1000),ranGen.nextInt(50));
            }
        }
        else if (command.equals("abilityT") && rest && energy > 30.0)
        {
            ability = true;
            abilityNumber = 3;
            imageFrames = 0;
            movement = false;
            attack = false;
            jump = false;
            imageFramesMax = 72;
            energy-= 10.0;
            Greenfoot.playSound("Sound//White//WhiteAbilityT.mp3");
        }
        else if (command.equals("abilityT") && ability && abilityNumber == 3 && energy > 2.0)
        {
            if (imageFrames >= 28 && imageFrames < 32)
            {
                imageFrames = 28;
                energy-= 0.3;
                hitpoint += 0.2;
            }
        }
        else if (command.equals("abilityUlt") && rest && energy > 50.0)
        {
            ability = true;
            abilityNumber = 4;
            imageFrames = 0;
            ultCounter = 45;
            movement = false;
            attack = false;
            jump = false;
            imageFramesMax = 44;
            energy-= 50.0;
        }
        else if  (!jump && !attack && !jumpAttack && !ability && !flash && !hurt && !dead)
        {
            movement = false;
            if (command.equals("moveLeft") && !inAir && y >= groundHeight)
            {
                movement = true;
                moveDirection = false;
                imageFramesMax = 12;
                x-= speed;
                y = groundHeight;
            }
            else if (command.equals("moveRight") && !inAir && y >= groundHeight)
            {
                movement = true;
                moveDirection = true;
                imageFramesMax = 12;
                x+= speed;
                y = groundHeight;
            }
            else if (command.equals("moveLeft"))
            {
                moveDirection = false;
                x -= jumpingSpeed;
            }
            else if (command.equals("moveRight"))
            {
                moveDirection = true;
                x += jumpingSpeed;
            }
        }
    }
    /**
     * set the position changes of the character;
     */
    private void positionChange(int getChange)
    {
        if (moveDirection)
        {   
            x += getChange;
        }
        else
        {
            x -= getChange;
        }
    }

    public boolean ifDead()
    {
        return dead;
    }
    /**
     * return the character's states to the world, and process this to change the state bars.
     */
    public double characterHitpoint()
    {
        if (hitpoint > 0.0)
        {
            return hitpoint/hitpointMax;
        }
        else
        {
            imageFrames = 0;
            dead = true;
            return 0.01;
        }
    }

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
    /**
     * add a object which represents the area which the character's attack had influenced;
     */
    private void dealtDamage(int getX, int getY, int getWidth, int getLength, int getTime, int getDelay, double getDamage,int applyForce)
    {
        if (character.equals("P1"))
        {
            if (moveDirection)
            {
                getWorld().addObject(new P1AttackArea(x + getX, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,moveDirection),x + getX, y + getY);
            } 
            else
            {   
                getWorld().addObject(new P1AttackArea(x - getX, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,moveDirection),x - getX, y + getY);
            }
        }
        else  if (character.equals("P2"))
        {
            if (moveDirection)
            {
                getWorld().addObject(new P2AttackArea(x + getX, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,moveDirection),x + getX, y + getY);
            } 
            else
            {   
                getWorld().addObject(new P2AttackArea(x - getX, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,moveDirection),x - getX, y + getY);
            }
        }
    }

    /**
     * Detect damage from the enemy attack
     */
    public void damageDetection ()
    {
        if (character.equals("P1"))
        {
            P2AttackArea getHit = (P2AttackArea)getOneIntersectingObject(P2AttackArea.class);
            if (getHit != null)
            {
                hurt = true;
                hitpoint-= getHit.damageDealt();
                if (getHit.returnForceDirection())
                    x+= getHit.dealForce();
                else
                    x-= getHit.dealForce();
            }
            else
            {
                hurt = false;
            }
        }
        else  if (character.equals("P2"))
        {
            P1AttackArea getHit = (P1AttackArea)getOneIntersectingObject(P1AttackArea.class);
            if (getHit != null)
            {
                hurt = true;
                hitpoint-= getHit.damageDealt();
                if (getHit.returnForceDirection())
                    x+= getHit.dealForce();
                else
                    x-= getHit.dealForce();
            }
            else
            {
                hurt = false;
            }
        }
    }
    //Reseting the character once receive the order from userInterface
    public void reset(int getX, int getY,boolean getDirection)
    {
        moveDirection = getDirection;
        x = getX;
        y = getY;
        dead = false;
        imageFrames = 0;
        imageFramesMax = 1;
        hitpoint = hitpointMax;
        energy = energyMax;
    }
}


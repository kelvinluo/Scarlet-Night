import greenfoot.*; 
import java.util.Random;
public class Ninja extends Characters
{
    /**
     * This is the character Ninja, its setting is the same as White and Ringer, since these are all made by Kelvin Luo
     *      * If want to see the detail, please check White class
     */
    public Queue queue = new Queue();

    private boolean dead = false;
    private GreenfootImage ninjaImage = new GreenfootImage("Ninja//ninjaNormal//ninjaNormal1.png");
    private String character = "P1";
    private Shadow shadow = new Shadow();
    private Random ranGen = new Random();
    double hitpointMax = 100;
    double hitpoint = hitpointMax;

    double  energyMax = 100;
    double  energy = energyMax;

    private int groundHeight = 450;
    private int x = 900,y = groundHeight;
    private int imageFrames = 1;
    private int imageFramesMax = 4;
    private int attackStyle = 1;
    private int deadFrames = 0;
    // true == right, false = left
    private boolean direction = true;

    private int speed = 0;
    private double verticalSpeed = 0;
    private int maxSpeed = 15;
    private double jumpHeight = 15.0;
    private double gravity = 0.0;

    //Cold times
    private boolean moveDirection = false;
    private boolean movement = false;
    private boolean attack = false;
    private boolean jumpAttack = false;
    private boolean hurt = false;
    private boolean jump = false;
    private boolean inAir = false;
    private boolean holdingButton = false;
    private boolean doubleJump = false;
    private boolean jumpAttacked = false;

    private boolean ability = false;
    private boolean abilityH = false;
    private boolean abilityR = false;
    private boolean abilityT = false;
    private int abilityTstyle = 1;
    private int abilityRColdTime = 0;

    public Ninja(String getPlayer,int getX,int getY)
    {
        this.character = getPlayer;
        if (getPlayer.equals("P2"))
        {
            moveDirection = false;
        }
        x = getX;
        y = getY;
    }

    public void act() 
    {
        if (!dead)
        {
            setPosition();
            imageChange();
            damageDetection ();
        }
        else if (dead)
        {
            deadImage();
        }
    }

    public void deadImage()
    {

        if (deadFrames < 35)
        {
            deadFrames++;
        }
        ninjaImage = new GreenfootImage("Ninja//ninjaDie//ninjaDie"+((deadFrames/3) +1)+".png");
        if (moveDirection == false)
        {
            ninjaImage.mirrorHorizontally();
        }
        setImage(ninjaImage);
        shadow.getPosition(x,y,moveDirection,10);
        shadow.getSize(groundHeight+30,40,8);
    }

    public void setPosition()
    {
        setLocation(x,y);
        setImage(ninjaImage);   
        if (abilityRColdTime > 0)
        {
            abilityRColdTime--;
        }
        if (speed > maxSpeed && !jumpAttack)
        {
            speed = maxSpeed;
        }
        else if (movement)
        {
            speed++;
        }
        else if (!movement && !jumpAttack)
        {
            speed = 0;
        }
        if (verticalSpeed <= 0.0)
        {
            verticalSpeed = 0.0;
        }
        if (y > groundHeight)
        {
            y = groundHeight;
            gravity = 0;
        }
        if (energy < energyMax && !ability && !jumpAttack)
        {
            energy+= 0.5;
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
            shadow.getPosition(x,y,moveDirection,-10);
            shadow.getSize(groundHeight+30,50,8);
        }
        else
        {
            shadow.getPosition(x,y,moveDirection,10);
            shadow.getSize(groundHeight+30,40,8);
        }
    }

    public void imageChange()
    {
        imageFrames++;

        /**
         * Attack

        if (attack && imageFrames == 10)
        {
        positionChange(20);
        }
         */
        /**
         * Jumping button release detection
         */
        if (hurt)
        {
            imageFrames = 0;
            ability = false;
            abilityH = false;
            abilityR = false;
            abilityT = false;
            jump = false;
            jumpAttack = false;
            attack = false;
            movement = false;
            gravity = 0.0;
        }
        if (jump && inAir)
        {
            imageFrames = 1;
        }

        if(imageFrames >= imageFramesMax)
        {
            imageFrames = 1;
            attack = false; 
            jumpAttack = false;
            ability = false;
            abilityH = false;
            abilityR = false;
            abilityT = false;
            verticalSpeed = 0;
        }
        else if (jumpAttack && imageFramesMax <= imageFrames)
        {
            jumpAttack = false;
            jumpHeight = 15.0;
            speed = 0;
            gravity = (double)jumpHeight;
        }
        else if (jumpAttack && inAir)
        {
            positionChange(speed);
            speed -= 1;
        }

        if (!inAir)
        {
            jumpAttacked = false;
        }

        if (hurt)
        {
            ninjaImage = new GreenfootImage("Ninja//ninjaHurt//ninjaHurt.png");
        }
        else if (abilityT)
        {
            ninjaImage = new GreenfootImage("Ninja//ninjaAbilityT//ninjaAbilityT" + (imageFrames/3 + 1) +"Style"+ abilityTstyle +".png");
            if (imageFrames == 6)
            {
                dealtDamage(0,0,150,20,1,0,5.0,60);
            }
        }
        else if (abilityR)
        {
            ninjaImage = new GreenfootImage("Ninja//ninjaAbilityR//ninjaAbilityR" + (imageFrames/3 + 1) + ".png");
            jump = false;
            doubleJump = false;
            if (imageFrames == 1 && inAir)
            {
                imageFrames = 33;
            }
            if (inAir)
            {
                getWorld().addObject(new RedShadow(moveDirection,true),x,y);
                verticalSpeed -= 1.0;
                y += (double)verticalSpeed;
                dealtDamage(0,0,50,100,1,0,2.0,20);
            }
            if (y >= groundHeight && inAir)
            {
                inAir = false;
                imageFrames = imageFramesMax;
                abilityR = false;
                y = groundHeight;
                jumpHeight = 15.0;
                abilityRColdTime = 100;
                gravity = 15.0;
            }
            else  if (imageFrames >= 15 && imageFrames < 33 && !inAir)
            {
                y -= (double)verticalSpeed;
                verticalSpeed -= 1.0;
            }
            else if (imageFrames >= 33 && y < groundHeight && !inAir)
            {
                if (imageFrames == 33)
                {
                    Greenfoot.playSound("Sound//Ninja//ninjaAbilityR.mp3");
                }
                dealtDamage(0,0,80,80,1,0,2.0,20);
                getWorld().addObject(new RedShadow(moveDirection,false),x,y);
                if ((y + 7) < groundHeight)
                {
                    y+= 20;
                }
                else
                {
                    y = groundHeight;
                }
                if (moveDirection)
                {
                    x+= 50;
                }
                else
                {
                    x-= 50;
                }
                abilityRColdTime = 100;
                if (x <= 0 || x >= 1000)
                {
                    imageFrames = imageFramesMax;
                    abilityR = false;
                }
            }
        }
        else if (abilityH)
        {
            ninjaImage = new GreenfootImage("Ninja//ninjaAbilityH//ninjaAbilityH" + (imageFrames/5 + 1) + ".png");
            if (imageFrames == 25)
            {
                positionChange(-200);
                y-= 110;
                for (int counter = 10; counter <= 50; counter+= 10)
                {
                    if (moveDirection)
                    {
                        getWorld().addObject(new Kuwu(moveDirection,counter,character),x+20,y);
                    }
                    else
                    {
                        getWorld().addObject(new Kuwu(moveDirection,counter,character),x-20,y);
                    }
                }
            }
            else if (imageFrames > 40 && imageFrames < 75)
            {
                positionChange(-5);
                gravity += 0.5;
                y += (int)(gravity);
            }
        }
        else if (attack)
        {
            ninjaImage = new GreenfootImage("Ninja//ninjaAttack//ninjaAttack" + (imageFrames/5 + 1) + "Style" + attackStyle +".png");
        }
        else if (jumpAttack)
        {
            ninjaImage = new GreenfootImage("Ninja//ninjaJump//jumpAttack" + (imageFrames/5 + 1)+".png");
            dealtDamage(50,0,150,20,1,0,1.0,3);
        }
        else if (doubleJump)
        {
            imageFrames = 0;
            ninjaImage = new GreenfootImage("Ninja//ninjaJump//jump3.png");
            gravity += 0.5;
            y -= (int)(jumpHeight) - (int)(gravity);
            if (y >= groundHeight)
            {
                inAir = false;
                doubleJump = false;
                jumpHeight = 15.0;
                gravity = 0;
                y = groundHeight;
            }
        }
        else if (jump)
        {
            ninjaImage = new GreenfootImage("Ninja//ninjaJump//jump1.png");
        }
        else if (inAir)
        {
            imageFrames = 0;
            ninjaImage = new GreenfootImage("Ninja//ninjaJump//jump2.png");
            gravity += 0.5;
            y -= (int)(jumpHeight) - (int)(gravity);
            if (y >= groundHeight && inAir && gravity > 5.0)
            {
                inAir = false;
                jumpHeight = 15.0;
                gravity = 0;
            }
        }
        else if (movement && !jump && !inAir)
        {
            ninjaImage = new GreenfootImage("Ninja//ninjaRun//ninjaRun" + (imageFrames/2 + 1)  + ".png");
        }

        else if (hurt)
        {
            ninjaImage = new GreenfootImage("Ninja//ninjaNormal//ninjaNormal" + (imageFrames/10 + 1)  + ".png");
        }
        else
        {
            ninjaImage = new GreenfootImage("Ninja//ninjaNormal//ninjaNormal" +(imageFrames/10 + 1)  + ".png");
            imageFramesMax = 40;
            y = groundHeight;
            gravity = 0.0;
            jumpHeight = 15.0;
            doubleJump = false;
        }

        if (moveDirection == false)
        {
            ninjaImage.mirrorHorizontally();
        }
        movement = false;
    }

    public void control(String command)
    {
        Random randomGen = new Random();
        boolean rest = false;
        if (!ability && !inAir && !jumpAttacked && !attack && !jump && !doubleJump)
        {
            rest = true;
        }
        if (!(command.equals("jump")) && inAir && jump)
        {
            jump = false;
            movement = false;
        }
        if (command.equals("abilityT") && rest && energy > 30.0)
        {
            ability = true;
            abilityT = true;
            imageFramesMax = 30;
            imageFrames = 0;
            energy -= 30.0;
            abilityTstyle = ranGen.nextInt(2)+1;
            Greenfoot.playSound("Sound//Ninja//ninjaAbilityT.mp3");
        }
        else if (command.equals("abilityH") && rest && energy > 50.0)
        {
            ability = true;
            abilityH = true;
            imageFramesMax = 90;
            imageFrames = 0;
            energy -= 50.0;
        }
        else if (command.equals("abilityR") && rest && energy > 30.0 && abilityRColdTime == 0)
        {
            ability = true;
            abilityR = true;
            imageFramesMax = 69;
            imageFrames = 0;
            energy -= 30.0;
            verticalSpeed = 20.0;
        }
        else if (command.equals("abilityR") && !jumpAttack && inAir && !ability && !jump && energy > 30.0 && abilityRColdTime == 0)
        {
            ability = true;
            abilityR = true;
            jump = false;
            doubleJump =false;
            imageFramesMax = 69;
            imageFrames = 0;
            energy -= 30.0;
            verticalSpeed = 30.0;
            Greenfoot.playSound("Sound//Ninja//ninjaAbilityR.mp3");
        }
        else if (command.equals("jump") && rest)
        {
            jump = true;
            inAir = true;                                               
            imageFramesMax = 5;
            imageFrames = 0;
            gravity = 0.0;
        }
        else if (command.equals("jump") && inAir && attack == false && doubleJump == false && jump == false && !jumpAttack)
        {
            inAir = true;
            doubleJump = true;
            imageFramesMax = 2;
            imageFrames = 0;
            gravity = 0.0;
        }
        else if (command.equals("attack") && inAir && !jump && !jumpAttack && !jumpAttacked && energy > 30.0)
        {
            jumpAttack = true;
            jumpAttacked = true;
            attack = false;
            imageFramesMax = 20;
            imageFrames = 0;
            maxSpeed = 20;
            speed = 30;
            energy -= 30.0;
            Greenfoot.playSound("Sound//Ninja//NinjaCharge.mp3");
        }
        else if (command.equals("attack") && rest)
        {
            attack = true;
            jump = false;
            imageFrames = 0;
            imageFramesMax = 15;
            int previousStyle = attackStyle;
            while (attackStyle == previousStyle)
            {
                attackStyle = randomGen.nextInt(3)+1;
            }
            dealtDamage(40,0,20,70,1,5,1.0,20);
            Greenfoot.playSound("Sound//Ninja//attack.mp3");
        }
        else if  (!jump && !attack && !command.equals("jump") && !jumpAttack && !ability && !dead && !hurt)
        {
            movement = false;
            if (command.equals("moveLeft") && !inAir && y >= groundHeight)
            {
                movement = true;
                moveDirection = false;
                imageFramesMax = 10;
                x-= speed;
                y = groundHeight;
            }
            else if (command.equals("moveRight") && !inAir && y >= groundHeight)
            {
                movement = true;
                moveDirection = true;
                imageFramesMax = 10;
                x+= speed;
                y = groundHeight;
            }
            else if (command.equals("moveLeft"))
            {
                moveDirection = false;
                x -= 4;
            }
            else if (command.equals("moveRight"))
            {
                moveDirection = true;
                x += 4;
            }
        }
    }

    public void positionChange(int measure)
    {
        if (moveDirection)
        {   
            x += measure;
        }
        else
        {
            x -= measure;
        }
    }

    public boolean ifDead()
    {
        return dead;
    }

    public double characterHitpoint()
    {
        if (hitpoint > 0.0)
        {
            return hitpoint/hitpointMax;
        }
        else
        {
            dead = true;
            hurt = false;
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
        deadFrames = 0;
    }
}
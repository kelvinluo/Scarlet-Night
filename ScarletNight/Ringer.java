import greenfoot.*; 
import java.util.Random;
public class Ringer extends Characters
{
    /**
     * This is the character Ninja, its setting is the same as White and Ringer, since these are all made by Kelvin Luo
     * If want to see the detail, please check White class
     */
    private GreenfootImage ringerImage = new GreenfootImage("Ringer//ringerNormal//ringerNormalWithWeapon1.png");
    private String character = "P1";
    private Shadow shadow = new Shadow();
    private Random ranGen = new Random();
    private boolean dead = false;
    double hitpointMax = 125;
    double hitpoint = hitpointMax;

    double  energyMax = 100;
    double  energy = energyMax;

    private int groundHeight = 455;
    private int x = 900,y = groundHeight;
    private int imageFrames = 1;
    private int imageFramesMax = 4;
    private int attackStyle = 1;
    private int attackDistance = 300;

    // true == right, false = left
    private boolean direction = true;
    private int speed = 0;
    private double verticalSpeed = 0;
    private int maxSpeed = 10;
    private double jumpHeight = 15.0;
    private double gravity = 0.0;

    //Cold times
    private boolean moveDirection = true;
    private boolean movement = false;
    private boolean attack = false;
    private boolean jumpAttack = false;
    private boolean hurt = false;
    private boolean jump = false;
    private boolean inAir = false;
    private boolean holdingButton = false;
    private boolean doubleJump = false;
    private boolean jumpAttacked = false;
    private boolean withWeapon = true;

    private boolean ability = false;
    private boolean abilityH = false;
    private boolean abilityR = false;
    private int abilityRColdTime = 0;
    private int deadFrames = 0;
    public Ringer(String getPlayer,int getX,int getY)
    {
        this.character = getPlayer;
        if (getPlayer.equals("P2"))
        {
            moveDirection = false;
        }
        x = getX;
        y = getY;
    }
    private Ring ring = new Ring(x,y,moveDirection,10,character);
    public void act() 
    {
        if (!dead)
        {
            setPosition();
            imageChange();
            damageDetection ();
            detectRing();
        }
        else
        {
            deadImage();
        }
    }
    public void deadImage()
    {

        if (deadFrames < 30)
        {
            deadFrames++;
        }
        ringerImage = new GreenfootImage("Ringer//ringerDie//ringerDie"+((deadFrames/3) +1)+".png");
        if (moveDirection == false)
        {
            ringerImage.mirrorHorizontally();
        }
        setImage(ringerImage);
        shadow.getPosition(x,y,moveDirection,10);
        shadow.getSize(groundHeight+30,40,8);
    }
    public void detectRing()
    {
        if (!withWeapon)
        {
            if (ring.getX() < x + 20 && ring.getX() > x - 20 && ring.getY() < y + 30 && ring.getY() > y - 30 && ring.returned())
            {
                withWeapon = true;
                ring.remove();
            }
        }
    }

    public void setPosition()
    {
        setLocation(x,y);
        setImage(ringerImage);   
        if (abilityRColdTime > 0)
        {
            abilityRColdTime--;
        }
        if (speed > maxSpeed)
        {
            speed = maxSpeed;
        }
        if (movement)
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
            shadow.getPosition(x,y,moveDirection,0);
            shadow.getSize(groundHeight+25,50,8);
        }
        else if (attack)
        {
            shadow.getPosition(x,y,moveDirection,-10);
            shadow.getSize(groundHeight+25,60,8);
        }
        else
        {
            shadow.getPosition(x,y,moveDirection,10);
            shadow.getSize(groundHeight+25,40,8);
        }
        if (!withWeapon)
        {
            ring.follow(x,y);
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
            abilityR = false;
            abilityH = false;
            attack = false;
            jump = false;
        }
        
        if (jump && inAir)
        {
            imageFrames = 1;
        }

        if(imageFrames >= imageFramesMax && !inAir)
        {
            imageFrames = 1;
            attack = false; 
            jumpAttack = false;
            ability = false;
            abilityH = false;
            abilityR = false;
            verticalSpeed = 0;
        }
        else if (jumpAttack && imageFramesMax <= imageFrames)
        {
            jumpAttack = false;
            jumpHeight = 15.0;
            speed = 0;
            gravity = (double)jumpHeight;
        }

        if (!inAir)
        {
            jumpAttacked = false;
        }

        if (abilityR)
        {
            ringerImage = new GreenfootImage("Ringer//ringerAbilityR//ringerAbilityR"+(imageFrames/4 + 1)+".png");
            if (((imageFrames/4)+1) == 5)
            {
                y-= 50;
                dealtDamage(0,0,100,100,1,0,5.0,50);
                Greenfoot.playSound("Sound//Ringer//ringerAbilityR.mp3");
            }
            else if (((imageFrames/4)+1) > 10 && ((imageFrames/4)+1) < 15)
            {
                y+= 20;
                dealtDamage(0,0,100,100,1,0,5.0,50);
            }
            if (y >= groundHeight)
            {
                y = groundHeight;
            }
        }
        else if (abilityH)
        {
            ringerImage = new GreenfootImage("Ringer//ringerAbilityH//ringerAbilityH"+(imageFrames/4 + 1)+".png");
            if (((imageFrames/4)+1) == 3)
            {
                y-= 30;
            }
            else if (((imageFrames/4)+1) > 3 && ((imageFrames/3)+1) < 6)
            {
                y-= 10;
            }
            else if (((imageFrames/4)+1) > 6 && ((imageFrames/3)+1) < 13)
            {
                y+= 20;
                getWorld().addObject(new SpitFire(moveDirection,character),x,y);
            }
            if (y >= groundHeight)
            {
                y = groundHeight;
            }
        }
        else if (attack)
        {
            ringerImage = new GreenfootImage("Ringer//ringerAttack//ringerAttack"+(imageFrames/3 + 1)+".png");
            if (imageFrames == 12)
            {
                withWeapon = false;
                ring = new Ring(x,y,moveDirection,attackDistance,character);
                getWorld().addObject(ring,x,y);
                Greenfoot.playSound("Sound//Ringer//ringerAttack.mp3");
            }
        }
        else if (jumpAttack)
        {
            ringerImage = new GreenfootImage("Ringer//ringerJump//ringerJumpAttack"+(imageFrames/3 + 1)+".png");
        }
        else if (doubleJump)
        {
            imageFrames = 0;
            if (withWeapon)
            {
                ringerImage = new GreenfootImage("Ringer//ringerJump//ringerJumpWithWeapon2.png");
            }
            else
            {
                ringerImage = new GreenfootImage("Ringer//ringerJump//ringerJumpWithoutWeapon2.png");
            }
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
            if (withWeapon)
            {
                ringerImage = new GreenfootImage("Ringer//ringerJump//ringerJumpWithWeapon1.png");
            }
            else
            {
                ringerImage = new GreenfootImage("Ringer//ringerJump//ringerJumpWithoutWeapon1.png");
            }
        }
        else if (inAir)
        {
            imageFrames = 0;
            if (withWeapon)
            {
                ringerImage = new GreenfootImage("Ringer//ringerJump//ringerJumpWithWeapon2.png");
            }
            else
            {
                ringerImage = new GreenfootImage("Ringer//ringerJump//ringerJumpWithoutWeapon2.png");
            }
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
            if(withWeapon)
            {
                ringerImage = new GreenfootImage("Ringer//ringerMovement//ringerMoveWithWeapon" +(imageFrames/3 + 1)  + ".png");
            }
            else
            {
                ringerImage = new GreenfootImage("Ringer//ringerMovement//ringerMoveWithoutWeapon" +(imageFrames/3 + 1)  + ".png");
            }
        }
        else
        {
            if(withWeapon)
            {
                ringerImage = new GreenfootImage("Ringer//ringerNormal//ringerNormalWithWeapon" +(imageFrames/10 + 1)  + ".png");
            }
            else
            {
                ringerImage = new GreenfootImage("Ringer//ringerNormal//ringerNormalWithoutWeapon" +(imageFrames/10 + 1)  + ".png");
            }
            imageFramesMax = 50;
            y = groundHeight;
            gravity = 0.0;
            jumpHeight = 15.0;
            doubleJump = false;
            attackDistance = 300;
        }

        if (moveDirection == false)
        {
            ringerImage.mirrorHorizontally();
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
        if (command.equals("abilityH") && rest && energy > 50.0)
        {
            ability = true;
            abilityH = true;
            imageFrames = 0;
            imageFramesMax = 60;
            energy-= 50.0;
        }
        else if (command.equals("abilityR") && rest && energy > 50.0)
        {
            ability = true;
            abilityR = true;
            imageFrames = 0;
            imageFramesMax = 76;
            energy-= 50.0;
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
        else if (command.equals("attack") && inAir && !jump && !jumpAttack && !jumpAttacked && withWeapon)
        {
            withWeapon = false;
            jumpAttack = true;
            jumpAttacked = true;
            attack = false;
            imageFramesMax = 21;
            imageFrames = 0;
            ring = new Ring(x,y,moveDirection,attackDistance,character);
            getWorld().addObject(ring,x,y);
        }
        else if (command.equals("attack") && rest && withWeapon)
        {
            attack = true;
            jump = false;
            imageFrames = 0;
            imageFramesMax = 27;
        }
        else if (command.equals("attack") && attack && withWeapon && imageFrames <= 4 && imageFrames >= 2)
        {
            imageFrames = 2;
            if (attackDistance < 1000)
            {
                attackDistance+=10;
            }
        }
        else if  (!jump && !attack && !command.equals("jump") && !jumpAttack && !ability && !hurt)
        {
            movement = false;
            if (command.equals("moveLeft") && !inAir && y >= groundHeight)
            {
                movement = true;
                moveDirection = false;
                imageFramesMax = 33;
                x-= speed;
                y = groundHeight;
            }
            else if (command.equals("moveRight") && !inAir && y >= groundHeight)
            {
                movement = true;
                moveDirection = true;
                imageFramesMax = 33;
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

    public void damageDetection ()
    {
        hurt = false;
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
        deadFrames = 0;
        hitpoint = hitpointMax;
        energy = energyMax;
    }

    private void dealtDamage(int getX, int getY, int getWidth, int getLength, int getTime, int getDelay,double getDamage, int applyForce)
    {
        if (character.equals("P1"))
        {
            if (direction)
            {
                getWorld().addObject(new P1AttackArea(getX() + getX, getY() + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,direction),getX() + getX, getY() + getY);
            } 
            else
            {   
                getWorld().addObject(new P1AttackArea(getX() - getX, getY() + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,direction),getX() - getX, getY() + getY);
            }
        }
        else  if (character.equals("P2"))
        {
            if (direction)
            {
                getWorld().addObject(new P2AttackArea(getX() + getX, getY() + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,direction),getX() + getX, getY() + getY);
            } 
            else
            {   
                getWorld().addObject(new P2AttackArea(getX() - getX, getY() + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,direction),getX() - getX, getY() + getY);
            }
        }
    }

}
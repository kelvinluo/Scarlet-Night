import greenfoot.*; 
import java.util.Random;
public class Bond extends Characters
{
    // create and innitalize new greenfoot image of James Bond
    private GreenfootImage bondImage = new GreenfootImage("Ranged//gunnerNormal//gunnerNormal1.png");    
    private Shadow shadow = new Shadow(); // James's shadow underneath him, size changes accordingly to height
    private int groundHeight = 450; // set initial ground height
    private int x = 20,y = groundHeight; // initialized values
    private int imageFrames = 1; // start like all other characters at sprite 1 (frame 1)
    private int imageFramesMax = 4; // cap sprite at frame 4, for now.
    private int attackStyle = 1; // the assign integer value to the numerous attack animations    
    private boolean direction = true; // true == right, false = left
    private String character;  // empty string of which will store the command of various skills sent to this class from world
    private int speed = 0; // immobile when first start game
    private int maxSpeed = 7; // fastest velocity Bond can attain
    private double jumpHeight = 3.0; // highest altitude Bond can attain
    private double gravity = 0.0; // set gravity to 0 at the start of game
    private double hitpointMax = 100; // 100 hp
    private double hitpoint = hitpointMax; // transfer to new variable of which will be displayed on screen
    private double  energyMax = 100; // 100 mana
    private double  energy = energyMax; //  transfer to new variable of which will be displayed on screen
    private boolean dead = false; // set current state of character to alive

    //Cold times
    private boolean moveDirection = true;
    private boolean movement = false;
    private boolean attack = false;
    private boolean hurt = false;
    private boolean jump = false;
    private boolean inAir = false;
    private boolean holdingButton = false;
    private boolean flash = false;
    private boolean ult = false;
    private boolean abilityR = false;
    private boolean abilityT = false;
    private boolean gaji= false;
        // Constructor for Bond class
    public Bond(String getCharacter,int getX,int getY)
    {
        character = getCharacter;
        if (getCharacter.equals("P2"))
        {
            moveDirection = false;
        }
        x = getX;
        y = getY;
    }
    // Act method of which constantly checks for certain requirements and conditions   
    public void act() 
    {
        if (!dead)
        {
            // locates the position of the character
            setPosition();
            // frames, animation
            imageChange();
            // Gajiwala :D
            damageDetection();
        }   
        else 
        {   
            if (imageFrames < 76)
            {
                bondImage = new GreenfootImage ("Ranged//gunnerDie//gunnerDie" + ((imageFrames/4) +1) + ".png");
                imageFrames++;  
                setImage(bondImage);
                if (imageFrames == 1)
                {
                    getWorld().removeObject(shadow);
                }  
            }
        }
    }
    
    // tracks, traces and sets the position of the character
    public void setPosition()
    {
        setLocation(x,y);
        setImage(bondImage);

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
        if (x<0)
        {
            x = 0;
        }
        else if (x>1000)
        {
            x = 1000;
        }
        if (energy < energyMax)
        {
            energy+= 0.1;
        }
        if (hitpoint <= 0.0)
        {
            hitpoint = 0.0;
            dead = true;
            imageFrames = 4;
        }
    }
    // animation, where the magic happens and brings the character to life!
    public void imageChange()
    {
        imageFrames++;
        /**
         * Attack
         */
        /**
         * Jumping button release detection
         */
        if (hurt)
        {
            abilityR = false;
            abilityT = false;
            ult = false;
            attack = false;
            jump = false;
            imageFrames = 0;
        }
        if (!(Greenfoot.isKeyDown("j")) && inAir && jump)
        {
            jump = false;
            movement = false;
        }

        if (jump)
        {
            imageFrames = 2;
        }
        else if(imageFrames >= imageFramesMax && !inAir)
        {
            // resets the animation. Stops it from looping after initial click
            imageFrames = 0;     
            attack = false;
            ult = false;
            abilityR = false;
            abilityT = false;
        }

        if (attack)
        {
            bondImage = new GreenfootImage("Ranged//gunnerAttack//gunnerAttack" + (imageFrames/6 + 1) +".png");
        }
        else if (abilityR)
        {            
            bondImage = new GreenfootImage("Ranged//gunnerAbilityR//gunnerAbilityR" + (imageFrames/4 +1) + ".png");
            if (imageFrames == 25)
            {
                y-= 300;
                Greenfoot.playSound("Sound//Bond//gunnerAbilityR.mp3");
            }
            if (imageFrames > 25 && imageFrames < 60)
            {
                y += 1;
                gaji = true;
                getWorld().addObject(new Bullet(character), x, y);
            }
            if (imageFrames > 60 && imageFrames < 80)
            {
                y += 15;
                gaji = false;
            }
            
            if (y >= groundHeight)
            {
                y = groundHeight;
            }
        }

        else if (jump)
        {
            bondImage = new GreenfootImage("Ranged//gunnerJump//gunnerJump" + (imageFrames - 1)  + ".png");
        }
        else if (ult)
        {
            bondImage = new GreenfootImage("Ranged//gunnerAbility//gunnerAbilityH00" + imageFrames+ ".png");
            if(imageFrames > 18 && imageFrames < 56)
            {
                if (moveDirection)
                {
                    getWorld().addObject(new Bullet(moveDirection, character),x + 50,y + ((new Random()).nextInt(10))-20);
                }
                else
                {
                    getWorld().addObject(new Bullet(moveDirection, character),x - 50,y + ((new Random()).nextInt(10))-20);
                }
            }
        }
        else if (abilityT)
        {
            bondImage = new GreenfootImage("Ranged//gunnerAbilityT//gunnerAbilityT" + ((imageFrames/3)) + ".png");
            if(imageFrames == 65)
            {
                if (moveDirection)
                {
                    getWorld().addObject(new Grenade(moveDirection, character, x + 50, y - 20),x + 50, y - 20);
                }
                else
                {
                    getWorld().addObject(new Grenade(moveDirection, character, x - 50, y - 20),x - 50, y - 20);
                }
            }
        }
        else if (inAir)
        {
            imageFrames = 0;
            bondImage = new GreenfootImage("Ranged//gunnerJump//gunnerJump2.png");
            gravity += 0.5;
            y -= (int)(jumpHeight) - (int)(gravity);
            if (y >= groundHeight && inAir && gravity > 5.0)
            {
                inAir = false;
                jumpHeight = 15;
                gravity = 0;
            }
        }         
        else if (movement && !jump && !inAir)
        {
            bondImage = new GreenfootImage("Ranged//gunnerRun//gunnerRun" + (imageFrames/4 + 1)  + ".png");
        }

        else if (hurt)
        {
            bondImage = new GreenfootImage("Ranged//gunnerNormal//gunnerNormal" + (imageFrames/10 + 1)  + ".png");
        }
        else
        {
            bondImage = new GreenfootImage("Ranged//gunnerNormal//gunnerNormal" +(imageFrames/10 + 1)  + ".png");
            imageFramesMax = 40;
            y = groundHeight;
            gravity = 0.0;
            jumpHeight = 15;
            flash = false;
        }

        if (moveDirection == false)
        {
            bondImage.mirrorHorizontally();
        }
        movement = false;
        getWorld().addObject(shadow,x,y);
        if (movement)
        {
            shadow.getPosition(x,y,moveDirection,0);
            shadow.getSize(groundHeight+30,50,8);
        }
        else
        {
            shadow.getPosition(x,y,moveDirection,0);
            shadow.getSize(groundHeight+30,40,8);
        }
    }
    // character commands
    public void control(String command)
    {
        Random randomGen = new Random();
        if (command.equals("jump") && inAir == false && attack == false && ult == false && abilityR == false)
        {
            jump = true;
            inAir = true;                                               
            imageFramesMax = 5;
            imageFrames = 0;
            gravity = 0.0;
        }
        else if (command.equals("abilityH") && ult == false && attack == false && inAir == false && jump == false && energy >= 30 && abilityR == false)
        {
            ult = true;
            jump = false;            
            movement = false;                
            inAir = false;          
            holdingButton = false;
            imageFrames = 0;
            imageFramesMax = 72;
            energy-= 30;
            Greenfoot.playSound("Sound//Bond//gunnerAbilityH.mp3");
        }

        else if (command.equals("attack") && attack == false && inAir == false && jump == false && ult == false && abilityR == false && abilityT == false)
        {
            attack = true;
            jump = false;
            imageFrames = 0;
            imageFramesMax = 25;
            if (moveDirection)
            {
                getWorld().addObject(new Bullet(moveDirection, character),x + 50,y + ((new Random()).nextInt(10))-20);
            }
            else
            {
                getWorld().addObject(new Bullet(moveDirection, character),x - 50,y + ((new Random()).nextInt(10))-20);
            }
            Greenfoot.playSound("Sound//Bond//gunnerAttack.mp3");
        }
        else if (command.equals("abilityR") && attack == false && inAir == false && jump == false && ult == false && abilityR == false && energy >= 30 && abilityT == false)
        {
            abilityR = true;              
            ult = false;                        
            movement = false;                        
            holdingButton = false;             
            jump = false;
            inAir = false;                                               
            imageFramesMax = 116;
            imageFrames = 0;
            gravity = 0.0;
            energy-= 30;
        }
        else if (command.equals("abilityT") && attack == false && inAir == false && jump == false && ult == false && abilityR == false && energy >= 20 && abilityT == false)
        {
            ult = false;
            abilityR = false;
            abilityT = true;                        
            movement = false;           
            jump = false;
            inAir = false;                                               
            imageFramesMax = 75;
            imageFrames = 3;    
            energy-= 20;
        }   
        else if  (!ult && !jump && !attack && !command.equals("jump") && !abilityT && !hurt && !abilityR && !ult)
        {
            movement = false;
            if (command.equals("moveLeft") && !inAir && y >= groundHeight)
            {
                movement = true;
                moveDirection = false;
                imageFramesMax = 24;
                x-= speed;
                y = groundHeight;
            }
            else if (command.equals("moveRight") && !inAir && y >= groundHeight)
            {
                movement = true;
                moveDirection = true;
                imageFramesMax = 24;
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
    
    // character selection
    private void dealtDamage(int getX, int getY, int getWidth, int getLength, int getTime, int getDelay, double getDamage,int applyForce)
    {
        if (character.equals("P1")) // player 1
        {
            if (moveDirection) 
            {
                // add new object (player 1) into the world at the correction orientation
                getWorld().addObject(new P1AttackArea(x + getX, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,moveDirection),x + getX, y + getY); 
            } 
            else
            {   // add new object (player 2) into the world at the opposite orientation
                getWorld().addObject(new P1AttackArea(x - getX, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,moveDirection),x - getX, y + getY);
            }
        }
        else  if (character.equals("P2")) // player 2
        {
            if (moveDirection) 
            {
                 // add new object (player 1) into the world at the correction orientation
                getWorld().addObject(new P2AttackArea(x + getX, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,moveDirection),x + getX, y + getY);
            } 
            else
            {   
                // // add new object (player 2) into the world at the opposite orientation
                getWorld().addObject(new P2AttackArea(x - getX, y + getY,getWidth,getLength,getTime,getDelay,getDamage,applyForce,moveDirection),x - getX, y + getY);
            }
        }
    }
    // collision detection
    public void damageDetection ()
    {
        hurt = false;
        if (character.equals("P1"))
        {
            P2AttackArea getHit = (P2AttackArea)getOneIntersectingObject(P2AttackArea.class);
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
    }
        public boolean ifDead()
    {
        return dead;
    }
}
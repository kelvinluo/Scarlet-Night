import greenfoot.*;
/**
 * This method is created for loading all the pictures before using them, so the game will run softer;
 */
public class Load extends Cover
{
    private String character1, character2;
    private GreenfootImage loadImage = new GreenfootImage("whiteSwordMan//whiteNormal//whiteNormal1.png");

    private int imageFrames = 0;
    private int imageFramesMax = 5;
    private LoadingScreen load = new LoadingScreen();
    private boolean end = false;
    public Load(String getChar1, String getChar2)
    {
        character1 = getChar1;
        character2 = getChar2;
    }

    public void act() 
    {
        getWorld().addObject(load,500,300);
        if (character1.equals("White"))
        {
            imageChangeWhite(0);
        }
        else if (character1.equals("Ninja"))
        {
            imageChangeNinja(0);
        }
        else if (character1.equals("Mage"))
        {
            imageChangeMage(0);
        }
        else if (character1.equals("Ringer"))
        {
            imageChangeRinger(0);
        }
        else if (character1.equals("Bond"))
        {
            imageChangeBond(0);
        }
        if (character2.equals("White"))
        {
            imageChangeWhite(0);
        }
        else if (character2.equals("Ninja"))
        {
            imageChangeNinja(0);
        }
        else if (character2.equals("Mage"))
        {
            imageChangeMage(0);
        }
        else if (character2.equals("Ringer"))
        {
            imageChangeRinger(0);
        }
        else if (character2.equals("Bond"))
        {
            imageChangeBond(0);
        }
        setImage(new GreenfootImage("ballsI.png"));
        getWorld().removeObject(load);
        end = true;
    }

    /**
     * The foloowing methods are used to load pictures of different characters
     */
    
    public void imageChangeBond(int getList)
    {
        if (getList == 10)
        {
            return ;
        }
        imageFrames++;
        if (getList == 1)
        {
            loadImage = new GreenfootImage("Ranged//gunnerRun//gunnerRun"+imageFrames+".png");
            imageFramesMax = 6;
        }
        else if (getList == 2)
        {
            loadImage = new GreenfootImage("Ranged//gunnerNormal//gunnerNormal"+imageFrames+".png");
            imageFramesMax = 4;
        }
        else if (getList == 3)
        {
            loadImage = new GreenfootImage("Ranged//gunnerJump//gunnerJump"+imageFrames+".png");
            imageFramesMax = 2;
        }
        else if (getList == 4)
        {
            loadImage = new GreenfootImage("Ranged//gunnerDie//gunnerDie"+imageFrames+".png");
            imageFramesMax = 20;
        }
        else if (getList == 5)
        {
            loadImage = new GreenfootImage("Ranged//gunnerAttack//gunnerAttack"+imageFrames+".png");
            imageFramesMax = 5;
        }
        else if (getList == 6)
        {
            loadImage = new GreenfootImage("Ranged//gunnerAbilityT//gunnerAbilityT"+imageFrames+".png");
            imageFramesMax = 25;
        }
        else if (getList == 7)
        {
            loadImage = new GreenfootImage("Ranged//gunnerAbilityR//gunnerAbilityR"+imageFrames+".png");
            imageFramesMax = 58;
        }
        else if (getList == 8)
        {
            loadImage = new GreenfootImage("Ranged//gunnerAbility//gunnerAbilityH00"+imageFrames+".png");
            imageFramesMax = 72;
        }
        else if (getList == 9)
        {
            loadImage = new GreenfootImage("Ranged//explosive//explosive00"+imageFrames+".png");
            imageFramesMax = 27;
        }
        if (imageFrames == imageFramesMax)
        {
            getList++;
            imageFrames = 0;
        }
        setImage(loadImage);
        imageChangeNinja(getList);
    }

    public void imageChangeRinger(int getList)
    {
        if (getList == 14)
        {
            return ;
        }
        imageFrames++;
        if (getList == 1)
        {
            loadImage = new GreenfootImage("Mage//ringerNormal//ringerNormalWithoutWeapon"+imageFrames+".png");
            imageFramesMax = 5;
        }
        else if (getList == 2)
        {
            loadImage = new GreenfootImage("Mage//ringerNormal//ringerNormalWithWeapon"+imageFrames+".png");
            imageFramesMax = 5;
        }
        else if (getList == 3)
        {
            loadImage = new GreenfootImage("Mage//ringerMovement//ringerMoveWithoutWeapon"+imageFrames+".png");
            imageFramesMax = 11;
        }
        else if (getList == 4)
        {
            loadImage = new GreenfootImage("Mage//ringerMovement//ringerMoveWithWeapon"+imageFrames+".png");
            imageFramesMax = 11;
        }
        else if (getList == 5)
        {
            loadImage = new GreenfootImage("Mage//ringerJump//ringerJumpWithoutWeapon"+imageFrames+".png");
            imageFramesMax = 2;
        }
        else if (getList == 6)
        {
            loadImage = new GreenfootImage("Mage//ringerJump//ringerJumpWithWeapon"+imageFrames+".png");
            imageFramesMax = 2;
        }
        else if (getList == 7)
        {
            loadImage = new GreenfootImage("Mage//ringerJumpt//ringerJumpAttack"+imageFrames+".png");
            imageFramesMax = 7;
        }
        else if (getList == 8)
        {
            loadImage = new GreenfootImage("Mage//ringerMovement//ringerDie"+imageFrames+".png");
            imageFramesMax = 11;
        }
        else if (getList == 9)
        {
            loadImage = new GreenfootImage("Mage//ringerAttack//ringerAttack"+imageFrames+".png");
            imageFramesMax = 9;
        }
        else if (getList == 10)
        {
            loadImage = new GreenfootImage("Mage//ringerAbilityR//ringerAbilityR"+imageFrames+".png");
            imageFramesMax = 19;
        }
        else if (getList == 11)
        {
            loadImage = new GreenfootImage("Mage//ringerAbilityH//ringerAbilityH"+imageFrames+".png");
            imageFramesMax = 15;
        }
        else if (getList == 12)
        {
            loadImage = new GreenfootImage("Mage//ringerAbilityH//bomb"+imageFrames+".png");
            imageFramesMax = 4;
        }
        else if (getList == 13)
        {
            loadImage = new GreenfootImage("Mage//ringerAbilityH//bomb"+imageFrames+".png");
            imageFramesMax = 4;
        }
        if (imageFrames == imageFramesMax)
        {
            getList++;
            imageFrames = 0;
        }
        setImage(loadImage);
        imageChangeNinja(getList);
    }

    public void imageChangeMage(int getList)
    {
        if (getList == 8)
        {
            return ;
        }
        imageFrames++;
        if (getList == 0)
        {
            loadImage = new GreenfootImage("Mage//FireBall//fireBall"+imageFrames+".png");
            imageFramesMax = 5;
        }
        else if (getList == 1)
        {
            loadImage = new GreenfootImage("Mage//MageAbilityH//mageAbilityH"+imageFrames+".png");
            imageFramesMax = 10;
        }
        else if (getList == 2)
        {
            loadImage = new GreenfootImage("Mage//MageAbilityH//singularity"+imageFrames+".png");
            imageFramesMax = 16;
        }
        else if (getList == 3)
        {
            loadImage = new GreenfootImage("Mage//MageAbilityR//MageAbilityR"+imageFrames+".png");
            imageFramesMax = 22;
        }
        else if (getList == 4)
        {
            loadImage = new GreenfootImage("Mage//MageAbilityR//railgun"+imageFrames+".png");
            imageFramesMax = 2;
        }
        else if (getList == 5)
        {
            loadImage = new GreenfootImage("Mage//MageAbilityUlt//mageAbilityUlt00"+imageFrames+".png");
            imageFramesMax = 57;
        }
        else if (getList == 6)
        {
            loadImage = new GreenfootImage("Mage//mageAttack//mageAttack"+imageFrames+".png");
            imageFramesMax = 5;
        }
        else if (getList == 7)
        {
            loadImage = new GreenfootImage("Mage//mageMove//mageMove"+imageFrames+".png");
            imageFramesMax = 4;
        }
        else if (getList == 7)
        {
            loadImage = new GreenfootImage("Mage//mageNormal//mageNormal"+imageFrames+".png");
            imageFramesMax = 8;
        }
        if (imageFrames == imageFramesMax)
        {
            getList++;
            imageFrames = 0;
        }
        setImage(loadImage);
        imageChangeNinja(getList);
    }

    public void imageChangeNinja(int getList)
    {
        if (getList == 16)
        {
            return ;
        }
        imageFrames++;
        if (getList == 0)
        {
            loadImage = new GreenfootImage("Ninja//ninjaRun//ninjaRun"+imageFrames+".png");
            imageFramesMax = 6;
        }
        else if (getList == 1)
        {
            loadImage = new GreenfootImage("Ninja//ninjaNormal//ninjaNormal"+imageFrames+".png");
            imageFramesMax = 4;
        }
        else if (getList == 2)
        {
            loadImage = new GreenfootImage("Ninja//ninjaJump//jump"+imageFrames+".png");
            imageFramesMax = 3;
        }
        else if (getList == 3)
        {
            loadImage = new GreenfootImage("Ninja//ninjaJump//jumpAttack"+imageFrames+".png");
            imageFramesMax = 4;
        }
        else if (getList == 4)
        {
            loadImage = new GreenfootImage("Ninja//ninjaHurt//ninjaHurt.png");
            imageFramesMax = 1;
        }
        else if (getList == 5)
        {
            loadImage = new GreenfootImage("Ninja//ninjaAttack//ninjaAttack"+imageFrames+"Style1.png");
            imageFramesMax = 3;
        }
        else if (getList == 6)
        {
            loadImage = new GreenfootImage("Ninja//ninjaAttack//ninjaAttack"+imageFrames+"Style2.png");
            imageFramesMax = 3;
        }
        else if (getList == 7)
        {
            loadImage = new GreenfootImage("Ninja//ninjaAttack//ninjaAttack"+imageFrames+"Style3.png");
            imageFramesMax = 3;
        }
        else if (getList == 8)
        {
            loadImage = new GreenfootImage("Ninja//ninjaAbilityT//ninjaAbilityT"+imageFrames+"Style1.png");
            imageFramesMax = 10;
        }
        else if (getList == 9)
        {
            loadImage = new GreenfootImage("Ninja//ninjaAbilityT//ninjaAbilityT"+imageFrames+"Style2.png");
            imageFramesMax = 10;
        }
        else if (getList == 10)
        {
            loadImage = new GreenfootImage("Ninja//ninjaAbilityR//ninjaAbilityR"+imageFrames+".png");
            imageFramesMax = 23;
        }
        else if (getList == 11)
        {
            loadImage = new GreenfootImage("Ninja//ninjaAbilityR//redShadow.png");
            imageFramesMax = 1;
        }
        else if (getList == 12)
        {
            loadImage = new GreenfootImage("Ninja//ninjaAbilityH//ninjaAbilityH"+imageFrames+".png");
            imageFramesMax = 18;
        }
        else if (getList == 13)
        {
            loadImage = new GreenfootImage("Ninja//Kuwu//KuWu.png");
            imageFramesMax = 1;
        }
        else if (getList == 14)
        {
            loadImage = new GreenfootImage("Ninja//Kuwu//KuWuDone"+imageFrames+".png");
            imageFramesMax = 3;
        }
        else if (getList == 15)
        {
            loadImage = new GreenfootImage("Ninja//ninjaDie//ninjaDie"+imageFrames+".png");
            imageFramesMax = 12;
        }
        if (imageFrames == imageFramesMax)
        {
            getList++;
            imageFrames = 0;
        }
        setImage(loadImage);
        imageChangeNinja(getList);
    }

    public void imageChangeWhite(int getList)
    {
        if (getList == 18)
        {
            return ;
        }
        imageFrames++;
        if (getList == 0)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteRun//whiteRun"+imageFrames+".png");
            imageFramesMax = 6;
        }
        else if(getList == 1)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteNormal//whiteNormal"+imageFrames+".png");
            imageFramesMax = 5;
        }
        else if(getList == 2)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteJump//Flash"+imageFrames+".png");
            imageFramesMax = 3;
        }
        else if(getList == 3)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteJump//whiteJump"+imageFrames+".png");
            imageFramesMax = 6;
        }
        else if(getList == 4)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteJump//whiteJumpAttack"+imageFrames+".png");
            imageFramesMax = 5;
        }
        else if(getList == 5)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteHurt//hurt.png");
            imageFramesMax = 1;
        }
        else if(getList == 6)
        {
            loadImage = new GreenfootImage("whiteSwordMan//WhiteDie//whiteDie"+imageFrames+".png");
            imageFramesMax = 10;
        }
        else if(getList == 7)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteAttack//whiteAttack"+imageFrames+"Style1.png");
            imageFramesMax = 5;
        }
        else if(getList == 8)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteAttack//whiteAttack"+imageFrames+"Style2.png");
            imageFramesMax = 5;
        }
        else if(getList == 9)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteAttack//whiteAttack"+imageFrames+"Style3.png");
            imageFramesMax = 5;
        }
        else if(getList == 10)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteAbilityTwo//whiteAttack"+imageFrames+"Ability2.png");
            imageFramesMax = 21;
        }
        else if(getList == 11)
        {
            imageFramesMax = 1;
            setImage( new GreenfootImage("whiteSwordMan//whiteAbilityTwo//sword.png"));
            setImage( new GreenfootImage("whiteSwordMan//whiteAbilityTwo//swordDone.png"));
        }
        else if (getList == 12)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteAbilityThree//whiteAttack1Ability3.png");
            imageFramesMax = 18;
        }
        else if (getList == 13)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteAbilityThree//Halo"+imageFrames+".png");
            imageFramesMax = 6;
        }
        else if (getList == 14)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteAbilityOne//whiteAttack"+imageFrames+"Ability1.png");
            imageFramesMax = 15;
        }
        else if (getList == 15)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteAbilityOne//shockBlade"+imageFrames+".png");
            imageFramesMax = 3;
        }
        else if (getList == 16)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteAbilityUlt//whiteAbilityUlt"+imageFrames+".png");
            imageFramesMax = 5;
        }
        else if (getList == 17)
        {
            loadImage = new GreenfootImage("whiteSwordMan//whiteAbilityUlt//windBlade"+imageFrames+".png");
            imageFramesMax = 9;
        }
        if (imageFrames == imageFramesMax)
        {
            getList++;
            imageFrames = 0;
        }
        setImage(loadImage);
        imageChangeWhite(getList);
    }

    public boolean returnResult()
    {
        if (end == true)
        {
            setImage(new GreenfootImage("ballsI.png"));
        }
        return end;
    }
}

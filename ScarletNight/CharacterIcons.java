import greenfoot.*;
public class CharacterIcons extends CharacterSelectionItems
{
    public CharacterIcons(String getCharacter)
    {
        if (getCharacter.equals("White"))
        setImage(new GreenfootImage("CharacterIcons//FuShu.png"));
        else if (getCharacter.equals("Bond"))
        setImage(new GreenfootImage("CharacterIcons//JamesBond.png"));
        else if (getCharacter.equals("Ninja"))
        setImage(new GreenfootImage("CharacterIcons//Torato.png"));
        else if (getCharacter.equals("Mage"))
        setImage(new GreenfootImage("CharacterIcons//Gajiwala.png"));
        else if (getCharacter.equals("Ringer"))
        setImage(new GreenfootImage("CharacterIcons//MarkGreen.png"));
    }
}

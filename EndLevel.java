import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class LevelEnd here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndLevel extends World
{

    /**
     * Constructor for objects of class LevelEnd.
     * 
     */
    private GreenfootImage moneyImage;
    private GreenfootImage dayImage;
    private int offset;
    public EndLevel(int day, int moneyCount)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        // new greenfoot image, draw image then addObject. 
        dayImage = new GreenfootImage(day + "/14", 26, Color.WHITE, new Color(0,0,0,0));
        getBackground().drawImage(dayImage, 385, 99);
        
        moneyImage = new GreenfootImage("$" + moneyCount + "", 26, Color.WHITE, new Color(0,0,0,0));
        // make sure displayed money is centred in the box
        if (moneyCount > 99) {
            offset = 0;
        } else if (moneyCount > 9) {
            offset = 5;
        } else {
            offset = 10;
        }
        getBackground().drawImage(moneyImage, 381 + offset, 172);
        
    }
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            IntroScreen introScreen = new IntroScreen();
            Greenfoot.setWorld(introScreen);
            
        }
    }
}

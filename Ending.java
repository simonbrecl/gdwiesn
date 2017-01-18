import greenfoot.*;


import java.awt.*;

/**
 * Write a description of class LevelEnd here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ending extends World {

    static int money;
    /**
     * Constructor for objects of class LevelEnd.
     */
    private GreenfootImage total;


    public Ending(int moneyCount) {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        setBackground("final.jpg");
        money += moneyCount- 200;
        total = new GreenfootImage(money + "€", 26, Color.BLACK, new Color(0, 0, 0, 0));
        getBackground().drawImage(total, 434, 280);
    }

    public void act() {
        if (Greenfoot.mouseClicked(null)) {
            IntroScreen home = new IntroScreen();
            Greenfoot.setWorld(home);
        }
        }
}

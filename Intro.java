import greenfoot.*;
import greenfoot.MouseInfo;

import java.awt.*;

/**
 * Write a description of class Intro here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Intro extends World {
    //private GreenfootSound introSound = new GreenfootSound("intro.mp3");

    private World previousWorld;

    /**
     * Constructor for objects of class Intro.
     */
    public Intro(World previousWorld) {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        writeText();
        this.previousWorld = previousWorld;
    }

    private void writeText() {
        addObject(new Actor() {
            {
                setImage(new GreenfootImage("You are the owner of the “Studentenfestzelt”, an Oktoberfest tent dedicated \nto students by providing cheap beer.\nHowever, student prices aren't exactly giving you all the returns you need to break even.\n\nComplete all 16 days of Oktoberfest by meeting the loan payments required each day.\nPrepare and deliver as much food and as many beers as possible to your costumers.\n\nUpgrade and manage your facilities, staff and stock ", 21, Color.black, Color.white));
            }
        }, 418, 260);

    }

    public void act() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseClicked(null)) {
            int x = mouse.getX();
            int y = mouse.getY();
            if (x > 580 && x < 755 && y > 474 && y < 555) {
                Greenfoot.setWorld(previousWorld);
            }
        }
    }
}

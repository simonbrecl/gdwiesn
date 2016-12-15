import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.MouseInfo;
import greenfoot.World;
import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;


/**
 * Write a description of class MyWorld here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MyWorld extends World {
    private GreenfootSound ambientSound = new GreenfootSound("bayerisches-bierzelt-atmosphre-mit-essen-und-trinken.mp3");

    static final int MIN_PER_LEVEL = 5;
    private Timer levelTimer;
    private boolean timerGoing = false;
    private ActionListener taskPerformer;
    static final Lives heart1 = new Lives();
    static final Lives heart2 = new Lives();
    static final Lives heart3 = new Lives();
    static final int MAX_PEOPLE = 30;
    static final int MIN_PEOPLE = 1;
    static final int INTERVAL = 5;
    int obsID = 0;
    
    

    Long beginTime = System.currentTimeMillis();

    private int stupidTimer = 0;
    private int day = 1;

    Message messagebox = new Message("");

    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld() {
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        prepare();
        addRandomPeople();
        started();
    }

    public void started() {
        ambientSound.playLoop();
        Levelmap.clock.startClock(MIN_PER_LEVEL);
    }

    public void stopped() {
        GreenfootSound sound = new GreenfootSound("bayerisches-bierzelt-atmosphre-mit-essen-und-trinken.mp3");

        ambientSound.stop();
        //Levelmap.clock.stopClock();
        //levelTimer.stop();
        stupidTimer = 0;
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        Levelmap.loadObjects("levels/MyWorld.xml", this);
        Font font = getBackground().getFont();
       font = font.deriveFont(Font.PLAIN, 24);
        getBackground().setFont(font);
        getBackground().setColor(Color.black);
        getBackground().drawString("LEVEL "+day, 20, 548);
        addObject(heart1,33,574);
        addObject(heart2,80,574);
        addObject(heart3,127,574);
    }
    
    public void act() {
        if ((System.currentTimeMillis() - beginTime) / 1000 >= INTERVAL) {
            addRandomPeople();
            beginTime = System.currentTimeMillis();
        }

        stupidTimer++;

        if (stupidTimer >= MIN_PER_LEVEL*60*60) {
            EndLevel endLevel = new EndLevel(day, Money.getMoney());
            Money.clearPreviousDaysMoney();
            Greenfoot.setWorld(endLevel);
            
        }

        if(Levelmap.money.getMoney() > Levelmap.goal.getGoal()) {

            Levelmap.goal.goalReached();
        }

        MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        if (mouseInfo != null && mouseInfo.getButton() == 1 && mouseInfo.getClickCount() > 0) {
            if (mouseInfo.getActor() instanceof Bar || mouseInfo.getActor() instanceof Table) {
                Levelmap.waitress.moveTo(Pathmap.findPath(Levelmap.waitress.getX(), Levelmap.waitress.getY(), mouseInfo.getX(), mouseInfo.getY()));
            }
        }
    }

    private void addRandomPeople() {
        for (int i = 0; i < Greenfoot.getRandomNumber(MAX_PEOPLE + 1 - MAX_PEOPLE) + MIN_PEOPLE; i++)
            addObject(new Obstacle(obsID++), 250 + Greenfoot.getRandomNumber(30), 550 + Greenfoot.getRandomNumber(30));
    }

}

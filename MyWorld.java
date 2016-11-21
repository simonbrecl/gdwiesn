import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.MouseInfo;
import greenfoot.World;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * Write a description of class MyWorld here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MyWorld extends World {
    private GreenfootSound ambientSound = new GreenfootSound("bayerisches-bierzelt-atmosphre-mit-essen-und-trinken.mp3");

    static final int MIN_PER_LEVEL = 2;
    private Clock clock;
    private Timer levelTimer;
    private boolean timerGoing = false;
    private ActionListener taskPerformer;

    static final int MAX_PEOPLE = 30;
    static final int MIN_PEOPLE = 1;
    static final int INTERVAL = 1;
    int obsID = 0;

    Long beginTime = System.currentTimeMillis();

    private int stupidTimer = 0;

    Message messagebox = new Message("");
    Money money;

    private Waitress waitress;

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
        
        /*taskPerformer = new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
              System.out.println("Stop da clock!");
              clock.stopClock();
              levelTimer.stop();
              
              timerGoing = false;
              Greenfoot.stop();
          }
        };
        
        
        levelTimer = new Timer(MIN_PER_LEVEL * 60 * 1000, taskPerformer);
        levelTimer.start();
        */
        clock.startClock();


    }

    public void stopped() {
        GreenfootSound sound = new GreenfootSound("bayerisches-bierzelt-atmosphre-mit-essen-und-trinken.mp3");

        ambientSound.stop();
        //clock.stopClock();
        //levelTimer.stop();
        stupidTimer = 0;
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        Levelmap.loadObjects("levels/MyWorld.xml", this);

        BeerButton beerButton = new BeerButton();
        addObject(beerButton, 413, 23);

        waitress = new Waitress();
        addObject(waitress, 85, 47);

        clock = new Clock(2);
        addObject(clock, 700, 60);

        money = new Money();
        addObject(money, 763, 575);
    }

    public void act() {
        if ((System.currentTimeMillis() - beginTime) / 1000 >= INTERVAL) {
            addRandomPeople();
            beginTime = System.currentTimeMillis();
        }

        stupidTimer++;

        if (stupidTimer >= 7200) {
            Greenfoot.stop();
        }

        MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        if (mouseInfo != null && mouseInfo.getButton() == 1 && mouseInfo.getClickCount() > 0) {
            if (mouseInfo.getActor() instanceof Bar || mouseInfo.getActor() instanceof Table) {
                waitress.moveTo(Pathmap.findPath(waitress.getX(), waitress.getY(), mouseInfo.getX(), mouseInfo.getY()));
            }
        }
    }

    public Money getMoney() {
        return money;
    }

    private void addRandomPeople() {
        for (int i = 0; i < Greenfoot.getRandomNumber(MAX_PEOPLE + 1 - MAX_PEOPLE) + MIN_PEOPLE; i++)
            addObject(new Obstacle(obsID++), 250 + Greenfoot.getRandomNumber(30), 550 + Greenfoot.getRandomNumber(30));
    }

}

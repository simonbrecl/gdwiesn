import greenfoot.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    static final int MAX_PEOPLE = 30;
    static final int MIN_PEOPLE = 1;
    static final int INTERVAL = 5;
    int obsID = 0;
    static TentState tent;
    
    private Pathmap pathmap = new Pathmap("levels/MyWorld.xml");

    Long beginTime = System.currentTimeMillis();

    private int stupidTimer = 0;
    private int day = 1;
    
    static boolean tutorialActive = false;

    private int tutorialTimer = 0;
    SausageBoy sausageBoy;
    static int tutorialStage;

    Message messagebox = new Message("");

    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld() {
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        prepare();
        // addRandomPeople();
        started();
        tutorialStage = 1;
    }

    public void started() {
        ambientSound.playLoop();

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


        tent = new TentState();

        if (tutorialActive) {
            sausageBoy = new SausageBoy();
            addObject(sausageBoy, 640, 370);
        }
    }

    public void act() {
        
        if (tutorialActive == false) {
            Levelmap.clock.startClock(MIN_PER_LEVEL);
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
        }
        
        if (tutorialActive == true) {
            if (tutorialStage == 1) {
                tutorialTimer++;
                if (tutorialTimer == 250) {
                    tutorialStage = 2;
                    sausageBoy.updateImage(tutorialStage);
                    addRandomPeople();
                    tutorialTimer = 0;
                }
            } else if (tutorialStage == 2) {
                tutorialTimer++;
                if (tutorialTimer == 250) {
                    tutorialStage = 3;
                    sausageBoy.updateImage(tutorialStage);
                    flashBarrel();
                    tutorialTimer = 0;
                }
            } else if (tutorialStage == 4) {
                // beerflash
                //flashBeer();
                sausageBoy.updateImage(tutorialStage);
            } else if (tutorialStage == 5) {
                sausageBoy.updateImage(tutorialStage);
                List<Waitress> waitressList = getObjects(Waitress.class);
                Waitress waitress = waitressList.get(0);
                if (waitress.getBeerCount() > 0) {
                    List<Customer> customerList = getObjects(Customer.class);
                    Customer customer = customerList.get(0);
                    customer.flashTrue();
                }
            } else if (tutorialStage >= 6 && tutorialStage <= 8) {
                sausageBoy.updateImage(tutorialStage);
            } else if (tutorialStage == 9) {
                removeObject(sausageBoy);
                tutorialActive = false;
            }
            
            // turn flashing off once clicked
            List<BeerButton> barrelList = getObjects(BeerButton.class);
            BeerButton beerButton = barrelList.get(0);
        }    

        MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        if (mouseInfo != null && mouseInfo.getButton() == 1 && mouseInfo.getClickCount() > 0) {
            Actor actor = mouseInfo.getActor();

            // Exclude other click-areas!
            if (!(actor instanceof BeerButton) && !(actor instanceof SausageBoy)) {
                Levelmap.waitress.moveTo(pathmap.findPath(Levelmap.waitress.getX(), Levelmap.waitress.getY(), mouseInfo.getX(), mouseInfo.getY()));
            }
        }
    }

    private void addRandomPeople() {
        for (int i = 0; i < Greenfoot.getRandomNumber(MAX_PEOPLE + 1 - MAX_PEOPLE) + MIN_PEOPLE; i++)
            addObject(new Customer(obsID++), 250 + Greenfoot.getRandomNumber(30), 550 + Greenfoot.getRandomNumber(30));
    }
    
    private void flashBarrel() {
        List<BeerButton> barrelList = getObjects(BeerButton.class);
        BeerButton beerButton = barrelList.get(0);
        beerButton.barrelFlash();
    }
    
    private void flashBeer() {
        List<Beer> beerList = getObjects(Beer.class);
        Beer beer = beerList.get(0);
        beer.beerFlash();
    }
    
    static boolean isTutorialActive() {
        return tutorialActive;
    }
    
    static void incrementTutorialStage() {
        tutorialStage++;
    }
    
    static int getTutorialStage() {
        return tutorialStage;
    }
}

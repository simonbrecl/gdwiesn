import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.MouseInfo;
import greenfoot.World;
import java.awt.*;
import greenfoot.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Level2 extends World {
    static final int MIN_PER_LEVEL = 5;
    private Timer levelTimer;
    private boolean timerGoing = false;
    private ActionListener taskPerformer;
    static final Lives heart1 = new Lives();
    static final Lives heart2 = new Lives();
    static final Lives heart3 = new Lives();
    static final int MAX_PEOPLE = 60;
    static final int MIN_PEOPLE = 1;
    static final int INTERVAL = 5;
    static int tutorialStage;
    int obsID = 0;

    public static TentState tent;
    
    private Pathmap pathmap = new Pathmap("levels/Level2.xml");

    Long beginTime = System.currentTimeMillis();
    private GreenfootSound ambientSound = new GreenfootSound("bayerisches-bierzelt-atmosphre-mit-essen-und-trinken.mp3");
    private ArrayList<Seat> allSeats = new ArrayList<>();
    private Levelmap levelmap = new Levelmap("levels/Level2.xml", this);
    private int stupidTimer = 0;
    private int day = 2;
    Message messagebox = new Message("");
    private int tutorialTimer = 0;

    /**
     * Constructor for objects of class Level2.
     */
    public Level2() {
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        prepare();
        for (Table t : levelmap.tables) {
            allSeats.addAll(t.getSeats());
        }
        started();
        tutorialStage = 1;
    }

    static int getTutorialStage() {
        return tutorialStage;
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

        Font font = getBackground().getFont();
        font = font.deriveFont(Font.PLAIN, 24);
        getBackground().setFont(font);
        getBackground().setColor(Color.black);
        getBackground().drawString("LEVEL "+day, 20, 548);
        addObject(heart1,33,574);
        addObject(heart2,80,574);
        addObject(heart3,127,574);

        tent = new TentState();
    }
    
    public void act() {
        
            levelmap.clock.startClock(MIN_PER_LEVEL);
            if ((System.currentTimeMillis() - beginTime) / 1000 >= INTERVAL) {
                addRandomPeople();
                beginTime = System.currentTimeMillis();
                levelmap.clock.startClock(MIN_PER_LEVEL);
            }

            stupidTimer++;

            if (stupidTimer >= MIN_PER_LEVEL*60*60) {
                EndLevel endLevel = new EndLevel(day, Money.getMoney(), tent);
                Money.clearPreviousDaysMoney();
                Greenfoot.setWorld(endLevel);

            }

            if(levelmap.money.getMoney() > levelmap.goal.getGoal()) {

                levelmap.goal.goalReached();
            }
        
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        if (mouseInfo != null && mouseInfo.getButton() == 1 && mouseInfo.getClickCount() > 0) {
            Actor actor = mouseInfo.getActor();

            // Exclude other click-areas!
            if (!(actor instanceof BeerButton) && !(actor instanceof SausageBoy)) {
                levelmap.waitress.moveTo(mouseInfo.getX(), mouseInfo.getY());
            }
        }
    }

    private void addTutorialCustomer() {
        Customer c = new Customer(obsID++);
        addObject(c, 350, 550);
        Seat s = allSeats.get(23);
        s.setTaken(true);
        c.setSeat(s);
        c.moveTo(s.getX(), s.getY());
    }
    
    private void addRandomPeople() {
        for (int i = 0; i < Greenfoot.getRandomNumber(MAX_PEOPLE + 1 - MAX_PEOPLE) + MIN_PEOPLE; i++) {
            Customer c = new Customer(obsID++);
            addObject(c, 350, 580);
            int seatIndex = Greenfoot.getRandomNumber(allSeats.size());
            while (allSeats.get(seatIndex).isTaken()) {
                seatIndex = Greenfoot.getRandomNumber(allSeats.size());
            }
            Seat s = allSeats.get(seatIndex);
            s.setTaken(true);
            c.setSeat(s);
            c.moveTo(s.getX(), s.getY());
        }
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
}
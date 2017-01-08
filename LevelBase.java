import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.World;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Daniel on 19.12.2016.
 */
public class LevelBase extends World {

    //Duration of a level
    public int MIN_PER_LEVEL = 5;
    //Maximum amount of customers
    public int MAX_PEOPLE = 30;
    //Minimum amount of customers
    public int MIN_PEOPLE = 1;
    //Interval of spawning customers
    public int INTERVAL = 5;
    public Levelmap levelmap;
    public TentState tent;
    public ArrayList<Seat> allSeats = new ArrayList<>();
    public int stupidTimer = 0;
    public int day = 1;
    public int obsID = 0;
    Message messagebox = new Message("");
    private Lives heart1 = new Lives();
    private Lives heart2 = new Lives();
    private Lives heart3 = new Lives();
    private Long beginTime = System.currentTimeMillis();
    private GreenfootSound ambientSound = new GreenfootSound("bayerisches-bierzelt-atmosphre-mit-essen-und-trinken.mp3");

    public LevelBase(int day, TentState state, String pathToLevelmap) {
        super(800, 600, 1);
        prepare();
        this.day = day;
        tent = state;
        levelmap = new Levelmap(pathToLevelmap, this, tent);
        for (Table t : levelmap.getTables()) {
            allSeats.addAll(t.getSeats());
        }
        started();
    }

    public void started() {
        ambientSound.playLoop();
    }

    public void stopped() {
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
        getBackground().drawString("LEVEL " + day, 20, 548);
        addObject(heart1, 183, 574);
        addObject(heart2, 230, 574);
        addObject(heart3, 277, 574);

        tent = new TentState();

    }


    public void baseLevelAct() {
        levelmap.getClock().startClock(MIN_PER_LEVEL, day);
        spawnCustomers();
        updateClock();
        updateIfGoalReached();
        clickControl();
    }

    private void spawnCustomers() {
        if ((System.currentTimeMillis() - beginTime) / 1000 >= INTERVAL) {
            addRandomPeople();
            beginTime = System.currentTimeMillis();
        }
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

    private void updateClock() {
        stupidTimer++;

        if (stupidTimer >= MIN_PER_LEVEL * 60 * 60) {
            EndLevel endLevel = new EndLevel(day, levelmap.getMoney().getMoney(), tent);
            Money.clearPreviousDaysMoney();
            Greenfoot.setWorld(endLevel);

        }
    }

    private void updateIfGoalReached() {
        if (levelmap.getMoney().getMoney() > levelmap.getGoal().getGoal()) {

            levelmap.getGoal().goalReached();
        }
    }

    public void clickControl() {
        greenfoot.MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        if (mouseInfo != null && mouseInfo.getButton() == 1 && mouseInfo.getClickCount() > 0) {
            Actor actor = mouseInfo.getActor();
            System.out.println("Inside clickControl() and inside if statement");

            // Exclude other click-areas!
            if (!(actor instanceof BeerButton) && !(actor instanceof SausageBoy) && !(actor instanceof PretzelMachine)) {
                levelmap.getWaitress().moveTo(mouseInfo.getX(), mouseInfo.getY());
            }
        }
    }

    public Lives getHeart1() {
        return heart1;
    }

    public Lives getHeart2() {
        return heart2;
    }

    public Lives getHeart3() {
        return heart3;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void updateTentState(TentState state) {
        tent = state;
    }

    public TentState getTentState() {
        return tent;
    }
}

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.awt.*;

/**
 * Write a description of class LevelEnd here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EndLevel extends World {

    private int money;
    /**
     * Constructor for objects of class LevelEnd.
     */
    private GreenfootImage moneyImage;
    private GreenfootImage dayImage;
    private GreenfootImage goal;
    private GreenfootImage rent;
    private GreenfootImage total;
    private GreenfootImage savings;
    private GreenfootImage goalreached;

    private int i = 1;

    private int offset;
    private int money1;
    private TentState tentState;


    public EndLevel(int day, int moneyCount, TentState state) {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);

        money = state.getMoney() + moneyCount - 200;

        this.tentState = state;
        this.tentState.updateMoney(money);

        setBackground("levelend.jpg");

        // new greenfoot image, draw image then addObject. 
        dayImage = new GreenfootImage(day+3 + "/16", 26, Color.BLACK, new Color(0, 0, 0, 0));
        getBackground().drawImage(dayImage, 315, 419);
        moneyImage = new GreenfootImage(moneyCount + "€", 26, Color.BLACK, new Color(0, 0, 0, 0));
        getBackground().drawImage(moneyImage, 401 + offset, 512);
        if (day>1) {
            savings = new GreenfootImage(money + "€", 26, Color.BLACK, new Color(0, 0, 0, 0));
            getBackground().drawImage(savings, 401 + offset, 483);
        }
        else {
            savings = new GreenfootImage("0€", 26, Color.BLACK, new Color(0, 0, 0, 0));
            getBackground().drawImage(savings, 406 + offset, 480);
        }
        goal = new GreenfootImage(i*150 + "", 26, Color.BLACK, new Color(0, 0, 0, 0));
        getBackground().drawImage(goal, 403 + offset, 419);

        if (moneyCount>i*150) {
            goalreached = new GreenfootImage("Goal reached", 15, Color.green, new Color(0, 0, 0, 0));
            getBackground().drawImage(goalreached, 390 + offset, 443);
        }
        else {
            goalreached = new GreenfootImage("Goal not reached", 15, Color.RED, new Color(0, 0, 0, 0));
            getBackground().drawImage(goalreached, 380 + offset, 443);
        }
        rent = new GreenfootImage("200€", 26, Color.BLACK, new Color(0, 0, 0, 0));
        getBackground().drawImage(rent, 401 + offset, 540);
        money += moneyCount - 200;

        total = new GreenfootImage(money + "€", 26, Color.BLACK, new Color(0, 0, 0, 0));
        getBackground().drawImage(total, 414 + offset, 568);

        this.tentState = state;
        state.updateMoney(money);
        i++;
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            UpgradeScreen screen = new UpgradeScreen(tentState);
            Greenfoot.setWorld(screen);
        }
    }
}

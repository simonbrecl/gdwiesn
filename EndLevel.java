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

    static int money;
    /**
     * Constructor for objects of class LevelEnd.
     */
    private GreenfootImage moneyImage;
    private GreenfootImage dayImage;
    private GreenfootImage lostCostum;
    private GreenfootImage rent;
    private GreenfootImage total;
    private int offset;
    private TentState tentState;


    public EndLevel(int day, int moneyCount, TentState state) {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        setBackground("levelend.jpg");
        int i = Customer.counter1;
        // new greenfoot image, draw image then addObject. 
        dayImage = new GreenfootImage(day + "/14", 26, Color.BLACK, new Color(0, 0, 0, 0));
        getBackground().drawImage(dayImage, 366, 380);
        moneyImage = new GreenfootImage(moneyCount + "€", 26, Color.BLACK, new Color(0, 0, 0, 0));
        getBackground().drawImage(moneyImage, 413 + offset, 479);
        lostCostum = new GreenfootImage(i + "", 26, Color.BLACK, new Color(0, 0, 0, 0));
        getBackground().drawImage(lostCostum, 366 + offset, 420);
        rent = new GreenfootImage("200€", 26, Color.BLACK, new Color(0, 0, 0, 0));
        getBackground().drawImage(rent, 413 + offset, 517);
        money += moneyCount - 200;
        total = new GreenfootImage(money + "€", 26, Color.BLACK, new Color(0, 0, 0, 0));
        getBackground().drawImage(total, 411 + offset, 553);

        // make sure displayed money is centred in the box
        if (moneyCount > 99) {
            offset = 0;
        } else if (moneyCount > 9) {
            offset = 5;
        } else {
            offset = 10;
        }

        this.tentState = state;
        state.updateMoney(money);

    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            UpgradeScreen screen = new UpgradeScreen(money, tentState);
            Greenfoot.setWorld(screen);
        }
    }
}

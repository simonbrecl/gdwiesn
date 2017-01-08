import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.MouseInfo;
import greenfoot.World;

/**
 * Created by ericasolum on 12/1/16.
 */

public class UpgradeScreen extends World {

    private int money;
    public TentState tentState;
    
    public UpgradeScreen(int money, TentState state) {
        super(800, 600, 1);
        this.money = money;
        tentState = state;
        prepare();

    }

    public void prepare() {
        Upgrademap.loadObjects("levels/Upgrade-Screen.xml", this);

    }

    public void act() {


    }

    public TentState getTentState() {
        return tentState;
    }

    public void goToNextDay() {
        tentState.increaseDay();
        Level2 level = new Level2(tentState.getDay(), tentState);
        Greenfoot.setWorld(level);
    }


}

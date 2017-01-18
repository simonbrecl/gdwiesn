import greenfoot.Greenfoot;
import greenfoot.World;


/**
 * Created by ericasolum on 12/1/16.
 */

public class UpgradeScreen extends World {

    public TentState tentState;
    public Upgrademap upgrademap;

    public UpgradeScreen(TentState state) {
        super(800, 600, 1);
        tentState = state;
        prepare();

    }

    public void prepare() {
        upgrademap = new Upgrademap("levels/Upgrade-Screen.xml", this, tentState);

    }

    public void act() {


    }

    public TentState getTentState() {
        return tentState;
    }

    public void goToNextDay() {
        tentState.increaseDay();
        if (tentState.getDay() == 2) {
            Level2 level = new Level2(tentState);
            Greenfoot.setWorld(level);
            level.getHeart2().getImage().setTransparency(255);
            level.getHeart3().getImage().setTransparency(255);
            Customer.counter1 = 0;
        } else if (tentState.getDay() == 3) {

            Customer.counter1 = 0;
            Level3 level3 = new Level3(tentState);
            Greenfoot.setWorld(level3);
            level3.getHeart2().getImage().setTransparency(255);
            level3.getHeart3().getImage().setTransparency(255);
        }
        else if (tentState.getDay() == 4) {

            Customer.counter1 = 0;
            Level4 level4 = new Level4(tentState);
            level4.getHeart2().getImage().setTransparency(255);
            level4.getHeart3().getImage().setTransparency(255);
            Greenfoot.setWorld(level4);
        }
    }
}

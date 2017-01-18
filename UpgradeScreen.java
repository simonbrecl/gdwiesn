import greenfoot.Actor;
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

        Customer.counter1 = 0;

        switch (tentState.getDay()) {
            case 2:
                Greenfoot.setWorld(new Level2(tentState));
                break;

            case 3:
                Greenfoot.setWorld(new Level3(tentState));
                break;

            case 4:
                Greenfoot.setWorld(new Level4(tentState));
                break;

        }
    }
}

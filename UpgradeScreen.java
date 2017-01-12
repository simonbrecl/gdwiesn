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
    public Upgrademap upgrademap;
    private LevelBase world;

    public UpgradeScreen(int money, TentState state) {
        super(800, 600, 1);
        this.money = money;
        tentState = state;
        prepare();

    }

    public void prepare() {
        upgrademap = new Upgrademap("levels/Upgrade-Screen.xml", this, tentState);
        world = new LevelBase(2,300, new TentState(), "levels/MyWorld.xml");

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
            world.getHeart2().getImage().setTransparency(255);
            world.getHeart3().getImage().setTransparency(255);
            Customer.counter1 = 0;
        } else if (tentState.getDay() == 3) {
            //Says level 2 for now but change this later
            Level2 level = new Level2(tentState);
            Greenfoot.setWorld(level);
        }

    }
}

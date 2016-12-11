import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.MouseInfo;
import greenfoot.World;

/**
 * Created by ericasolum on 12/1/16.
 */

public class UpgradeScreen extends World {

    private int money;
    
    public UpgradeScreen(int money) {
        super(800, 600, 1);
        this.money = money;
        prepare();

    }

    public void prepare() {
        Upgrademap.loadObjects("levels/Upgrade-Screen.xml", this);

    }

    public void act() {


        MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        if (mouseInfo != null && mouseInfo.getButton() == 1 && mouseInfo.getClickCount() > 0) {
            Actor actor = mouseInfo.getActor();

            // Exclude other click-areas!
            System.out.println("Clicked on an area: " + actor.toString());
        }

    }


}

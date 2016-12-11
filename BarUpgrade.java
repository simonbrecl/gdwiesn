import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * Created by ericasolum on 12/1/16.
 */
public class BarUpgrade extends Actor {
    private boolean upgradeBought = false;
    
    public BarUpgrade() {
        prepare();
    }

    public void prepare() {
        GreenfootImage image = new GreenfootImage("bar-upgrade-overlay.png");
        this.setImage(image);
    }

    public void buyUpgrade() {

        upgradeBought = true;
        GreenfootImage image = new GreenfootImage("bar-upgrade.png");
        this.setImage(image);
    }
}

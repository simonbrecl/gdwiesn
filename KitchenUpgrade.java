import greenfoot.Actor;
import greenfoot.GreenfootImage;

/**
 * Created by ericasolum on 12/1/16.
 */

public class KitchenUpgrade extends Actor{

    private boolean pretzelBought = false;
    private boolean sausageBought = false;
    private int pretzelPrice = 50;
    private int sausagePrice = 25;

    public KitchenUpgrade() {

        prepare();
    }

    public void prepare() {
        GreenfootImage image = new GreenfootImage("kitchen-upgrade1-overlay.png");
        this.setImage(image);
    }

    public void buyPretzel() {
        pretzelBought = true;
        this.setImage(new GreenfootImage("kitchen-upgrade2-overlay.png"));

    }

    public void buySausage() {
        sausageBought = true;
        this.setImage(new GreenfootImage("kitchen-upgrade2.png"));

    }
}

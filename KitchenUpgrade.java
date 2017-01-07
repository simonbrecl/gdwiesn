import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import java.util.List;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;

/**
 * Created by ericasolum on 12/1/16.
 */

public class KitchenUpgrade extends Actor{

    private boolean pretzelBought = false;
    private boolean sausageBought = false;
    private int pretzelPrice = 100;
    private int sausagePrice = 50;
    private int nextUpgradeCost = 100;

    private GreenfootImage upgrade1 = new GreenfootImage("kitchen-upgrade1.png");
    private GreenfootImage upgrade1Overlay = new GreenfootImage("kitchen-upgrade1-overlay.png");

    private GreenfootImage upgrade2 = new GreenfootImage("kitchen-upgrade2.png");
    private GreenfootImage upgrade2Overlay = new GreenfootImage("kitchen-upgrade2-overlay.png");

    //Add text box here
    String text = "Purchase the kitchen so customers can buy yummy snacks such as pretzels and sausages." +
            "\nCost: " + nextUpgradeCost + "€" + "\n\nClick to buy!";

    private TextBox textBox = new TextBox(new Point(250, 100), text, new Font("Helvetica", Font.PLAIN, 15));
    private boolean boxShowing = false;



    public KitchenUpgrade() {

        prepare();

    }

    public void prepare() {
        if (pretzelBought) {
            this.setImage(upgrade2Overlay);
            nextUpgradeCost = sausagePrice;
        }
        else if (sausageBought) {
            this.setImage(upgrade2);
        }
        else {
            this.setImage(upgrade1Overlay);
        }

    }

    public void buyPretzel() {
        pretzelBought = true;
        nextUpgradeCost = sausagePrice;
        this.setImage(new GreenfootImage("kitchen-upgrade2-overlay.png"));
        UpgradeScreen world = (UpgradeScreen) getWorld();
        if(world != null) {
            world.tentState.upgradeKitchen();
        }


    }

    public void buySausage() {
        sausageBought = true;
        this.setImage(new GreenfootImage("kitchen-upgrade2.png"));
        UpgradeScreen world = (UpgradeScreen) getWorld();
        if(world != null) {
            world.tentState.upgradeKitchen();
        }
    }

    public void act() {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        if(boxShowing) {
            this.getWorld().removeObject(textBox);
            boxShowing = false;
        }
        if(!pretzelBought) {
            this.setImage(upgrade1Overlay);
        }
        else if(pretzelBought && !sausageBought) {
            this.setImage(upgrade2Overlay);
        }
        else {
            this.setImage(upgrade2);
        }

        if (mouseInfo != null) {
            List objects = getWorld().getObjectsAt(mouseInfo.getX(), mouseInfo.getY(), KitchenUpgrade.class);
            for (Object object : objects)
            {
                if (object == this)
                {
                    if(!sausageBought) {
                        this.setImage(upgrade1);
                    }
                    else {
                        this.setImage(upgrade2);
                    }


                    this.getWorld().addObject(textBox, 600, 100);
                    boxShowing = true;
                }
            }

            // Buy upgrade
            if (Greenfoot.mouseClicked(this)) {
                if(!pretzelBought) {
                    buyPretzel();
                }
                else if(pretzelBought && !sausageBought) {
                    buySausage();
                }


            }
        }


    }
}

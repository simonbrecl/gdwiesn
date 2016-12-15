import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;

import java.awt.*;
import java.util.List;

/**
 * Created by ericasolum on 12/1/16.
 */
public class BarUpgrade extends Actor {
    private boolean upgradeBought = false;
    private GreenfootImage upgradeOverlay = new GreenfootImage("bar-upgrade-overlay.png");
    private GreenfootImage upgrade = new GreenfootImage("bar-upgrade.png");
    private int nextUpgradeCost = 100;

    String text = "Purchase the bar upgrade for a second beer tap." +
            "\nCost: " + nextUpgradeCost + "€" + "\n\nClick to buy!";
    private TextBox textBox = new TextBox(new Point(200, 100), text, new Font("Helvetica", Font.PLAIN, 15));
    private boolean boxShowing = false;


    public BarUpgrade() {
        prepare();
    }

    public void prepare() {
        if (upgradeBought) {
            this.setImage(upgrade);
        }
        else {
            this.setImage(upgradeOverlay);
        }

    }


    public void buyUpgrade() {

        upgradeBought = true;
        this.setImage(upgrade);
    }

    public void act() {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();



        if(!upgradeBought) {
            this.setImage(upgradeOverlay);

            if(boxShowing) {
                this.getWorld().removeObject(textBox);
                boxShowing = false;
            }
            if(mouseInfo != null) {
                List objects = getWorld().getObjectsAt(mouseInfo.getX(), mouseInfo.getY(), BarUpgrade.class);
                for (Object object : objects)
                {
                    if (object == this)
                    {

                        this.setImage(upgrade);
                        //Add text box here
                        this.getWorld().addObject(textBox, 200, 100);
                        boxShowing = true;
                    }


                }
            }

        }
        else {
            this.setImage(upgrade);
        }


    }
}

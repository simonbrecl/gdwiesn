import greenfoot.*;
import greenfoot.MouseInfo;

import java.awt.*;
import java.util.List;

/**
 * Created by ericasolum on 12/1/16.
 */
public class BarUpgrade extends Actor {
    public static final int SECOND_TAP_PRICE = 100;
    private int nextUpgradeCost = 100;

    private boolean upgradeBought = false;
    private GreenfootImage upgradeOverlay = new GreenfootImage("bar-upgrade-overlay.png");
    private GreenfootImage upgrade = new GreenfootImage("bar-upgrade.png");


    //Add text box here
    String text = "Purchase the bar upgrade for a second beer tap and the ability to keep up to 10 beers on the counter." +
            "\nCost: " + nextUpgradeCost + "€";
    String clickToBuy = "\n\nClick to buy!";
    String moneyExtra = "\n\nSorry, you need more money!";

    private TextBox notEnoughMoneyBox = new TextBox(new Point(250, 100), text + moneyExtra, new Font("Helvetica", Font.PLAIN, 15));
    private TextBox readyToBuyBox = new TextBox(new Point(250, 100), text + clickToBuy, new Font("Helvetica", Font.PLAIN, 15));
    private TextBox currentBox;

    private boolean boxShowing = false;
    private UpgradeScreen world;


    public BarUpgrade() {
        prepare();
    }

    public void prepare() {
        //Set the text boxes to read-only
        readyToBuyBox.setReadOnly(true);
        notEnoughMoneyBox.setReadOnly(true);
        
        if (upgradeBought) {
            this.setImage(upgrade);
        }
        else {
            this.setImage(upgradeOverlay);
        }

    }

    public void addedToWorld(World world) {
        this.world = (UpgradeScreen) getWorld();
    }


    public void buyUpgrade() {

        upgradeBought = true;
        this.setImage(upgrade);
        if(world != null) {
            boolean success = world.tentState.upgradeBar();
            if(success) {
                world.upgrademap.money.updateMoney(world.tentState.getMoney());
            }
        }
    }

    public void act() {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        if(boxShowing) {
            this.getWorld().removeObject(currentBox);
            boxShowing = false;
        }

        if(!upgradeBought) {
            this.setImage(upgradeOverlay);


            if(mouseInfo != null) {
                List objects = getWorld().getObjectsAt(mouseInfo.getX(), mouseInfo.getY(), BarUpgrade.class);
                for (Object object : objects)
                {
                    if (object == this)
                    {

                        this.setImage(upgrade);
                        //Add text box here
                        if(world.getTentState().getMoney() < nextUpgradeCost) {
                            currentBox = notEnoughMoneyBox;
                        }
                        else {
                            currentBox = readyToBuyBox;
                        }
                        this.getWorld().addObject(currentBox, 200, 100);
                        boxShowing = true;
                    }


                }
                // Buy upgrade
                if (Greenfoot.mouseClicked(this)) {
                    if(world.getTentState().getMoney() >= nextUpgradeCost) {
                        buyUpgrade();
                    }
                }
            }

        }
        else {
            this.setImage(upgrade);
        }


    }
}

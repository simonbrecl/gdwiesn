import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import greenfoot.World;
import java.util.List;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;

/**
 * Created by ericasolum on 12/1/16.
 */

public class KitchenUpgrade extends Actor{
    public static final int PRETZEL_PRICE = 100;
    public static final int SAUSAGE_PRICE = 50;

    private boolean pretzelBought = false;
    private boolean sausageBought = false;
    private int nextUpgradeCost = 100;

    private GreenfootImage upgrade1 = new GreenfootImage("kitchen-upgrade1.png");
    private GreenfootImage upgrade1Overlay = new GreenfootImage("kitchen-upgrade1-overlay.png");

    private GreenfootImage upgrade2 = new GreenfootImage("kitchen-upgrade2.png");
    private GreenfootImage upgrade2Overlay = new GreenfootImage("kitchen-upgrade2-overlay.png");

    //Add text box here
    String text = "Purchase the kitchen so customers can buy yummy snacks such as pretzels and sausages." +
            "\nCost: " + nextUpgradeCost + "€";
    String clickToBuy = "\n\nClick to buy!";
    String moneyExtra = "\n\nSorry, you need more money!";

    private TextBox notEnoughMoneyBox = new TextBox(new Point(250, 100), text + moneyExtra, new Font("Helvetica", Font.PLAIN, 15));
    private TextBox readyToBuyBox = new TextBox(new Point(250, 100), text + clickToBuy, new Font("Helvetica", Font.PLAIN, 15));
    private TextBox currentBox;

    private boolean boxShowing = false;
    private UpgradeScreen world;



    public KitchenUpgrade() {
        prepare();
    }

    public void addedToWorld(World world) {
        this.world = (UpgradeScreen) getWorld();
    }

    public void prepare() {
        if (pretzelBought) {
            this.setImage(upgrade2Overlay);
            nextUpgradeCost = SAUSAGE_PRICE;
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
        nextUpgradeCost = SAUSAGE_PRICE;
        this.setImage(new GreenfootImage("kitchen-upgrade2-overlay.png"));
        if(world != null) {
            boolean success = world.tentState.upgradeKitchen();

        }


    }

    public void buySausage() {
        sausageBought = true;
        this.setImage(new GreenfootImage("kitchen-upgrade2.png"));
        if(world != null) {
            world.tentState.upgradeKitchen();
        }
    }

    public void act() {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        if(boxShowing) {
            world.removeObject(currentBox);
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
            List objects = world.getObjectsAt(mouseInfo.getX(), mouseInfo.getY(), KitchenUpgrade.class);
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

                    if(world.getTentState().getMoney() < nextUpgradeCost) {
                        currentBox = notEnoughMoneyBox;
                    }
                    else {
                        currentBox = readyToBuyBox;
                    }

                    world.addObject(currentBox, 600, 100);
                    boxShowing = true;
                }
            }

            // Buy upgrade
            if (Greenfoot.mouseClicked(this)) {
                if(world.getTentState().getMoney() >= nextUpgradeCost) {
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
}

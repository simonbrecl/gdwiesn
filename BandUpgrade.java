/**
 * Created by ericasolum on 12/1/16.
 */

import greenfoot.*;
import greenfoot.MouseInfo;

import java.awt.*;
import java.util.List;


public class BandUpgrade extends Actor {
    public static final int BAND_COST = 300;
    private int nextUpgradeCost;
    private boolean upgradeBought = false;
    private GreenfootImage band = new GreenfootImage("band.png");
    private GreenfootImage bandOverlay = new GreenfootImage("band-overlay.png");

    //Add text box here
    String text = "Click on the band to play \"Sweet Caroline\", which will reset every customer's patience level. Use when angry customers are about to leave to avoid losing a life." +
            "\nCost: " + BAND_COST + "€";
    String clickToBuy = "\n\nClick to buy!";
    String moneyExtra = "\n\nSorry, you need more money!";

    private TextBox notEnoughMoneyBox = new TextBox(new Point(250, 110), text + moneyExtra, new Font("Helvetica", Font.PLAIN, 15));
    private TextBox readyToBuyBox = new TextBox(new Point(250, 110), text + clickToBuy, new Font("Helvetica", Font.PLAIN, 15));
    private TextBox currentBox;

    private boolean boxShowing = false;
    private UpgradeScreen world;

    public BandUpgrade() {

        prepare();
    }

    public void prepare() {
        if(upgradeBought) {
            this.setImage(band);
        }
        else {
            this.setImage(bandOverlay);
        }

        nextUpgradeCost = BAND_COST;

        //Set the text boxes to read-only
        readyToBuyBox.setReadOnly(true);
        notEnoughMoneyBox.setReadOnly(true);
    }

    public void addedToWorld(World world) {
        this.world = (UpgradeScreen) getWorld();
    }

    public void buyUpgrade() {

        upgradeBought = true;
        this.setImage(band);

        if(world != null) {
            boolean success = world.tentState.upgradeBand();
            if(success) {
                world.upgrademap.money.updateMoney(world.tentState.getMoney());
            }
        }
    }

    public void act() {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        if(boxShowing) {
            world.removeObject(currentBox);
            boxShowing = false;
        }

        if(!upgradeBought) {
            this.setImage(bandOverlay);

            if(mouseInfo != null) {
                List objects = getWorld().getObjectsAt(mouseInfo.getX(), mouseInfo.getY(), BandUpgrade.class);
                for (Object object : objects)
                {
                    if (object == this)
                    {
                        this.setImage(band);

                    }

                    if(world.getTentState().getMoney() < nextUpgradeCost) {
                        currentBox = notEnoughMoneyBox;
                    }
                    else {
                        currentBox = readyToBuyBox;
                    }

                    world.addObject(currentBox, 380, 400);
                    boxShowing = true;
                }
                // Buy upgrade
                if (Greenfoot.mouseClicked(this)) {
                    if(world.getTentState().getMoney() >= nextUpgradeCost) {
                        if(!upgradeBought) {
                            buyUpgrade();
                        }
                    }
                }
            }

        }
        else {
            this.setImage(band);
        }




    }
}

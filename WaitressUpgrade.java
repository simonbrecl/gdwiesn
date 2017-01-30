import greenfoot.*;

import java.awt.*;
import java.util.*;

/**
 * Created by ericasolum on 1/30/17.
 */
public class WaitressUpgrade extends Actor {
	static final int UPGRADE_COST = 200;


	private String clickToBuy = "\n\nClick to buy!";

	private String moneyExtra = "\n\nSorry, you need more money!";

	private boolean upgradeBought = false;

	private int nextUpgradeCost = 200;

	//Add text box here
	private String text = "Purchase Super Speedy Shoes for your waitress so that she can walk and serve people faster." +
			"\nCost: " + nextUpgradeCost + "€";

	private GreenfootImage upgradeOverlay = new GreenfootImage("waitress-overlay.png");
	private GreenfootImage upgrade = new GreenfootImage("waitress-upgraded.png");

	private TextBox notEnoughMoneyBox = new TextBox(new Point(250, 100), text + moneyExtra, new Font("Helvetica", Font.PLAIN, 15));

	private TextBox readyToBuyBox = new TextBox(new Point(250, 100), text + clickToBuy, new Font("Helvetica", Font.PLAIN, 15));


	private TextBox currentBox;

	private boolean boxShowing = false;

	private UpgradeScreen world;

	public WaitressUpgrade(int level) {
		if (level > 1) {
			upgradeBought = true;
		}
		prepare();
	}

	public void addedToWorld(World world) {
		this.world = (UpgradeScreen)getWorld();
	}



	private void prepare() {
		if (upgradeBought) {
			this.setImage(upgrade);
		} else {
			this.setImage(upgradeOverlay);
		}

		nextUpgradeCost = UPGRADE_COST;

		//Set the text boxes to read-only
		readyToBuyBox.setReadOnly(true);
		notEnoughMoneyBox.setReadOnly(true);
	}

	private void buyUpgrade() {


		if (world != null) {
			boolean success = world.tentState.upgradeWaitress();

			if (success) {
				upgradeBought = true;
				this.setImage(upgrade);
				world.upgrademap.money.updateMoney(world.tentState.getMoney());
			}
		}
	}

	public void act() {
		greenfoot.MouseInfo mouseInfo = Greenfoot.getMouseInfo();

		if(!world.infoShowing) {
			if (boxShowing) {
				world.removeObject(currentBox);
				boxShowing = false;
			}

			if (!upgradeBought) {
				this.setImage(upgradeOverlay);

				if (mouseInfo != null) {
					java.util.List objects = getWorld().getObjectsAt(mouseInfo.getX(), mouseInfo.getY(), WaitressUpgrade.class);
					for (Object object : objects) {
						if (object == this) {
							this.setImage(upgrade);
						}

						if (world.getTentState().getMoney() < nextUpgradeCost) {
							currentBox = notEnoughMoneyBox;
						} else {
							currentBox = readyToBuyBox;
						}

						world.addObject(currentBox, 380, 500);
						boxShowing = true;
					}
					// Buy upgrade
					if (Greenfoot.mouseClicked(this)) {
						if (world.getTentState().getMoney() >= nextUpgradeCost) {
							if (!upgradeBought) {
								buyUpgrade();
							}
						}
					}
				}
			} else {
				this.setImage(upgrade);
			}
		}

	}

}

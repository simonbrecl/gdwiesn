import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import greenfoot.World;

import java.awt.*;
import java.util.List;

/**
 * The kitchen upgrade.
 */
public class KitchenUpgrade extends Actor {
	static final int PRETZEL_PRICE = 100;

	static final int SAUSAGE_PRICE = 50;

	private String clickToBuy = "\n\nClick to buy!";

	private String moneyExtra = "\n\nSorry, you need more money!";

	private boolean pretzelBought = false;

	private boolean sausageBought = false;

	private boolean sausageDisabled = true;

	private int nextUpgradeCost = 100;

	//Add text box here
	private String text = "Purchase the kitchen so customers can buy yummy pretzels." +
			"\nCost: " + nextUpgradeCost + "€";

	private GreenfootImage upgrade1 = new GreenfootImage("kitchen-upgrade1.png");

	private GreenfootImage upgrade1Overlay = new GreenfootImage("kitchen-upgrade1-overlay.png");

	private GreenfootImage upgrade2 = new GreenfootImage("kitchen-upgrade2.png");

	private GreenfootImage upgrade2Overlay = new GreenfootImage("kitchen-upgrade2-overlay.png");

	private TextBox notEnoughMoneyBox = new TextBox(new Point(250, 100), text + moneyExtra, new Font("Helvetica", Font.PLAIN, 15));

	private TextBox readyToBuyBox = new TextBox(new Point(250, 100), text + clickToBuy, new Font("Helvetica", Font.PLAIN, 15));

	private TextBox currentBox;

	private boolean boxShowing = false;

	private UpgradeScreen world;

	public KitchenUpgrade(int level) {
		if (level > 0) {
			pretzelBought = true;
		}
		prepare();
	}

	public void addedToWorld(World world) {
		this.world = (UpgradeScreen)getWorld();
	}

	private void prepare() {
		//Set the text boxes to read-only
		readyToBuyBox.setReadOnly(true);
		notEnoughMoneyBox.setReadOnly(true);

		if (pretzelBought && sausageDisabled) {
			if (!sausageDisabled) {
				this.setImage(upgrade2Overlay);
			} else {
				nextUpgradeCost = SAUSAGE_PRICE;
				this.setImage(upgrade1);
			}
		} else if (sausageBought) {
			this.setImage(upgrade2);
		} else {
			this.setImage(upgrade1Overlay);
		}
	}

	private void buyPretzel() {
		pretzelBought = true;
		nextUpgradeCost = SAUSAGE_PRICE;
		this.setImage(new GreenfootImage("kitchen-upgrade2-overlay.png"));
		if (world != null) {
			boolean success = world.tentState.upgradeKitchen();
			if (success) {
				world.upgrademap.money.updateMoney(world.tentState.getMoney());
			}
		}
	}

	public void buySausage() {
		sausageBought = true;
		this.setImage(new GreenfootImage("kitchen-upgrade2.png"));
		if (world != null) {
			world.tentState.upgradeKitchen();
		}
	}

	public void act() {
		MouseInfo mouseInfo = Greenfoot.getMouseInfo();

		if (boxShowing) {
			world.removeObject(currentBox);
			boxShowing = false;
		}
		if (!pretzelBought) {
			this.setImage(upgrade1Overlay);
		} else if (pretzelBought && !sausageBought && !sausageDisabled) {
			this.setImage(upgrade2Overlay);
		} else {
			if (!sausageDisabled) {
				this.setImage(upgrade2);
			}
		}

		if (mouseInfo != null) {
			List objects = world.getObjectsAt(mouseInfo.getX(), mouseInfo.getY(), KitchenUpgrade.class);
			for (Object object : objects) {
				if (object == this) {
					if (!sausageBought) {
						this.setImage(upgrade1);
					} else {
						if (!sausageDisabled) {
							this.setImage(upgrade2);
						}
					}

					if (world.getTentState().getMoney() < nextUpgradeCost) {
						currentBox = notEnoughMoneyBox;
					} else {
						currentBox = readyToBuyBox;
					}
					if (!pretzelBought) {
						world.addObject(currentBox, 600, 100);
						boxShowing = true;
					}
				}
			}

			// Buy upgrade
			if (Greenfoot.mouseClicked(this)) {
				if (world.getTentState().getMoney() >= nextUpgradeCost) {
					if (!pretzelBought) {
						buyPretzel();
					}
					/*else if(pretzelBought && !sausageBought) {
						buySausage();
                    }*/
				}
			}
		}
	}
}

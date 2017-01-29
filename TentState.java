/**
 * Class for storing the current state of the tent, including upgrades and additional areas.
 */
public class TentState {

	private int kitchenLevel;

	private int securityLevel;

	private int bandLevel;

	private int decorationsLevel;

	private int barLevel;

	private int money;

	private int day;

	/* Initialized at the start of the game */
	public TentState() {
		kitchenLevel = 0;
		securityLevel = 0;
		bandLevel = 0;
		decorationsLevel = 0;
		barLevel = 1;
		money = 0;
		day = 1;
	}

	void updateMoney(int m) {
		money = m;
	}

	/**
	 * UPGRADE METHODS
	 */

    /* Upgrade the kitchen level.
	Returns true if successful, false otherwise. */
	boolean upgradeKitchen() {
		//Buy pretzels
		if (kitchenLevel == 0) {
			if (money < KitchenUpgrade.PRETZEL_PRICE) {
				return false;
			}
			money -= KitchenUpgrade.PRETZEL_PRICE;
		}

		//Buy pretzels
		else if (kitchenLevel == 1) {
			if (money < KitchenUpgrade.SAUSAGE_PRICE) {
				return false;
			}
			money -= KitchenUpgrade.SAUSAGE_PRICE;
		}
		if (kitchenLevel == 2) {
			return false;
		}
		kitchenLevel++;
		return true;
	}

	/* Upgrade the bar level */
	boolean upgradeBar() {
		if (barLevel < 2 && money >= BarUpgrade.SECOND_TAP_PRICE) {
			barLevel++;
			money -= BarUpgrade.SECOND_TAP_PRICE;
			return true;
		}
		return false;
	}

	/* Upgrade the decorations level */
	public boolean upgradeDecorations() {
		if (decorationsLevel < 3) {
			decorationsLevel++;
			return true;
		}
		return false;
	}

	boolean upgradeBand() {
		if (bandLevel == 0) {
			if (money < BandUpgrade.BAND_COST) {
				return false;
			}
			money -= BandUpgrade.BAND_COST;
			bandLevel++;
			return true;
		}
		return false;
	}

	public boolean upgradeSecurity() {
		if (securityLevel < 1) {
			securityLevel++;
			return true;
		}
		return false;
	}

	boolean increaseDay() {
		if (day < 16) {
			day++;
			return true;
		}
		return false;
	}

	/**
	 * GET METHODS
	 */

    /* Get the level of the kitchen.
		0 - no kitchen
        1 - kitchen only sells pretzels
        2 - kitchen sells sausages and pretzels
     */
	int getKitchenLevel() {
		return kitchenLevel;
	}

	/* Get the level of the bar.
		1 - bar only has one tap
		2 - bar has two barrels
	 */
	int getBarLevel() {
		return barLevel;
	}

	/* Get the level of the decorations.
	 */
	public int getDecorationsLevel() {
		return decorationsLevel;
	}

	public int getSecurityLevel() {
		return securityLevel;
	}

	/* Get the level of the bar.
		0 - no band
		1 - band only plays annoying music?
		2 - band plays nice music and switches up the songs?
	 */
	int getBandLevel() {
		return bandLevel;
	}

	/*
		Return the number of possible things customers can order
		1 - only beer
		2 - beer and pretzels
		3 - beer, pretzels, and sausages
	 */
	int getNumOrderOptions() {
		return kitchenLevel + 1;
	}

	public int getMoney() {
		return money;
	}

	int getDay() {
		return day;
	}
}

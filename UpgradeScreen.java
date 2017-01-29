import greenfoot.Greenfoot;
import greenfoot.World;

/**
 * The upgrade screen.
 */
public class UpgradeScreen extends World {

	TentState tentState;

	Upgrademap upgrademap;

	public UpgradeScreen(TentState state) {
		super(800, 600, 1);
		tentState = state;
		prepare();
	}

	private void prepare() {
		upgrademap = new Upgrademap("levels/Upgrade-Screen.xml", this, tentState);
	}

	public void act() {

	}

	TentState getTentState() {
		return tentState;
	}

	void goToNextDay() {
		tentState.increaseDay();

		Customer.counter1 = 0;

		switch (tentState.getDay()) {
			case 2:
				Greenfoot.setWorld(new Level2(tentState));
				break;

			case 3:
				Greenfoot.setWorld(new Level3(tentState));
				break;

			case 4:
				Greenfoot.setWorld(new Level4(tentState));
				break;

			case 5:
				Greenfoot.setWorld(new Level5(tentState));
				break;

			case 6:
				Greenfoot.setWorld(new Level6(tentState));
				break;

			case 7:
				Greenfoot.setWorld(new Level7(tentState));
				break;

			case 8:
				Greenfoot.setWorld(new Level8(tentState));
				break;
		}
	}
}

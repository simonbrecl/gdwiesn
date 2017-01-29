import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.World;

/**
 * The beer while its poured.
 */

public class Beer extends Actor {
	private static final int POUR_TIME = 2000;

	private static boolean isPoured = false;

	private static boolean isFlashing = false;

	private long pourTimer = System.currentTimeMillis();

	private Bar bar;

	private int counter = 0;

	public void act() {
		// pour();
		if (isFlashing) {
			counter++;
			if (counter % 25 == 0) {
				setImage("new-beer-glow.png");
			}
			if (counter % 50 == 0) {
				setImage("new-beer.png");
				counter = 0;
			}
			if (Greenfoot.mouseClicked(this)) {
				isFlashing = false;
			}
		}
	}

	void pour() {
		//setImage("beer.png");
		isPoured = true;

		if ((System.currentTimeMillis() - pourTimer) > POUR_TIME) {
			setImage("beer.png");
			isPoured = true;
		}
	}

	boolean isPoured() {
		//lets the waitress check if the beer is poured
		return isPoured;
	}

	void pickUp() {
		if (bar != null) bar.beerCount--;

		getWorld().removeObject(this);
	}

	@Override
	protected void addedToWorld(World world) {
		Actor bar = getOneIntersectingObject(Bar.class);

		if (bar != null) {
			this.bar = (Bar)bar;

			this.bar.beerCount++;
		}
	}

	void beerFlash() {
		isFlashing = true;
	}
}

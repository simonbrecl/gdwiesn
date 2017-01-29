import greenfoot.Actor;
import greenfoot.World;

/**
 * A pretzel.
 */
public class Pretzel extends Actor {

	private static boolean isMade = true;

	private Kitchen kitchen;

	public Pretzel() {
		this.setImage("plate-pretzel.png");
	}

	void pickUp() {
		if (kitchen != null) kitchen.foodCount--;

		getWorld().removeObject(this);
	}

	protected void addedToWorld(World world) {
		Actor kitchen = getOneIntersectingObject(Kitchen.class);

		if (kitchen != null) {
			this.kitchen = (Kitchen)kitchen;

			this.kitchen.foodCount++;
		}
	}

	boolean isMade() {
		//setImage("beer.png");
		return isMade;
	}

	public void make() {
		isMade = true;
	}
}

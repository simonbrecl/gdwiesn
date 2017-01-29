import greenfoot.Actor;
import greenfoot.World;

/**
 * Created by ericasolum on 12/24/16.
 */
public class Pretzel extends Actor {
	private static final int POUR_TIME = 2000;

	static boolean isMade = true;

	public long pourTimer = System.currentTimeMillis();

	public boolean isClicked = false;

	private Kitchen kitchen;

	private int counter = 0;

	public Pretzel() {
		this.setImage("plate-pretzel.png");
	}

	public void pickUp() {
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

	public boolean isMade() {
		//setImage("beer.png");
		return isMade;
	}

	public void make() {
		isMade = true;
	}
}

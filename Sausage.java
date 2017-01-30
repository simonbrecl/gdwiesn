/**
 * Created by ericasolum on 1/29/17.
 * Class for the sausage food item.
 */

import greenfoot.Actor;
import greenfoot.World;

public class Sausage extends Actor {

	private static boolean isMade = true;

	private Kitchen kitchen;

	public Sausage() {
		this.setImage("plate-sausage.png");
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


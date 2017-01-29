import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

/**
 * The kitchen.
 */
public class Kitchen extends Actor {
	static final int FOOD_MAX = 4;

	int foodCount = 0;

	private boolean mouseOver = false;

	private int level = 1;

	public Kitchen() {
		this.setImage("kitchen-counter1.png");
	}

	@Override
	protected void addedToWorld(World world) {
		getWorld().addObject(new PretzelMachine(this), getX() - 150, getY() - 25);
	}

	public void act() {
		if (!mouseOver && Greenfoot.mouseMoved(this)) {
			if (level == 1) {
				setImage("kitchen1-highlight.png");
			} else {
				setImage("kitchen2-highlight.png");
			}

			mouseOver = true;
		}
		if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
			if (level == 1) {
				setImage("kitchen-counter1.png");
			} else {
				setImage("kitchen-upgrade2.png");
			}

			mouseOver = false;
		}
	}

	public void upgrade() {
		level = 2;
		this.setImage(new GreenfootImage("kitchen-upgrade2.png"));

		//Later add sausage machine
	}
}

import greenfoot.Actor;
import greenfoot.Greenfoot;

/**
 * The beer barrel.
 */
public class BeerBarrel extends Actor {
	private boolean mouseOver = false;

	public void act() {
		{
			if (!mouseOver && Greenfoot.mouseMoved(this)) {
				setImage("barrel1.png");
				mouseOver = true;
			}
			if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
				setImage("barrel.png");
				mouseOver = false;
			}
		}
	}
}

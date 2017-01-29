import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.MouseInfo;
import greenfoot.World;

/**
 * The no lives left screen.
 */
public class NoLives extends World {
	private LevelBase previousLevel;

	/**
	 * Constructor for objects of class NoLives.
	 */
	public NoLives(LevelBase previousLevel) {
		// Create a new world with 600x400 cells with a cell size of 1x1 pixels.
		super(800, 600, 1);
		GreenfootSound sound = new GreenfootSound("fail.wav");
		sound.play();
		this.previousLevel = previousLevel;
	}

	public void act() {
		MouseInfo mouse = Greenfoot.getMouseInfo();
		if (Greenfoot.mouseClicked(null)) {
			int x = mouse.getX();
			int y = mouse.getY();
			if (x > 231 && x < 532 && y > 375 && y < 442) {
				Greenfoot.setWorld(previousLevel);
			}
		}
	}
}

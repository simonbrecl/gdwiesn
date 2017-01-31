import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
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
		setBackground(new GreenfootImage("LivesLost.png"));
	}

	public void act() {
		MouseInfo mouse = Greenfoot.getMouseInfo();
		if (Greenfoot.mouseClicked(null)) {
			int x = mouse.getX();
			int y = mouse.getY();
			if (x >= 200 && x <= 500 && y >= 400) {
				Greenfoot.setWorld(previousLevel);
			}
		}
	}
}

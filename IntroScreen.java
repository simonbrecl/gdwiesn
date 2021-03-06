import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.MouseInfo;
import greenfoot.World;

/**
 * The intro screen.
 */
public class IntroScreen extends World {
	private GreenfootSound introSound1 = new GreenfootSound("intro.mp3");

	/**
	 * Constructor for objects of class IntroScreen.
	 */
	public IntroScreen() {
		// Create a new world with 600x400 cells with a cell size of 1x1 pixels.
		super(800, 600, 1);
		prepare();
		started();
	}

	public void started() {
		introSound1.playLoop();
	}

	public void act() {
		MouseInfo mouse = Greenfoot.getMouseInfo();
		if (Greenfoot.mouseClicked(null)) {
			int x = mouse.getX();
			int y = mouse.getY();
			if (x > 305 && x < 490 && y > 464 && y < 530) {
				introSound1.stop();
				LevelBase myworld = new Level1();
				Greenfoot.setWorld(myworld);
			}

			if (x > 304 && x < 490 && y > 375 && y < 450) {
				introSound1.stop();
				LevelBase myworld = new LevelIntro(this);
				Greenfoot.setWorld(myworld);
			}
		}
	}

	/**
	 * Prepare the world for the start of the program.
	 * That is: create the initial objects and add them to the world.
	 */
	private void prepare() {
	}
}

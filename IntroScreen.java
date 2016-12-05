import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.MouseInfo;
import greenfoot.World;

/**
 * Write a description of class IntroScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IntroScreen extends World {
    private GreenfootSound introSound1 = new GreenfootSound("intro.mp3");
    private int i;

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
            if (x > 304 && x < 490 && y > 375 && y < 450) {
                introSound1.stop();
                MyWorld myworld = new MyWorld();
                Greenfoot.setWorld(myworld);
            }

            if (x > 305 && x < 490 && y > 464 && y < 530) {
                Intro intro = new Intro(this);
                Greenfoot.setWorld(intro);
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

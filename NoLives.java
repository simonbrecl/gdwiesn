import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.MouseInfo;
import greenfoot.World;

/**
 * Write a description of class NoLives here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NoLives extends World
{

    /**
     * Constructor for objects of class NoLives.
     * 
     */
    public NoLives()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        GreenfootSound sound = new GreenfootSound("fail.wav");
        sound.play();
    }
    
    public void act () {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseClicked(null)) {
            int x = mouse.getX();
            int y = mouse.getY();
            if (x > 231 && x < 532 && y > 375 && y < 442) {
                Level1 myworld = new Level1();
                Greenfoot.setWorld(myworld);
            }
        }
    }
}

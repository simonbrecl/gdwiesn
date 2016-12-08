import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

/**
 * Write a description of class Bar here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bar extends Actor {
    static final int BEER_MAX = 5;
    int beerCount = 0;

    public Bar() {
        /*
        int x = 25;
        
        for (int i = 0; i < 5; i++) {
            getImage().drawImage(new GreenfootImage("new-beer.png"), x, 10);
        
            x += 45;
        }
        */
        this.setImage(new GreenfootImage("bar-for-barrel.png"));
    }

    @Override
    protected void addedToWorld(World world) {
        getWorld().addObject(new BeerButton(this), getX() + 90, getY() - 25);
    }

    /**
     * Act - do whatever the Bar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Add your action code here.
    }
}

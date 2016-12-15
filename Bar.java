import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;
import greenfoot.*;

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
        this.setImage(new GreenfootImage("bar.png"));
    }

    @Override
    protected void addedToWorld(World world) {
        getWorld().addObject(new BeerButton(this), getX() + 150, getY() - 25);
    }

    /**
     * Act - do whatever the Bar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean mouseOver = false;
    public void act() {
        if (!mouseOver && Greenfoot.mouseMoved(this))  
           {  
                setImage("bar-for-barrel1.png");  
                mouseOver = true;  
           }  
        if (mouseOver && Greenfoot.mouseMoved(null) && ! Greenfoot.mouseMoved(this))  
           {  
               setImage("bar-for-barrel.png");  
               mouseOver = false;  
           } 
    }
}

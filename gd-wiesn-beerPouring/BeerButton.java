import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BeerButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BeerButton extends Actor
{
    /**
     * Act - do whatever the BeerButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        pourBeer();
    }
    public void pourBeer()
    {
        if (Greenfoot.mouseClicked(this)) {
            World world = getWorld();
            Beer newBeer = new Beer();
            world.addObject(newBeer, 307, 20);
            newBeer.pour();
        }
    }
}

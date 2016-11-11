import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BeerButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BeerButton extends Actor
{
    private boolean filling;
    private int fillCounter = 0;
    /**
     * Act - do whatever the BeerButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        pourBeer();
        if(filling) {
            fillCounter++;
        }
    }
    public void pourBeer()
    {
        if (Greenfoot.mouseClicked(this)) {
            filling = true;
            this.setImage(new GreenfootImage("barrel-empty-beer.png"));
            
            
        }
        
        if(filling) {
            if (fillCounter == 60) {
            this.setImage(new GreenfootImage("barrel-filling-beer.png"));
            }
            else if (fillCounter == 120) {
                this.setImage(new GreenfootImage("barrel-full-beer.png"));
            }
            else if(fillCounter >= 180) {
                this.setImage(new GreenfootImage("barrel.png"));
                
                World world = getWorld();
                Beer newBeer = new Beer();
                world.addObject(newBeer, 307, 30);
                newBeer.pour();
                
                filling = false;
                fillCounter = 0;
            }
        }
        
    }
}

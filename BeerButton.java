import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

/**
 * Write a description of class BeerButton here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BeerButton extends Actor {

    private Bar bar;

    private boolean filling;
    private int fillCounter = 0;

    BeerButton(Bar bar) {
        this.bar = bar;
    }

    /**
     * Act - do whatever the BeerButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        pourBeer();

        if (filling) {
            fillCounter++;
        }

    }

    public void pourBeer() {
        if (Greenfoot.mouseClicked(this) && bar.beerCount < bar.BEER_MAX) {

            filling = true;
            this.setImage(new GreenfootImage("barrel-empty-beer.png"));
        }

        if (filling) {
            if (fillCounter == 40) {
                this.setImage(new GreenfootImage("barrel-filling-beer.png"));
            } else if (fillCounter == 80) {
                this.setImage(new GreenfootImage("barrel-full-beer.png"));
            } else if (fillCounter >= 100) {
                this.setImage(new GreenfootImage("barrel.png"));

                World world = getWorld();
                Beer newBeer = new Beer();
                world.addObject(newBeer, bar.getX() - 20 + (bar.beerCount * 20), bar.getY() - 20);
                newBeer.pour();

                Greenfoot.playSound("zischen-sprudelwasser.mp3");

                filling = false;
                fillCounter = 0;
            }
        }
    }
}

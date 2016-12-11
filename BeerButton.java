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

    private int counter = 0;
    static boolean barrelFlash;
    
    BeerButton(Bar bar) {
        this.bar = bar;
    }

    /**
     * Act - do whatever the BeerButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BeerButton() {
        barrelFlash = false;
    }
    
    public void act() {
        pourBeer();

        if (filling) {
            fillCounter++;
        }
        
        // flash the barrel for tutorial mode
        if (barrelFlash == true) {
            counter++;
            if (counter%25==0) {
                setImage("barrelGlow.png");
            }
            if (counter%50==0) {
                setImage("barrel.png");
                counter = 0;
            }
            if (Greenfoot.mouseClicked(this)) {
                barrelFlash = false;
                MyWorld world = (MyWorld) getWorld();
                world.incrementTutorialStage();
            }
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

                MyWorld world = (MyWorld) getWorld();
                Beer newBeer = new Beer();
                world.addObject(newBeer, bar.getX() - 20 + (bar.beerCount * 20), bar.getY() - 20);
                newBeer.pour();
                
                if (world.isTutorialActive()) {
                    newBeer.beerFlash();
                }

                Greenfoot.playSound("zischen-sprudelwasser.mp3");

                filling = false;
                fillCounter = 0;
            }
        }
    }
    
    static void barrelFlash() {
        //change from true to false or vice versa.
        if (barrelFlash == true) {
            barrelFlash = false;
        }
        if (barrelFlash == false) {
            barrelFlash = true;
        }
    }
    
    static boolean isFlashing() {
        return barrelFlash;
    }
}

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * Write a description of class BeerButton here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BeerButton extends Actor {

    static boolean barrelFlash;
    /**
     * Act - do whatever the BeerButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean mouseOver = false;
    private Bar bar;
    private boolean filling;
    private int fillCounter = 0;
    private int counter = 0;

    BeerButton(Bar bar) {
        this.bar = bar;
    }

    public BeerButton() {
        barrelFlash = false;
    }

    static boolean isFlashing() {
        return barrelFlash;
    }

    public void act() {
        pourBeer();

        if (filling) {
            fillCounter++;
        }

        if (!mouseOver && Greenfoot.mouseMoved(this)) {
            setImage("barrel1.png");
            mouseOver = true;
        }
        if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
            setImage("barrel.png");
            mouseOver = false;
        }

        // flash the barrel for tutorial mode
        if (barrelFlash) {
            counter++;
            if (counter % 25 == 0) {
                setImage("barrelGlow.png");
            }
            if (counter % 50 == 0) {
                setImage("barrel.png");
                counter = 0;
            }
            if (Greenfoot.mouseClicked(this)) {
                barrelFlash = false;
                Level1 world = (Level1) getWorld();
                world.incrementTutorialStage();
            }
        }
    }

    public void pourBeer() {
        if (Greenfoot.mouseClicked(this) && bar.beerCount < bar.BEER_MAX) {

            filling = true;
            this.setImage(new GreenfootImage("barrel-empty-beer1.png"));
        }

        if (filling) {
            if (fillCounter == 40) {
                this.setImage(new GreenfootImage("barrel-filling-beer1.png"));
            } else if (fillCounter == 80) {
                this.setImage(new GreenfootImage("barrel-full-beer1.png"));
            } else if (fillCounter >= 100) {
                this.setImage(new GreenfootImage("barrel.png"));

                Beer newBeer = new Beer();
                if (getWorld() instanceof Level1) {
                    Level1 world = (Level1) getWorld();
                    world.addObject(newBeer, bar.getX() - 20 + (bar.beerCount * 20), bar.getY() - 20);
                    if (world.isTutorialActive()) {
                        newBeer.beerFlash();
                    }
                } else {
                    LevelBase world = (LevelBase) getWorld();
                    world.addObject(newBeer, bar.getX() - 20 + (bar.beerCount * 20), bar.getY() - 20);
                }
                newBeer.pour();


                Greenfoot.playSound("zischen-sprudelwasser.mp3");

                filling = false;
                fillCounter = 0;
            }
        }
    }

    void barrelFlash() {
        //change from true to false or vice versa.
        barrelFlash = !barrelFlash;
    }
}

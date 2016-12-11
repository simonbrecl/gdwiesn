import greenfoot.*;

/**
 * Write a description of class Beer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Beer extends Actor {
    private Bar bar;
    public long pourTimer = System.currentTimeMillis();
    private static final int POUR_TIME = 2000;
    public boolean isClicked = false;
    static boolean isPoured = false;
    static boolean isFlashing = false;
    private int counter = 0;

    public void act() {
        // pour();
        if (isFlashing == true) {
            counter++;
            if (counter%25==0) {
                setImage("new-beer-glow.png");
            }
            if (counter%50==0) {
                setImage("new-beer.png");
                counter = 0;
            }
            if (Greenfoot.mouseClicked(this)) {
                isFlashing = false;
                MyWorld world = (MyWorld) getWorld();
                //world.incrementTutorialStage();
            }
        }

    }

    public void pour() {
        //setImage("beer.png");
        isPoured = true;


        if ((System.currentTimeMillis() - pourTimer) > POUR_TIME) {
            setImage("beer.png");
            isPoured = true;
        }


    }

    public boolean isPoured() {
        //lets the waitress check if the beer is poured
        return isPoured;
    }

    void pickUp() {
        if (bar != null) bar.beerCount--;

        getWorld().removeObject(this);
    }

    @Override
    protected void addedToWorld(World world) {
        Actor bar = getOneIntersectingObject(Bar.class);

        if (bar != null) {
            this.bar = (Bar) bar;

            this.bar.beerCount++;
        }
    }
    public void beerFlash() {
        isFlashing = true;
    }
}

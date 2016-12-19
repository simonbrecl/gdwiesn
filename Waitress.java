import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

/**
 * Write a description of class Waitress here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Waitress extends MovableActor {
    private static final int PICKUP_RADIUS = 45;
    private static final int BEER_MAX = 5;

    private int beerCount = 0;
    private LevelBase world;

    private GreenfootImage originalImage;

    public Waitress(World world) {
        super("levels/MyWorld.xml", 5);
        this.world = (LevelBase) world;
        originalImage = getImage();
    }

    /**
     * Act - do whatever the Waitress wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        super.act();

        drawBeer();
        unloadBeer(); // check if waitress is touching a table with customers whilst she has beers
        checkBeerIsPoured(); //see if there are any beers ready close to the waitress
    }

    private void unloadBeer() {
        if (beerCount > 0 && isTouching(Table.class)) {
            Table table = (Table) getOneIntersectingObject(Table.class);

            if (table.incrementBeer()) {
                beerCount--;
                Greenfoot.playSound("put-on-table.wav");
            }
        }
    }


    private void checkBeerIsPoured() {
        if (!getObjectsInRange(PICKUP_RADIUS, Beer.class).isEmpty()) {
            Beer beer = getObjectsInRange(PICKUP_RADIUS, Beer.class).get(0);
            if (beer.isPoured() && beerCount < BEER_MAX) {
                beer.pickUp();
                beerCount++;
                //loadBeer();

                if (world instanceof Level1) {
                    if (((Level1) world).isTutorialActive()) {
                        ((Level1) world).incrementTutorialStage(); // tutorialStage = 5
                    }
               }


            }
        }
    }

    private void drawBeer() {
        int x = 0;
        int y = 0;

        int offset = (int) Math.floor(BEER_MAX / 2);

        setImage(new GreenfootImage(originalImage));


        if (beerCount != 0) {
            for (int i = 0; i < beerCount; i++) {
                y = 45 - (int) Math.pow(i - offset, 2);
                getImage().drawImage(new GreenfootImage("beer.png"), x, y);
                x += 5;
            }
        }
    }
    public int getBeerCount() {
        return beerCount;
    }
}

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
    private static final int TWO_HAND_BEER_MAX = 6;
    private static final int ONE_HAND_BEER_MAX = 3;
    private static final int TWO_HAND_FOOD_MAX = 2;
    private static final int ONE_HAND_FOOD_MAX = 1;
    private static final int FOOD_MAX = 2;
    private static final int PRETZEL_MAX = 2;
    private static final int DIFFERENT_ITEM_MAX = 2;
    private static final int BEER_TIME = 50;

    private int beerCount = 0;
    private int itemCount = 0;
    private int foodCount = 0;
    private int beerTimer = 0;
    private LevelBase world;

    private GreenfootImage originalImage = new GreenfootImage("oktoberfest-waitress.png");
    private GreenfootImage newImage = new GreenfootImage("oktoberfest-waitress.png");

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


        //Find more efficient way to do this later
        this.setImage(originalImage);

        /*if(!getObjectsInRange(PICKUP_RADIUS, Bar.class).isEmpty()
                || !getObjectsInRange(PICKUP_RADIUS, Table.class).isEmpty()) {
            drawBeer();
            unloadBeer(); // check if waitress is touching a table with customers whilst she has beers
            checkBeerIsPoured(); //see if there are any beers ready close to the waitress
        }

        if(!getObjectsInRange(PICKUP_RADIUS, Kitchen.class).isEmpty()
                || !getObjectsInRange(PICKUP_RADIUS, Table.class).isEmpty()) {
            drawPretzel();
            unloadPretzel(); // check if waitress is touching a table with customers while she has pretzels
            checkPretzelIsMade(); //see if there are any pretzels ready close to the waitress
        } */


        newImage = new GreenfootImage("oktoberfest-waitress.png");
        unloadBeer(); // check if waitress is touching a table with customers whilst she has beers
        checkBeerIsPoured(); //see if there are any beers ready close to the waitress

        //drawPretzel();
        unloadPretzel(); // check if waitress is touching a table with customers while she has pretzels
        checkPretzelIsMade(); //see if there are any pretzels ready close to the waitress

        if(itemCount > 0) {
            drawBeer();
            drawPretzel();
        }


        this.setImage(newImage);





    }

    private void unloadBeer() {
        if (beerCount > 0 && isTouching(Table.class)) {
            Table table = (Table) getOneIntersectingObject(Table.class);

            if (table.incrementBeer()) {
                beerCount--;
                itemCount--;
                Greenfoot.playSound("put-on-table.wav");
            }
        }
    }

    private void unloadPretzel() {
        if (foodCount > 0 && isTouching(Table.class)) {
            Table table = (Table) getOneIntersectingObject(Table.class);

            if (table.incrementPretzel()) {
                foodCount--;
                itemCount--;
                Greenfoot.playSound("put-on-table.wav");
            }
        }
    }


    private void checkBeerIsPoured() {
        if (!getObjectsInRange(PICKUP_RADIUS, Beer.class).isEmpty()) {
            Beer beer = getObjectsInRange(PICKUP_RADIUS, Beer.class).get(0);
            //Waitress can hold 6 beers or 3 beers and 1 food item
            if((beer.isPoured() && foodCount == 0 && beerCount < TWO_HAND_BEER_MAX)
                    || (beer.isPoured() && foodCount == 1 && beerCount < ONE_HAND_BEER_MAX)) {

                beer.pickUp();
                beerCount++;
                itemCount++;
                if (getWorld() instanceof Level1) {
                    Level1 world1 = (Level1) getWorld();
                    if (world1.isTutorialActive()) {
                        world1.incrementTutorialStage(); // tutorialStage = 5
                    }
                }



            }

        }
    }

    private void checkPretzelIsMade() {
        if (!getObjectsInRange(PICKUP_RADIUS, Pretzel.class).isEmpty()) {
            Pretzel pretzel = getObjectsInRange(PICKUP_RADIUS, Pretzel.class).get(0);
            if((pretzel.isMade() && beerCount == 0 && foodCount < TWO_HAND_FOOD_MAX)
                    || (pretzel.isMade() && beerCount > 0 && beerCount <= ONE_HAND_BEER_MAX && foodCount < ONE_HAND_FOOD_MAX)) {
                pretzel.pickUp();
                foodCount++;
                itemCount++;
            }
        }
    }

    private void drawPretzel() {
        int x = 30;
        int y = 0;

        int offset = (int) Math.floor(FOOD_MAX / 2);

        if (foodCount != 0) {
            for (int i = 0; i < foodCount; i++) {
                y = 45 - (int) Math.pow(i - offset, 2);

                //getImage().drawImage(new GreenfootImage("plate-pretzel.png"), x, y);
                newImage.drawImage(new GreenfootImage("plate-pretzel.png"), x, y);
                x -= 27;
            }
        }
    }

    private void drawBeer() {
        int x = 0;
        int y = 0;

        int offset = (int) Math.floor(TWO_HAND_BEER_MAX / 2);


        //setImage(new GreenfootImage(originalImage));

        //Not the most efficient way to do this but I don't care
        for (int i = 0; i < beerCount; i++) {
            //Position the left beers so that the middle one is drawn on top
            if(i == 0) {
                x = 0;
                y = 45;
            }
            if(i == 1) {
                x = 10;
                y = 45;
            }
            if(i == 2) {
                x = 5;
                y = 50;
            }

            //Position the right beers so that the middle one is drawn on top
            if(i == 3) {

                x = 30;
                y = 45;

            }
            //Position the right beers so that the middle one is drawn on top
            if(i == 4) {

                x = 40;
                y = 45;

            }
            //Position the right beers so that the middle one is drawn on top
            if(i == 5) {

                x = 35;
                y = 50;

            }

            //y = 45 - (int) Math.pow(i - offset, 2);
            //getImage().drawImage(new GreenfootImage("beer.png"), x, y);
            newImage.drawImage(new GreenfootImage("beer.png"), x, y);
            //x += 5;
        }

    }
    public int getItemCount() {
        return itemCount;
    }

}

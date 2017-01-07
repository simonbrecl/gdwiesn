import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * Write a description of class Waitress here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Waitress extends MovableActor {
    private static final int PICKUP_RADIUS = 45;
    private static final int BEER_MAX = 5;
    private static final int FOOD_MAX = 2;
    private static final int PRETZEL_MAX = 2;
    private static final int DIFFERENT_ITEM_MAX = 2;
    private static final int BEER_TIME = 50;

    private int beerCount = 0;
    private int itemCount = 0;
    private int pretzelCount = 0;
    private int beerTimer = 0;
    private MyWorld world1;

    private GreenfootImage originalImage = new GreenfootImage("oktoberfest-waitress.png");
    private GreenfootImage newImage = new GreenfootImage("oktoberfest-waitress.png");

    public Waitress() {
        super("levels/MyWorld.xml", 5);

        //originalImage = getImage();
        //newImage = getImage();
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
        if (pretzelCount > 0 && isTouching(Table.class)) {
            Table table = (Table) getOneIntersectingObject(Table.class);

            if (table.incrementPretzel()) {
                pretzelCount--;
                itemCount--;
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
                itemCount++;
                //loadBeer();
                
                if (getWorld() instanceof MyWorld) {
                world1 = (MyWorld) getWorld();
               }
              
            
               if (world1.isTutorialActive()) {
                    world1.incrementTutorialStage(); // tutorialStage = 5
               }
            }
        }
    }

    private void checkPretzelIsMade() {
        if (!getObjectsInRange(PICKUP_RADIUS, Pretzel.class).isEmpty()) {
            Pretzel pretzel = getObjectsInRange(PICKUP_RADIUS, Pretzel.class).get(0);
            if (pretzel.isMade() && pretzelCount < FOOD_MAX) {
                pretzel.pickUp();
                pretzelCount++;
                itemCount++;
                //loadBeer();



            }
        }
    }

    private void drawPretzel() {
        int x = 3;
        int y = 0;

        int offset = (int) Math.floor(FOOD_MAX / 2);

        //setImage(new GreenfootImage(originalImage));


        if (pretzelCount != 0) {
            for (int i = 0; i < pretzelCount; i++) {
                y = 45 - (int) Math.pow(i - offset, 2);

                //getImage().drawImage(new GreenfootImage("plate-pretzel.png"), x, y);
                newImage.drawImage(new GreenfootImage("plate-pretzel.png"), x, y);
                x += 27;
            }
        }
    }

    private void drawBeer() {
        int x = 0;
        int y = 0;

        int offset = (int) Math.floor(BEER_MAX / 2);

        //setImage(new GreenfootImage(originalImage));


        for (int i = 0; i < beerCount; i++) {
            y = 45 - (int) Math.pow(i - offset, 2);
            //getImage().drawImage(new GreenfootImage("beer.png"), x, y);
            newImage.drawImage(new GreenfootImage("beer.png"), x, y);
            x += 5;
        }

    }
    public int getItemCount() {
        return itemCount;
    }

}

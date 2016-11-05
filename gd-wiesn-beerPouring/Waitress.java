import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.lang.Math;

/**
 * Write a description of class Waitress here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Waitress extends Actor {
    private static final int MOVE_DELTA = 5;
    private static final int PICKUP_RADIUS = 20;
    private static final int BEER_MAX = 5;
    private static final int BEER_TIME = 50;
    
    private int beerCount = 0;
    private int beerTimer = 0;
    
    private GreenfootImage originalImage;
    
    public Waitress() {
        originalImage = getImage();
    }
    
    /**
     * Act - do whatever the Waitress wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        move();
        drawBeer();
        unloadBeer(); // check if waitress is touching a table with customers whilst she has beers
        checkBeerIsPoured(); //see if there are any beers ready close to the waitress
        
        
    }    
    
    private void move() {
        int x = getX();
        int y = getY();
        
        if (Greenfoot.isKeyDown("up")) y -= MOVE_DELTA;
        if (Greenfoot.isKeyDown("left")) x -= MOVE_DELTA;
        if (Greenfoot.isKeyDown("right")) x += MOVE_DELTA;
        if (Greenfoot.isKeyDown("down")) y += MOVE_DELTA;
        
        setLocation(x, y);
    }
    
    private void loadBeer() {
        /*if (beerTimer > 0) {
            beerTimer--;
        } else if (beer < BEER_MAX && isTouching(Bar.class)) {
            beer++;
            beerTimer = BEER_TIME;
            
            Greenfoot.playSound("zischen-sprudelwasser.mp3");
        }
        */
        
    }
    
    private void unloadBeer() {
        /*
        if (beerTimer > 0) {
            beerTimer--;
        } else if (beerCount > 0 && isTouching(Table.class)) {
            Table table = (Table) getOneIntersectingObject(Table.class);
            
            if (table.incrementBeer()) {
                beerCount--;
                
                Greenfoot.playSound("put-on-table.wav");
            }
            beerTimer = BEER_TIME;
        }
        
        else if (beerCount > 0 && isTouching(Obstacle.class)) {
                Obstacle obstacle = (Obstacle) getOneIntersectingObject(Obstacle.class);
                Greenfoot.playSound("drop.mp3");
                beerCount--;
                beerTimer = BEER_TIME;
            }
        */
       if (beerCount > 0 && isTouching(Table.class))
       {
           Table table = (Table) getOneIntersectingObject(Table.class);
           
           if (table.incrementBeer()) 
           {
               beerCount--;
               Greenfoot.playSound("put-on-table.wav");
           }
       }
    }
    
    
    private void checkBeerIsPoured() 
    {
        if (!getObjectsInRange(PICKUP_RADIUS, Beer.class).isEmpty())
        {
            Beer beer = getObjectsInRange(PICKUP_RADIUS, Beer.class).get(0);
            if (beer.isPoured()) 
            {
                World world = getWorld();
                world.removeObject(beer);
                beerCount++;
                //loadBeer();
            }
        }
    }
    private void drawBeer()
    {
        int x = 0;
        int y = 0;
        
        int offset = (int) Math.floor(BEER_MAX / 2);
        
        setImage(new GreenfootImage(originalImage));
        
        
        if (beerCount != 0) {
            for (int i= 0; i < beerCount; i++)
            {y = 45 - (int) Math.pow(i - offset, 2);
            getImage().drawImage(new GreenfootImage("beer.png"), x, y);
            x +=5;
            }
        }
    }
}

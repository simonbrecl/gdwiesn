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
    
    private static final int BEER_MAX = 5;
    private static final int BEER_TIME = 50;
    
    private int beer = 0;
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
        
        loadBeer();
        unloadBeer();
        
        updateBeerCount();
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
        if (beerTimer > 0) {
            beerTimer--;
        } else if (beer < BEER_MAX && isTouching(Bar.class)) {
            beer++;
            beerTimer = BEER_TIME;
            
            Greenfoot.playSound("zischen-sprudelwasser.mp3");
        }
    }
    
    private void unloadBeer() {
        if (beerTimer > 0) {
            beerTimer--;
        } else if (beer > 0 && isTouching(Table.class)) {
            Table table = (Table) getOneIntersectingObject(Table.class);
            
            if (table.incrementBeer()) {
                beer--;
                
                Greenfoot.playSound("put-on-table.wav");
            }
            beerTimer = BEER_TIME;
        }
        
        else if (beer > 0 && isTouching(Obstacle.class)) {
                Obstacle obstacle = (Obstacle) getOneIntersectingObject(Obstacle.class);
                Greenfoot.playSound("drop.mp3");
                beer--;
                beerTimer = BEER_TIME;
            }
    }
    
    private void updateBeerCount() {
        int x = 0;
        int y = 0;
        
        int offset = (int) Math.floor(BEER_MAX / 2);
        
        setImage(new GreenfootImage(originalImage));
        
        for (int i = 0; i < beer; i++) {
            y = 45 - (int) Math.pow(i - offset, 2);
            
            getImage().drawImage(new GreenfootImage("beer.png"), x, y);
        
            x += 5;
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.lang.Math;
import java.util.List;

/**
 * Write a description of class Waitress here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Waitress extends Actor {
    private static final int MOVE_DELTA = 5;

    private List<int[]> moveToPath;
    
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
        if (moveToPath == null || moveToPath.size() == 0) {
            return;
        }

        int x = getX();
        int y = getY();

        int[] waypoint = moveToPath.get(0);

        int distanceX = Math.abs(x - waypoint[0]);
        int distanceY = Math.abs(y - waypoint[1]);

        double steps = Math.max(distanceX, distanceY) / MOVE_DELTA;

        int moveX = (int) Math.round(distanceX / steps);
        int moveY = (int) Math.round(distanceY / steps);

        if (distanceX <= MOVE_DELTA) {
            x = waypoint[0];
        } else {
            x += (x < waypoint[0]) ? moveX : -moveX;
        }

        if (distanceY <= MOVE_DELTA) {
            y = waypoint[1];
        } else {
            y += (y < waypoint[1]) ? moveY : -moveY;
        }

        if (x == waypoint[0] && y == waypoint[1]) {
            moveToPath.remove(0);
        }

        setLocation(x, y);
    }

    public void moveTo(List<int[]> path) {
        this.moveToPath = path;
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

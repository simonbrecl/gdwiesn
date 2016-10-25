import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class Table here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Table extends Actor {
    private static final int BEER_MAX = 8;
    
    int beer = 0;
    int wantBeer = 0;
    Seat[] seats;
    
    /**
     * Act - do whatever the Table wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if ((beer + wantBeer) < BEER_MAX && Greenfoot.getRandomNumber(1000) < 1) {
            wantBeer++;
        }

        updateBeerCount();
        updateWantBeerCount();
    }    
    
    public void incrementBeer() {
        if (beer >= BEER_MAX || wantBeer == 0) {
            return;
        }
        
        beer++;
        wantBeer--;
    }
    
    private void updateBeerCount() {
        int x = 13;
        int y = 0;
        
        for (int i = 0; i < beer; i++) {
            y = (i % 2 != 0) ? 30 : 0;
            
            getImage().drawImage(new GreenfootImage("beer.png"), x, y);
        
            if (i % 2 != 0) {
                x += 37;
            }
        }
    }
    
    private void updateWantBeerCount() {
        getImage().drawImage(new GreenfootImage(String.valueOf(wantBeer), 20, Color.WHITE, Color.BLACK), 0, 20);
    }
}

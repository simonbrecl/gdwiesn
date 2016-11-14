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

    private int beer = 0;
    private int wantBeer = 0;

    private GreenfootImage originalImage;

    Seat[] seats;

    public Table() {
        originalImage = getImage();
    }

    /**
     * Act - do whatever the Table wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
    }

    public synchronized boolean incrementBeer() {
        if (beer >= BEER_MAX || wantBeer <= 0) {
            return false;
        }
        beer++;
        updateBeerCount();
        updateWantBeerCount();
        return true;
    }

    private void updateBeerCount() {
        int x = 12;
        int y = 0;

        setImage(new GreenfootImage(originalImage));

        for (int i = 0; i < beer; i++) {
            y = (i % 2 != 0) ? 30 : 0;

            getImage().drawImage(new GreenfootImage("new-beer.png"), x, y);

            if (i % 2 != 0) {
                x += 37;
            }
        }
    }

    private void updateWantBeerCount() {
        getImage().drawImage(new GreenfootImage(String.valueOf(wantBeer), 20, Color.WHITE, Color.BLACK), 70, 20);
    }

    public synchronized void wantBeer() {
        wantBeer++;
        updateWantBeerCount();
    }

    public synchronized boolean takeBeer() {
        if (beer > 0) {
            wantBeer--;
            updateWantBeerCount();
            return true;
        } else {
            return false;
        }
    }

    public synchronized void cancelOrder() {
        wantBeer--;
        updateWantBeerCount();
    }

    public synchronized void finishedBeer() {
        beer--;
        updateBeerCount();
    }
}

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.awt.*;

/**
 * Write a description of class Table here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Table extends Actor {
    private Levelmap levelmap;

    private int beer = 0;
    private int wantBeer = 0;

    private GreenfootImage originalImage;

    public Table(World world, int x, int y, Levelmap levelmap) {
        this.levelmap = levelmap;

        originalImage = getImage();

        createSeats(world, x, y, true);
    }

    @Override
    protected void addedToWorld(World world) {
        createSeats(world, getX(), getY(), false);
    }

    private void createSeats(World world, int x, int y, boolean upperRow) {
        int seatOffset = -55;

        for (int i = 0; i < 4; i++) {
            world.addObject(new Seat(this, upperRow), x + seatOffset, y + (upperRow ? -50 : 40));

            seatOffset += 37;
        }
    }

    /**
     * Act - do whatever the Table wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        updateBeerCount();
        updateWantBeerCount();
    }

    public synchronized boolean incrementBeer() {
        if (wantBeer <= 0) {
            return false;
        }

        beer++;
        wantBeer--;
        updateBeerCount();
        updateWantBeerCount();

        levelmap.money.addMoney(15, getX() + 100, getY());

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
            beer--;
            updateBeerCount();

            return true;
        } else {
            return false;
        }
    }

    public synchronized void cancelOrder() {
        wantBeer--;
        updateWantBeerCount();
    }
}

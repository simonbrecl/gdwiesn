import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class Table here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Table extends Actor
{
    int beer = 0;
    
    /**
     * Act - do whatever the Table wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateBeerCount();
    }    
    
    public void incrementBeer() {
        beer++;
    }
    
    private void updateBeerCount() {
        getImage().drawImage(new GreenfootImage(String.valueOf(beer), 20, Color.WHITE, Color.BLACK), 0, 0);
    }
}

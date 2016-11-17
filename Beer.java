import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Beer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Beer extends Actor
{
    /**
     * Act - do whatever the Beer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public long pourTimer = System.currentTimeMillis();
    private static final int POUR_TIME = 2000;
    public boolean isClicked = false;
    static boolean isPoured = false;
    public void act() 
    {
        pour();
        
    }
    public void pour()
    {
            setImage("beer.png");
                isPoured = true;
        
       
            if ((System.currentTimeMillis()- pourTimer) > POUR_TIME)
            {
                setImage("beer.png");
                isPoured = true;
            }
           
        
    }
    public boolean isPoured() 
    {
        //lets the waitress check if the beer is poured
        return isPoured;
    }
        
}

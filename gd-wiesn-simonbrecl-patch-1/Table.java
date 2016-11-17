import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.awt.Font;
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
    private int timer = 0;
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
         Message msgbox = ((MyWorld) getWorld()).messagebox; 
         timer++;
        if (beer > 0 && Greenfoot.getRandomNumber(1000) < 1) {
            beer--;
            Greenfoot.playSound("drunk-up.wav");
            World myWorld = getWorld();
            MyWorld myworld = (MyWorld) myWorld;
            Money money = myworld.getMoney();
            money.addMoney(15);
            
            String text = "+15€"; 
            msgbox.setText(text); 
            getWorld().addObject(msgbox, getX()+100, getY());
                       
        }
        
        if ((beer + wantBeer) < BEER_MAX && Greenfoot.getRandomNumber(1000) < 1) {
            wantBeer++;
        }
        
        if (timer > 180) {
                getWorld().removeObject(msgbox);
                timer = 0;
        } 
        
        updateBeerCount();
        updateWantBeerCount();
    }    
    
    public boolean incrementBeer() {
        if (beer >= BEER_MAX || wantBeer == 0) {
            return false;
        }
        
        beer++;
        wantBeer--;
        
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
}

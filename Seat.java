import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Seat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Seat extends Actor {
    public boolean isTaken = false;
    
    /**
     * Act - do whatever the Seat wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Add your action code here.
    }
  
    public boolean isTaken(){
        return isTaken;
    }
    
    public void setTaken(boolean flag) {
        this.isTaken = flag;
    }
}

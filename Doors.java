import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Doors here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Doors extends Actor
{
    private int openCounter = 0;
    private boolean open = false;
    /**
     * Act - do whatever the Doors wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if(open) {
            openCounter++;
        }
        
        if(open) {
            if(openCounter <= 15) {
            transitionDoors();
            }
            else if(openCounter > 15 && openCounter <= 60) {
                openDoors();
                
            }
            else if(openCounter > 60 && openCounter <= 75) {
                transitionDoors();
                
            }
            else if(openCounter > 75) {
                closeDoors();
            }
        }
        
    }   
    
    public void openDoors() {
        
        open = true;
        this.setImage(new GreenfootImage("open-doors.png"));
    }
    
    public void transitionDoors() {
        this.setImage(new GreenfootImage("half-open-doors.png"));
    }
    
    public void closeDoors() {
        open = false;
        openCounter = 0;
        this.setImage(new GreenfootImage("closed-doors.png"));
    }
    
    public boolean areOpen() {
        return open;
    }
}

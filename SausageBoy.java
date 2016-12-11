import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SausageBoy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SausageBoy extends Actor
{
    /**
     * Act - do whatever the SausageBoy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        MyWorld world = (MyWorld) getWorld();
        if (world.getTutorialStage() >= 6 && world.getTutorialStage()<= 9) {
            if (Greenfoot.mouseClicked(this)) {
                // continue
                world.incrementTutorialStage();
            }
        }
    }    
    public SausageBoy() {
        setImage("sausage_1.png");
    }
    public void updateImage(int tutorialStage) {
        setImage("sausage_" + tutorialStage + ".png");
        
    }  
}

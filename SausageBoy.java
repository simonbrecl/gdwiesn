import greenfoot.Actor;
import greenfoot.Greenfoot;

/**
 * Write a description of class SausageBoy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SausageBoy extends Actor
{
    public SausageBoy() {
        setImage("sausage_1.png");
    }

    /**
     * Act - do whatever the SausageBoy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        Level1 world = (Level1) getWorld();
        if (world.getTutorialStage() >= 6 && world.getTutorialStage()<= 9) {
            if (Greenfoot.mouseClicked(this)) {
                // continue
                world.incrementTutorialStage();
            }
        }
    }

    public void updateImage(int tutorialStage) {
        setImage("sausage_" + tutorialStage + ".png");
        
    }  
}

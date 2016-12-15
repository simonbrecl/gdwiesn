import greenfoot.*;
/**
 * Write a description of class BeerBarrel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BeerBarrel extends Actor {
    /**
     * Act - do whatever the BeerBarrel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    boolean mouseOver = false;
    public void act() {
    {     
        if (!mouseOver && Greenfoot.mouseMoved(this))  
           {  
                setImage("barrel1.png");  
                mouseOver = true;  
           }  
        if (mouseOver && Greenfoot.mouseMoved(null) && ! Greenfoot.mouseMoved(this))  
           {  
               setImage("barrel.png");  
               mouseOver = false;  
           } 
    }
  }
}

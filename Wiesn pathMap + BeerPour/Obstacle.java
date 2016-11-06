import greenfoot.*;  
import java.awt.Color;

public class Obstacle extends Actor
{
    private int beer = 0;

     public void act() 
    {
        moveAround();
    }    
    
    public boolean atWorldEdge() {
    if (getX() <= 5 || getX() >= getWorld() . getWidth() -5)
        return true;
    if (getY() <= 5 || getY() >= getWorld() . getHeight() -5) 
        return true;
    else
        return false;
    }
    
  private void moveAround()  
    {    
    move (2);
    if(Greenfoot.getRandomNumber(100) > 96) {
        turn (Greenfoot.getRandomNumber(100));
    }
    if (getX() <= 5 || getX() >= getWorld().getWidth()-5) {
        turn(180);
    }
    if (getY() <= 5 || getY() >= getWorld().getWidth()-5) {
        turn(180);
    }
     
   if (atWorldEdge()){  
        turn (180);
   }  
  
   if (isTouching(Table.class) || isTouching(Bar.class)) {
         move(-2);
         setRotation(getRotation()+180);
  }
}  
     
   public void incrementBeer() {
            beer++;
        }
}


    

    
   

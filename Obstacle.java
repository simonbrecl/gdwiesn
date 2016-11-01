import greenfoot.*;  
import java.awt.Color;

public class Obstacle extends Actor
{
    private final int id;
    private int beer = 0;
    private int drinkingTime = 0;
    private boolean sitting = false;
    private Seat seat = null;

    public Obstacle(int id){
        this.id = id;
        drinkingTime = Greenfoot.getRandomNumber(2000)+1000;
    }

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
        if(isSitting()){
            drinkingTime--;
            if(drinkingTime == 0){
                seat.setTaken(false);
                World world = getWorld();
                world.removeObject(this);
            }
            return;
        }

       System.out.println(id + " is moving");
        int x = getX();
        int y = getY();
        int randomNum = Greenfoot.getRandomNumber(4);
        boolean walkedVertical=false;
        boolean positiveDirection=false;

        if (randomNum == 0) {
            y -= 5;
            walkedVertical=true;
            positiveDirection=false;
        } else if (randomNum == 1){
            x -= 5;
            walkedVertical=false;
            positiveDirection=false;
        } else if (randomNum == 2){
            x += 5;
            walkedVertical=false;
            positiveDirection=true;
        } else if (randomNum == 3) {
            y += 5;
            walkedVertical=true;
            positiveDirection=true;
        }

        if(isTouching(Table.class)){
            if(walkedVertical){
                if(positiveDirection){
                    y-=5;
                }else{
                    y+=5;
                }
            }else{
                if(positiveDirection){
                    x-=5;
                }else{
                    x+=5;
                }
            }
        }else if (isTouching(Seat.class)){
            seat = (Seat) getOneIntersectingObject(Seat.class);
            if(!seat.isTaken()){
                System.out.println(id + " sat down");
                seat.setTaken(true);
                x = seat.getX();
                y = seat.getY()+10;
                setSitting(true);
            }
        }
        /**else if(isTouching(Obstacle.class)){
            if(walkedVertical){
                if(positiveDirection){
                    y-=5;
                }else{
                    y+=5;
                }
            }else{
                if(positiveDirection){
                    x-=5;
                }else{
                    x+=5;
                }
            }
        }
        */
       System.out.println(id + " moved to "+ x +","+y);
        setLocation(x, y);
    }  

    public void incrementBeer() {
        beer++;
    }

    public boolean isSitting() {
        return sitting;
    }

    public void setSitting(boolean flag) {
        this.sitting = flag;
    }
}


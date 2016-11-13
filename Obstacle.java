import greenfoot.*;

import java.awt.Color;

public class Obstacle extends Actor {
    private final int id;
    private int beer = 0;
    private final int DRUNKTIME;
    private int drinkingTime = 0;
    private boolean sitting = false;
    private Seat seat = null;
    private boolean walkedVertical = false;
    private boolean positiveDirection = false;
    CustomerSmiley cs;

    public Obstacle(int id) {
        this.id = id;
        drinkingTime = Greenfoot.getRandomNumber(500) + 500;
        DRUNKTIME = drinkingTime;
    }

    public void act() {
        moveAround();
    }

    public boolean atWorldEdge() {
        if (getX() <= 5 || getX() >= getWorld().getWidth() - 5)
            return true;
        if (getY() <= 5 || getY() >= getWorld().getHeight() - 5)
            return true;
        else
            return false;
    }

    private void moveAround() {
        int posX = getX();
        int posY = getY();

        if (isSitting()) {
            drinkingTime--;
            if(drinkingTime < DRUNKTIME/2){
                cs.setImage("smiley4.png");
            }
            if(drinkingTime < DRUNKTIME/4){
                cs.setImage("smiley3.png");
            }
            if (drinkingTime == 0) {
                seat.setTaken(false);
                World world = getWorld();
                world.removeObject(this);
                world.removeObject(cs);
            }
            return;
        }

        System.out.println(id + " is moving");
        int randomNum = Greenfoot.getRandomNumber(4);

        for (int i = 0; i < 5; i++) {
            if (randomNum == 0) {
                posY -= 1;
                walkedVertical = true;
                positiveDirection = false;
            } else if (randomNum == 1) {
                posX -= 1;
                walkedVertical = false;
                positiveDirection = false;
            } else if (randomNum == 2) {
                posX += 1;
                walkedVertical = false;
                positiveDirection = true;
            } else if (randomNum == 3) {
                posY += 1;
                walkedVertical = true;
                positiveDirection = true;
            }

            if (isTouching(Table.class)) {
                if (walkedVertical) {
                    if (positiveDirection) {
                        posY -= 5;
                    } else {
                        posY += 5;
                    }
                } else {
                    if (positiveDirection) {
                        posX -= 5;
                    } else {
                        posX += 5;
                    }
                }
            } else if (isTouching(Seat.class)) {
                seat = (Seat) getOneIntersectingObject(Seat.class);
                if (!seat.isTaken()) {
                    System.out.println(id + " sat down");
                    seat.setTaken(true);
                    posX = seat.getX();
                    posY = seat.getY() + 10;
                    setSitting(true);
                    setLocation(posX, posY);
                    World w = getWorld();
                    cs = new CustomerSmiley();
                    w.addObject(cs, this.getX(),this.getY()-40);
                    break;
                }
            }
            setLocation(posX, posY);
        }
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


import greenfoot.*;

public class Obstacle extends Actor {
    private final int id;
    private final int TOTAL_WAITINGTIME;
    private final int TOTAL_DRINKINGTIME;
    private boolean waiting = false;
    private int currentWaitingTime = 0;
    private int currentDrinkingTime = 0;
    private boolean sitting = false;
    private Seat seat = null;
    private boolean walkedVertical = false;
    private boolean positiveDirection = false;
    CustomerSmiley cs;

    public Obstacle(int id) {
        this.id = id;
        currentWaitingTime = Greenfoot.getRandomNumber(500) + 500;
        currentDrinkingTime = Greenfoot.getRandomNumber(500) + 250;
        TOTAL_WAITINGTIME = currentWaitingTime;
        TOTAL_DRINKINGTIME = currentDrinkingTime;
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
            if (isWaiting()) {
                if (seat.getTable().takeBeer()) {
                    setWaiting(false);
                    return;
                }
                currentWaitingTime--;
                if (currentWaitingTime < TOTAL_WAITINGTIME / 2) {
                    cs.setImage("smiley4.png");
                }
                if (currentWaitingTime < TOTAL_WAITINGTIME / 4) {
                    cs.setImage("smiley3.png");
                }
                if (currentWaitingTime == 0) {
                    seat.getTable().cancelOrder();
                    seat.setTaken(false);
                    World world = getWorld();
                    world.removeObject(this);
                    world.removeObject(cs);
                }
                return;
            } else {
                if (currentDrinkingTime == TOTAL_DRINKINGTIME)
                    cs.setImage("fullbeer.png");
                currentDrinkingTime--;
                if (currentDrinkingTime < TOTAL_DRINKINGTIME - TOTAL_DRINKINGTIME / 3) {
                    cs.setImage("twothirdbeer.png");
                }
                if (currentDrinkingTime < TOTAL_DRINKINGTIME - 2 * TOTAL_DRINKINGTIME / 3) {
                    cs.setImage("onethirdbeer.png");
                }
                if (currentDrinkingTime == 0) {
                    seat.getTable().finishedBeer();
                    Greenfoot.playSound("drunk-up.wav");
                    seat.setTaken(false);
                    World world = getWorld();
                    world.removeObject(this);
                    world.removeObject(cs);
                }
                return;
            }
        }

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
                    seat.setTaken(true);
                    posX = seat.getX();
                    posY = seat.getY() + 10;
                    setSitting(true);
                    setLocation(posX, posY);
                    World w = getWorld();
                    cs = new CustomerSmiley();
                    w.addObject(cs, this.getX(), this.getY() - 50);
                    seat.getTable().wantBeer();
                    setWaiting(true);
                    break;
                }
            }
            setLocation(posX, posY);
        }
    }

    public boolean isSitting() {
        return sitting;
    }

    public void setSitting(boolean flag) {
        this.sitting = flag;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }


}


import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.World;

public class Customer extends Actor {

    private final int id;
    private int posX;
    private int posY;
    private int deltaX = 1;
    private int deltaY = 1;
    private final int TOTAL_WAITINGTIME;
    private final int TOTAL_DRINKINGTIME;
    private boolean waiting = false;
    private int currentWaitingTime = 0;
    private int currentDrinkingTime = 0;
    private boolean sitting = false;
    private Seat seat = null;
    private boolean walkedVertical = false;
    private boolean positiveDirection = false;
    private CustomerSmiley cs;

    public Customer(int id) {
        setImage("customer/model/walker.png");
        this.id = id;
        currentWaitingTime = Greenfoot.getRandomNumber(500) + 1000;
        currentDrinkingTime = Greenfoot.getRandomNumber(500) + 2500;
        TOTAL_WAITINGTIME = currentWaitingTime;
        TOTAL_DRINKINGTIME = currentDrinkingTime;
        if (Greenfoot.getRandomNumber(2) > 0) {
            deltaY = -deltaY;
        }
        if (Greenfoot.getRandomNumber(2) > 0) {
            deltaX = -deltaX;
        }
    }

    public void act() {
        moveAround();
    }

    public boolean atWorldEdge() {
        if (getX() <= 10 || getX() >= getWorld().getWidth() - 10)
            return true;
        if (getY() <= 10 || getY() >= getWorld().getHeight() - 10)
            return true;
        else
            return false;
    }

    private void moveAround() {
        posX = getX();
        posY = getY();

        if (isSitting()) {
            if (isWaiting()) {
                if (seat.getTable().takeBeer()) {
                    setWaiting(false);
                    return;
                }
                currentWaitingTime--;
                if (currentWaitingTime < TOTAL_WAITINGTIME / 2) {
                    cs.setMood(1);
                }
                if (currentWaitingTime < TOTAL_WAITINGTIME / 4) {
                    cs.setMood(0);
                }
                if (currentWaitingTime == 0) {
                    seat.getTable().cancelOrder();
                    seat.setTaken(false);
                    World world = getWorld();
                    world.removeObject(this);
                    world.removeObject(cs);
                }
            } else {
                if (currentDrinkingTime == TOTAL_DRINKINGTIME) {
                    cs.setProgress(2);
                }
                currentDrinkingTime--;
                if (currentDrinkingTime < TOTAL_DRINKINGTIME - TOTAL_DRINKINGTIME / 3) {
                    cs.setProgress(1);
                }
                if (currentDrinkingTime < TOTAL_DRINKINGTIME - 2 * TOTAL_DRINKINGTIME / 3) {
                    cs.setProgress(0);
                }
                if (currentDrinkingTime == 0) {
                    Greenfoot.playSound("drunk-up.wav");
                    seat.setTaken(false);
                    World world = getWorld();
                    world.removeObject(this);
                    world.removeObject(cs);
                }
            }
            return;
        }

        walkDiagonal();


    }

    private void walkDiagonal() {
        posX += deltaX;
        setLocation(posX, posY);
        checkToBounceX();
        posY += deltaY;
        setLocation(posX, posY);
        checkToBounceY();
    }

    private void checkToBounceX() {
        if (atWorldEdge()) {
            deltaX = -deltaX;
            posX = posX + deltaX;
        } else if (isTouching(Seat.class)) {
            if (!tryToSitDown()) {
                deltaX = -deltaX;
                posX = posX + deltaX;
            }
        } else if (isTouching(Table.class) || isTouching(Bar.class)) {
            deltaX = -deltaX;
            posX = posX + deltaX;
        }
        setLocation(posX, posY);
    }

    private void checkToBounceY() {
        if (atWorldEdge()) {
            deltaY = -deltaY;
            posY = posY + deltaY;
        } else if (isTouching(Seat.class)) {
            if (!tryToSitDown()) {
                deltaY = -deltaY;
                posY = posY + deltaY;
            }
        } else if (isTouching(Table.class) || isTouching(Bar.class)) {
            deltaY = -deltaY;
            posY = posY + deltaY;
        }
        setLocation(posX, posY);
    }

    private boolean tryToSitDown() {
        seat = (Seat) getOneIntersectingObject(Seat.class);
        if (!seat.isTaken()) {
            seat.setTaken(true);
            setSitting(true);
            if (seat.isUpperRow()) {
                posX = seat.getX();
                posY = seat.getY() - 10;
                setImage("customer/model/walkerSittingFront.png");
            } else {
                posX = seat.getX();
                posY = seat.getY() - 20;
                setImage("customer/model/walkerSittingBack.png");
            }
            setLocation(posX, posY);
            World w = getWorld();
            int order = Greenfoot.getRandomNumber(3);
            if(order == 0)
            cs = new CustomerSmiley(CustomerOrder.BEER, getX()+10, getY() - 30);
            if(order == 1)
                cs = new CustomerSmiley(CustomerOrder.SAUSAGE, getX()+10, getY() - 30);
            if(order == 2)
                cs = new CustomerSmiley(CustomerOrder.PRETZEL, getX()+10, getY() - 30);
            w.addObject(cs, this.getX()+10, this.getY() - 30);
            seat.getTable().wantBeer();

            setWaiting(true);
            return true;
        } else {
            return false;
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


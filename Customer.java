import greenfoot.Greenfoot;
import greenfoot.World;

public class Customer extends MovableActor {

    private final int id;
    private final int TOTAL_WAITINGTIME;
    private final int TOTAL_DRINKINGTIME;
    private boolean waiting = false;
    private int currentWaitingTime = 0;
    private int currentDrinkingTime = 0;
    private boolean sitting = false;
    private Seat seat = null;
    private CustomerSmiley cs;
    private int counter = 0;
    private boolean isFlashing = false;
    private boolean reachedDestination = false;
    private boolean leaving = false;

    public Customer(int id) {
        super("levels/CustomerPathmap.xml", 5);
        setImage("customer/model/walker.png");
        this.id = id;
        currentWaitingTime = Greenfoot.getRandomNumber(500) + 1000;
        currentDrinkingTime = Greenfoot.getRandomNumber(500) + 2500;
        TOTAL_WAITINGTIME = currentWaitingTime;
        TOTAL_DRINKINGTIME = currentDrinkingTime;
    }

    public void act() {
        super.act();
        moveAround();
        //MyWorld world = (MyWorld) getWorld();
        if (isFlashing) {
            counter++;
            if (counter % 25 == 0) {
                setImage("walkerSittingBack_glow.png");
            }
            if (counter % 50 == 0) {
                setImage("walkerSittingBack.png");
                counter = 0;
            }
        }
    }

    @Override
    public void finishedMoveTo() {
        System.out.println("customer finished moving");
        reachedDestination = true;
    }

    private void moveAround() {

        if (isSitting()) {
            if (isWaiting()) {
                if (seat.getTable().takeBeer()) {
                    setWaiting(false);
                    MyWorld world = (MyWorld) getWorld();
                    if (world.isTutorialActive()) {
                        isFlashing = false;
                        setImage("walkerSittingBack.png");
                        //MyWorld world = (MyWorld) getWorld();
                        world.incrementTutorialStage();
                    }
                    return;
                }
                currentWaitingTime--;

                MyWorld tutWorld = (MyWorld) getWorld();
                if (!tutWorld.isTutorialActive()) {
                    if (currentWaitingTime < TOTAL_WAITINGTIME / 2) {
                        cs.setMood(1);
                    }
                    if (currentWaitingTime < TOTAL_WAITINGTIME / 4) {
                        cs.setMood(0);
                    }
                    if (currentWaitingTime == 0) {
                        seat.getTable().cancelOrder();
                        leaveToDoor();
                    }
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
                    leaveToDoor();
                }
            }
        } else if (reachedDestination) {
            if (leaving) {
                World w = getWorld();
                w.removeObject(this);
            } else {
                tryToSitDown();
            }
        }
    }

    private void leaveToDoor() {
        seat.setTaken(false);
        setSitting(false);
        reachedDestination = false;
        leaving = true;
        setImage("customer/model/walker.png");
        moveTo(350, 570);
        World w = getWorld();
        w.removeObject(cs);
    }


    private boolean tryToSitDown() {
        setSitting(true);
        int posX;
        int posY;
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
        MyWorld w = (MyWorld) getWorld();
        int order = Greenfoot.getRandomNumber(3);
        //for now, just Beer is available
        order = 0;
        if (w.isTutorialActive()) {
            order = 0;
        }
        if (order == 0)
            cs = new CustomerSmiley(CustomerOrder.BEER, getX() + 10, getY() - 30);
        if (order == 1)
            cs = new CustomerSmiley(CustomerOrder.SAUSAGE, getX() + 10, getY() - 30);
        if (order == 2)
            cs = new CustomerSmiley(CustomerOrder.PRETZEL, getX() + 10, getY() - 30);
        w.addObject(cs, this.getX() + 10, this.getY() - 30);
        seat.getTable().wantBeer();

        setWaiting(true);
        return true;
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

    public void flashTrue() {
        isFlashing = true;
    }

    public void flashFalse() {
        isFlashing = false;
    }


    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}


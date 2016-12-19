import greenfoot.Greenfoot;
import greenfoot.World;

public class Customer extends MovableActor {

    static int counter = 0;
    static int counter1 = 0;
    private final int id;
    private final int TOTAL_WAITINGTIME;
    private final int TOTAL_DRINKINGTIME;
    private boolean waiting = false;
    private int currentWaitingTime = 0;
    private int currentDrinkingTime = 0;
    private boolean sitting = false;
    private Seat seat = null;
    private CustomerSmiley cs;
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
        if (getWorld() instanceof MyWorld) {
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
    }

    @Override
    public void finishedMoveTo() {
        reachedDestination = true;
    }

    private void moveAround() {

        if (isSitting()) {
            if (isWaiting()) {
                //Check if its the first level
                if (getWorld() instanceof MyWorld) {
                    MyWorld world = (MyWorld) getWorld();
                    if (seat.getTable().takeBeer()) {
                        setWaiting(false);
                        if (world.isTutorialActive()) {
                            isFlashing = false;
                            setImage("walkerSittingBack.png");
                            world.incrementTutorialStage();
                        }
                        return;
                    }

                    if (!world.isTutorialActive()) {
                        normalAct();
                    }
                } else {
                    normalAct();
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
                    leaveToDoor(false);
                }
            }
        } else if (reachedDestination) {
            if (leaving) {
                if (getWorld() instanceof MyWorld) {
                    World w = getWorld();
                    w.removeObject(this);
                }

                if (getWorld() instanceof Level2) {
                    World y = getWorld();
                    y.removeObject(this);
                }
            } else {
                tryToSitDown();
            }
        }
    }

    private void normalAct() {
        LevelBase world = (LevelBase) getWorld();
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
            leaveToDoor(true);
            counter1++;
            if (counter1 == 1) {
                world.getHeart3().getImage().setTransparency(100);
            }

            if (counter1 == 2) {
                world.getHeart2().getImage().setTransparency(100);
            }

            if (counter1 == 3) {
                counter1 = 0;
                world.getHeart2().getImage().setTransparency(255);
                world.getHeart3().getImage().setTransparency(255);
                Money.clearPreviousDaysMoney();
                NoLives dead = new NoLives();
                Greenfoot.setWorld(dead);
            }
        }
    }

    private void leaveToDoor(boolean angry) {
        seat.setTaken(false);
        setSitting(false);
        reachedDestination = false;
        leaving = true;
        if (angry) {
            setImage("customer/model/walkerAngry.png");
        } else {
            setImage("customer/model/walker.png");
        }
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
        int order = 0;

        if (getWorld() instanceof MyWorld) {
            MyWorld w = (MyWorld) getWorld();
            order = Greenfoot.getRandomNumber(3);
            //for now, just Beer is available
            order = 0;
            if (w.isTutorialActive()) {
                order = 0;
            }
        }
        if (order == 0)
            cs = new CustomerSmiley(CustomerOrder.BEER, getX() + 10, getY() - 30);
        if (order == 1)
            cs = new CustomerSmiley(CustomerOrder.SAUSAGE, getX() + 10, getY() - 30);
        if (order == 2)
            cs = new CustomerSmiley(CustomerOrder.PRETZEL, getX() + 10, getY() - 30);
        if (getWorld() instanceof MyWorld) {
            MyWorld w = (MyWorld) getWorld();
            w.addObject(cs, this.getX() + 10, this.getY() - 30);
        }

        if (getWorld() instanceof Level2) {
            Level2 y = (Level2) getWorld();
            y.addObject(cs, this.getX() + 10, this.getY() - 30);
        }
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


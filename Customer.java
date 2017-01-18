import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.World;

public class Customer extends MovableActor {

    static int counter = 0;
    static int counter1 = 0;
    private final int id;
    private final int TOTAL_WAITINGTIME;
    private final int TOTAL_DRINKINGTIME;
    private final int BEER_CUTOFF = 8;
    private final int PRETZEL_CUTOFF = 12;
    private boolean waiting = false;
    private int currentWaitingTime = 0;
    private int currentDrinkingTime = 0;
    private boolean sitting = false;
    private Seat seat = null;
    private CustomerSmiley cs;
    private boolean isFlashing = false;
    private boolean reachedDestination = false;
    private boolean leaving = false;
    private int order = 0;
    private boolean isGirl = false;


    public Customer(int id) {
        super("levels/CustomerPathmap.xml", 5);
        if (Greenfoot.getRandomNumber(2) == 1) {
            setImage("customer/model/girlwalker.png");
            isGirl = true;
        } else {
            setImage("customer/model/walker.png");
        }
        this.id = id;
        currentWaitingTime = Greenfoot.getRandomNumber(500) + 1000;
        currentDrinkingTime = Greenfoot.getRandomNumber(500) + 1250;
        TOTAL_WAITINGTIME = currentWaitingTime;
        TOTAL_DRINKINGTIME = currentDrinkingTime;
    }

    public void act() {
        super.act();
        moveAround();
        if (getWorld() instanceof Level1) {
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

                if (getWorld() instanceof Level1) {
                    Level1 world = (Level1) getWorld();
                    if (order < BEER_CUTOFF && seat.getTable().takeBeer(cs.getMood())) {
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
                    LevelBase world = (LevelBase)getWorld();
                    world.seatsTaken--;
                }
            }
        } else if (reachedDestination) {
            if (leaving) {
                getWorld().removeObject(this);
            } else {
                tryToSitDown();
            }
        }
    }

    private void normalAct() {
        LevelBase world = (LevelBase) getWorld();
        if (order < BEER_CUTOFF && seat.getTable().takeBeer(cs.getMood())) {
            setWaiting(false);
            return;
        }
        else if(order < PRETZEL_CUTOFF && seat.getTable().takePretzel(cs.getMood())) {
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
            if(order < BEER_CUTOFF) {
                seat.getTable().cancelOrder(Beer.class);
            }
            else if(order < PRETZEL_CUTOFF) {
                seat.getTable().cancelOrder(Pretzel.class);
            }
            leaveToDoor(true);
            world.seatsTaken--;
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
            if (isGirl) {
                setImage("customer/model/girlwalkerAngry.png");
            } else {
                setImage("customer/model/walkerAngry.png");
                GreenfootSound sound = new GreenfootSound("angry.wav");
                sound.play();
            }
        } else {
            if (isGirl) {
                setImage("customer/model/girlwalker.png");
            } else {
                setImage("customer/model/walker.png");
            }
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
            if (isGirl) {
                setImage("customer/model/girlwalkerSittingFront.png");
            } else {
                setImage("customer/model/walkerSittingFront.png");
            }
        } else {
            posX = seat.getX();
            posY = seat.getY() - 20;
            if (isGirl) {
                setImage("customer/model/girlwalkerSittingBack.png");
            } else {
                setImage("customer/model/walkerSittingBack.png");
            }
        }
        setLocation(posX, posY);

        //Determine what the customer will order based on purchased upgrades

        int order = 0;

        if(getWorld() instanceof Level2) {
            Level2 w = (Level2) getWorld();
            order = Greenfoot.getRandomNumber(w.tent.getNumOrderOptions() * 5);
        }


        if (order < 8) {
            cs = new CustomerSmiley(CustomerOrder.BEER, getX() + 10, getY() - 30);
            seat.getTable().wantBeer();
        }
        if (order >= 8 && order < 12) {
            cs = new CustomerSmiley(CustomerOrder.PRETZEL, getX() + 10, getY() - 30);
            seat.getTable().wantPretzel();

        }

        if (order >= 12) {
            cs = new CustomerSmiley(CustomerOrder.SAUSAGE, getX() + 10, getY() - 30);
            seat.getTable().wantSausage();
        }

        LevelBase w = (LevelBase) getWorld();
        w.addObject(cs, this.getX() + 10, this.getY() - 30);

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


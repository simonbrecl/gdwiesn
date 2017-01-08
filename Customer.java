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
    static int counter = 0;
    static int counter1 = 0;
    private boolean isFlashing = false;
    private boolean reachedDestination = false;
    private boolean leaving = false;
    private int order = 0;

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
        //System.out.println("customer finished moving");
        reachedDestination = true;
    }

    private void moveAround() {

        if (isSitting()) {
            if (isWaiting()) {
                if (getWorld() instanceof MyWorld) {

                    if (order == 0 && seat.getTable().takeBeer()) {
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
                    else if(order == 1 && seat.getTable().takePretzel()) {
                        setWaiting(false);
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
                            seat.getTable().cancelOrder(Beer.class);
                            leaveToDoor();
                            counter1++;
                            if (counter1 == 1) {
                                MyWorld.heart3.getImage().setTransparency(100);        
                                Level2.heart3.getImage().setTransparency(100);
                           }
                           
                           if (counter1 == 2) {
                                MyWorld.heart2.getImage().setTransparency(100);
                                Level2.heart2.getImage().setTransparency(100);
                           }
                            
                           if (counter1 == 3) {
                               counter1 = 0;
                               MyWorld.heart2.getImage().setTransparency(255);
                               MyWorld.heart3.getImage().setTransparency(255);
                               Level2.heart3.getImage().setTransparency(255);
                               Level2.heart3.getImage().setTransparency(255);
                               Money.clearPreviousDaysMoney();
                               NoLives dead = new NoLives();
                               Greenfoot.setWorld(dead);
                            }
                        }
                    }
                }
                
                if (getWorld() instanceof Level2) {

                    if (order == 0 && seat.getTable().takeBeer()) {
                        setWaiting(false);
                        return;
                    }
                    else if(order == 1 && seat.getTable().takePretzel()) {
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
                            seat.getTable().cancelOrder(Beer.class);
                            leaveToDoor();
                            counter1++;
                            if (counter1 == 1) {
                                MyWorld.heart3.getImage().setTransparency(100);        
                                Level2.heart3.getImage().setTransparency(100);
                           }
                           
                           if (counter1 == 2) {
                                MyWorld.heart2.getImage().setTransparency(100);
                                Level2.heart2.getImage().setTransparency(100);
                           }
                            
                           if (counter1 == 3) {
                               counter1 = 0;
                               MyWorld.heart2.getImage().setTransparency(255);
                               MyWorld.heart3.getImage().setTransparency(255);
                               Level2.heart3.getImage().setTransparency(255);
                               Level2.heart3.getImage().setTransparency(255);
                               Money.clearPreviousDaysMoney();
                               NoLives dead = new NoLives();
                               Greenfoot.setWorld(dead);
                            }
                        }
                }
           }
            
            else {
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

        //Determine what the customer will order based on purchased upgrades
        if (getWorld() instanceof MyWorld) {
            MyWorld w = (MyWorld) getWorld();
            order = Greenfoot.getRandomNumber(w.tent.getNumOrderOptions());
            //for now, just Beer is available
            //order = 0;
            if (w.isTutorialActive()) {
                order = 0;
            }
        }
        else if(getWorld() instanceof Level2) {
            Level2 w = (Level2) getWorld();
            order = Greenfoot.getRandomNumber(w.tent.getNumOrderOptions());
        }


        if (order == 0) {
            cs = new CustomerSmiley(CustomerOrder.BEER, getX() + 10, getY() - 30);
            seat.getTable().wantBeer();
        }
        if (order == 1) {
            cs = new CustomerSmiley(CustomerOrder.PRETZEL, getX() + 10, getY() - 30);
            seat.getTable().wantPretzel();
        }

        if (order == 2) {
            cs = new CustomerSmiley(CustomerOrder.SAUSAGE, getX() + 10, getY() - 30);
            seat.getTable().wantSausage();
        }
        if (getWorld() instanceof MyWorld) {
            MyWorld w = (MyWorld) getWorld();
            w.addObject(cs, this.getX() + 10, this.getY() - 30);
       }
       
       if (getWorld() instanceof Level2) {
           Level2 y = (Level2) getWorld();
           y.addObject(cs, this.getX() + 10, this.getY() - 30);
       }


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


import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.World;

public class Obstacle extends Actor {

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
    static int counter = 0;

    public Obstacle(int id) {
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
                    counter++;
                 
                    if (counter == 1) {
                        MyWorld.heart3.getImage().setTransparency(100);        
                        Level2.heart3.getImage().setTransparency(100);
                   }
                   
                   if (counter == 2) {
                        MyWorld.heart2.getImage().setTransparency(100);
                        Level2.heart2.getImage().setTransparency(100);
                   }
                    
                   if (counter == 3) {
                       counter=0;
                       MyWorld.heart2.getImage().setTransparency(255);
                       MyWorld.heart3.getImage().setTransparency(255);
                       Level2.heart3.getImage().setTransparency(255);
                       Level2.heart3.getImage().setTransparency(255);
                       Money.clearPreviousDaysMoney();
                       NoLives dead = new NoLives();
                       Greenfoot.setWorld(dead);
                    }
                
                }
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
                    Greenfoot.playSound("drunk-up.wav");
                    seat.setTaken(false);
                    World world = getWorld();
                    world.removeObject(this);
                    world.removeObject(cs);
                }
            }
            return;
        }

        //int randomNum = Greenfoot.getRandomNumber(4);
        //walkRandom(randomNum);
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

    private void walkRandom(int randomNum) {
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
                tryToSitDown();
            }
            setLocation(posX, posY);
        }
    }

    private boolean tryToSitDown() {
        seat = (Seat) getOneIntersectingObject(Seat.class);
        if (!seat.isTaken()) {
            seat.setTaken(true);
            setSitting(true);
            if (seat.isUpperRow()) {
                posX = seat.getX();
                posY = seat.getY() - 10;
                setImage("walkerSittingFront.png");
            } else {
                posX = seat.getX();
                posY = seat.getY() - 20;
                setImage("walkerSittingBack.png");
            }
            setLocation(posX, posY);
            World w = getWorld();
            cs = new CustomerSmiley();
            w.addObject(cs, this.getX(), this.getY() - 30);
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


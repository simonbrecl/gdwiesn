import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;

/**
 * The customer.
 */
public class Customer extends MovableActor {

	static int counter1 = 0;

	private static int counter = 0;

	private static boolean puked;

	private final int TOTAL_WAITINGTIME;

	private final int TOTAL_DRINKINGTIME;

	private final int BEER_CUTOFF = 9;

	private final int PRETZEL_CUTOFF = 13;

	private final int SAUSAGE_CUTOFF = 15;

	private boolean waiting = false;

	private int currentWaitingTime = 0;

	private int currentDrinkingTime = 0;

	private boolean sitting = false;

	private Seat seat = null;

	private CustomerSmiley cs;

	private boolean isFlashing = false;

	private boolean reachedDestination = false;

	private boolean leaving = false;

	private boolean repeat = false;

	private int order;

	private boolean isGirl = false;

	public Customer(int id) {
		super("levels/CustomerPathmap.xml", 5);

		if (Greenfoot.getRandomNumber(2) == 1) {
			setImage("customer/model/girlwalker.png");
			isGirl = true;
		} else {
			setImage("customer/model/walker.png");
		}

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

		if (seat.getTable().isDirty() && repeat == false) {
			leaveToDoor(false);
			repeat = true;
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
					Level1 world = (Level1)getWorld();
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

					if (!puked && Greenfoot.getRandomNumber(10) == 5) {     // how frequent you want customers to throw up
						seat.getTable().puke();
						puked = true;
						Greenfoot.playSound("vomit.mp3");
					}
					leaveToDoor(false);
				}
			}
		} else if (reachedDestination) {
			if (leaving) {
				LevelBase w = (LevelBase)getWorld();
				w.removeCustomer(this);
			} else {
				tryToSitDown();
			}
		}
	}

	private void normalAct() {
		LevelBase world = (LevelBase)getWorld();

		if(isSitting()) {
			if (order < BEER_CUTOFF && seat.getTable().takeBeer(cs.getMood())) {
				setWaiting(false);
				return;
			}
			else if (order >= BEER_CUTOFF && order < PRETZEL_CUTOFF && seat.getTable().takePretzel(cs.getMood())) {
				setWaiting(false);
				return;
			}
			else if (order >= PRETZEL_CUTOFF && order < SAUSAGE_CUTOFF && seat.getTable().takeSausage(cs.getMood())) {
				setWaiting(false);
				return;
			}
		}


		currentWaitingTime--;

		if (currentWaitingTime < TOTAL_WAITINGTIME / 2) {
			cs.setMood(1);
		}

		if (currentWaitingTime < TOTAL_WAITINGTIME / 4) {
			cs.setMood(0);
		}

		if (currentWaitingTime == 0) {
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

				LevelBase newWorld;

				if (world instanceof Level2) {
					newWorld = new Level2(world.getTentState());
				} else if (world instanceof Level3) {
					newWorld = new Level3(world.getTentState());
				} else if (world instanceof Level4) {
					newWorld = new Level4(world.getTentState());
				} else if (world instanceof Level5) {
					newWorld = new Level5(world.getTentState());
				} else if (world instanceof Level6) {
					newWorld = new Level6(world.getTentState());
				} else if (world instanceof Level7) {
					newWorld = new Level7(world.getTentState());
				} else if (world instanceof Level8) {
					newWorld = new Level8(world.getTentState());
				} else {
					newWorld = new Level1();
				}

				NoLives dead = new NoLives(newWorld);
				Greenfoot.setWorld(dead);
			}
		}
	}

	private void leaveToDoor(boolean angry) {
		if (isSitting()) {
			((LevelBase)getWorld()).seatsTaken--;

			if (isWaiting()) {
				if (order < BEER_CUTOFF) {
					seat.getTable().cancelOrder(Beer.class);
				} else if (order < PRETZEL_CUTOFF) {
					seat.getTable().cancelOrder(Pretzel.class);
				}
				else if (order < SAUSAGE_CUTOFF) {
					seat.getTable().cancelOrder(Sausage.class);
				}
			}
		}

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
		LevelBase w = (LevelBase)getWorld();
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

		order = 0;

		if (!(getWorld() instanceof Level1)) {
			LevelBase w = (LevelBase)getWorld();
			order = Greenfoot.getRandomNumber(w.tent.getNumOrderOptions() * 5);
		}


		if (order < BEER_CUTOFF) {
			cs = new CustomerSmiley(CustomerOrder.BEER, getX() + 10, getY() - 30);
			seat.getTable().wantBeer();
		}
		else if (order < PRETZEL_CUTOFF) {
			cs = new CustomerSmiley(CustomerOrder.PRETZEL, getX() + 10, getY() - 30);
			seat.getTable().wantPretzel();
		}

		else {
			cs = new CustomerSmiley(CustomerOrder.SAUSAGE, getX() + 10, getY() - 30);
			seat.getTable().wantSausage();
		}

		LevelBase w = (LevelBase)getWorld();
		w.addObject(cs, this.getX() + 10, this.getY() - 30);

		setWaiting(true);


		return true;
	}

	void resetPatienceLevel() {

		if (sitting) {
			currentWaitingTime = TOTAL_WAITINGTIME;
			cs.setMood(2);
		}
	}

	private boolean isSitting() {
		return sitting;
	}

	private void setSitting(boolean flag) {
		this.sitting = flag;
	}

	private boolean isWaiting() {
		return waiting;
	}

	private void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}

	void setSeat(Seat seat) {
		this.seat = seat;
	}
}


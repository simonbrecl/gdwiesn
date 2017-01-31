import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootSound;
import greenfoot.World;

import java.util.ArrayList;

/**
 * The level base class.
 */
public class LevelBase extends World {

	//Money goal.
	public int goal = 150;

	public int numSeatsTotal = 32;

	TentState tent;

	ArrayList<Seat> allSeats = new ArrayList<>();

	int obsID = 0;

	int seatsTaken = 0;

	Message messagebox = new Message("");

	//Duration of a level
	private int minPerLevel = 5;

	//Maximum amount of customers
	private int maxPeople = 30;

	//Minimum amount of customers
	private int minPeople = 1;

	//Interval of spawning customers
	private int interval = 5;

	private Levelmap levelmap;

	private ArrayList<Customer> customers = new ArrayList<>();

	private int stupidTimer = 0;

	private int day = 1;

	private int minPeopleBase = 0;

	private Lives heart1 = new Lives();

	private Lives heart2 = new Lives();

	private Lives heart3 = new Lives();

	private Long beginTime = System.currentTimeMillis();

	private GreenfootSound ambientSound = new GreenfootSound("bayerisches-bierzelt-atmosphre-mit-essen-und-trinken.mp3");

	public static GreenfootSound music = new GreenfootSound("oktoberfest-music.mp3");


	public LevelBase(int day, int goal, TentState state, String pathToLevelmap) {
		super(800, 600, 1);
		prepare();
		this.goal = goal;
		this.day = day;
		/*if(day > 4) {
			minPeopleBase = 1;
        }
        if(day > 8) {
            minPeopleBase = 2;
        }*/
		tent = state;
		if (tent.getBandLevel() > 0) {
			loadLevelMap("levels/MyWorld-Band.xml");
		} else {
			loadLevelMap(pathToLevelmap);
		}
		started();
	}

	@Override
	public void act() {
		baseLevelAct();
		clickControl();
		cheatControl();
	}

	public void started() {
		ambientSound.playLoop();
		if(tent.getBandLevel() > 0 && !music.isPlaying()) {

			music.playLoop();
			music.setVolume(50);
		}

	}

	public void stopped() {
		ambientSound.stop();
		if(music.isPlaying()) {
			music.stop();
		}
		//Levelmap.clock.stopClock();
		//levelTimer.stop();
		stupidTimer = 0;
	}

	/**
	 * Prepare the world for the start of the program.
	 * That is: create the initial objects and add them to the world.
	 */
	private void prepare() {

		addObject(heart1, 183, 574);
		addObject(heart2, 230, 574);
		addObject(heart3, 277, 574);
	}

	//Made a separate function for this so we can add a different pathmap for the band upgrade
	private void loadLevelMap(String pathToLevelmap) {
		levelmap = new Levelmap(pathToLevelmap, this, tent);
		for (Table t : levelmap.getTables()) {
			allSeats.addAll(t.getSeats());
		}
	}

	void baseLevelAct() {
		levelmap.getClock().startClock(minPerLevel, day);
		spawnCustomers();
		updateClock();
		updateIfGoalReached();
	}

	private void spawnCustomers() {
		if ((System.currentTimeMillis() - beginTime) / 1000 >= interval) {
			addRandomPeople();
			beginTime = System.currentTimeMillis();
		}
	}

	private void addRandomPeople() {
		int randomNumberToAdd = minPeopleBase + Greenfoot.getRandomNumber(minPeople + 1);
		if (seatsTaken < maxPeople) {
			for (int i = 0; i < randomNumberToAdd; i++) {
				Customer c = new Customer(obsID++);
				addObject(c, 350, 580);
				int seatIndex = Greenfoot.getRandomNumber(allSeats.size());
				while (allSeats.get(seatIndex).isTaken()) {
					seatIndex = Greenfoot.getRandomNumber(allSeats.size());
				}
				Seat s = allSeats.get(seatIndex);
				s.setTaken(true);
				c.setSeat(s);
				c.moveTo(s.getX(), s.getY());
				seatsTaken++;
				customers.add(c);
			}
		}
	}

	private void updateClock() {
		stupidTimer++;
		if (stupidTimer >= minPerLevel * 60 * 60) {
			if (day == 15) {
				Ending ending = new Ending(levelmap.getMoney().getMoney(), tent);
				Greenfoot.setWorld(ending);
			} else {
					EndLevel endLevel = new EndLevel(day, goal, levelmap.getMoney().getMoney(), tent);
					Greenfoot.setWorld(endLevel);

			}
		}
	}

	private void updateIfGoalReached() {
		if (levelmap.getMoney().getMoney() > levelmap.getGoal().getGoal()) {

			levelmap.getGoal().goalReached();
		}
	}

	void clickControl() {
		greenfoot.MouseInfo mouseInfo = Greenfoot.getMouseInfo();

		if (mouseInfo != null && mouseInfo.getButton() == 1 && mouseInfo.getClickCount() > 0) {
			Actor actor = mouseInfo.getActor();

			// Exclude other click-areas!
			if (!(actor instanceof BeerButton) && !(actor instanceof SausageBoy) && !(actor instanceof PretzelMachine)
					&& !(actor instanceof LeftBeerBarrel) && !(actor instanceof Grill)) {
				levelmap.getWaitress().moveTo(mouseInfo.getX(), mouseInfo.getY());
			}
		}
	}

	void cheatControl() {
		if (Greenfoot.isKeyDown("shift") && Greenfoot.isKeyDown("escape")) {
			levelmap.getMoney().setMoney(1000);
			stupidTimer = minPerLevel * 60 * 60;

			baseLevelAct();
		}
	}

	void removeCustomer(Customer c) {
		boolean success = customers.remove(c);
		if (!success) {
			System.out.println("Error removing customer");
		}
		removeObject(c);
	}

	void resetCustomerMoods() {
		for (Customer c : customers) {
			c.resetPatienceLevel();
		}
	}

	public Lives getHeart1() {
		return heart1;
	}

	Lives getHeart2() {
		return heart2;
	}

	Lives getHeart3() {
		return heart3;
	}

	public void updateTentState(TentState state) {
		tent = state;
	}

	TentState getTentState() {
		return tent;
	}

	void setMinPerLevel(int minPerLevel) {
		this.minPerLevel = minPerLevel;
	}

	void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}

	void setMinPeople(int minPeople) {
		this.minPeople = minPeople;
	}

	void setInterval(int interval) {
		this.interval = interval;
	}
}

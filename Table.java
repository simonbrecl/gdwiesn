import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.awt.*;
import java.util.ArrayList;

/**
 * A table.
 */
public class Table extends Actor {
	private static final int CLEANING_TIME = 300;

	private boolean mouseOver = false;

	private Levelmap levelmap;

	private ArrayList<Seat> seats = new ArrayList<>();

	private int beer = 0;

	private int wantBeer = 0;

	private int pretzel = 0; //How many pretzels are on the table that are unclaimed?

	private int wantPretzel = 0; // Customers at table with pretzel orders

	private int sausage = 0;

	private int wantSausage = 0;

	private int cleanCounter = 0;

	private int imageMarker = 0;

	private boolean puke = false;

	private int tableNum;

	private GreenfootImage originalImage;

	public Table(World world, int x, int y, Levelmap levelmap, int num) {
		this.levelmap = levelmap;

		originalImage = getImage();

		createSeats(world, x, y, true);
		this.tableNum = num;
	}

	@Override
	protected void addedToWorld(World world) {
		createSeats(world, getX(), getY(), false);
	}

	private void createSeats(World world, int x, int y, boolean upperRow) {
		int seatOffset = -55;

		for (int i = 0; i < 4; i++) {
			Seat s = new Seat(this, upperRow);
			seats.add(s);
			world.addObject(s, x + seatOffset, y + (upperRow ? -50 : 40));

			seatOffset += 37;
		}
	}

	/**
	 * Act - do whatever the Table wants to do. This method is called whenever
	 * the 'Act' or 'Run' button gets pressed in the environment.
	 */

	public void act() {
		glow();

		if (beer > 0) {
			updateBeerCount();
		}

		if (pretzel > 0) {
			updatePretzelCount();
		}

		if (sausage > 0) {
			updateSausageCount();
		}
	}

	//Check if beer should be put on the table
	synchronized boolean incrementBeer() {
		if (wantBeer <= 0) {
			return false;
		}

		beer++;
		wantBeer--;
		updateBeerCount();
		//updateWantBeerCount();

		return true;
	}

	synchronized boolean incrementPretzel() {
		if (wantPretzel <= 0) {

			return false;
		}

		pretzel++;
		wantPretzel--;
		updatePretzelCount();

		return true;
	}

	synchronized boolean incrementSausage() {
		if (wantSausage <= 0) {

			return false;
		}

		sausage++;
		wantSausage--;
		updateSausageCount();

		return true;
	}

	private void glow() {
		if (!isDirty()) {
			if (!mouseOver && Greenfoot.mouseMoved(this)) {
				setImage("table-object1.png");
				mouseOver = true;
			}
			if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
				setImage("table-object.png");
				mouseOver = false;
			}
		} else {
			//new glow
		}
	}

	private void updateBeerCount() {
		int x = 12;
		int y = 0;

		//setImage(new GreenfootImage(originalImage));

		for (int i = 0; i < beer; i++) {
			y = (i % 2 != 0) ? 30 : 0;

			//getImage().drawImage(new GreenfootImage("new-beer.png"), x, y);

			if (i % 2 != 0) {
				x += 37;
			}
		}
	}

	private void updatePretzelCount() {
		int x = 12;
		int y = 0;

		//setImage(new GreenfootImage(originalImage));

		for (int i = 0; i < pretzel; i++) {
			y = (i % 2 != 0) ? 30 : 0;

			//getImage().drawImage(new GreenfootImage("plate-pretzel.png"), x, y);

			if (i % 2 != 0) {
				x += 37;
			}
		}
	}

	private void updateSausageCount() {
		int x = 12;
		int y = 0;

		//setImage(new GreenfootImage(originalImage));

		for (int i = 0; i < sausage; i++) {
			y = (i % 2 != 0) ? 30 : 0;

			//getImage().drawImage(new GreenfootImage("plate-sausage.png"), x, y);

			if (i % 2 != 0) {
				x += 37;
			}
		}
	}


	synchronized void wantBeer() {
		wantBeer++;
		//updateWantBeerCount();
	}

	synchronized void wantPretzel() {
		wantPretzel++;
	}

	synchronized void wantSausage() {
		wantSausage++;
	}

	synchronized boolean takeBeer(int mood) {
		if (beer > 0) {
			beer--;
			updateBeerCount();

			pay(10, mood);

			return true;
		} else {
			return false;
		}
	}

	synchronized boolean takePretzel(int mood) {
		if (pretzel > 0) {
			pretzel--;
			updatePretzelCount();

			pay(5, mood);

			return true;
		} else {
			return false;
		}
	}

	synchronized boolean takeSausage(int mood) {
		if (sausage > 0) {
			sausage--;
			updateSausageCount();

			pay(5, mood);

			return true;
		} else {
			return false;
		}
	}

	synchronized void cancelOrder(Class<?> cls) {
		if (cls == Beer.class) {
			wantBeer--;
		} else if (cls == Pretzel.class) {
			wantPretzel--;
		}
		else if (cls == Sausage.class) {
			wantSausage--;
		}

		//updateWantBeerCount();
	}

	ArrayList<Seat> getSeats() {
		return seats;
	}

	private void pay(int money, int mood) {
		switch (mood) {
			case 1:
				money += 2;
				break;

			case 2:
				money += 5;
				break;
		}

		levelmap.getMoney().addMoney(money, getX() + (getX() > getWorld().getWidth() / 2 ? 100 : -100), getY());
	}

	void puke() {
		setImage("table-object-puke.png");
		puke = true;
	}

	boolean isDirty() {
		return puke;
	}

	void clean() {
		cleanCounter++;
		if (cleanCounter % 20 == 0) {
			if (imageMarker == 0) {
				setImage("table-object-clean1.png");
				imageMarker = 1;
			} else if (imageMarker == 1) {
				setImage("table-object-clean.png");
				imageMarker = 0;
			}
		}
		if (cleanCounter >= CLEANING_TIME) {
			puke = false;
			setImage("table-object.png");
			cleanCounter = 0;
			Waitress waitress = (Waitress)getOneIntersectingObject(Waitress.class);
			waitress.changeCleaningStatus();
			if (getWorld() instanceof Level1) {
				Level1 world1 = (Level1)getWorld();
				if (world1.isTutorialActive()) {
					world1.incrementTutorialStage();
				}
			}
		}
		/*while (cleanCounter < CLEANING_TIME) {
			if (cleanCounter % 20 == 0) {
                setImage("table-object-clean.png");
            } else if(cleanCounter % 10 == 0) {
                setImage("table-object-puke.png");
            }
            cleanCounter++;
        }
        */
	}
	public int getTableNumber() {
		return tableNum;
	}
}



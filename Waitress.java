import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * The waitress.
 */
public class Waitress extends MovableActor {
	private static final int PICKUP_RADIUS = 125;

	private static final int TWO_HAND_BEER_MAX = 6;

	private static final int ONE_HAND_BEER_MAX = 3;

	private static final int TWO_HAND_FOOD_MAX = 2;

	private static final int ONE_HAND_FOOD_MAX = 1;

	private static final int FOOD_MAX = 2;

	private static final int PRETZEL_MAX = 2;

	private static final int DIFFERENT_ITEM_MAX = 2;

	private static final int BEER_TIME = 50;

	private int beerCount = 0;

	private int itemCount = 0;

	private int pretzelCount = 0;

	private int sausageCount = 0;

	private int level = 1;

	private boolean cleaning = false;

	private GreenfootImage originalImage = new GreenfootImage("waitress.png");

	private GreenfootImage originalImageUpgraded = new GreenfootImage("waitress-upgraded.png");

	private GreenfootImage newImage = new GreenfootImage("waitress.png");


	public Waitress(String pathmap, int level) {
		super(pathmap, 5 * level);

		/*if(level > 1) {
			originalImageUpgraded = getImage();
		}
		else {
			originalImage = getImage();
		}*/


		this.level = level;
	}

	/**
	 * Act - do whatever the Waitress wants to do. This method is called whenever
	 * the 'Act' or 'Run' button gets pressed in the environment.
	 */
	public void act() {
		super.act();

		//Find more efficient way to do this later
		if(level > 1) {
			this.setImage(originalImageUpgraded);
		}
		else {
			this.setImage(originalImage);
		}

		cleanTable();

		if(level > 1) {
			newImage = new GreenfootImage("waitress-upgraded.png");
		}
		else {
			newImage = new GreenfootImage("waitress.png");
		}

		unloadBeer(); // check if waitress is touching a table with customers whilst she has beers
		checkBeerIsPoured(); //see if there are any beers ready close to the waitress

		//Check and draw pretzels
		unloadPretzel(); // check if waitress is touching a table with customers while she has pretzels
		checkPretzelIsMade(); //see if there are any pretzels ready close to the waitress

		//Check and draw sausages
		unloadSausage(); // check if waitress is touching a table with customers while she has sausages
		checkSausageIsMade(); //see if there are any sausages close to the waitress

		if (itemCount > 0) {
			drawBeer();
			drawFood();
		}

		this.setImage(newImage);
	}

	private void unloadBeer() {
		if (beerCount > 0 && isTouching(Table.class)) {
			Table table = (Table)getOneIntersectingObject(Table.class);

			if (table.incrementBeer()) {
				beerCount--;
				itemCount--;
				Greenfoot.playSound("put-on-table.wav");
			}
		}
	}

	private void unloadPretzel() {
		if (pretzelCount > 0 && isTouching(Table.class)) {
			Table table = (Table)getOneIntersectingObject(Table.class);

			if (table.incrementPretzel()) {
				pretzelCount--;
				itemCount--;
				Greenfoot.playSound("put-on-table.wav");
			}
		}
	}

	private void unloadSausage() {
		if (sausageCount > 0 && isTouching(Table.class)) {
			Table table = (Table)getOneIntersectingObject(Table.class);

			if (table.incrementSausage()) {
				sausageCount--;
				itemCount--;
				Greenfoot.playSound("put-on-table.wav");
			}
		}
	}

	private void checkBeerIsPoured() {
		if (!getObjectsInRange(PICKUP_RADIUS, Beer.class).isEmpty()) {
			Beer beer = getObjectsInRange(PICKUP_RADIUS, Beer.class).get(0);
			//Waitress can hold 6 beers or 3 beers and 1 food item
			if ((beer.isPoured() && pretzelCount == 0 && sausageCount == 0&& beerCount < TWO_HAND_BEER_MAX)
					|| (beer.isPoured() && (pretzelCount + sausageCount > 0) && beerCount < ONE_HAND_BEER_MAX)) {

				beer.pickUp();
				beerCount++;
				itemCount++;
				if (getWorld() instanceof Level1) {
					Level1 world1 = (Level1)getWorld();
					if (world1.isTutorialActive()) {
						world1.incrementTutorialStage(); // tutorialStage = 5
					}
				}
			}
		}
	}

	private void checkPretzelIsMade() {
		if (!getObjectsInRange(PICKUP_RADIUS, Pretzel.class).isEmpty()) {
			Pretzel pretzel = getObjectsInRange(PICKUP_RADIUS, Pretzel.class).get(0);
			if ((pretzel.isMade() && beerCount == 0 && (pretzelCount + sausageCount) < TWO_HAND_FOOD_MAX)
					|| (pretzel.isMade() && beerCount > 0 && beerCount <= ONE_HAND_BEER_MAX && (pretzelCount + sausageCount) < ONE_HAND_FOOD_MAX)) {
				pretzel.pickUp();
				pretzelCount++;
				itemCount++;
			}
		}
	}

	private void checkSausageIsMade() {
		if (!getObjectsInRange(PICKUP_RADIUS, Sausage.class).isEmpty()) {
			Sausage sausage = getObjectsInRange(PICKUP_RADIUS, Sausage.class).get(0);
			if ((sausage.isMade() && beerCount == 0 && (pretzelCount + sausageCount) < TWO_HAND_FOOD_MAX)
					|| (sausage.isMade() && beerCount > 0 && beerCount <= ONE_HAND_BEER_MAX && (pretzelCount + sausageCount) < ONE_HAND_FOOD_MAX)) {
				sausage.pickUp();
				sausageCount++;
				itemCount++;
			}
		}
	}


	private void drawFood() {
		int x = 30;
		int y = 0;

		int offset = (int)Math.floor(FOOD_MAX / 2);
		y = 45 - (int)Math.pow(0 - offset, 2);

		//Probably some better way to do this with less complexity, but it's late and I don't care. This is CPE 357 anymore.
		if(sausageCount == 2) {
			newImage.drawImage(new GreenfootImage("plate-sausage.png"), x, y);
			x -= 27;

			y = 45 - (int)Math.pow(1 - offset, 2);
			newImage.drawImage(new GreenfootImage("plate-sausage.png"), x, y);

		}

		else if(pretzelCount == 2) {
			newImage.drawImage(new GreenfootImage("plate-pretzel.png"), x, y);
			x -= 27;

			y = 45 - (int)Math.pow(1 - offset, 2);
			newImage.drawImage(new GreenfootImage("plate-pretzel.png"), x, y);
		}

		else if(pretzelCount == 1 && sausageCount == 1) {
			newImage.drawImage(new GreenfootImage("plate-pretzel.png"), x, y);
			x -= 27;

			y = 45 - (int)Math.pow(1 - offset, 2);
			newImage.drawImage(new GreenfootImage("plate-sausage.png"), x, y);
		}
		else if(pretzelCount == 1) {
			newImage.drawImage(new GreenfootImage("plate-pretzel.png"), x, y);
		}
		else if(sausageCount == 1) {
			newImage.drawImage(new GreenfootImage("plate-sausage.png"), x, y);
		}




		/*if (sausageCount != 0 || pretzelCount != 0) {
			for (int i = 0; i < sausageCount + pretzelCount; i++) {
				y = 45 - (int)Math.pow(i - offset, 2);

				//getImage().drawImage(new GreenfootImage("plate-pretzel.png"), x, y);
				if(sausageCount > 1) {
					newImage.drawImage(new GreenfootImage("plate-sausage.png"), x, y);
				}
				else if (sausageCount == 1 && i == 0) {
					newImage.drawImage(new GreenfootImage("plate-sausage.png"), x, y);
				}
				else if(pretzelCount > 1) {
					newImage.drawImage(new GreenfootImage("plate-pretzel.png"), x, y);
				}
				else if (sausageCount == 1 && i == 1) {
					newImage.drawImage(new GreenfootImage("plate-sausage.png"), x, y);
				}

				x -= 27;
			}
		}*/
	}

	private void drawBeer() {
		int x = 0;
		int y = 0;

		int offset = (int)Math.floor(TWO_HAND_BEER_MAX / 2);

		//setImage(new GreenfootImage(originalImage));

		//Not the most efficient way to do this but I don't care
		for (int i = 0; i < beerCount; i++) {
			//Position the left beers so that the middle one is drawn on top
			if (i == 0) {
				x = 0;
				y = 45;
			}
			if (i == 1) {
				x = 10;
				y = 45;
			}
			if (i == 2) {
				x = 5;
				y = 50;
			}

			//Position the right beers so that the middle one is drawn on top
			if (i == 3) {

				x = 30;
				y = 45;
			}
			//Position the right beers so that the middle one is drawn on top
			if (i == 4) {

				x = 40;
				y = 45;
			}
			//Position the right beers so that the middle one is drawn on top
			if (i == 5) {

				x = 35;
				y = 50;
			}

			//y = 45 - (int) Math.pow(i - offset, 2);
			//getImage().drawImage(new GreenfootImage("beer.png"), x, y);
			newImage.drawImage(new GreenfootImage("beer.png"), x, y);
			//x += 5;
		}
	}

	int getItemCount() {
		return itemCount;
	}

	private void cleanTable() {
		if (isTouching(Table.class)) {
			Table table = (Table)getOneIntersectingObject(Table.class);
			if (table.isDirty() && Greenfoot.mouseClicked(table)) {
				cleaning = true; // commence clean
				Greenfoot.playSound("cleaning.wav");
			}
			if (table.isDirty() && cleaning) {
				//currentImage = getImage();
				//setImage("oktoberfest-waitress-sponge.png");
				table.clean();

				//setImage(currentImage);
			}
		}
	}

	void changeCleaningStatus() {
		cleaning = !cleaning;
	}
}

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * Write a description of class BeerButton here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LeftBeerBarrel extends Actor {

	private static boolean barrelFlash;

	/**
	 * Act - do whatever the BeerButton wants to do. This method is called whenever
	 * the 'Act' or 'Run' button gets pressed in the environment.
	 */
	private boolean mouseOver = false;

	private Bar bar;

	private boolean filling;

	private int fillCounter = 0;

	private int counter = 0;

	LeftBeerBarrel(Bar bar) {
		this.bar = bar;
		this.setImage("left-barrel.png");
	}

	public LeftBeerBarrel() {
		barrelFlash = false;
	}

	public void act() {
		pourBeer();

		if (filling) {
			fillCounter++;
		}

		//highlighted
		if (!mouseOver && Greenfoot.mouseMoved(this)) {
			setImage("left-barrel1.png");
			if (filling) {
				if (fillCounter < 40) {
					this.setImage(new GreenfootImage("left-barrel-filling-beer1.png"));
				} else if (fillCounter < 80) {
					this.setImage(new GreenfootImage("left-barrel-full-beer1.png"));
				}
			}

			mouseOver = true;
		}
		if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
			setImage("left-barrel.png");
			if (filling) {
				if (fillCounter < 40) {
					this.setImage(new GreenfootImage("left-barrel-filling-beer.png"));
				} else if (fillCounter < 80) {
					this.setImage(new GreenfootImage("left-barrel-full-beer.png"));
				}
			}

			mouseOver = false;
		}
	}

	private void pourBeer() {
		if (Greenfoot.mouseClicked(this) && bar.beerCount < Bar.beerMaximum) {

			filling = true;
			if (mouseOver) {
				this.setImage(new GreenfootImage("left-barrel-empty-beer1.png"));
			} else {
				this.setImage(new GreenfootImage("left-barrel-empty-beer.png"));
			}
		}

		if (filling) {
			if (fillCounter == 40) {
				if (mouseOver) {
					this.setImage(new GreenfootImage("left-barrel-filling-beer1.png"));
				} else {
					this.setImage(new GreenfootImage("left-barrel-filling-beer.png"));
				}
			} else if (fillCounter == 80) {
				if (mouseOver) {
					this.setImage(new GreenfootImage("left-barrel-full-beer1.png"));
				} else {
					this.setImage(new GreenfootImage("left-barrel-full-beer.png"));
				}
			} else if (fillCounter >= 100) {
				if (mouseOver) {
					this.setImage(new GreenfootImage("left-barrel1.png"));
				} else {
					this.setImage(new GreenfootImage("left-barrel.png"));
				}

				int offset = 40;
				if (bar.upgradeLevel > 1) {
					offset = 90;
				}

				Beer newBeer = new Beer();
				LevelBase world = (LevelBase)getWorld();
				world.addObject(newBeer, bar.getX() - offset + (bar.beerCount * 20), bar.getY() - 20);

				newBeer.pour();

				Greenfoot.playSound("zischen-sprudelwasser.mp3");

				filling = false;
				fillCounter = 0;
			}
		}
	}
}

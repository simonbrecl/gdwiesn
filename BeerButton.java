import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * The beer button.
 */
public class BeerButton extends Actor {

	private static boolean barrelFlash;

	private boolean mouseOver = false;

	private Bar bar;

	private boolean filling;

	private int fillCounter = 0;

	private int counter = 0;

	BeerButton(Bar bar) {
		this.bar = bar;
	}

	public BeerButton() {
		barrelFlash = false;
	}

	public void act() {
		pourBeer();
		if (filling) {
			fillCounter++;
		}

		if (!mouseOver && Greenfoot.mouseMoved(this)) {
			setImage("barrel1.png");

			//Change image for highlighted barrel
			if (filling) {
				if (fillCounter < 40) {
					this.setImage(new GreenfootImage("barrel-filling-beer1.png"));
				} else if (fillCounter < 80) {
					this.setImage(new GreenfootImage("barrel-full-beer1.png"));
				}
			}
			mouseOver = true;
		}
		//Change image for non-highlighted barrel
		if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
			setImage("barrel.png");

			if (filling) {
				if (fillCounter < 40) {
					this.setImage(new GreenfootImage("barrel-filling-beer.png"));
				} else if (fillCounter < 80) {
					this.setImage(new GreenfootImage("barrel-full-beer1.png"));
				}
			}
			mouseOver = false;
		}

		// flash the barrel for tutorial mode
		if (barrelFlash && getWorld() instanceof Level1) {
			counter++;
			if (counter % 25 == 0) {
				setImage("barrelGlow.png");
			}
			if (counter % 50 == 0) {
				setImage("barrel.png");
				counter = 0;
			}
			if (Greenfoot.mouseClicked(this)) {
				barrelFlash = false;
				Level1 world = (Level1)getWorld();
				// world.incrementTutorialStage();
			}
		}
	}

	private void pourBeer() {
		if (Greenfoot.mouseClicked(this) && bar.beerCount < Bar.beerMaximum) {

			filling = true;
			if (mouseOver) {
				this.setImage(new GreenfootImage("barrel-empty-beer1.png"));
			} else {
				this.setImage(new GreenfootImage("barrel-empty-beer.png"));
			}
		}

		if (filling) {
			if (fillCounter == 40) {
				if (mouseOver) {
					this.setImage(new GreenfootImage("barrel-filling-beer1.png"));
				} else {
					this.setImage(new GreenfootImage("barrel-filling-beer.png"));
				}
			} else if (fillCounter == 80) {
				if (mouseOver) {
					this.setImage(new GreenfootImage("barrel-full-beer1.png"));
				} else {
					this.setImage(new GreenfootImage("barrel-full-beer.png"));
				}
			} else if (fillCounter >= 100) {
				if (mouseOver) {
					this.setImage(new GreenfootImage("barrel1.png"));
				} else {
					this.setImage(new GreenfootImage("barrel.png"));
				}

				Beer newBeer = new Beer();
				if (getWorld() instanceof Level1) {
					Level1 world = (Level1)getWorld();
					world.addObject(newBeer, bar.getX() - 40 + (bar.beerCount * 20), bar.getY() - 20);
					if (world.isTutorialActive()) {
						newBeer.beerFlash();
						if (world.getTutorialStage() == 3) {
							world.incrementTutorialStage();
						}
					}
				} else {
					int offset = 40;
					if (bar.upgradeLevel > 1) {
						offset = 90;
					}
					LevelBase world = (LevelBase)getWorld();
					world.addObject(newBeer, bar.getX() - offset + (bar.beerCount * 20), bar.getY() - 20);
				}
				newBeer.pour();

				Greenfoot.playSound("zischen-sprudelwasser.mp3");

				filling = false;
				fillCounter = 0;
			}
		}
	}

	void barrelFlash() {
		//change from true to false or vice versa.
		barrelFlash = !barrelFlash;
	}
}

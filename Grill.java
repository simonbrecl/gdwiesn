/**
 * Created by ericasolum on 1/29/17.
 * Class for the sausage grill.
 */

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Grill extends Actor {
	private boolean mouseOver = false;

	private Kitchen kitchen;

	private boolean making;

	private int makeCounter = 0;

	private int timerX = 8;

	private int timerY = 10;

	private boolean machineFlash;

	private GreenfootImage normalImage = new GreenfootImage("grill.png");

	public Grill(Kitchen kitchen) {
		this.kitchen = kitchen;
		machineFlash = false;
		this.setImage(normalImage);
	}

	public void act() {

		serveSausage();

		if (making) {
			makeCounter++;
		}

		if (!mouseOver && Greenfoot.mouseMoved(this)) {
			//Has to reload the image every time so the timer gets cleared
			setImage(new GreenfootImage("grill-overlay.png"));
			mouseOver = true;
		}
		if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
			//Has to reload the image every time so the timer gets cleared
			setImage(new GreenfootImage("grill.png"));
			mouseOver = false;
		}
	}



	private void serveSausage() {

		if (Greenfoot.mouseClicked(this) && kitchen.foodCount < Kitchen.FOOD_MAX) {
			making = true;
			//this.setImage(new GreenfootImage("barrel-empty-beer1.png"));
		}

		if (making) {
			if (makeCounter > 0 && makeCounter <= 20) {
				getImage().drawImage(new GreenfootImage("timer/0.png"), timerX, timerY);
			} else if (makeCounter <= 40) {
				getImage().drawImage(new GreenfootImage("timer/1.png"), timerX, timerY);
			} else if (makeCounter <= 60) {
				getImage().drawImage(new GreenfootImage("timer/2.png"), timerX, timerY);
			} else if (makeCounter < 80) {
				getImage().drawImage(new GreenfootImage("timer/3.png"), timerX, timerY);
			}

			if (makeCounter >= 80) {
				this.setImage(new GreenfootImage("grill.png"));

				Sausage newSausage = new Sausage();

				getWorld().addObject(newSausage, kitchen.getX() - 50 + (kitchen.foodCount * 30), kitchen.getY() - 20);

				Greenfoot.playSound("zischen-sprudelwasser.mp3");

				making = false;
				makeCounter = 0;
			}
		}
	}

}

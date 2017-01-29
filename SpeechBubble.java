import greenfoot.Actor;
import greenfoot.GreenfootImage;

/**
 * Created by Daniel on 19.12.2016.
 */
public class SpeechBubble extends Actor {

	private Boss boss;

	private GreenfootImage image;

	private int part = 1;

	private GifImage bubble1 = new GifImage("introBubbles/bubble" + part + ".gif");

	private boolean lastWasShown = false;

	//If set to true, it means the speechbubble should be kept on the screen for a short time when finishing.
	private int wasteCounter = 100;

	public SpeechBubble(Boss boss) {
		this.boss = boss;
	}

	public void act() {
		if (lastWasShown) {
			if (wasteCounter >= 0) {
				wasteCounter--;
				return;
			}
			part++;
			if (part >= 7) {
				boss.setfinishedTalking(true);
				getWorld().removeObject(this);
				return;
			}
			bubble1 = new GifImage("introBubbles/bubble" + part + ".gif");
			wasteCounter = 100;
			lastWasShown = false;
		}
		image = bubble1.getCurrentImage();
		setImage(image);
		//Checks if the last image of the gif was shown.
		if (image.equals(bubble1.getImages().get(bubble1.getImages().size() - 1))) {
			lastWasShown = true;
		}
	}
}

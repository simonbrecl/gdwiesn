import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;

/**
 * The message popup.
 */
public class Message extends Actor {
	public Message() {
		updateImage("");
	}

	public Message(String text) {
		updateImage(text);
	}

	void setText(String text) {
		updateImage(text);
	}

	private void updateImage(String text) {
		setImage(new GreenfootImage(text, 30, Color.black, Color.white));
	}
}
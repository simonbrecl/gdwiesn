import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;

import java.awt.*;

/**
 * Created by ericasolum on 12/1/16.
 */
public class DoneButton extends Actor {

	private GreenfootImage sb;

	private GreenfootImage board;

	public DoneButton(int width, int height) {
		int boardTransparency = 200;
		board = new GreenfootImage(width, height);
		board.setColor(Color.white);
		board.setTransparency(boardTransparency);
		board.fillRect(0, 0, width, height);
		this.setImage(board);
		update();
	}

	public void act() {

		MouseInfo mouseInfo = Greenfoot.getMouseInfo();

		if (Greenfoot.mouseClicked(this)) {
			//SAVE TENT STATE HERE
			UpgradeScreen world = (UpgradeScreen)getWorld();
			world.goToNextDay();
		}
	}

	private void update() {
		sb = new GreenfootImage(board);
		sb.drawImage(new GreenfootImage("Done", 18, Color.blue, Color.white), 25, 5);
		this.setImage(sb);
	}
}

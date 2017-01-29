import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.awt.*;

/**
 * The end screen.
 */
public class Ending extends World {

	static int money;

	private GreenfootImage total;

	private TentState tentState;

	public Ending(int moneyCount, TentState state) {
		// Create a new world with 600x400 cells with a cell size of 1x1 pixels.
		super(800, 600, 1);
		setBackground("final.jpg");
		money = state.getMoney() + moneyCount - 150;
		this.tentState = state;
		this.tentState.updateMoney(money);
		total = new GreenfootImage(money + "€", 26, Color.BLACK, new Color(0, 0, 0, 0));
		getBackground().drawImage(total, 434, 280);
	}

	public void act() {
		if (Greenfoot.mouseClicked(null)) {
			IntroScreen home = new IntroScreen();
			Greenfoot.setWorld(home);
		}
	}
}

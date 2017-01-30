import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;
import greenfoot.MouseInfo;

import java.awt.*;

/**
 * The screen after each level.
 */
public class EndLevel extends World {

	static int i = 1;

	private int money;

	/**
	 * Constructor for objects of class LevelEnd.
	 */
	private GreenfootImage moneyImage;

	private GreenfootImage dayImage;

	private GreenfootImage goal;

	private GreenfootImage rent;

	private GreenfootImage total;

	private GreenfootImage savings;

	private GreenfootImage goalreached;

	private int offset;

	private TentState tentState;

	public EndLevel(int day, int moneyCount, TentState state) {
		// Create a new world with 600x400 cells with a cell size of 1x1 pixels.
		super(800, 600, 1);

		System.out.println("Money made: " + moneyCount);

		money = state.getMoney() + moneyCount - 150;
		this.tentState = state;
		this.tentState.updateMoney(money);
		setBackground("levelend.jpg");
		// new greenfoot image, draw image then addObject.
		dayImage = new GreenfootImage(day + 1 + "/16", 26, Color.BLACK, new Color(0, 0, 0, 0));
		getBackground().drawImage(dayImage, 315, 419);
		moneyImage = new GreenfootImage(moneyCount + "€", 26, Color.BLACK, new Color(0, 0, 0, 0));
		getBackground().drawImage(moneyImage, 401 + offset, 512);
		if (day > 1) {
			savings = new GreenfootImage(money + "€", 26, Color.BLACK, new Color(0, 0, 0, 0));
			getBackground().drawImage(savings, 401 + offset, 483);
		} else {
			savings = new GreenfootImage("0€", 26, Color.BLACK, new Color(0, 0, 0, 0));
			getBackground().drawImage(savings, 406 + offset, 480);
		}
		goal = new GreenfootImage(i * 100 + "", 26, Color.BLACK, new Color(0, 0, 0, 0));
		getBackground().drawImage(goal, 403 + offset, 419);

		if (moneyCount > i * 100) {
			goalreached = new GreenfootImage("Goal reached", 15, Color.green, new Color(0, 0, 0, 0));
			getBackground().drawImage(goalreached, 390 + offset, 443);
		} else {
			goalreached = new GreenfootImage("Goal not reached", 15, Color.RED, new Color(0, 0, 0, 0));
			getBackground().drawImage(goalreached, 380 + offset, 443);
		}
		rent = new GreenfootImage("150€", 26, Color.BLACK, new Color(0, 0, 0, 0));
		getBackground().drawImage(rent, 401 + offset, 540);

		total = new GreenfootImage(money + "€", 26, Color.BLACK, new Color(0, 0, 0, 0));
		getBackground().drawImage(total, 414 + offset, 568);

		this.tentState = state;
		state.updateMoney(money);
		i++;
	}

	public void act() {
		MouseInfo mouse = Greenfoot.getMouseInfo();
		if (Greenfoot.mouseClicked(this))
		{
			if (mouse.getX() >= 500 && mouse.getX() <= 800 &&
					mouse.getY() >= 400 && mouse.getY() <= 600)
			{
				UpgradeScreen screen = new UpgradeScreen(tentState);
				Greenfoot.setWorld(screen);

			}
		}

	}
}

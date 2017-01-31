import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;

/**
 * The goal notice.
 */

public class Goal extends Actor {

	private static int goal = 0;

	private GreenfootImage sb;

	private GreenfootImage board;

	private boolean reached = false;

	public Goal(int width, int height) {
		int boardTransparency = 150;
		board = new GreenfootImage(width, height);
		board.setColor(Color.white);
		board.setTransparency(boardTransparency);
		board.fillRect(0, 0, width, height);
		this.setImage(board);
		update();
	}

	public void act() {

	}

	public synchronized int getGoal() {
		return goal;
	}

	public synchronized void setGoal(int goal) {
		Goal.goal = goal;
		update();
	}

	private void update() {
		sb = new GreenfootImage(board);
		sb.drawImage(new GreenfootImage("Goal: " + goal + "€", 18, Color.red, Color.white), 25, 5);
		this.setImage(sb);
	}

	void goalReached() {
		sb = new GreenfootImage(board);
		sb.drawImage(new GreenfootImage("Goal: " + goal + "€", 18, new Color(2, 149, 55), Color.white), 25, 5);
		this.setImage(sb);
		reached = true;
	}

	public boolean isGoalReached()
	{
		return reached;
	}

}

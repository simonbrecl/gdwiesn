import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;

/**
 * The money notice.
 */
public class Money extends Actor {

	private int money = 0;

	private int timer = 0;

	private GreenfootImage sb;

	private GreenfootImage board;

	public Money(int width, int height) {
		int boardTransparency = 150;
		board = new GreenfootImage(width, height);
		board.setColor(Color.white);
		board.setTransparency(boardTransparency);
		board.fillRect(0, 0, width, height);
		this.setImage(board);
		update();
	}

	public void act() {
		timer++;
		Message msgbox = ((LevelBase)getWorld()).messagebox;

		if (timer > 180) {
			getWorld().removeObject(msgbox);
			timer = 0;
		}
	}

	public synchronized int getMoney() {
		return money;
	}

	public synchronized void setMoney(int money) {
		this.money = money;
	}

	synchronized void addMoney(int money, int x, int y) {
		this.money += money;
		update();
		Message msgbox = ((LevelBase)getWorld()).messagebox;
		msgbox.setText("+" + money + "€");
		getWorld().addObject(msgbox, x, y);
	}

	private void update() {
		sb = new GreenfootImage(board);
		sb.drawImage(new GreenfootImage("Money: " + money + "€", 18, Color.black, Color.white), 25, 5);
		this.setImage(sb);
	}
}
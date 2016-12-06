import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;

/**
 * Write a description of class Money here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Money extends Actor {

    private int timer = 0;
    private GreenfootImage sb;
    private GreenfootImage board;
    static int money = 0;

    public Money(int width ,int height) {
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

        Message msgbox = ((MyWorld) getWorld()).messagebox;

        if (timer > 180) {
            getWorld().removeObject(msgbox);
            timer = 0;
        }
    }

    public synchronized void setMoney(int score) {
        this.money = money;
    }

    static synchronized int getMoney() {
        return money;
    }

    public synchronized void addMoney(int pts, int x, int y) {
        money += pts;
        update();

        Message msgbox = ((MyWorld) getWorld()).messagebox;
        msgbox.setText("+15€");
        getWorld().addObject(msgbox, x, y);
    }

    private void update() {
        sb = new GreenfootImage(board);
        sb.drawImage(new GreenfootImage("Money: " + money + "€", 18, Color.black, Color.white), 25, 5);
        this.setImage(sb);
    }
    static void clearPreviousDaysMoney()
    {
        money = 0;
    }
}

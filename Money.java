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

    static int money = 0;
    private int timer = 0;
    private GreenfootImage sb;
    private GreenfootImage board;
    private Message msgbox;

    public Money(int width ,int height) {
        int boardTransparency = 150;
        board = new GreenfootImage(width, height);
        board.setColor(Color.white);
        board.setTransparency(boardTransparency);
        board.fillRect(0, 0, width, height);
        this.setImage(board);
        update();
    }

    static void clearPreviousDaysMoney() {
        money = 0;
    }

    public void act() {
        timer++;
        if (getWorld() instanceof MyWorld) {
        Message msgbox = ((MyWorld) getWorld()).messagebox;
        if (timer > 180) {
            getWorld().removeObject(msgbox);
            timer = 0;
        }
     }

     if (getWorld() instanceof Level2) {
        Message msgbox = ((Level2) getWorld()).messagebox;
        if (timer > 180) {
            getWorld().removeObject(msgbox);
            timer = 0;
        }
     }
    }

    public synchronized int getMoney() {
        return money;
    }

    public synchronized void setMoney(int score) {
        this.money = money;
    }

    public synchronized void addMoney(int pts, int x, int y) {
        money += pts;
        update();
       if (getWorld() instanceof MyWorld) {
        Message msgbox = ((MyWorld) getWorld()).messagebox;
        msgbox.setText("+15€");
        getWorld().addObject(msgbox, x, y);
       }

        if (getWorld() instanceof Level2) {
        Message msgbox = ((Level2) getWorld()).messagebox;
        msgbox.setText("+15€");
        getWorld().addObject(msgbox, x, y);
       }
    }

    private void update() {
        sb = new GreenfootImage(board);
        sb.drawImage(new GreenfootImage("Money: " + money + "€", 18, Color.black, Color.white), 25, 5);
        this.setImage(sb);
    }
}
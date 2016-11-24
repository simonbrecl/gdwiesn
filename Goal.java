import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;

/**
 * Write a description of class Money here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Goal extends Actor {

    private GreenfootImage sb;
    private GreenfootImage board;
    static int goal = 0;

    public Goal(int width ,int height) {
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

    public synchronized void setGoal(int goal) {
        this.goal = goal;
        update();
    }

    static synchronized int getGoal() {
        return goal;
    }


    private void update() {
        sb = new GreenfootImage(board);
        sb.drawImage(new GreenfootImage("Goal: " + goal + "€", 18, Color.red, Color.white), 25, 5);
        this.setImage(sb);
    }

    public void goalReached() {
        sb = new GreenfootImage(board);
        sb.drawImage(new GreenfootImage("Goal: " + goal + "€", 18, new Color(2, 149, 55), Color.white), 25, 5);
        this.setImage(sb);
    }
}

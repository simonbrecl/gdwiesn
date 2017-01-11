import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;

/**
 * Write a description of class Moneyleft here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class MoneyLeft extends Actor {

    private int timer = 0;
    private GreenfootImage sb;
    private GreenfootImage board;
    private int money = 0;
    private Message msgbox;
    private int width;
    private int height;

    public MoneyLeft(int width ,int height, int amount) {
        money = amount;
        this.width = width;
        this.height = height;
        int boardTransparency = 150;
        board = new GreenfootImage(width, height);
        board.setColor(Color.white);
        board.setTransparency(boardTransparency);
        board.fillRect(0, 0, width, height);
        this.setImage(board);
        update();
    }


    public int getMoney() {
        return money;
    }

    public void updateMoney(int amount) {
        money = amount;
        update();
    }



    private void update() {

        sb = new GreenfootImage(board);
        String text = "Money Left: " + money + "€";

        getImage().setFont(new Font("Helvetica", Font.PLAIN, 18));

        FontRenderContext frc = getImage().getAwtImage().createGraphics().getFontRenderContext();
        Rectangle2D bounds = getImage().getFont().getStringBounds(text, frc);

        sb.drawImage(new GreenfootImage("Money Left: " + money + "€", 18, Color.black, Color.white), (width - (int)bounds.getWidth())/2, 5);
        /*
        getImage().setColor(Color.black);
        LineMetrics lm = getImage().getFont().getLineMetrics(text, frc);
        sb.drawImage(new GreenfootImage(text, 18, Color.black, Color.white), 1, Math.round(height + lm.getStrikethroughOffset() + lm.getStrikethroughThickness() - 1));*/

        this.setImage(sb);
    }

}
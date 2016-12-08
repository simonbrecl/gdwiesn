import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;

/**
 * Created by ericasolum on 12/1/16.
 */
public class SaveButton extends Actor {

    private GreenfootImage sb;
    private GreenfootImage board;

    public SaveButton(int width ,int height) {
        int boardTransparency = 200;
        board = new GreenfootImage(width, height);
        board.setColor(Color.white);
        board.setTransparency(boardTransparency);
        board.fillRect(0, 0, width, height);
        this.setImage(board);
        update();
    }

    public void act() {

    }

    private void update() {
        sb = new GreenfootImage(board);
        sb.drawImage(new GreenfootImage("Save", 18, Color.blue, Color.white), 25, 5);
        this.setImage(sb);
    }

}

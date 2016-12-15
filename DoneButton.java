import greenfoot.*;

import java.awt.*;

/**
 * Created by ericasolum on 12/1/16.
 */
public class DoneButton extends Actor {

    private GreenfootImage sb;
    private GreenfootImage board;

    public DoneButton(int width ,int height) {
        int boardTransparency = 200;
        board = new GreenfootImage(width, height);
        board.setColor(Color.white);
        board.setTransparency(boardTransparency);
        board.fillRect(0, 0, width, height);
        this.setImage(board);
        update();
    }

    public void act() {

        greenfoot.MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        if (mouseInfo != null && mouseInfo.getButton() == 1 && mouseInfo.getClickCount() > 0) {
            Actor actor = mouseInfo.getActor();

            //SAVE TENT STATE HERE

            MyWorld world = new MyWorld();
            Greenfoot.setWorld(world);
        }

    }



    private void update() {
        sb = new GreenfootImage(board);
        sb.drawImage(new GreenfootImage("Done", 18, Color.blue, Color.white), 25, 5);
        this.setImage(sb);
    }

}

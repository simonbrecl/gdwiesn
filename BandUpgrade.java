/**
 * Created by ericasolum on 12/1/16.
 */

import greenfoot.Actor;
import greenfoot.GreenfootImage;


public class BandUpgrade extends Actor {

    public BandUpgrade() {

        prepare();
    }

    public void prepare() {
        GreenfootImage image = new GreenfootImage("band.png");
        this.setImage(image);

    }
}

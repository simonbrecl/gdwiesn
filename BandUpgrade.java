/**
 * Created by ericasolum on 12/1/16.
 */

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;

import java.util.List;


public class BandUpgrade extends Actor {

    private boolean upgradeBought = false;
    private GreenfootImage band = new GreenfootImage("band.png");
    private GreenfootImage bandOverlay = new GreenfootImage("band-overlay.png");

    public BandUpgrade() {

        prepare();
    }

    public void prepare() {
        if(upgradeBought) {
            this.setImage(band);
        }
        else {
            this.setImage(bandOverlay);
        }


    }

    public void buyUpgrade() {

        upgradeBought = true;
        this.setImage(band);
    }

    public void act() {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        if(!upgradeBought) {
            this.setImage(bandOverlay);

            if(mouseInfo != null) {
                List objects = getWorld().getObjectsAt(mouseInfo.getX(), mouseInfo.getY(), BandUpgrade.class);
                for (Object object : objects)
                {
                    if (object == this)
                    {
                        this.setImage(band);

                    }
                }
            }

        }
        else {
            this.setImage(band);
        }




    }
}

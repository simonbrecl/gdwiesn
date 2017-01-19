import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * Created by ericasolum on 12/24/16.
 */
public class PretzelMachine extends Actor {
    private Kitchen kitchen;

    private boolean making;
    private int makeCounter = 0;
    private int timerX = 8;
    private int timerY = 10;
    
    private boolean machineFlash;
    public boolean mouseOver = false;
    private GreenfootImage normalImage = new GreenfootImage("pretzelMaker.png");

    public PretzelMachine(Kitchen kitchen) {
        this.kitchen = kitchen;
        machineFlash = false;
        this.setImage(normalImage);
    }

    public void act() {

        makePretzel();

        if (making) {
            makeCounter++;
        }

        if (!mouseOver && Greenfoot.mouseMoved(this))
        {
            //Has to reload the image every time so the timer gets cleared
            setImage(new GreenfootImage("pretzelMaker-highlighted.png"));
            mouseOver = true;
        }
        if (mouseOver && Greenfoot.mouseMoved(null) && ! Greenfoot.mouseMoved(this))
        {
            //Has to reload the image every time so the timer gets cleared
            setImage(new GreenfootImage("pretzelMaker.png"));
            mouseOver = false;
        }


    }

    public void makePretzel() {

        if (Greenfoot.mouseClicked(this) && kitchen.foodCount < kitchen.FOOD_MAX) {
            making = true;
            //this.setImage(new GreenfootImage("barrel-empty-beer1.png"));
        }

        if (making) {
            if (makeCounter > 0 && makeCounter <= 20) {
                getImage().drawImage(new GreenfootImage("timer/0.png"), timerX, timerY);
            }
            else if (makeCounter <= 40) {
                getImage().drawImage(new GreenfootImage("timer/1.png"), timerX, timerY);
            }
            else if (makeCounter <= 60) {
                getImage().drawImage(new GreenfootImage("timer/2.png"), timerX, timerY);
            }
            else if (makeCounter < 80) {
                getImage().drawImage(new GreenfootImage("timer/3.png"), timerX, timerY);
            }


            if (makeCounter >= 80) {
                this.setImage(new GreenfootImage("pretzelMaker.png"));

                Pretzel newPretzel = new Pretzel();

                getWorld().addObject(newPretzel, kitchen.getX() - 50 + (kitchen.foodCount * 30), kitchen.getY() - 20);

                Greenfoot.playSound("zischen-sprudelwasser.mp3");

                making = false;
                makeCounter = 0;
            }

        }
    }

    public void machineFlash() {
        //change from true to false or vice versa.
        if (machineFlash == true) {
            machineFlash = false;
        }
        if (machineFlash == false) {
            machineFlash = true;
        }
    }

    public boolean isFlashing() {
        return machineFlash;
    }





}

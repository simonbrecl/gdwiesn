import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

/**
 * Created by ericasolum on 1/16/17.
 */
public class Band extends Actor {
    private GreenfootImage band = new GreenfootImage("band.png");
    private GreenfootSound song = new GreenfootSound("sweet-caroline-clip2.mp3");
    private int counter = 0;
    private int timerX = 10;
    private int timerY = 10;
    private boolean waiting = false;

    public Band() {
        this.setImage(band);
    }

    /**
     * Act - do whatever the Bar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean mouseOver = false;
    public void act() {
        if (!mouseOver && Greenfoot.mouseMoved(this))
        {
            setImage("band-highlighted.png");


            mouseOver = true;
        }
        if (mouseOver && Greenfoot.mouseMoved(null) && ! Greenfoot.mouseMoved(this))
        {

            setImage("band.png");

            mouseOver = false;
        }

        //On click, call playSong
        if (Greenfoot.mouseClicked(this) && counter == 0) {
            this.setImage("band-overlay.png");
            playSong();
        }

        //Increase the counter if the band has been activated recently
        if(waiting) {
            //Wait for roughly 30 seconds before enabling band again
            if(counter >= 1800) {
                counter = 0;
                waiting = false;
                this.setImage("band.png");

            }
            else {
                counter++;
            }

            //Animation here


            if (counter % 80 == 0) {
                getImage().drawImage(new GreenfootImage("timer/3.png"), timerX, timerY);
            }
            else if (counter % 60 == 0) {
                getImage().drawImage(new GreenfootImage("timer/2.png"), timerX, timerY);
            }
            else if (counter % 40 == 0) {
                getImage().drawImage(new GreenfootImage("timer/1.png"), timerX, timerY);
            }
            else if (counter % 20 == 0) {
                getImage().drawImage(new GreenfootImage("timer/0.png"), timerX, timerY);
            }



        }

    }

    private void playSong() {
        song.play();
        waiting = true;
        //Do some crazy customer patience magic here

        LevelBase world = (LevelBase) getWorld();
        world.resetCustomerMoods();
    }

}
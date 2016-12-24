import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.World;

/**
 * Created by ericasolum on 12/24/16.
 */
public class Pretzel extends Actor{
    private Kitchen kitchen;
    public long pourTimer = System.currentTimeMillis();
    private static final int POUR_TIME = 2000;
    public boolean isClicked = false;
    static boolean isMade = false;
    private int counter = 0;

    public Pretzel() {
        this.setImage("pretzel.png");
    }

    public void pickUp() {
        if (kitchen != null) kitchen.foodCount--;

        getWorld().removeObject(this);
    }

    protected void addedToWorld(World world) {
        Actor kitchen = getOneIntersectingObject(Kitchen.class);

        if (kitchen != null) {
            this.kitchen = (Kitchen) kitchen;

            this.kitchen.foodCount++;
        }
    }

    public void isMade() {
        //setImage("beer.png");
        isMade = true;


    }

}

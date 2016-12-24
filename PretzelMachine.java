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
    
    private boolean machineFlash;
    public boolean mouseOver = false;
    private GreenfootImage normalImage = new GreenfootImage("pretzelMaker.png");
    private GreenfootImage highlightedImage = new GreenfootImage("pretzelMaker-highlighted.png");

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
            setImage(highlightedImage);
            mouseOver = true;
        }
        if (mouseOver && Greenfoot.mouseMoved(null) && ! Greenfoot.mouseMoved(this))
        {
            setImage(normalImage);
            mouseOver = false;
        }


    }

    public void makePretzel() {
        if (Greenfoot.mouseClicked(this) && kitchen.foodCount < kitchen.FOOD_MAX) {

            making = true;
            //this.setImage(new GreenfootImage("barrel-empty-beer1.png"));
        }

        if (making) {
            /*if (makeCounter == 40) {
                this.setImage(new GreenfootImage("barrel-filling-beer1.png"));
            } else if (makeCounter == 80) {
                this.setImage(new GreenfootImage("barrel-full-beer1.png"));
            } else if (makeCounter >= 100) {
                this.setImage(new GreenfootImage("barrel.png"));

                MyWorld world1;
                Level2 world2;
                Beer newBeer = new Beer();
                if (getWorld() instanceof MyWorld) {
                    world1 = (MyWorld) getWorld();
                    world1.addObject(newBeer, kitchen.getX() - 20 + (kitchen.foodCount * 20), kitchen.getY() - 20);
                    if (world1.isTutorialActive()) {
                        newBeer.beerFlash();
                    }
                }
                if (getWorld() instanceof Level2) {
                    world2 = (Level2) getWorld();
                    world2.addObject(newBeer, kitchen.getX() - 20 + (kitchen.foodCount * 20), kitchen.getY() - 20);
                }
                newBeer.pour();



                Greenfoot.playSound("zischen-sprudelwasser.mp3");

                making = false;
                makeCounter = 0;
            }*/
            if (makeCounter >= 100) {
                System.out.println("Done making this shit");
                this.setImage(new GreenfootImage("pretzelMaker.png"));

                MyWorld world1;
                Level2 world2;
                Pretzel newPretzel = new Pretzel();
                if (getWorld() instanceof MyWorld) {
                    world1 = (MyWorld) getWorld();
                    world1.addObject(newPretzel, kitchen.getX() - 20 + (kitchen.foodCount * 20), kitchen.getY() - 20);

                }
                if (getWorld() instanceof Level2) {
                    world2 = (Level2) getWorld();
                    world2.addObject(newPretzel, kitchen.getX() - 20 + (kitchen.foodCount * 20), kitchen.getY() - 20);
                }




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

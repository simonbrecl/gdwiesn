import greenfoot.World;

import java.util.List;

/**
 * Write a description of class Level1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Level1 extends LevelBase {

    static boolean tutorialActive = true;
    static int repeat = 1;
    private int tutorialStage;
    private int tutorialTimer = 0;
    private SausageBoy sausageBoy = new SausageBoy();

    /**
     * Constructor for objects of class Level1.
     */
    public Level1() {
        super(1, 100, new TentState(), "levels/MyWorld.xml");

        tutorialStage = 1;
        setMinPerLevel(1);
        setMaxPeople(5);
        setMinPeople(1);
        setInterval(9);
        if (tutorialActive) {
            sausageBoy = new SausageBoy();
            addObject(sausageBoy, 640, 370);
        }

        if (repeat > 1) {
            tutorialActive = false;
        }

    }


    public boolean isTutorialActive() {
        return tutorialActive;
    }

    public void incrementTutorialStage() {
        tutorialStage++;
    }

    public int getTutorialStage() {
        return tutorialStage;
    }


    public void act() {
        cheatControl();

        if (tutorialActive) {
            if (tutorialStage == 1) {
                tutorialTimer++;
                if (tutorialTimer == 250) {
                    tutorialStage = 2;
                    sausageBoy.updateImage(tutorialStage);
                    addTutorialCustomer();
                    tutorialTimer = 0;
                }
            } else if (tutorialStage == 2) {
                tutorialTimer++;
                if (tutorialTimer == 250) {
                    tutorialStage = 3;
                    sausageBoy.updateImage(tutorialStage);
                    flashBarrel();
                    tutorialTimer = 0;
                }
            } else if (tutorialStage == 4) {
                // beerflash
                sausageBoy.updateImage(tutorialStage);
            } else if (tutorialStage == 5) {
                sausageBoy.updateImage(tutorialStage);
                List<Waitress> waitressList = getObjects(Waitress.class);
                Waitress waitress = waitressList.get(0);
                if (waitress.getItemCount() > 0) {
                    List<Customer> customerList = getObjects(Customer.class);
                    if(!customerList.isEmpty()) {
                        Customer customer = customerList.get(0);
                        customer.flashTrue();
                    }

                }
            } else if (tutorialStage >= 6 && tutorialStage <= 8) {
                sausageBoy.updateImage(tutorialStage);
            } else if (tutorialStage == 9) {
                removeObject(sausageBoy);
                tutorialActive = false;
                repeat++;
            }

        } else {
            baseLevelAct();
        }
        clickControl();
    }

    private void addTutorialCustomer() {
        Customer c = new Customer(obsID++);
        addObject(c, 350, 550);
        Seat s = allSeats.get(15);
        s.setTaken(true);
        c.setSeat(s);
        c.moveTo(s.getX(), s.getY());
    }

    private void flashBarrel() {
        List<BeerButton> barrelList = getObjects(BeerButton.class);
        BeerButton beerButton = barrelList.get(0);
        beerButton.barrelFlash();
    }
}

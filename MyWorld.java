import java.util.List;

/**
 * Write a description of class MyWorld here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MyWorld extends LevelBase {

    private boolean tutorialActive = true;
    private int tutorialStage;
    private int tutorialTimer = 0;
    private SausageBoy sausageBoy = new SausageBoy();

    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld() {
        super("levels/MyWorld.xml");
        tutorialStage = 1;

        if (tutorialActive) {
            sausageBoy = new SausageBoy();
            addObject(sausageBoy, 640, 370);
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
                flashBeer();
                sausageBoy.updateImage(tutorialStage);
            } else if (tutorialStage == 5) {
                sausageBoy.updateImage(tutorialStage);
                List<Waitress> waitressList = getObjects(Waitress.class);
                Waitress waitress = waitressList.get(0);
                if (waitress.getBeerCount() > 0) {
                    List<Customer> customerList = getObjects(Customer.class);
                    Customer customer = customerList.get(0);
                    customer.flashTrue();
                }
            } else if (tutorialStage >= 6 && tutorialStage <= 8) {
                sausageBoy.updateImage(tutorialStage);
            } else if (tutorialStage == 9) {
                removeObject(sausageBoy);
                tutorialActive = false;
            }
        } else {
            baseLevelAct();
        }
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
    
    private void flashBeer() {
        List<Beer> beerList = getObjects(Beer.class);
        Beer beer = beerList.get(0);
        beer.beerFlash();
    }
}

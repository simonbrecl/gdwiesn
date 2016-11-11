import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World {
    private GreenfootSound ambientSound = new GreenfootSound("bayerisches-bierzelt-atmosphre-mit-essen-und-trinken.mp3");
    static final int MAX_PEOPLE = 5;
    static final int MIN_PEOPLE = 1;
    static final int INTERVAL= 15;
    Long beginTime = System.currentTimeMillis();
    Message messagebox = new Message("");
    Money money;
    
    public List<Table> tables = new ArrayList<Table>();
    
    public Waitress waitress;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld() {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1);
        prepare();
        addRandomPeople();
    }
    
    public void started() {
        ambientSound.playLoop();
    }
    
    public void stopped() {
        GreenfootSound sound = new GreenfootSound("bayerisches-bierzelt-atmosphre-mit-essen-und-trinken.mp3");
        
        ambientSound.stop();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        Bar bar = new Bar();
        addObject(bar,307,60);

        BeerButton beerButton = new BeerButton();
        addObject(beerButton, 500, 40);

        //Top row
        createTable(150, 175);
        createTable(400, 175);
        createTable(650, 175);

        //Middle row
        createTable(150, 325);
        createTable(400, 325);
        createTable(650, 325);

        //Bottom row
        createTable(150, 475);
        createTable(400, 475);
        createTable(650, 475);

        waitress = new Waitress();
        addObject(waitress,85,47);

        money = new Money();
        addObject(money, 700, 27);
        beerButton.setLocation(413,23);
    }
   
   /* Create a table with 4 seats on each side */
   private void createTable(int centerX, int centerY) {
       Table table = new Table();
       tables.add(table);
       
       
       table.seats = new Seat[8];
       
       int seatOffset = -55;
       
       for(int i = 0; i < 4; i++) {
           Seat seat = new Seat();
           table.seats[i] = seat;
           addObject(table.seats[i], centerX + seatOffset, centerY - 50);
           seatOffset+= 37;
       }
       
       //Draw the table between the two layers of seats
       addObject(table, centerX, centerY);
       
       seatOffset = -55;
       
       for(int i=4; i<8; i++) {
           Seat seat = new Seat();
           table.seats[i] = seat;
           
           addObject(table.seats[i], centerX + seatOffset, centerY + 40);
           seatOffset+= 37;
       }
       
    }
         
    public void act()
    {
        if ((System.currentTimeMillis() - beginTime) / 1000 >= INTERVAL)
        {
            addRandomPeople();
            beginTime = System.currentTimeMillis();
        }
        
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        /*if (mouseInfo != null && mouseInfo.getButton() == 1 && mouseInfo.getClickCount() > 0) {
            if (mouseInfo.getActor() instanceof Bar || mouseInfo.getActor() instanceof Table) {
                waitress.moveTo(Pathmap.findPath(waitress.getX(), waitress.getY(), mouseInfo.getX(), mouseInfo.getY()));
            }
        }*/
        if (mouseInfo != null && mouseInfo.getButton() == 1 && mouseInfo.getClickCount() > 0) 
        {
            if (mouseInfo.getActor() instanceof Bar || mouseInfo.getActor() instanceof Table)
            {
               
               waitress.moveTo(Pathmap.findPath(waitress.getX(), waitress.getY(), mouseInfo.getX(), mouseInfo.getY()));
            }
        }
    }
    
    public Money getMoney() {
        return money;
    }
     
    private void addRandomPeople()
    {
        for (int i = 0; i < Greenfoot.getRandomNumber(MAX_PEOPLE + 1 - MAX_PEOPLE) + MIN_PEOPLE; i++)
            addObject(new Obstacle(), Greenfoot.getRandomNumber(250), 550 );
    }

}

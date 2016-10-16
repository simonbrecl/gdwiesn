import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    public List<Table> tables = new ArrayList<Table>();
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Bar bar = new Bar();
        addObject(bar,307,20);
        
        
        
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
        
        Waitress waitress = new Waitress();
        addObject(waitress,85,47);
   }
   
   /* Create a table with 4 seats on each side */
   public void createTable(int centerX, int centerY) {
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
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
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
        
        Table table1 = new Table();
        addObject(table1,100,150);
        Table table2 = new Table();
        addObject(table2,500,150);
        Table table3 = new Table();
        addObject(table3,300,150);
        Table table4 = new Table();
        addObject(table4,100,245);
        Table table5 = new Table();
        addObject(table5,300,245);
        Table table6 = new Table();
        addObject(table6,500,245);
        Table table7 = new Table();
        addObject(table7,100,340);
        Table table8 = new Table();
        addObject(table8,300,340);
        Table table9 = new Table();
        addObject(table9,500,340);
 
        Waitress waitress = new Waitress();
        addObject(waitress,85,47);
   }
}

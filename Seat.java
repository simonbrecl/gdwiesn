import greenfoot.Actor;

/**
 * Write a description of class Seat here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Seat extends Actor {

    public boolean isTaken = false;
    public final Table table;
    public final boolean upperRow;

    public Seat(Table table, boolean upperRow) {
        this.table = table;
        this.upperRow = upperRow;
    }

    /**
     * Act - do whatever the Seat wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Add your action code here.
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean flag) {
        this.isTaken = flag;
    }

    public Table getTable() {
        return table;
    }

    public boolean isUpperRow() {
        return upperRow;
    }

}

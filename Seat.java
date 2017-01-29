import greenfoot.Actor;

/**
 * A seat.
 */
public class Seat extends Actor {

	public final Table table;

	private final boolean upperRow;

	private boolean isTaken = false;

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

	boolean isTaken() {
		return isTaken;
	}

	void setTaken(boolean flag) {
		this.isTaken = flag;
	}

	public Table getTable() {
		return table;
	}

	boolean isUpperRow() {
		return upperRow;
	}
}

/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level5 extends LevelBase {

	/**
	 * Constructor for object of class Level2.
	 */

	public Level5(TentState state) {
		super(9, 500, state, "levels/MyWorld.xml");

		setMinPerLevel(1);
		setMaxPeople(24);
		setMinPeople(6);
		setInterval(5);
	}

	public void act() {
		super.act();
	}
}

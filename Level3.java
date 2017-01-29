/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level3 extends LevelBase {

	/**
	 * Constructor for objects of class Level2.
	 */

	public Level3(TentState state) {
		super(5, 300, state, "levels/MyWorld.xml");

		setMinPerLevel(1);
		setMaxPeople(18);
		setMinPeople(5);
		setInterval(6);
	}

	public void act() {
		super.act();
	}
}

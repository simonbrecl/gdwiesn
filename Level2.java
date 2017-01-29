/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level2 extends LevelBase {

	/**
	 * Constructor for objects of clas Level2.
	 */

	public Level2(TentState state) {
		super(3, 200, state, "levels/MyWorld.xml");

		setMinPerLevel(1);
		setMaxPeople(15);
		setMinPeople(5);
		setInterval(7);
	}
}

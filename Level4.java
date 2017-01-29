/**
 * the fourth level.
 */
public class Level4 extends LevelBase {
	public Level4(TentState state) {
		super(7, 400, state, "levels/MyWorld.xml");

		setMinPerLevel(1);
		setMaxPeople(20);
		setMinPeople(5);
		setInterval(5);
	}

	public void act() {
		super.act();
	}
}

/**
 * The sixth level.
 */
public class Level6 extends LevelBase {
	public Level6(TentState state) {
		super(11, 600, state, "levels/MyWorld.xml");

		setMinPerLevel(1);
		setMaxPeople(26);
		setMinPeople(6);
		setInterval(5);
	}

	public void act() {
		super.act();
	}
}

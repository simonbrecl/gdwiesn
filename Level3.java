/**
 * The third level.
 */
public class Level3 extends LevelBase {
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

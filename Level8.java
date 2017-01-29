/**
 * The eighth level.
 */
public class Level8 extends LevelBase {
	public Level8(TentState state) {
		super(15, 800, state, "levels/MyWorld.xml");

		setMinPerLevel(2);
		setMaxPeople(32);
		setMinPeople(7);
		setInterval(4);
	}

	public void act() {
		super.act();
	}
}

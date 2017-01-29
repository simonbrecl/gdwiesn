/**
 * The seventh level.
 */
public class Level7 extends LevelBase {
	public Level7(TentState state) {
		super(13, 700, state, "levels/MyWorld.xml");

		setMinPerLevel(1);
		setMaxPeople(28);
		setMinPeople(7);
		setInterval(5);
	}

	public void act() {
		super.act();
	}
}

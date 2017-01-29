/**
 * The fifth level.
 */
public class Level5 extends LevelBase {
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

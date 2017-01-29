/**
 * The second level.
 */
public class Level2 extends LevelBase {
	public Level2(TentState state) {
		super(3, 200, state, "levels/MyWorld.xml");

		setMinPerLevel(1);
		setMaxPeople(15);
		setMinPeople(5);
		setInterval(7);
	}
}

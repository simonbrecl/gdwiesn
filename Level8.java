/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level8 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level8(TentState state) {
        super(8, 330, state, "levels/MyWorld.xml");

        setMinPerLevel(3);
        setMaxPeople(25);
        setMinPeople(7);
        setInterval(3);
    }

    public void act() {
        super.act();
    }
}

/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level11 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level11(TentState state) {
        super(11, 360, state, "levels/MyWorld.xml");

        setMinPerLevel(4);
        setMaxPeople(25);
        setMinPeople(10);
        setInterval(2);
    }

    public void act() {
        super.act();
    }
}

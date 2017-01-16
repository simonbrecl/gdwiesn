/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level16 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level16(TentState state) {
        super(16, 500, state, "levels/MyWorld.xml");

        setMinPerLevel(5);
        setMaxPeople(32);
        setMinPeople(20);
        setInterval(1);
    }

    public void act() {
        super.act();
    }
}

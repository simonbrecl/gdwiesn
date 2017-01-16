/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level15 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level15(TentState state) {
        super(15, 400, state, "levels/MyWorld.xml");

        setMinPerLevel(5);
        setMaxPeople(30);
        setMinPeople(18);
        setInterval(2);
    }

    public void act() {
        super.act();
    }
}

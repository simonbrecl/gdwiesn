/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level7 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level7(TentState state) {
        super(7, 320, state, "levels/MyWorld.xml");

        setMinPerLevel(3);
        setMaxPeople(25);
        setMinPeople(6);
        setInterval(3);
    }

    public void act() {
        super.act();
    }
}

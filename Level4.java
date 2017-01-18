/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level4 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level4(TentState state) {
        super(7, 400, state, "levels/MyWorld.xml");

        setMinPerLevel(1);
        setMaxPeople(15);
        setMinPeople(5);
        setInterval(5);
    }

    public void act() {
        super.act();
    }
}

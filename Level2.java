/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level2 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level2(TentState state) {
        super(2, 200, state, "levels/MyWorld.xml");

        setMinPerLevel(2);
        setMaxPeople(10);
        setMinPeople(3);
        setInterval(5);
    }

}

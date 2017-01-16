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
        super(5, 300, state, "levels/MyWorld.xml");

        setMinPerLevel(2);
        setMaxPeople(20);
        setMinPeople(3);
        setInterval(5);
    }

    public void act() {
        super.act();
    }
}

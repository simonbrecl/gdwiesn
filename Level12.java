/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level12 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level12(TentState state) {
        super(12, 370, state, "levels/MyWorld.xml");

        setMinPerLevel(5);
        setMaxPeople(30);
        setMinPeople(12);
        setInterval(2);
    }

    public void act() {
        super.act();
    }
}

/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level2 extends LevelBase {

    /**
     * Constructor for objects of clas Level2.
     */

    public Level2(TentState state) {
        super(3, 200, state, "levels/MyWorld.xml");

        setMinPerLevel(1);
        setMaxPeople(10);
        setMinPeople(2);
        setInterval(7);
    }

    public void act() {
        super.act();
    }
}

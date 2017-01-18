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
        super(15, 800, state, "levels/MyWorld.xml");

        setMinPerLevel(2);
        setMaxPeople(20);
        setMinPeople(3);
        setInterval(5);
    }

    public void act() {
        super.act();
    }
}

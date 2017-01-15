/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level13 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level13(TentState state) {
        super(13, 380, state, "levels/MyWorld.xml");

        setMinPerLevel(5);
        setMaxPeople(30);
        setMinPeople(14);
        setInterval(2);
    }

    public void act() {
        super.act();
    }
}

/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level14 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level14(TentState state) {
        super(14, 390, state, "levels/MyWorld.xml");

        setMinPerLevel(5);
        setMaxPeople(30);
        setMinPeople(16);
        setInterval(2);
    }

    public void act() {
        super.act();
    }
}

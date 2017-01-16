/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level5 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level5(TentState state) {
        super(5, 300, state, "levels/MyWorld.xml");

        setMinPerLevel(3);
        setMaxPeople(20);
        setMinPeople(5);
        setInterval(3);
    }

    public void act() {
        super.act();
    }
}

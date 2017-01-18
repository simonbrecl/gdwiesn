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
        super(13, 700, state, "levels/MyWorld.xml" );

        setMinPerLevel(1);
        setMaxPeople(20);
        setMinPeople(5);
        setInterval(4);
    }

    public void act() {
        super.act();
    }
}

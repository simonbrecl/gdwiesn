/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level6 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level6(TentState state) {
        super(6, 310, state, "levels/MyWorld.xml");

        setMinPerLevel(3);
        setMaxPeople(20);
        setMinPeople(6);
        setInterval(3);
    }

    public void act() {
        super.act();
    }
}

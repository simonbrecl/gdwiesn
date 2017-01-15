/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level9 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level9(TentState state) {
        super(9, 340, state, "levels/MyWorld.xml");

        setMinPerLevel(4);
        setMaxPeople(25);
        setMinPeople(8);
        setInterval(3);
    }

    public void act() {
        super.act();
    }
}

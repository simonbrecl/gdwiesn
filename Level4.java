/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level4 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level4(TentState state) {
        super(7, 400, state, "levels/MyWorld.xml");
        setMinPerLevel(2);
        setMaxPeople(30);
        setMinPeople(3);
        setInterval(2);
    }

    public void act() {
        super.act();
    }
}

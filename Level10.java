/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level10 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level10(TentState state) {
        super(10, 350, state, "levels/MyWorld.xml");

        setMinPerLevel(4);
        setMaxPeople(25);
        setMinPeople(9);
        setInterval(2);
    }

    public void act() {
        super.act();
    }
}

/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level2 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level2(TentState state) {
        super(5, 300, state, "levels/MyWorld.xml");
        setDay(5);
        setMinPerLevel(3);
        setMaxPeople(20);
        setMinPeople(1);
        setInterval(4);
    }

    public void act() {
        super.act();
    }
}

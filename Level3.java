/**
 * Write a description of class Level2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Level3 extends LevelBase {

    /**
     * Constructor for objects of class Level2.
     */

    public Level3(TentState state) {
        super(9, 450, state, "levels/MyWorld.xml");
        setMinPerLevel(2);
        setMaxPeople(25);
        setMinPeople(1);
        setInterval(3);
    }

    public void act() {
        super.act();
    }
}

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
        super(2, state, "levels/Level2.xml");
    }

    public void act() {
        baseLevelAct();
    }
}

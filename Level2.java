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
    public Level2() {
        super("levels/Level2.xml");
        setDay(2);
        setMinPerLevel(2);
        setMaxPeople(20);
        setMinPeople(1);
        setInterval(3);
    }

    public void act() {
        baseLevelAct();
    }
}

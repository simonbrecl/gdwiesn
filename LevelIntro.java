import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

/**
 * Created by Daniel on 19.12.2016.
 */
public class LevelIntro extends LevelBase {

    private World previousWorld;
    private boolean bossLeft = false;

    public LevelIntro(World previousWorld) {
        super(0, 0, new TentState(), "levels/LevelIntro.xml");
        setBackground(new GreenfootImage("wood-floor4.jpg"));
        this.previousWorld = previousWorld;
        addBoss();
        //Stop the ambiance sounds
        stopped();
    }

    private void addBoss() {
        Boss boss = new Boss("levels/LevelIntro.xml", this);
        addObject(boss, 350, 580);
        boss.moveTo(350, 0);
    }

    @Override
    public void act() {
        if (bossLeft) {
            Greenfoot.setWorld(previousWorld);
        }
    }

    public void setBossLeft(boolean bossLeft) {
        this.bossLeft = bossLeft;
    }
}

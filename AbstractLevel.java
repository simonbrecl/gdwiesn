import greenfoot.World;

/**
 * Created by ericasolum on 1/8/17.
 */
public abstract class AbstractLevel extends World{
    private TentState tentState;
    private int day;

    public AbstractLevel(int day, TentState state) {
        super(800, 600, 1);
        this.tentState = state;
        this.day = day;

    }

    public TentState getTentState() {
        return tentState;
    }

    public void updateTentState(TentState state) {
        tentState = state;
    }

}

import greenfoot.GreenfootImage;

/**
 * The boss.
 */
public class Boss extends MovableActor {
	//Flag if boss is entering the tent.
	private boolean coming = true;

	private LevelIntro levelIntro;

	private SpeechBubble bubble;

	private boolean finishedTalking;

	private boolean leaving;

	public Boss(String pathmap, LevelIntro levelIntro) {
		super(pathmap, 5);
		setImage(new GreenfootImage("boss.png"));
		bubble = new SpeechBubble(this);
		this.levelIntro = levelIntro;
	}

	@Override
	public void act() {
		super.act();
		if (levelIntro == null) {
			levelIntro = (LevelIntro)getWorld();
		}
		if (!coming && finishedTalking && !leaving) {
			moveTo(350, 570);
			leaving = true;
		}
	}

	@Override
	public void finishedMoveTo() {
		if (coming) {
			levelIntro.addObject(bubble, getX() - 70, getY() - 70);
			coming = false;
		} else {
			levelIntro.setBossLeft();
			levelIntro.removeObject(this);
		}
	}

	void setfinishedTalking() {
		this.finishedTalking = true;
	}
}

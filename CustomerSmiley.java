import greenfoot.Actor;

/**
 * The customers smiley.
 */
public class CustomerSmiley extends Actor {

	private final int initialPosX;

	private final int initialPosY;

	private CustomerOrder choice;

	/**
	 * Mood of the customer.
	 * 2 = good.
	 * 1 = neutral.
	 * 0 = mad.
	 */
	private int mood;

	public CustomerSmiley(CustomerOrder choice, int initialPosX, int initialPosY) {
		this.choice = choice;
		this.initialPosX = initialPosX;
		this.initialPosY = initialPosY;
		setMood(2);
	}

	int getMood() {
		return mood;
	}

	/**
	 * Sets the mood image for the waiting customer.
	 *
	 * @param moodLevel level of mood. as described in the variable.
	 */
	void setMood(int moodLevel) {
		mood = moodLevel;

		setLocation(initialPosX, initialPosY);
		String path = "customer/mood/";

		switch (choice) {
			case BEER: {
				path += "beer/";
				break;
			}
			case PRETZEL: {
				path += "pretzel/";
				break;
			}
			case SAUSAGE: {
				path += "sausage/";
				break;
			}
		}

		switch (moodLevel) {
			case 2: {
				path += "2.png";
				break;
			}
			case 1: {
				path += "1.png";
				break;
			}
			case 0: {
				path += "0.png";
				break;
			}
		}

		setImage(path);
	}

	/**
	 * Progress of the consuming customer.
	 *
	 * @param progress progress to set. 2 for just started. 1 for 1/3 done. 0 for 2/3 done
	 */
	void setProgress(int progress) {
		setLocation(initialPosX - 7, initialPosY - 8);
		String path = "customer/progress/";

		switch (choice) {
			case BEER: {
				path += "beer/";
				break;
			}
			case PRETZEL: {
				path += "pretzel/";
				break;
			}
			case SAUSAGE: {
				path += "sausage/";
				break;
			}
		}

		switch (progress) {
			case 2: {
				path += "2.png";
				break;
			}
			case 1: {
				path += "1.png";
				break;
			}
			case 0: {
				path += "0.png";
				break;
			}
		}

		setImage(path);
	}
}

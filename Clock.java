import greenfoot.Actor;
import greenfoot.GreenfootImage;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Write a description of class Timer here. Nope.
 *
 * @author Erica Solum
 * @version (a version number or a date)
 */
public class Clock extends Actor {

	private GreenfootImage face;

	private double degrees = 0.0;

	private int minutesPerLevel;

	private double decreaseVal;

	private Timer timer;

	private int delay;

	private double countsPerLevel;

	private int stupidTimer;

	private boolean started = false;

	private int day = 1;

	public Clock(int minutes) {
		minutesPerLevel = minutes;
		countsPerLevel = (double)minutes * 60 * 60; // convert to seconds and convert to avg 60fps
		//decreaseVal = 360.0 / (double)(60 * minutes);
		decreaseVal = 360.0 / countsPerLevel;
		delay = minutes * 60 * 1000; // milliseconds
		stupidTimer = 0;
		drawClockFace();
	}

	/* Starts the clock */
	public void startClock(int minutes, int day) {
		minutesPerLevel = minutes;
		countsPerLevel = (double)minutes * 60 * 60;
		decreaseVal = 360.0 / countsPerLevel;
		delay = minutes * 60 * 1000; // milliseconds
		stupidTimer = 0;
		started = true;
		this.day = day;
		//timer.start();
	}

	public void stopClock() {
		//timer.stop();
		started = false;
	}

	public void drawClockFace() {

		face = new GreenfootImage(100, 100);
		face.setColor(Color.BLACK);
		face.drawOval(0, 0, 100, 100);
		face.fillOval(0, 0, 100, 100);

		face.setColor(Color.WHITE);

		face.fillOval(2, 2, 96, 96);

		updateFace();

		this.setImage(face);
	}

	/**
	 * Act - do whatever the Timer wants to do. This method is called whenever
	 * the 'Act' or 'Run' button gets pressed in the environment.
	 */
	public void act() {
		if (started) {
			stupidTimer++;
			decreaseClock();
			updateLabel();
		}

        /*if (stupidTimer > countsPerLevel) {
			Greenfoot.stop();
        }*/

	}

	public void updateFace() {

		// turning 2 days per level

		if (degrees > 180.0) {
			day++;
			updateLabel();
		}

		if (degrees > 230.0) {
			face.setColor(Color.ORANGE);
			face.fillOval(2, 2, 96, 96);
		}

		if (degrees > 300.0) {
			face.setColor(Color.RED);
			face.fillOval(2, 2, 96, 96);
		}

		face.setColor(Color.LIGHT_GRAY);
		face.fillShape(new Arc2D.Double(2.0, 2.0, 96.0, 96.0, 90.0, -degrees, Arc2D.PIE));

		drawClockMarks();
	}

	private void drawClockMarks() {
		face.setColor(Color.DARK_GRAY);
		//Draw top lines
		face.drawLine(50, 0, 50, 10);
		face.drawLine(49, 0, 49, 10);
		face.drawLine(51, 0, 51, 10);

		//Draw bottom lines
		face.drawLine(50, 90, 50, 100);
		face.drawLine(49, 90, 49, 100);
		face.drawLine(51, 90, 51, 100);

		//Draw right lines
		face.drawLine(90, 50, 100, 50);
		face.drawLine(90, 49, 100, 49);
		face.drawLine(90, 51, 100, 51);

		//Draw left lines
		face.drawLine(0, 50, 10, 50);
		face.drawLine(0, 49, 10, 49);
		face.drawLine(0, 51, 10, 51);

		face.setColor(Color.DARK_GRAY);
		for (double i = 0; i < 360; i += 30) {
			double x1 = 50 + 50.0 * Math.cos((i * Math.PI) / 180.0);
			double y1 = 50 + 50.0 * Math.sin((i * Math.PI) / 180.0);

			double x2 = 50 + 45.0 * Math.cos((i * Math.PI) / 180.0);
			double y2 = 50 + 45.0 * Math.sin((i * Math.PI) / 180.0);

			face.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		}
		Font font = face.getFont();
		font = font.deriveFont(Font.PLAIN, 16);
		face.setFont(font);
		face.setColor(Color.black);
	}

	// update label
	public void updateLabel() {
		face.drawString("Day " + day, 30, 55);
	}

	public void decreaseClock() {
		degrees += decreaseVal;
		updateFace();
	}
}

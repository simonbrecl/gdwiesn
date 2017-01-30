import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import greenfoot.World;

import java.awt.Color;

/**
 * Created by ericasolum on 1/30/17.
 */
public class UpgradesInfoWindow extends Actor{

	GreenfootImage image = new GreenfootImage("infoScreen.png");
	UpgradeScreen world;

	public UpgradesInfoWindow()
	{
		this.setImage(image);
	}

	public void act() {
		MouseInfo mouse = Greenfoot.getMouseInfo();
		if (Greenfoot.mouseClicked(this))
		{
			if (mouse.getX() >= 300 && mouse.getX() <= 500 &&
					mouse.getY() >= 200 && mouse.getY() <= 500)
			{
				world.removeObject(this);
				world.infoShowing = false;

			}
		}
	}

	public void addedToWorld(World world) {
		this.world = (UpgradeScreen)getWorld();
	}


}


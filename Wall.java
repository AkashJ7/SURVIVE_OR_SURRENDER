import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

/**
 * Wall class to create stone Walls
 *
 * @author Akash Jagdeesh
 * @author Arjun Jagdeesh
 */

public class Wall extends GameObject {

	/** Wall sprite */
	ImageIcon block;
	/** Height of Wall (in blocks) */
	double height;

	/**
	 * Instantiates a wall GameObject,
	 * and assigns a tag as well as a sprite
	 *
	 * @param x The x coordinate of the collider box
	 * @param y The y coordinate of the collider box
	 * @param h The number of blocks in the wall (how tall the wall is)
	 * @param tag The tag associated with the Wall (see GameObject)
	 * @see GameObject
	 */

	public Wall(double x, double y, double h, int tag) {
	 	super(x, y, 20, 20 * h, tag);
		height = h;
		block = new ImageIcon("Sprites/Block.jpg");
	}

	/**
	 * Draws the sprites
	 *
	 * @param screen The graphics panel to draw the sprites on
	 */

	public void animate(Graphics screen) {
	    for (int i = 0; i < height; i++) {
	    	screen.drawImage(block.getImage(), (int) this.box.x, (int) this.box.y + i * 20, 20, 20, null);
	    }
	}
}

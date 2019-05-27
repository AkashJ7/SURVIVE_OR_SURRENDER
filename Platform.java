import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

/**
 * Platform class to create stone Platforms
 *
 * @author Akash Jagdeesh
 * @author Arjun Jagdeesh
 */

public class Platform extends GameObject {

	/** The Platform sprite */
	ImageIcon block;
	/** The length (in blocks) of the platform */
	double length;

	/**
	 * Instantiates a platform GameObject,
	 * and assigns a tag as well as a sprite
	 *
	 * @param x The x coordinate of the collider box
	 * @param y The y coordinate of the collider box
	 * @param l The number of blocks in the platform (how long the platform is)
	 * @param tag The tag associated with the Platform (see GameObject)
	 * @see GameObject
	 */

	public Platform(double x, double y, double l, int tag) {
		super(x, y, 20 * l, 20, tag);
		length = l;
		block = new ImageIcon("Sprites/Block.jpg");
	}

	/**
	 * Draws the sprites
	 *
	 * @param screen The graphics panel to draw the sprites on
	 */

	public void animate(Graphics screen) {
		for (int i = 0; i < length; i++) {
			screen.drawImage(block.getImage(), (int) this.box.x + i * 20, (int) this.box.y, 20, 20, null);
		}
	}
}

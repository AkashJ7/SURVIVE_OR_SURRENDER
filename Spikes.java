import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

/**
 * Spikes class to create Spikes obstacles
 *
 * @author Akash Jagdeesh
 * @author Arjun Jagdeesh
 */

public class Spikes extends GameObject {

	/** Spikes sprite */
	ImageIcon spike;

	/**
	 * Instantiates a GameObject for a set of Spikes,
	 * and assigns a tag as well as a sprite
	 *
	 * @param x The x coordinate of the collider box
	 * @param y The y coordinate of the collider box
	 * @param w The number of spikes in the set of spikes
	 * @param tag The tag associated with the Spikes (see GameObject)
	 * @see GameObject
	 */

	public Spikes(double x, double y, double w, int tag) {
		super(x, y, w * 22.0, 20.0, tag);
		spike = new ImageIcon("Sprites/SPIKE.png");
	}

	/**
	 * Draws the sprites
	 *
	 * @param screen The graphics panel to draw the sprites on
	 */

	public void animate(Graphics screen) {
		for (int i = 0; i < this.box.width / 27; i++) {
			screen.drawImage(spike.getImage(), (int) (this.box.x + i * 27 - 4), (int) this.box.y - 10, null);
			//screen.drawRect((int) this.box.x, (int) this.box.y, (int) this.box.width, (int) this.box.height);
            // Collider box
		}
	}
}

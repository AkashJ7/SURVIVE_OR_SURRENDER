import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")

/**
 * Crusher class to create Crusher obstacles
 *
 * @author Akash Jagdeesh
 * @author Arjun Jagdeesh
 */

public class Crusher extends GameObject{

	/** Movement speed of the Crusher */
	double speed = 20;
	/** Crusher sprite */
	ImageIcon crusher;

	/**
	 * Instantiates a crusher GameObject,
	 * and assigns a tag as well as a sprite
	 *
	 * @param x The x coordinate of the collider box
	 * @param y The y coordinate of the collider box
	 * @param tag The tag associated with the Crusher (see GameObject)
	 * @see GameObject
	 */

	public Crusher(double x, double y, int tag) {
		super(x, y, 80, 30, tag);
		crusher = new ImageIcon("Sprites/CRUSHER.png");
	}

	/**
	 * Moves the crusher and draws the sprite
	 *
	 * @param screen The graphics panel to draw the sprite on
	 */

	public void animate(Graphics screen) {
		if (this.box.y > 390) speed = -2;
		if (this.box.y + this.box.height * 5 < 150) speed = 8;
		this.box.y += speed;

		screen.drawImage(crusher.getImage(), (int) (this.box.x - 5), (int) (this.box.y - (this.box.height + 10) * 4), (int) (this.box.width + 10), (int) (this.box.height + 10) * 5, null);
		//screen.drawRect((int) this.box.x, (int) this.box.y, (int) this.box.width, (int) this.box.height);
	      // Collider Box
   }
}

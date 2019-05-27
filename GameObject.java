import java.awt.geom.Rectangle2D;
import java.awt.*;

@SuppressWarnings("serial")

/**
 * A parent class that creates a box collider for all the Objects of our game inherit a box collider from
 *
 * @author Akash Jagdeesh
 * @author Arjun Jagdeesh
 */

public class GameObject {

	/** Rectangle for the box collider */
	Rectangle2D.Double box;
	/** Tag of GameObject (see parameter description in constructor) */
	int tag;

   /**
    * Creates a box collider, and assigns a tag
    * (used in GameManagement to decide when to put specific GameObject in level)
    * to the GameObject
    *
    * @param x The x coordinate of the collider box
    * @param y The y coordinate of the collider box
    * @param w The width of the collider box
    * @param h The height of the collider box
	* @param tag Used in GameManagement to decide when to put specific GameObject in level
    */

	public GameObject(double x, double y, double w, double h, int tag) {
		this.box = new Rectangle2D.Double(x, y, w, h);
		this.tag = tag;
	}

   /**
    * A method that all subclasses overwrite, to draw their sprites
    *
    * @param screen The graphics panel to draw the sprites on
    */

	public void animate(Graphics screen) {};
}

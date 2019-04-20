import java.awt.geom.Rectangle2D;
import java.awt.*;

@SuppressWarnings("serial")

public class GameObject {

	Rectangle2D.Double box;

	public GameObject(double x, double y, double w, double h) {
		this.box = new Rectangle2D.Double(x, y, w, h);
	}

	public void animate(Graphics screen) {};
}

import java.awt.geom.Rectangle2D;
import java.awt.*;

@SuppressWarnings("serial")

public class GameObject {

	Rectangle2D.Double box;
	int tag;

	public GameObject(double x, double y, double w, double h, int tag) {
		this.box = new Rectangle2D.Double(x, y, w, h);
		this.tag = tag;
	}

	public void animate(Graphics screen) {};
}

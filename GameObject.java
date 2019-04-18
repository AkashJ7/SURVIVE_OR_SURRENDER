import java.awt.geom.Rectangle2D;
import java.awt.*;

@SuppressWarnings("serial")

public class GameObject extends Rectangle2D.Double {

	double x, y, w, h;

	public GameObject(double x, double y, double w, double h) {
		super(x, y, w, h);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public void animate(boolean update, Graphics g) {};
}

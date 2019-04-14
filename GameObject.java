import java.awt.geom.Rectangle2D;

public class GameObject extends Rectangle2D.Double {

	double x, y, w, h;

	public GameObject(double x, double y, double w, double h) {
		super(x, y, w, h);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
}

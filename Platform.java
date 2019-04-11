public class Platform extends GameObject {

	public Platform(double x, double y, double w, double h, String type) {
		super(x, y, w, h);
		this.type = type; // HORIZONTAL OR VERTICAL (WALL)
	}
}

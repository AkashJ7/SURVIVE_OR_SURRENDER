public class Platform extends GameObject {

	public Platform(double x, double y, double l, String type) {
		super(x, y, l);
		this.type = type; // HORIZONTAL OR VERTICAL (WALL)
	}
}

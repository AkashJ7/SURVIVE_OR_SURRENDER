public class Platform extends GameObject {

	public Platform(double x, double y, double l, String type) {
		super(x, y);
		this.length = l;
		this.type = type; // HORIZONTAL OR VERTICAL (WALL)
	}
	if (type == "wall") {
		//code to make the length grow sideways
	} else if (type == "platform") {
		//code to make the length show down-up
	}
}

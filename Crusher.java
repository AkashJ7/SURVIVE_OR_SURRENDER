public class Crusher extends GameObject {
	public Crusher(double x, double y) {
		super(x, y, w, h); // w/h have to be decided on based off sprite
		// We can use my sprite from my game for this (resized)
	}
	public void smash() {
		//move the crusher down, so you would call smasher1.smash() instead
		//of smasher1.move("down")
	}
	public void recover() {
		//lifting up at slower rate
	}
}

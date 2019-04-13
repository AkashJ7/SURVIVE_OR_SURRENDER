//import javax.swing.Timer;

public class Crusher {//extends GameObject {// implements ActionListener {
	//private Timer timer1;
	private double moveHeight;
	private double speed;
	private double xx;
	private double yy;
	private int a = 0;

	public Crusher(double x, double y) {
		//super(x, y, 10.0, 10.0); // w/h have to be decided on based off sprite
		// We can use my sprite from my game for this (resized)
		xx = x;
		yy = y;

		moveHeight = 600-(yy + 10);
		//timer1 = new Timer(10, animate);
		//timer1.start();
	}
	public void animate() {
		while (a < 100) {
			while (yy > 600) {
				speed = -0.1;
			}
			while (yy < 50) {
				speed = 0.2;
			}
			yy += speed;
		}
		a++;
	}


}

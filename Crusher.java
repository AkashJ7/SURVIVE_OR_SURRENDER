import javax.swing.Timer;
import java.awt.event.*;

public class Crusher extends GameObject implements ActionListener {
	private Timer timer1;
	private double moveHeight;
	private double speed;
	private double xx;
	private double yy;

	public Crusher(double x, double y) {
		//super(x, y, 10.0, 10.0); // w/h have to be decided on based off sprite
		// We can use my sprite from my game for this (resized)
		xx = x;
		yy = y;
		moveHeight = 600-(yy + 10);

		ImageIcon crusher = new ImageIcon("Sprites/Metal Crusher.jpg");
	}

	public void animate(Graphics g) {
		while (yy > 600) {
				speed = -0.1;
		}
		while (yy < 50) {
			speed = 0.2;
		}
		yy += speed;
		g.drawImage(crusher.getImage(), xx, yy, null);
	}
}

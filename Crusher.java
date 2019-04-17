import javax.swing.Timer;
import java.awt.event.*;

public class Crusher extends GameObject implements ActionListener {
	private double moveHeight;
	private double speed;
	private double xx;
	private double yy;

	public Crusher(double x, double y) {
		super(x, y, 20.0, 50.0); // w/h have to be decided on based off sprite
		// We can use my sprite from my game for this (resized)
		xx = x;
		yy = y;
		moveHeight = 600-(yy + 50);

		ImageIcon crusher = new ImageIcon("Sprites/Metal Crusher.png");
	}

	public void animate(Graphics g) {
		if (yy > 600) {
				speed = -0.1;
		}
		if (yy < 50) {
			speed = 2;
		}
		yy += speed;
		g.drawImage(crusher.getImage(), xx, yy, null);
	}
}

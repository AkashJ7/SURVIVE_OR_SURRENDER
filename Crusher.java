import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

@SuppressWarnings("serial")

public class Crusher extends GameObject{
	private double moveHeight;
	private double speed = 5;
	private ImageIcon crusher;

	public Crusher(double x, double y) {
		super(x, y, 20 * 5, 49 * 5); // w/h have to be decided on based off sprite
		// We can use my sprite from my game for this (resized)

		crusher = new ImageIcon("Sprites/output-onlinepngtools.png");
	}

	@Override
	public void animate(Graphics screen) {
		if (this.y + 250 > 600) speed = -1;
		if (this.y + 250 < 50) speed = 5;
		this.y += speed;
		screen.drawImage(crusher.getImage(), (int) this.x, (int) this.y, (int) this.w, (int) this.h, null);
		screen.drawRect((int) this.x, (int) this.y, (int) this.w, (int) this.h);
	}
}

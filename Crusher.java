import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")

public class Crusher extends GameObject{
	private double moveHeight;
	private double speed = 20;
	private ImageIcon crusher;

	public Crusher(double x, double y) {
		super(x, y+200, 20 * 5, 50); // w/h have to be decided on based off sprite
		// We can use my sprite from my game for this (resized)

		crusher = new ImageIcon("Sprites/CRUSHER.png");
	}

	@Override
	public void animate(Graphics screen) {
		if (this.box.y > 420) speed = -5;
		if (this.box.y + 250 < 50) speed = 20;
		this.box.y += speed;

		screen.drawImage(crusher.getImage(), (int) this.box.x, (int) this.box.y-200, (int) this.box.width, (int) this.box.height*5, null);
		screen.drawRect((int) this.box.x, (int) this.box.y, (int) this.box.width, (int) this.box.height);
	}
}

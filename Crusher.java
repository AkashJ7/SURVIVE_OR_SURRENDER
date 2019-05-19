import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")

public class Crusher extends GameObject{
	double moveHeight;
	double speed = 20;
	ImageIcon crusher;

	public Crusher(double x, double y, int tag) {
		super(x, y, 80, 30, tag); // w/h have to be decided on based off sprite
		// We can use my sprite from my game for this (resized)

		crusher = new ImageIcon("Sprites/CRUSHER.png");
	}

	public void animate(Graphics screen) {
		if (this.box.y > 300) speed = -2;
		if (this.box.y + this.box.height * 5 < 450) speed = 8;
		this.box.y += speed;

		screen.drawImage(crusher.getImage(), (int) (this.box.x - 5), (int) (this.box.y - (this.box.height + 10) * 4), (int) (this.box.width + 10), (int) (this.box.height + 10) * 5, null);
		screen.drawRect((int) this.box.x, (int) this.box.y, (int) this.box.width, (int) this.box.height);
	}
}

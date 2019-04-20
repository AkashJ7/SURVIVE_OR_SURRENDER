import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class Spikes extends GameObject {

	private ImageIcon spike;

	public Spikes(double x, double y, double w) {
		super(x, y, w * 27.0, 30.0);
		spike = new ImageIcon("Sprites/SPIKE.png");
	}

	@Override
	public void animate(Graphics g) {
		for (int i = 0; i < this.w / 27; i++) {
			g.drawImage(spike.getImage(), (int) (this.x + i * 27), (int) this.y, null);
			g.drawRect((int)(this.x + i * 27.0), (int)this.y, 27, 30);
		}
	}
}

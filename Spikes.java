import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class Spikes extends GameObject {

	ImageIcon spike;

	public Spikes(double x, double y, double w) {
		super(x, y, w * 27.0, 30.0);
		spike = new ImageIcon("Sprites/SPIKE.png");
	}

	@Override
	public void animate(Graphics screen) {
		for (int i = 0; i < this.box.width / 27; i++) {
			screen.drawImage(spike.getImage(), (int) (this.box.x + i * 27), (int) this.box.y, null);
		}
	}
}

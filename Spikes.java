import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class Spikes extends GameObject {

	ImageIcon spike;

	public Spikes(double x, double y, double w, int tag) {
		super(x, y, w * 22.0, 20.0, tag);
		spike = new ImageIcon("Sprites/SPIKE.png");
	}

	public void animate(Graphics screen) {
		for (int i = 0; i < this.box.width / 27; i++) {
			screen.drawImage(spike.getImage(), (int) (this.box.x + i * 27 - 4), (int) this.box.y - 10, null);
			screen.drawRect((int) this.box.x, (int) this.box.y, (int) this.box.width, (int) this.box.height);
		}
	}
}

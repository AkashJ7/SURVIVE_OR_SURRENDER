import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class Platform extends GameObject {

	ImageIcon block;
	double length;

	public Platform(double x, double y, double l) {
		super(x, y, 20 * l, 20);
		length = l;
		block = new ImageIcon("Sprites/Block.jpg");
	}

	@Override
	public void animate(Graphics screen) {
		for (int i = 0; i < length; i++) {
			screen.drawImage(block.getImage(), (int) this.box.x + i * 20, (int) this.box.y, 20, 20, null);
		}
		screen.drawRect((int) this.box.x, (int) this.box.y, (int) (20*length), 20);
	}
}

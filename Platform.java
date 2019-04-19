import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class Platform extends GameObject {

	private ImageIcon block;

	public Platform(double x, double y, double l, String type) {
	 	// HORIZONTAL OR VERTICAL (WALL)
		if (type == "wall") {
			super(x, y, 5.0, 5.0 * l);
		} else if (type == "platform") {
			super(x, y, 5.0 * l, 5.0);
		}

		block = new ImageIcon("Sprites/block.png");
	}
	@Override
	public void animate(boolean update, Graphics g) {
		if (this.type = "wall") {
			for (int i = 0; i < this.l / 5.0; i++) {
				g.drawImage(block.getImage(), (int) this.x, (int) this.y + i * 5);
			}
		}
		else if (this.type = "platform") {
			for (int i = 0; i < this.l / 5.0; i++) {
				g.drawImage(block.getImage(), (int) this.x + i * 5, (int) this.y);
			}
		}
	}

}

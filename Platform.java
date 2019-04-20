import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class Platform extends GameObject {

	private ImageIcon block;
	private double length;
	private String type1;

	public Platform(double x, double y, double l, String type) {
	 	// HORIZONTAL OR VERTICAL (WALL)
		//if (type == "wall") {
			super(x, y, 20*l, 20);
		//} else if (type == "platform") {
		//	super(x, y, 5.0 * l, 5.0);
		//}
		length = l;
		type1 = type;
		block = new ImageIcon("Sprites/Block.JPG");
	}
	@Override
	public void animate(Graphics g) {
		if (type1.equals("wall")) {
			for (int i = 0; i < length / 5.0; i++) {
				g.drawImage(block.getImage(), (int) this.x, (int) this.y + i * 5, 20, 20, null);
				g.drawRect((int) this.x, (int) this.y + i * 20, 20, 20);
			}
		}
		else if (type1.equals("platform")) {
			for (int i = 0; i < length; i++) {
				g.drawImage(block.getImage(), (int) this.x + i * 20, (int) this.y, 20, 20, null);
				g.drawRect((int) this.x + i * 20, (int) this.y, 20, 20);
			}
		}
	}

}

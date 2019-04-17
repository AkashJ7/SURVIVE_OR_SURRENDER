import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Platform extends GameObject {

	public Platform(double x, double y, double l, String type) {
	 	// HORIZONTAL OR VERTICAL (WALL)
		if (type == "wall") {
			super(x, y, 5.0, 5.0*l);
		} else if (type == "platform") {
			super(x, y, 5.0*l, 5.0);
		}

		ImageIcon block = new ImageIcon("Sprites/block.png")
	}
	@Override
	public void animate(Graphics g) {
		if (this.type = "wall") {
			for (int i = 0; i < this.l, i++) {
				g.drawImage(block.getImage(), this.x, this.y+i*5.0);
			}
		}
		else if (this.type = "wall") {
			for (int i = 0; i < this.l, i++) {
				g.drawImage(block.getImage(), this.x+i*5.0, this.y);
			}
		}
	}

}

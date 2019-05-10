import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class Wall extends GameObject {

	ImageIcon block;
	double height;

	public Wall(double x, double y, double h) {
	 	super(x, y, 20, 20 * h);
		height = h;
		block = new ImageIcon("Sprites/Block.jpg");
	}

	public void animate(Graphics screen) {
	    for (int i = 0; i < height; i++) {
	    	screen.drawImage(block.getImage(), (int) this.box.x, (int) this.box.y + i * 20, 20, 20, null);
	    }
		screen.drawRect((int) this.box.x, (int) this.box.y, 20, (int) (20*height));
	}
}

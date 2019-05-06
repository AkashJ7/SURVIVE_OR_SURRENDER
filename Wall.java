import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class Wall extends GameObject {

	private ImageIcon block;
	private double height;
	public Wall(double x, double y, double l) {
	 	super(x, y, 20, 20*l);
		height = l;
		block = new ImageIcon("Sprites/Block.JPG");
	}
	@Override
	public void animate(Graphics screen) {
    for (int i = 0; i < height; i++) {
      screen.drawImage(block.getImage(), (int) this.box.x, (int) this.box.y + i * 20, 20, 20, null);
    }
    screen.drawRect((int) this.box.x, (int) this.box.y, 20, (int) (20*height));
	}

}

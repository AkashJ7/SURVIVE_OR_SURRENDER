import java.awt.Rectangle;
import java.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class Spikes extends GameObject {

	public Spikes(double x, double y, double w) {
		super(x, y, w*10.0, 10.0/*DO CONSTRUCTOR*/);
		Imageicon spike = new ImageIcon("Sprites/Ground Spikes.png");
	}


	public void animate(Graphics g) {
		for (int i = 0; i < this.w; i++) {
			g.drawImage(spike.getImage(), this.x+i*10.0, this.y, null);
		}
	}


}

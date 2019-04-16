import java.awt.Rectangle;
import java.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


public class Spikes extends GameObject {

	public Spikes(int x, int y, int w) {
		super(x, y, w);
		Imageicon spike = new ImageIcon("Sprites/Ground Spikes.jpg");
	}


	public void animate(Graphics g) {
		for (int i = 0; i < this.w; i++) {
			g.drawImage(spike.getImage(), this.x+i*this.w, this.y, null);
		}
	}


}

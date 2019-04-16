import javax.swing.Timer;
import java.awt.event.*;

public class Crusher implements ActionListener {//extends GameObject {
	private Timer timer1;
	//private double moveHeight;
	private double speed;
	private double xx;
	private double yy;

	public Crusher(double x, double y) {
		//super(x, y, 10.0, 10.0); // w/h have to be decided on based off sprite
		// We can use my sprite from my game for this (resized)
		xx = x;
		yy = y;
		timer1 = new Timer(1000, new Listener());
		timer1.start();
	}
		//moveHeight = 600-(yy + 10);

		private class Listener implements ActionListener {
			public void actionPerformed(ActionEvent e) 
				while (yy > 600) {
						speed = -0.1;
				}
				while (yy < 50) {
					speed = 0.2;
				}
				yy += speed;
				System.out.println(""+yy);
			}
		}


	/*public void animate() {
		while (yy > 600) {
				speed = -0.1;
		}
		while (yy < 50) {
			speed = 0.2;
		}
		yy += speed;
	}*/



	public static void main (String args[]) {
		Crusher a = new Crusher(10.0, 10.0);
		System.out.println(""+a.yy);
		System.out.println(""+a.timer1.isRunning());


	}
}

import java.awt.*; // Image
import java.awt.event.*; // event.KeyAdapter
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")

public class Player extends GameObject {

	int tries = 0;
	boolean alive;
	double speed = 3;
	boolean jump = false;
	double jumping_time = 0;
	double jump_height;
	boolean falling = false;
	static boolean RIGHT, LEFT, UP;

	public Player() {
		super(100, 500, 20, 50);
		alive = true;
		// Draw sprite 15 left and 5 up of rect
	}

	public void move (Graphics g) {
		if (jump_height > 0) { // If touched ground again
			jump = false; // Not jumping anymore
			jumping_time = 0; // Reset parabola
		}
		if (UP && falling == false) {
			jump = true;
		}
		if (jump) {
			jumping_time += 2;
			jump_height = -(-0.03*jumping_time*jumping_time + 3.5*jumping_time);
			// jump math prob needs tweaking based on our sizes/game
		}
		if (RIGHT) {
			this.x += speed;
			//g.draw(this);
			//System.out.println(this.x);
			// Sprite animation
		} else if (LEFT) {
			this.x -= speed;
			// Moving left, with opposite sprite animation
		}

		g.setColor(Color.RED);
		g.drawRect((int) this.x, (int) (this.y + jump_height), (int) this.w, (int) this.h);
	}

	public void constrain() {
		//
	}

	public void displayTries(Graphics g) {
		//
	}

	public void checkForFailure(ArrayList<GameObject> obstacles) {
		for(GameObject danger : obstacles) {
			if (danger.intersects(this.x, this.y + jump_height, this.w, this.h)) { alive = false; }
		}
	}

	static class KeyInput extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) RIGHT = true;
			else if (e.getKeyCode() == KeyEvent.VK_LEFT) LEFT = true;
			if (e.getKeyCode() == KeyEvent.VK_UP) UP = true;
		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) RIGHT = false;
			else if (e.getKeyCode() == KeyEvent.VK_LEFT) LEFT = false;
			if (e.getKeyCode() == KeyEvent.VK_UP) UP = false;
		}
	}
}

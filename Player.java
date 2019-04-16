import java.awt.*; // Image
import java.awt.event.*; // event.KeyAdapter
import java.util.*;
import javax.swing.*;

public class Player extends GameObject implements KeyListener {

	int tries = 0;
	boolean alive;
	double speed = 3;
	boolean jump = false;
	double jumping_time = 0;
	double jump_height;
	boolean falling = false;

	public Player() {
		super(100, 500, 20, 50);
		// Draw sprite 15 left and 5 up of rect
	}

	public void keyPressed(KeyEvent e) {
		if (jump_height > 0) { // If touched ground again
			jump = false; // Not jumping anymore
			jumping_time = 0; // Reset parabola
		}
		if (e.getKeyCode() == KeyEvent.VK_UP && falling == false) {
			jump = true;
		}
		if (jump) {
			jumping_time += 2;
			jump_height = -(-0.03*jumping_time*jumping_time + 3.5*jumping_time);
			// jump math prob needs tweaking based on our sizes/game
		}
		if (Scene.scene == "IN GAME" && e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.x += speed;
			// Sprite animation
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.x -= speed;
			// Moving left, with opposite sprite animation
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

	public void constrain() {
		//
	}

	public void displayTries(Graphics g) {
		//
	}

	public void checkForFailure(ArrayList<GameObject> obstacles) {
		for(GameObject danger : obstacles) {
			if (danger.intersects(this)) { alive = false; }
		}
	}
}

import java.awt.*; // Image, event.KeyEvent
import java.util.*;
import javax.swing.*;

public class Player extends GameObject {

	int tries = 0;
	boolean alive;

	public Player() {
		super(100, 50, 20, 50);
		this.alive = true;
	}

	public void move() {
		// If statements for keyboard go here
		// Draw sprite 15 left and 5 up of rect
	}

	public void constrain() {
		//
	}

	public void displayTries(Graphics g) {
		//
	}

	public void checkForFailure(ArrayList<GameObject> obstacles) {
		for(GameObject danger : obstacles) {
			if (danger.intersects(this)) { this.alive = false; }
		}
	}
}
